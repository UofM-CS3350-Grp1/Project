package persistence;
import objects.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

//----------------------------------------------------------------------------
//Class: DBController
//
//Def: Provides interface to DBMS.
//----------------------------------------------------------------------------

public class DBController 
{
	private final int SQL_DEBUGGING = 0; //1 for full SQL output, 0 to disable.
	private final int ERROR_LOG = 1; //1 for error log, 0 to disable.
	
	private String dbName;
	private String dbType;
	
	private Statement st1, st2, st3;
	private Connection conn;
	private ResultSet rs1, rs2, rs3, rsColumns;
	
	private String cmdString;
	private int updateIndex;

	//----------------------------------------------------------------------------
	//	Constructor, is passed specified database name.
	//----------------------------------------------------------------------------
	
	public DBController(String dbName)
	{
		this.dbName = dbName;
	}
	
	/**
	 * CONNECT()															</br></br>
	 * 
	 * NOTES:	Starts new session with DB.
	 * 
	 */
	
	public void connect()
	{
		String addr = "jdbc:hsqldb:file:database/" + dbName;
		try
		{
			dbType = "HSQL";
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			conn = DriverManager.getConnection(addr, "SA", "");
			st1 = conn.createStatement();
			st2 = conn.createStatement();
			st3 = conn.createStatement();
		}
		catch(Exception e)
		{
			errorOutput(e);
		}
		if(ERROR_LOG == 1)
			System.out.println("Opened " +dbType +" database " +dbName);
	
	}
	
	/**ISCLOSED()
	 * 
	 * Checks if dbms is closed.
	 */
	
	public boolean isClosed()
	{
		boolean output = false;
	
		try
		{
			output = conn.isClosed();
		}
		catch(Exception e)
		{
			errorOutput(e);
		}
		
		return output;
	}
	

	/**
	 * DISCONNECT()															</br></br>
	 * 
	 * NOTES:	Ends current session.
	 * 
	 */
	
	public void disconnect()
	{
		try
		{	// commit all changes to the database
			cmdString = "shutdown compact";
			st1.executeQuery(cmdString);
			conn.close();
		}
		catch (Exception e)
		{
			errorOutput(e);
		}
		if(ERROR_LOG == 1)
			System.out.println("Closed " +dbType +" database " +dbName);
		
	}
	

	/**
	 * INSERT()															</br></br>
	 * 
	 * NOTES:	Inserts a storable element into a table.
	 * 
	 * @param Table		-	Table to insert into 
	 * @param element	-	Client to insert							</br></br>
	 * 
	 */
	
	public int insert(String table, Storable element)
	{
		String modify = "";
		String warning = null;
		int newIndex = -1;
		ArrayList<String> objectIndexes;
		ArrayList<String> fields = fieldBuilder(table);
		boolean valid = true;
		int success = -1;
		
		//getActiveIndex("SERVICES");
		//getActiveIndex("CLIENTS");
		//getActiveIndex("CONTRACTS");
		
		if(table == null || table.isEmpty() || fields == null)
			valid = false;
		
		if(valid)
		{
			
			newIndex = getActiveIndex(table);
			//Confirm there is no difference in size between table and object
			if(fields.size() == element.toIndex().size() && newIndex != -1)
			{
				objectIndexes = element.toIndex();
				modify = "INSERT INTO " + table + "\nVALUES \n(" +newIndex+",\n";
				
				//Modify everything except for first index (ROW_ID)
				String protectedStr = null;
				for(int i = 1 ; i < fields.size()-1; i ++)
				{
					if(objectIndexes.get(i) != null)
					{
						protectedStr = objectIndexes.get( i );
						protectedStr = protectedStr.replaceAll( "'", "''" );
					}
					modify += "'" + protectedStr + "',\n";
				}
				
				protectedStr = objectIndexes.get( fields.size() -1 );
				protectedStr = protectedStr.replaceAll( "'", "''");
				modify += "'" + protectedStr + "')";
				
				if(SQL_DEBUGGING == 1)
					System.out.println(modify);
				
				try
				{
					cmdString = modify;
					updateIndex = st1.executeUpdate(cmdString);
					
					warning = checkWarning(st1, updateIndex);
					
					if(warning !=  null)
					{
						if(ERROR_LOG == 1)
							System.out.println(warning);
					}
					else
					{
						success = newIndex;
					}
				}
				catch(Exception e)
				{
					System.out.println("DBController exception encountered: " + e);
					errorOutput(e);
				}
				
			}
			else
			{
				if(ERROR_LOG == 1)
				{
					System.out.println("Size mismatch between DB fields and input object.");
					System.out.println("Fields: "+ fields.size() + " Elements " + element.toIndex().size());

				}
			}
		}
		
		return success;
	}

	
	/**
	 * UPDATE()															</br></br>
	 * 
	 * NOTES:	Updates a storable element on a table
	 * 
	 * @param table		-	Table to update *
	 * @param element	-	Storable to update							</br></br>
	 * 
	 */
	
	public boolean update(String table, Storable element)
	{
		String modify = "";
		String warning = null;
		ArrayList<String> objectIndexes;
		ArrayList<String> fields = fieldBuilder(table);
		boolean valid = false;
		boolean success = false;
		
		if(element != null)
			valid = modifyValidator(table, element.getID());
		
		if(fields == null)
			valid = false;
		
		if(valid)
		{
			//Confirm there is no difference in size between table and object
			if(fields.size() == element.toIndex().size())
			{
				objectIndexes = element.toIndex();
				modify = "UPDATE " + table + "\nSET \n";
				
				//Modify everything except for first index (ROW_ID)
				String protectedString = null;
				for(int i = 1 ; i < fields.size()-1; i ++)
				{
					protectedString = objectIndexes.get( i );
					protectedString = protectedString.replaceAll( "'", "''" );
					modify += fields.get(i) +" = '"+protectedString + "',\n";
				}
				
				protectedString = objectIndexes.get( fields.size() -1 );
				protectedString = protectedString.replaceAll( "'", "''" );
				modify += fields.get(fields.size()-1) + " = '" + protectedString + "'\n";
				
				modify += "WHERE\nROW_ID = " + element.getID();
				
				if(SQL_DEBUGGING == 1)
					System.out.println(modify);
				
				try
				{
					cmdString = modify;
					updateIndex = st1.executeUpdate(cmdString);
					
					warning = checkWarning(st1, updateIndex);
					
					if(warning !=  null)
					{
						if(ERROR_LOG == 1)
							System.out.println(warning);
					}
					else
					{
						success = true;
					}
				}
				catch(Exception e)
				{
					errorOutput(e);
				}
			}
			else
			{
				if(ERROR_LOG == 1)
				{
					System.out.println("Size mismatch between DB fields and input object.");
					System.out.println("Fields: "+ fields.size() + " Elements " + element.toIndex().size());

				}
			}
		}
		return success;
	}
	
	
	/**
	 * DROP()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop specified element from table.
	 * 
	 * @param table		- 	Table to drop from
	 * @param id		-	ID to drop 							</br></br>
	 * 
	 */
	public boolean drop(String table, int id)
	{
		String modify = "";
		String warning = null;
		boolean valid = modifyValidator(table, id);
		boolean success = false;
		
		if(valid)
		{
			modify = "DELETE FROM " + table + " WHERE ROW_ID = " +id;

			if(SQL_DEBUGGING == 1)
				System.out.println(modify);
			
			try
			{
				cmdString = modify;
				updateIndex = st1.executeUpdate(cmdString);
				
				warning = checkWarning(st1, updateIndex);
				
				if(warning !=  null)
				{
					if(ERROR_LOG == 1)
						System.out.println(warning);
				}
				else
				{
					success = true;
				}
			}
			catch(Exception e)
			{
				errorOutput(e);
			}
		}
		
		return success;
	}
	
	/**
	 * QUERYTRACKEDFEATURES()													</br></br>
	 * 
	 * NOTES:	Instantiates tracked features objects based on input clauses.
	 * 
	 * @param clauses	-	WHERE clauses.get(1)... etc.				</br></br>
	 * 
	 * @return	-	?													</br></br>
	 * 
	 */
	
	public ArrayList<ArrayList<String>> query(String tableTarget, ArrayList<ArrayList<String>> clauses)
	{
		int counter = 0;
		ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
		boolean validator = queryValidator(null, null, clauses);
		String query = "";
		ArrayList<String> fields = new ArrayList<String>();

		if(validator)
		{
			query = queryBuilder(tableTarget, null, null, clauses);	//Returns full objects so No Joins/All Values
			fields = fieldBuilder(tableTarget); //Retrieves columns from CONTRACTS table
			
			if(SQL_DEBUGGING == 1)
				System.out.println(query);
			
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				counter = rs3.getMetaData().getColumnCount();
				while(rs3.next())
				{
					ArrayList<String> row = new ArrayList<String>();
					//Appends services to output based on query results.
					for(int i = 0; i < counter; i++)
					{
						row.add(rs3.getString(fields.get(i)));
					}
					
					output.add(row);
				}
			}
			catch(Exception e)
			{
				errorOutput(e);
			}
		}
		else
		{
			output = null;
			if(ERROR_LOG == 1)
				System.out.println("Invaid Contract query.");
		}
		
		return output;
	}
	
	
	/**
	 * BLINDQUERY();
	 * 
	 *  Runs a generic SQL query against DBMS and retrns an ArrayList of Strings.
	 * 
	 * @param query
	 * @return
	 */
	
	public ArrayList<String> blindQuery(String query)
	{
		ArrayList<String> output = new ArrayList<String>();
		int counter = 0;
		
		try
		{
			cmdString = query;
			rs3 = st1.executeQuery(cmdString);
			counter = rs3.getMetaData().getColumnCount();
			
			while(rs3.next())
			{
				String row = new String();
				
				for(int i = 1; i <= counter; i++)
				{
					row += rs3.getString(i);
				}
				
				output.add(row);
			}
		}
		catch(Exception e)
		{
			errorOutput(e);
		}
		
		return output;
	}
	
	
	/**
	 * FIELDBUILDER()															</br></br>
	 * 
	 * NOTES:	Checks table metadata for all columns returns an ArrayList of
	 * 			strngs containing the column names.
	 * 
	 * @param 	table				-	String containing target table			</br></br>
	 * 
	 * @return 	ArrayList<String>	-	An ArrayList containing column names	</br></br>
	 * 
	 */
	
	private ArrayList<String> fieldBuilder(String table)
	{
		ArrayList<String> output = new ArrayList<String>();
		String columnName = "";
		
		if(table != null && !table.isEmpty())
		{
			try
			{
				ResultSet rsColumns = null;
				DatabaseMetaData meta = conn.getMetaData(); //Get metadata
				rsColumns = meta.getColumns(null, null, table, null); //Retrieve columns from table
		    
				while(rsColumns.next()) //Loop though columns and add to output
				{
					columnName = rsColumns.getString("COLUMN_NAME");
					output.add(new String(columnName));
				}
			}
			catch(Exception e)
			{
				errorOutput(e);
			}
		}
		else
		{
			output = null;
		}
		
		
		return output;
	}
	
	/**
	 * QUERYBUILDER()															</br></br>
	 * 
	 * NOTES:	Generates a dynamic query statement.
	 * 
	 * @param 	table				-	String containing target table			
	 * @param	selects				-	ArrayList containing select statements
	 * @param	join				- 	ArrayList of ArrayList<String> containing joins
	 * @param	clauses				- 	ArrayList of ArrayList<String> containing clauses</br></br>
	 * 
	 * @return 	String				-	Resulting query in a string				</br></br>
	 * 
	 */
	
	private String queryBuilder(
						String table,
						ArrayList<String> selects, 
						ArrayList<ArrayList<String>> joins, 
						ArrayList<ArrayList<String>> clauses)
	{
		String query = "SELECT \n";
		
		if(table != null || !table.isEmpty())
		{
			if(selects!= null) //Checks for selects, otherwise returns '*'
			{
				for(int i = 0; i < selects.size()-1; i++)
				{
					query = query + selects.get(i) + ",\n";
				}
			
				query = query + selects.get(selects.size()-1) + "\n";
			}
			else
			{
				query = query +"*\n";
			}
			
			query = query + "FROM " +table;
		
			if(joins != null) //Checks for joins, not currently implemented
			{
				//DO NOTHING ATM
			}
			
			if(clauses != null) //Checks for clauses
			{
				if(clauses.get(0).get(0).compareTo("ALL") != 0)
				{
					query = query + "\nWHERE\n";
					
					for(int i = 0; i < clauses.size()-1; i++) //Loops through clauses, spitting out clause arguments.
					{
						for(int j = 0; j < clauses.get(i).size() -1; j++) //Loops through clause parameters appending them to query.
						{
							query = query + clauses.get(i).get(j) + " ";
						}
						query = query + clauses.get(i).get(clauses.get(i).size()-1) + " AND";
					}
					query = query + clauses.get(clauses.size()-1).get(0) + " " + clauses.get(clauses.size()-1).get(1) + " " + clauses.get(clauses.size()-1).get(2);
				}
				else
				{
					query = query + "\nWHERE\n ROW_ID > -1";
				}
			}
		}
		else
		{
			query = null;
		}
		
		return query;
	}
	
	
	/**
	 * QUERYVALIDATOR()															</br></br>
	 * 
	 * NOTES:	Generates a dynamic query statement.
	 * 			
	 * @param	selects				-	ArrayList containing select statements
	 * @param	join				- 	ArrayList of ArrayList<String> containing joins
	 * @param	clauses				- 	ArrayList of ArrayList<String> containing clauses</br></br>
	 * 
	 * @return 	boolean				-	Truth result if query is usable			</br></br>
	 * 
	 */
	
	private boolean queryValidator(
							ArrayList<String> selects, 
							ArrayList<ArrayList<String>> joins,
							ArrayList<ArrayList<String>> clauses)
	{
		boolean validator = true;
		
		if((selects != null && (selects.size() < 1 || selects.contains(null))) || 
				(clauses != null && (clauses.size() < 1 || clauses.contains(null))) || 
				(joins != null && (joins.size() < 1 || joins.contains(null)))) //Checks for empty/invalid ArrayLists
		{ 
			validator = false; 
		}
		else
		{
			if(clauses != null) //Handle nested causes with nulls.
			{
				for(int i = 0;  i < clauses.size(); i++)
				{
					for(int j = 0; j < clauses.get(i).size(); j++)
					{
						if(clauses.get(i).get(j) == null || clauses.get(i).get(j).isEmpty())
						{
							validator = false;
						}
					}
				}
			}
			
			if(joins != null) //Handle nested joins with nulls.
			{
				for(int i = 0;  i < joins.size(); i++)
				{
					for(int j = 0; j < joins.get(i).size(); j++)
					{
						if(joins.get(i).get(j) == null || joins.get(i).get(j).isEmpty())
						{
							validator = false;
						}
					}
				}
			}
		}
		
		
		if(validator) //Invalidates if clauses arrays contain more the 3 arguments
		{
			if((clauses != null) && clauses.get(0).get(0).compareTo("ALL") != 0)
			{
				for(int i = 0; (clauses != null) && i < clauses.size()-1 && validator; i++)
				{
					if(clauses.get(i).size() != 3)
					{
						validator = false;
					}
				}
			}
		}
		
		if(selects == null && clauses == null && joins  == null)
			validator = false;
		
		return validator;
	}
	
	/**
	 * MODIFYVALIDATOR()
	 * 
	 * 		Checks if set ID exists on active table.
	 * 
	 * @param table		Table to check.
	 * @param id		Id to check for.
	 */
	
	private boolean modifyValidator(String table, int id)
	{
		boolean output = true;
		boolean validator = true;
		String query = "";
		ArrayList<String> selects = new ArrayList<String>();
		ArrayList<String> test = new ArrayList<String>();
		ArrayList<ArrayList<String>> clauses = new ArrayList<ArrayList<String>>();
		
		selects.add("COUNT(*) VAL");
		
		test.add("ROW_ID");
		test.add("=");
		test.add(""+id+"");
		
		clauses.add(test);
		
		validator = queryValidator(null, null, clauses);
		
		if(validator && table != null && !table.isEmpty())
		{
			query = queryBuilder(table, null, null, clauses);	//Returns full objects so No Joins/All Value
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				
				rs3.next();
				//System.out.println(rs3.getInt(1));
				if(rs3.getInt(1) < 1)
				{
					output = false; //Must contain at east one entry.
				}
			
			}
			catch(Exception e)
			{
				errorOutput(e);
			}
		}
		else
		{
			if(ERROR_LOG == 1)
				System.out.println("Invaid Modify Request.");
		}
		
		return output;
	}
	
	/**
	 * GETACTIVEINDEX()
	 * 
	 *		##This has no error checking, by no means should you be calling this.
	 *
	 * @param table		Table to find index on.
	 * @return
	 */
	
	private int getActiveIndex(String table)
	{
		int output = -1;
		String query = "SELECT MAX(ROW_ID) FROM "+ table;
		
		try
		{
			cmdString = query;
			rs3 = st1.executeQuery(cmdString);
			
			rs3.next();
			output = rs3.getInt(1) + 1;
			if(output <= 0)
			{
				output = -1;
			}
			//System.out.println(output);
		}
		catch(Exception e)
		{
			errorOutput(e);
		}
		
		return output;
	}
	
	/**
	 * ERROROUTPUT()															</br></br>
	 * 
	 * NOTES:	Spits out errors.
	 * 
	 */
	
	private void errorOutput(Exception e)
	{
		if(ERROR_LOG == 1)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**MODIFYWARNINGS()
	 * 
	 * @param st		Statement being executed.	
	 * @param index		Index indicating if modification took place.
	 * @return
	 */
	
	private String checkWarning(Statement st, int index)
	{
		String result = null;
		
		try
		{
			SQLWarning warning = st.getWarnings();
			if (warning != null)
			{
				result = warning.getMessage();
			}
		}
		catch (Exception e)
		{
			errorOutput(e);
		}
		if (index != 1)
		{
			result = "Failed to modify";
		}
		return result;
	}
	
	
}

package persistence;
import objects.*;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;

//----------------------------------------------------------------------------
//Class: DBController
//
//Def: Provides interface to DBMS.
//----------------------------------------------------------------------------

public class DBController 
{
	
	private String dbName;
	private String dbType;
	
	private Statement st1, st2, st3;
	private Connection conn;
	private ResultSet rs1, rs2, rs3, rsColumns;
	
	private String cmdString;

	//----------------------------------------------------------------------------
	//	Constructor, is passed specified database name.
	//----------------------------------------------------------------------------
	
	public DBController(String dbName)
	{
		this.dbName = dbName;
	}
	
	/**
	 * DISCONNECT()															</br></br>
	 * 
	 * NOTES:	Starts new session with DB.
	 * 
	 */
	
	public void connect()
	{
		String addr = "jdbc:hsqldb:database/" + dbName;
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
		
		System.out.println("Opened " +dbType +" database " +dbName);
		
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
		
		System.out.println("Closed " +dbType +" database " +dbName);
		
	}
	

	/**
	 * INSERT()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop insert element into table.
	 * 
	 * @param Table		-	Table to drop from
	 * @param element	-	Storable element							</br></br>
	 * 
	 */
	
	public void insert(String table, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
			
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	/**
	 * UPDATE()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will update specified element on table.
	 * 
	 * @param table		-	Table to update *
	 * @param element	-	Storable element							</br></br>
	 * 
	 */
	
	public void update(String table, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	
	/**
	 * DROP()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop specified element from table.
	 * 
	 * @param table		- 	Table to drop from
	 * @param element	-	Storable element							</br></br>
	 * 
	 */
	public void drop(String table, Storable element)
	{
		if(element instanceof Service)
		{
		
		}
		else if(element instanceof Client)
		{
			
		}
	/*	else if(element instanceof Contract)
		{
			
		} */
		else
		{
			System.out.println("Invalid Object: " + element.getClass().getName());
		}
		
	}
	
	/**
	 * QUERYSERVICES()													</br></br>
	 * 
	 * NOTES:	Instantiates service object based on input clauses.
	 * 
	 * @param clauses	-	ArrayList of ArrayList<String> containing clauses
	 * 
	 * @return	-	?													</br></br>
	 * 
	 */
	
	public ArrayList<Service> queryServices(ArrayList<ArrayList<String>> clauses)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		boolean validator = queryValidator(null, null, clauses);
		String query = "";
		ArrayList<String> fields = new ArrayList<String>();

		if(validator)
		{
			query = queryBuilder("SERVICES", null, null, clauses);	//Returns full objects so No Joins/All Values
			fields = fieldBuilder("SERVICES"); //Retrieves columns frm SERVICES table
			
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				
				while(rs3.next())
				{
					//Appends services to output based on query results.
					output.add(new Service(
								Integer.parseInt(rs3.getString(fields.get(0))),
								rs3.getString(fields.get(1)),
								rs3.getString(fields.get(2)),
								Float.parseFloat(rs3.getString(fields.get(3))),
								rs3.getString(fields.get(4))
								));
				}
			}
			catch(Exception e)
			{
				errorOutput(e);
			}
		}
		else
		{
			
		}
		
		return output;
	}
	
	/**
	 * QUERYCLIENT()													</br></br>
	 * 
	 * NOTES:	Instantiates contract object based on input clauses.
	 * 
	 * @param clauses	-	WHERE clauses.get(1)... etc.				</br></br>
	 * 
	 * @return	-	?													</br></br>
	 * 
	 */
	
	public ArrayList<Client> queryClient(ArrayList<String[]> clauses)
	{
		return null;
	}
	
	/**
	 * QUERYONTRACT()													</br></br>
	 * 
	 * NOTES:	Instantiates contract object based on input clauses.
	 * 
	 * @param clauses	-	WHERE clauses.get(1)... etc.				</br></br>
	 * 
	 * @return	-	?													</br></br>
	 * 
	 */
	
	/*public ArrayList<Contract> queryContract(ArrayList<String[]> clauses)
	{
		return null;
	}*/
	
	/**
	 * COMPLEXQUERY()													</br></br>
	 * 
	 * NOTES:	Instantiates contract object based on input clauses.
	 * 
	 * @param	selects							-	ArrayList containing select statements
	 * @param	join							- 	ArrayList of ArrayList<String> containing joins
	 * @param	clauses							- 	ArrayList of ArrayList<String> containing clauses</br></br>
	 * 
	 * @return	ArrayList<ArrayList<String>>	- 	ArrayList of ArrayList<String>s containing table results.	</br></br>
	 * 
	 */
	
	public ArrayList<String[]> complexQuery(
								boolean distinct,
								ArrayList<String> selects,
								ArrayList<ArrayList<String>> joins,  
								ArrayList<ArrayList<String>> clauses)
	{
		return null;
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
	
	ArrayList<String> fieldBuilder(String table)
	{
		ArrayList<String> output = new ArrayList<String>();
		String columnName = "";
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
	
	public String queryBuilder(
						String table,
						ArrayList<String> selects, 
						ArrayList<ArrayList<String>> joins, 
						ArrayList<ArrayList<String>> clauses)
	{
		String query = "SELECT \n";
		
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
		
		query = query + "\nWHERE\n";
		
		if(clauses != null) //Checks for clauses
		{
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			//IMORTANT: Currently only accepts 3 array elements, this may change as clauses become more complex.//
			//////////////////////////////////////////////////////////////////////////////////////////////////////
			
			for(int i = 0; i < clauses.size()-1; i++) //Loops through clauses, spitting out clause arguments.
			{
				query = query + clauses.get(i).get(0) + " " + clauses.get(i).get(1) + " " + clauses.get(i).get(2) + " AND";
			}
			query = query + clauses.get(clauses.size()-1).get(0) + " " + clauses.get(clauses.size()-1).get(1) + " " + clauses.get(clauses.size()-1).get(2);
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
	
	public boolean queryValidator(
							ArrayList<String> selects, 
							ArrayList<ArrayList<String>> joins,
							ArrayList<ArrayList<String>> clauses)
	{
		boolean validator = true;
		
		if((selects != null && selects.size() < 1) || (clauses != null && clauses.size() < 1) || (joins != null && clauses.size() < 1)) //Checks for empty ArrayLists
		{ 
			validator = false; 
		}
		
		//////////////////////////////////////////////////////////////////////
		//NOTE: THIS WILL LIKELY CHANGE AS CLAUSES BECOME MORE SOPHISTICATED//
		//////////////////////////////////////////////////////////////////////
		
		if(validator) //Invalidates if clauses arrays contain more the 3 arguments
		{
			for(int i = 0; i < clauses.size()-1 && validator; i++)
			{
				if(clauses.get(i).size() != 3)
				{
					validator = false;
				}
			}
		}
		
		return validator;
	}
	
	/**
	 * ERROROUTPUT()															</br></br>
	 * 
	 * NOTES:	Spits out errors.
	 * 
	 */
	
	public void errorOutput(Exception e)
	{
		
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	

	
	
	
}

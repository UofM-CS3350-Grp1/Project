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
	 * INSERTCLIENT()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop insert element into table.
	 * 
	 * @param Table		-	Table to insert into 
	 * @param element	-	Client to insert							</br></br>
	 * 
	 */
	
	public void insertClient(Client element)
	{

	}
	
	/**
	 * INSERTSERVICE()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop insert element into table.
	 * 
	 * @param Table		-	Table to insert into 
	 * @param element	-	Service to insert							</br></br>
	 * 
	 */
	
	public void insertService(Service element)
	{

	}
	
	/**
	 * INSERTCONTRACT()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will drop insert element into table.
	 * 
	 * @param Table		-	Table to insert into 
	 * @param element	-	Contrct to insert							</br></br>
	 * 
	 */
	
	public void insertContract(Contract element)
	{

	}
	
	/**
	 * UPDATECLIENT()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will update specified element on table.
	 * 
	 * @param table		-	Table to update *
	 * @param element	-	Client to update							</br></br>
	 * 
	 */
	
	public void updateClient(Client element)
	{
		
	}
	
	/**
	 * UPDATESERVICE()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will update specified element on table.
	 * 
	 * @param table		-	Table to update *
	 * @param element	-	Service to update							</br></br>
	 * 
	 */
	
	public void updateService(Service element)
	{
		
	}
	
	/**
	 * UPDATECONTRACT()															</br></br>
	 * 
	 * NOTES:	####VERY EARLY#### Will be re-implemented just throwing out ideas.
	 * 			Will update specified element on table.
	 * 
	 * @param table		-	Table to update *
	 * @param element	-	Contract to update							</br></br>
	 * 
	 */
	
	public void updateContract(Contract element)
	{
		
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
	public void drop(String table, int id)
	{

		
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
			fields = fieldBuilder("SERVICES"); //Retrieves columns from SERVICES table
			
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				
				while(rs3.next())
				{
					//Appends services to output based on query results.
					output.add(new Service(
								Integer.parseInt(rs3.getString(fields.get(0))), //ID
								rs3.getString(fields.get(1)),//Title
								rs3.getString(fields.get(2)),//Description
								Double.parseDouble(rs3.getString(fields.get(3))), //Rate
								rs3.getString(fields.get(4)) //Type
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
			System.out.println("Invaid Services query.");
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
	
	public ArrayList<Client> queryClients(ArrayList<ArrayList<String>> clauses)
	{
		ArrayList<Client> output = new ArrayList<Client>();
		boolean validator = queryValidator(null, null, clauses);
		String query = "";
		ArrayList<String> fields = new ArrayList<String>();

		if(validator)
		{
			query = queryBuilder("CLIENTS", null, null, clauses);	//Returns full objects so No Joins/All Values
			fields = fieldBuilder("CLIENTS"); //Retrieves columns from CLIENTS table
			
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				
				while(rs3.next())
				{
					//Appends services to output based on query results.
					output.add(new Client(
								Integer.parseInt(rs3.getString(fields.get(0))), //ID
								rs3.getString(fields.get(1)),//Name
								rs3.getString(fields.get(2)),//Phone Number
								rs3.getString(fields.get(3)),//Email
								rs3.getString(fields.get(4)),//Address
								rs3.getString(fields.get(5)),//Business Name
								Integer.parseInt(rs3.getString(fields.get(6)))//Status
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
			System.out.println("Invaid Clients query.");
		}
		
		return output;
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
	
	public ArrayList<Contract> queryContracts(ArrayList<ArrayList<String>> clauses)
	{
		ArrayList<Contract> output = new ArrayList<Contract>();
		boolean validator = queryValidator(null, null, clauses);
		String query = "";
		ArrayList<String> fields = new ArrayList<String>();

		if(validator)
		{
			query = queryBuilder("CONTRACTS", null, null, clauses);	//Returns full objects so No Joins/All Values
			fields = fieldBuilder("CONTRACTS"); //Retrieves columns from CONTRACTS table
			
			try
			{
				cmdString = query;
				rs3 = st1.executeQuery(cmdString);
				
				while(rs3.next())
				{
					//Appends services to output based on query results.
					output.add(new Contract(
								Integer.parseInt(rs3.getString(fields.get(0))), //ID
								rs3.getString(fields.get(1)), //Business Name
								rs3.getString(fields.get(2)), //Details
								Double.parseDouble(rs3.getString(fields.get(3))), //Value
								new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(rs3.getString(fields.get(4))) //End Date
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
			System.out.println("Invaid Contract query.");
		}
		
		return output;
	}
	
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

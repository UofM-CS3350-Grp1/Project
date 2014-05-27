package objects;

//----------------------------------------------------------------------------
//	Class: TestService
//
//	Def: Proxy service class.
//----------------------------------------------------------------------------

public class TestService extends Service
{
	
	private String title;			// Name of Service
	private String description;		// Description of service.
	private float rate;				// Rate at which this service is charged.
	private int id;					// Unique Row ID of service.
	
	//-------------
	//	CONSTRUCT
	//-------------
	

	//----------------------------------------------------------------------------
	//	DBMS Constructor, assumes ID is known
	//----------------------------------------------------------------------------
	
	public TestService(
					String title,
					String description,
					float rate,
					int id
					) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty() || id <= 0) // Reject objects with undefined names or invalid ID's
		{
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.id = id;
	}
	
	//----------------------------------------------------------------------------
	//	Business Logic constructor, used if a new service is created 
	//	within the program
	//----------------------------------------------------------------------------
	
	public TestService(
					String title,
					String description,
					float rate
					) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty()) // Reject objects with undefined names
		{
			throw new IllegalArgumentException();
		}
		
		this.title = title;
		this.description = description;
		this.rate = rate;
		this.id = 0;
	}
	
	//----------------------------------------------------------------------------
	//	Null constructor, currently creates a test service
	//----------------------------------------------------------------------------
	
	//TODO: Remove this and replace with a proper null constructor as needed.
	
	public TestService()
	{
		this.title = "A Service";
		this.description = "This is a test service";
		this.rate = 10.00f;
		this.id = 1;
	}
	
	//-------------
	//	GETTERS
	//-------------

	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public float getRate()
	{
		return rate;
	}
	
	//-------------
	//	SETTERS
	//-------------
	
	public void setTitle(String title) throws IllegalArgumentException
	{
		if(title == null || title.isEmpty()) // Reject changes that would remove the service name
		{
			throw new IllegalArgumentException();
		}
		
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setRate(float rate)
	{
		this.rate = rate;
	}
	
	//----------------------------------------------------------------------------
	//	COMMIT()
	//
	//	PARAMS: NONE
	//
	//	NOTES: 	Currently inactive, under normal conditions will perform either
	//			an INSERT or an UPDATE call to DBMS.
	//----------------------------------------------------------------------------
	
	public void commit()
	{
		System.out.println("Currently not comitting service changes to the database.");
	}
}

package objects;

//----------------------------------------------------------------------------
//	Class: Service
//
//	Def: Abstract base class for services.
//----------------------------------------------------------------------------

public abstract class Service implements Storable
{

	//-------------
	//	GETTERS
	//-------------
	
	public abstract String getTitle();
	public abstract String getDescription();
	public abstract float getRate();
	
	//-------------
	//	SETTERS
	//-------------
	
	public abstract void setTitle(String title);
	public abstract void setDescription(String description);
	public abstract void setRate(float rate);
	
	//	Push modified service to DBMS.
	public abstract void commit();
}

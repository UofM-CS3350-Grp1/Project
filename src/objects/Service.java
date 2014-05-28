package objects;

//----------------------------------------------------------------------------
//	Class: Service
//
//	Def: Abstract base class for services.
//  - Q: just wondering, why is it abstract?
//	- A: 
//       Mostly for convenience at this point, since our model is still being  
//		 defined any changes to this if it were an Interface would need to be 
//       added to all classes implementing it before testing. It was also my
//       assumption that the majority of services would have some unique
//       functionality so we would never be instantiating a top level Service 
//       object directly, if for implementation purposes you need to do so
//       by all means feel free to change this.
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

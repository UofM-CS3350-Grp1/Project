package objects;

import java.util.ArrayList;

/**
 * Interface for any object that can be saved in the database
 */
public interface Storable
{	
	public int getID();
	public ArrayList<String> toIndex();
}

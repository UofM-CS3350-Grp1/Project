package objects;

import java.util.ArrayList;

/**
 * Interface for any object that can be saved in the database
 */
public interface Storable
{	
	int getID();
	ArrayList<String> toIndex();
	String getTableName();
	boolean isInsertable();
}

package persistence;

import java.util.ArrayList;

import objects.Storable;

public abstract class AbstractDBInterface 
{
	public abstract void connect();
	public abstract void disconnect();
	public abstract boolean idExists(Storable storableTemplate);
	public abstract String toString();
	public abstract void errorMessage(String retrieve, String invalid, String instruction);
}

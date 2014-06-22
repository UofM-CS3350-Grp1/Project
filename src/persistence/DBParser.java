package persistence;

import objects.*;

import java.util.ArrayList;

public class DBParser 
{
	public DBParser(){}
	
	public ArrayList<Service> parseServices(ArrayList<ArrayList<String>> input)
	{
		ArrayList<Service> output = new ArrayList<Service>();
		Service item = null;
		
		for(int i = 0; i < input.size(); i++)
		{
			item = new Service(
					Integer.parseInt(input.get(i).get(0)),
					input.get(i).get(1),
					input.get(i).get(2),
					Double.parseDouble(input.get(i).get(3)),
					input.get(i).get(4)
					);
			
			output.add(item);
		}
		
		return output;
	}
	
	public static ArrayList<Service> parseClients(ArrayList<ArrayList<String>> input)
	{
		
		return null;
	}
	
	public static ArrayList<Service> parseContracts(ArrayList<ArrayList<String>> input)
	{
		
		return null;
	}
	
	public static ArrayList<Service> parseFeatureHistories(ArrayList<ArrayList<String>> input)
	{
		
		return null;
	}
	
	public static ArrayList<Service> parseFeatures(ArrayList<ArrayList<String>> input)
	{
		
		return null;
	}
}

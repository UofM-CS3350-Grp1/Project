package persistence;

import objects.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class DBParser 
{
	private DBInterface iFace;
	
	public DBParser(DBInterface iFace)
	{
		this.iFace = iFace;
	}
	
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
					input.get(i).get(4),
					Integer.parseInt(input.get(i).get(5)),
					Integer.parseInt(input.get(i).get(6))
					);
			
			output.add(item);
		}
		
		return output;
	}
	
	public ArrayList<Client> parseClients(ArrayList<ArrayList<String>> input)
	{
		ArrayList<Client> output = new ArrayList<Client>();
		Client item = null;
		
		for(int i = 0; i < input.size(); i++)
		{
			item = new Client(
					Integer.parseInt(input.get(i).get(0)),
					input.get(i).get(1),
					new PhoneNumber(input.get(i).get(2)),
					new Email(input.get(i).get(3)),
					input.get(i).get(4),
					input.get(i).get(5),
					Integer.parseInt(input.get(i).get(6))
					);
			
			output.add(item);
		}
		
		return output;
	}
	
	public ArrayList<Contract> parseContracts(ArrayList<ArrayList<String>> input)
	{
		
		ArrayList<Contract> output = new ArrayList<Contract>();
		Contract item = null;
		
		for(int i = 0; i < input.size(); i++)
		{
			//(int contractNumber, String businessName, String details, double value, Date period
			try
			{
			item = new Contract(
					Integer.parseInt(input.get(i).get(0)),
					input.get(i).get(1),
					input.get(i).get(2),
					Double.parseDouble(input.get(i).get(3)),
					new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(input.get(i).get(4))
					);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			output.add(item);
		}
		
		return output;
	}
	
	public ArrayList<FeatureHistory> parseFeatureHistories(ArrayList<ArrayList<String>> input)
	{
		ArrayList<FeatureHistory> output = new ArrayList<FeatureHistory>();
		FeatureHistory item = null;
		Date date = null;
		Trackable contents = null;
		
		for(int i = 0; i < input.size(); i++)
		{
			try
			{
				date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(input.get(i).get(4));
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			if(Integer.parseInt(input.get(i).get(2)) == -1)
			{
				contents = iFace.getServiceByID(Integer.parseInt(input.get(i).get(3)));
			}
			else
			{
				contents = iFace.getClientByID(Integer.parseInt(input.get(i).get(2)));
			}
			
			item = new FeatureHistory(
					iFace.getTrackedFeatureByID(Integer.parseInt(input.get(i).get(1))),
					contents,
					Double.parseDouble(input.get(i).get(6)),
					date,
					input.get(i).get(5),
					Integer.parseInt(input.get(i).get(0))
					);
			output.add(item);
		}
		
		return output;
	}
	
	public ArrayList<TrackedFeature> parseFeatures(ArrayList<ArrayList<String>> input)
	{
		ArrayList<TrackedFeature> output = new ArrayList<TrackedFeature>();
		TrackedFeature item = null;
		
		for(int i = 0; i < input.size(); i++)
		{
			
			item = new TrackedFeature(
					input.get(i).get(2),
					input.get(i).get(3),
					Integer.parseInt(input.get(i).get(0)),
					Integer.parseInt(input.get(i).get(1))
					);

			output.add(item);
		}
		
		return output;
	}
}

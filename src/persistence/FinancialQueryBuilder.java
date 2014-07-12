package persistence;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import objects.Client;
import objects.Contract;
import objects.MonthReport;
import objects.Service;
import objects.ServiceType;
import objects.TrackedFeatureType;

public class FinancialQueryBuilder extends RelationalQueryBuilder
{
	
	public FinancialQueryBuilder()
	{
		super();
	}
	
	/** GETLASTYEARRETURNS()
	 * 
	 * Returns up to the last 12 months worth of monthly reports.
	 */
	
	public ArrayList<MonthReport> getLastYearReturns(Client element)
	{
		ArrayList<MonthReport> tally = null;
		double serviceValue = 0;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		ArrayList<Contract> clientContracts = new ArrayList<Contract>();
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			//Find the oldest active contract to use for contract calendar
			clientContracts = this.getContractsByBusiness(element.getBusinessName());
			contractDate.setTime(clientContracts.get(0).getSignedDate());
			
			for(int i = 1; i <clientContracts.size(); i++)
			{
				if(contractDate.getTime().before(clientContracts.get(i).getSignedDate()))
					contractDate.setTime(clientContracts.get(i).getSignedDate());
			}
			
			
			for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM (RATE)" +
					"FROM " +
					"(SELECT DISTINCT SV.RATE "+
					"FROM "+
					"CLIENTS CL "+
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE < '"+sdf.format(toDate.getTime())+"')"+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID)"+
					"INNER JOIN SERVICES_TYPES ST ON(SV.SERVICE_TYPE_ID = ST.ROW_ID AND ST.SERVICE_TYPE != 'Web Design') "+
					"WHERE "+
					"CL.ROW_ID ="+element.getID()+")";
					
				returnVal = this.mainDB.blindQuery(sql);
					
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					serviceValue = Double.parseDouble(returnVal.get(0));
				else
					serviceValue = 0;
				
				sql ="SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE < '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE < '"+sdf.format(fromDate.getTime())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = Double.parseDouble(returnVal.get(0));
				else
					expenseValue = 0;
				
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (serviceValue-expenseValue)));
				expenseValue = 0;
				serviceValue = 0;
			}
		}
		
		return tally;
		
	}
	
	
	
	/**
	 * @param element client to look into
	 * @return current revenue from services to client
	 */
	
	public double getClientCurrentRevenue(Client element)
	{
		double output = -1;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		
		if(element != null && element.getID() > -1)
		{
			sql = "SELECT SUM (RATE) "+
			"FROM" + 
			"(SELECT DISTINCT SV.RATE "+
			"FROM "+
			"CLIENTS CL "+ 
			"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(new Date())+"' AND CON.START_DATE < '"+sdf.format(new Date())+"') "+
			"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
			"INNER JOIN SERVICES_TYPES ST ON(SV.SERVICE_TYPE_ID = ST.ROW_ID AND ST.SERVICE_TYPE != 'Web Design') " +
			"WHERE "+
			"CL.ROW_ID = "+element.getID()+") ";
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
				output = Double.parseDouble(returnVal.get(0));
			else
				output = 0;
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
				output = Double.parseDouble(returnVal.get(0));
			else
				output = 0;
		}
		
		return output;
	}
	
	
	/**
	 * @param element client to look into
	 * @return current cost of expenses
	 */
	
	public double getClientCurrentExpenses(Client element)
	{
		double output = -1;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Calendar toDate = Calendar.getInstance();
		toDate.setTime(new Date());
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			sql ="SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(new Date())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				output = Double.parseDouble(returnVal.get(0));
				output = -output;
			}
			else
				output = 0;
		}
		
		return output;
	}
	
	
	/** 
	 * @param element Service of Interest
	 * @return Ex3penses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceExpenses(ServiceType element)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			//Find the oldest active contract to use for contract calendar
			sql = "SELECT MIN(CON.SIGNED_DATE) "+
			"FROM "+
			"CONTRACTS CON "+
			"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
			"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
			"WHERE "+
			"ST.ROW_ID ="+ element.getID();
			
			returnVal = this.mainDB.blindQuery(sql);
			
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			
			contractDate.setTime(endDate);
			
			
			for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM(EX.VALUE) "+
				"FROM "+
				"CONTRACTS CON "+  
				"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"' ) "+
				"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
				"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(fromDate.getTime())+"') "+
				"WHERE "+
				"ST.ROW_ID = "+element.getID();
			
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = Double.parseDouble(returnVal.get(0));
				else
					expenseValue = 0;	
			
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
				expenseValue = 0;
			}
		}
		
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearServiceRevenue(ServiceType element)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(CON.SIGNED_DATE) "+
				"FROM "+
				"CONTRACTS CON "+
				"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
				"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
				"WHERE "+
				"ST.ROW_ID ="+ element.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			contractDate.setTime(endDate);
			if(element.getID() == 2)
				divisor = 12;
			
			for(int i = 0; i < 12 && toDate.getTime().after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM (RATE) "+
				"FROM" + 
				"(SELECT SV.RATE "+
				"FROM "+
				"CONTRACTS CON "+ 
				"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"' ) "+
				"INNER JOIN SERVICES_TYPES ST ON (ST.ROW_ID = SV.SERVICE_TYPE_ID) "+
				"WHERE "+
				"ST.ROW_ID = "+element.getID()+")";
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = (Double.parseDouble(returnVal.get(0)) / divisor);
				else
					expenseValue = 0;	
			
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
				expenseValue = 0;
			}
		}
		
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getLastYearClientFeaturesByType(Client client, TrackedFeatureType feat)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(client != null && client.getID() > -1 && feat != null && feat.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(FE.DATE_RECCORDED) "+
				"FROM "+
				"CLIENTS CL "+
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID) "+
				"INNER JOIN FEATURE_TYPES FT ON (FT.ROW_ID = FE.FEATURE_TYPE_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			contractDate.setTime(endDate);
			contractDate.add(Calendar.MONTH, -1);
			contractDate.setTime(contractDate.getTime());
			Date testTime = contractDate.getTime();
			
			for(int i = 0; i <12 &&toDate.getTime().after(contractDate.getTime()); i++)
			{
				sql = "SELECT SUM(FE.VALUE) "+
				"FROM "+
				"CLIENTS CL "+ 
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID AND FE.DATE_RECCORDED > '"+sdf.format(toDate.getTime())+"' AND FE.DATE_RECCORDED <= '"+sdf.format(fromDate.getTime())+"' ) "+
				"INNER JOIN FEATURE_TYPES FT ON (FE.FEATURE_TYPE_ID = FT.ROW_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = (Double.parseDouble(returnVal.get(0)));
				else
					expenseValue = 0;	
			
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
				expenseValue = 0;
			}
		}
		return tally;
	}
	
	/** 
	 * @param element Service of Interest
	 * @return Expenses + revenue for services for month
	 */
	
	public ArrayList<MonthReport> getSumFeatures(Client client, TrackedFeatureType feat)
	{
		ArrayList<MonthReport> tally = null;
		double expenseValue = 0;
		String sql = "";
		double divisor = 1;
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Date endDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		Contract clientContract = null;
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(client != null && client.getID() > -1 && feat != null && feat.getID() > -1)
		{
			
			tally = new ArrayList<MonthReport>();
			
			sql = "SELECT MIN(FE.DATE_RECCORDED) "+
				"FROM "+
				"CLIENTS CL "+
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID) "+
				"INNER JOIN FEATURE_TYPES FT ON (FT.ROW_ID = FE.FEATURE_TYPE_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
			returnVal = this.mainDB.blindQuery(sql);
					
			if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
			{
				try
				{
					endDate = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(returnVal.get(0));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
				
			contractDate.setTime(endDate);
			contractDate.add(Calendar.MONTH, -1);
			contractDate.setTime(contractDate.getTime());
			
			while(toDate.getTime().after(contractDate.getTime()))
			{
				sql = "SELECT SUM(FE.VALUE) "+
				"FROM "+
				"CLIENTS CL "+ 
				"INNER JOIN FEATURE FE ON (FE.CLIENT_ID = CL.ROW_ID AND FE.DATE_RECCORDED > '"+sdf.format(toDate.getTime())+"' AND FE.DATE_RECCORDED <= '"+sdf.format(fromDate.getTime())+"' ) "+
				"INNER JOIN FEATURE_TYPES FT ON (FE.FEATURE_TYPE_ID = FT.ROW_ID) "+
				"WHERE "+
				"CL.ROW_ID = "+client.getID()+" AND FT.ROW_ID = "+feat.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = (Double.parseDouble(returnVal.get(0)));
				else
					expenseValue = 0;	
			
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				tally.add(new MonthReport(fromDate.getTime(), (expenseValue)));
				expenseValue = 0;
			}
		}
		return tally;
	}
	
	public double getTotalAllFeatures(Client client, TrackedFeatureType type)
	{
		double output = 0;
		ArrayList<MonthReport> summer = new ArrayList<MonthReport>();
		summer = getSumFeatures(client, type);
		
		if(summer != null)
		{
			for(int i = 0 ; i < summer.size(); i++)
			{
				output += summer.get(i).getValue();
			}
		}
		return output;
	}
	
	/** GET ALLCLIENTRETURNS()
	 * 
	 * Returns the sum of all possible returns on a client.
	 */
	
	public double getAllClientReturns(Client element)
	{
		double output = 0;
		double serviceValue = 0;
		double expenseValue = 0;
		String sql = "";
		ArrayList<String> returnVal = new ArrayList<String>();
		Date startDate = new Date();
		Calendar fromDate = Calendar.getInstance();
		Calendar toDate = Calendar.getInstance();
		Calendar contractDate = Calendar.getInstance();
		ArrayList<Contract> clientContracts = new ArrayList<Contract>();
		toDate.setTime(startDate);
		fromDate.setTime(startDate);
		toDate.add(Calendar.MONTH, -1);
		
		if(element != null && element.getID() > -1)
		{
			//Find the oldest active contract to use for contract calendar
			clientContracts = this.getContractsByBusiness(element.getBusinessName());
			contractDate.setTime(clientContracts.get(0).getSignedDate());
			
			for(int i = 1; i <clientContracts.size(); i++)
			{
				if(contractDate.getTime().before(clientContracts.get(i).getSignedDate()))
					contractDate.setTime(clientContracts.get(i).getSignedDate());
			}
					
			
			while(toDate.getTime().after(contractDate.getTime()))
			{
				sql = "SELECT SUM (RATE)" +
					"FROM " +
					"(SELECT DISTINCT SV.RATE "+
					"FROM "+
					"CLIENTS CL "+
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"')"+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID)"+
					"INNER JOIN SERVICES_TYPES ST ON(SV.SERVICE_TYPE_ID = ST.ROW_ID AND ST.SERVICE_TYPE != 'Web Design') "+
					"WHERE "+
					"CL.ROW_ID ="+element.getID()+")";
					
				returnVal = this.mainDB.blindQuery(sql);
					
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					serviceValue = Double.parseDouble(returnVal.get(0));
				else
					serviceValue = 0;
				
				sql ="SELECT SUM(EX.VALUE) "+
					"FROM "+
					"CLIENTS CL "+ 
					"INNER JOIN CONTRACTS CON ON(CON.BUSINESS_NAME = CL.BUSINESS_NAME AND CON.END_DATE > '"+sdf.format(toDate.getTime())+"' AND CON.START_DATE <= '"+sdf.format(toDate.getTime())+"') "+
					"INNER JOIN SERVICES SV ON (SV.CONTRACT_ID = CON.ROW_ID) "+
					"INNER JOIN EXPENSE EX ON(EX.SERVICE_ID = SV.ROW_ID AND INCURRED_DATE > '"+sdf.format(toDate.getTime())+"' AND INCURRED_DATE <= '"+sdf.format(fromDate.getTime())+"') "+
					"WHERE "+
					"CL.ROW_ID = " +element.getID();
				
				returnVal = this.mainDB.blindQuery(sql);
				
				if(returnVal.size() == 1 && returnVal.get(0).compareTo("null") != 0)
					expenseValue = Double.parseDouble(returnVal.get(0));
				else
					expenseValue = 0;
				
				toDate.add(Calendar.MONTH, -1);
				fromDate.add(Calendar.MONTH, -1);
				
				output = output+(serviceValue-expenseValue);
				expenseValue = 0;
				serviceValue = 0;
			}
		}
		
		return output;
	}
}

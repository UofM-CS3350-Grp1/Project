package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Service;
import objects.ServiceType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessExpenses;
import business.ProcessService;

/**
 * Services screen
 */
public class JCCServiceScreenDrawer extends BaseJCCScreenDrawer
{
	private static final String[] tableColumnNames = { "Service ID", "Service", "Revenue", "Expenses", "Profit", "Expense Margin", "Profit Margin" };
	private static final int[] tableWidths = { 0, 150, 150, 150, 150, 150, 150 };
	private ProcessService processService;
	
	/*
	 * Call the constructor 
	 */
	public JCCServiceScreenDrawer( Composite container )
	{
		super(container);
		
		if(processService == null)
			processService = new ProcessService();
	}	
	
	/**
	 * Populates the table with the services data
	 */
	protected void populateTable()
	{
		Service service = null;
		TableItem item;
		ArrayList<ServiceType> serviceTypeList = null;
		ServiceType serviceType = null;
		
		table.removeAll();
		
		if(processService == null)
			processService = new ProcessService();
		
		serviceTypeList = processService.getServiceTypes();
		Iterator<ServiceType> it = serviceTypeList.iterator();
		
		while(it.hasNext())
		{			
			serviceType = it.next();
			/*while((service = processService.getNextService())!=null)
			{
				if(service.getServiceType()==serviceType)
				{*/
					item = new TableItem(table, SWT.NULL);
					
					double expense = getExpenses(serviceType);
					double total = getRevenue(serviceType);
					double profit = getProfit(expense, total);

					item.setText(0, serviceType.getID() + "");
					item.setText(1, serviceType.getType() + "");
					item.setText(2, "$ ");
					item.setText(3, "$ "+expense);
					item.setText(4, "$ "+profit);
					item.setText(5, getEM(expense, total)+"% ");
					item.setText(6, getPM(profit, total)+"% ");					
			//	}
			//}
		}
	}
	
	/*
	 * @return The total income of this service
	 */
	protected double getRevenue(ServiceType serviceType)
	{
		double result = 0;
		ProcessService processService = new ProcessService();
		ProcessClient processClient = new ProcessClient();
		ArrayList<Service> serviceList;
		Service temp = null;
		while((temp = processService.getNextService())!=null)
		{
			if(temp.getServiceType()==serviceType)
			{
				result += temp.getRate();
			}
		}
		return result;
	}

	/*
	 * @return The total profit margin (%) of this service
	 */
	protected double getPM(double profit, double total) 
	{
		double result = 0;
		result = Math.round((profit/total)*100.0);
		return result;
	}

	/*
	 * @return The total expense margin (%) of this contract
	 */
	protected double getEM(double expense, double total) 
	{
		double result = 0;
		result = Math.round((expense/total)*100.0);
		return result;
	}

	/*
	 * @return The total profit of this service
	 */
	protected double getProfit(double expense, double total) 
	{
		double result = 0;
		result = total - expense;
		return result;
	}

	/*
	 * @return The total expenses of this service
	 */
	protected double getExpenses(ServiceType serviceType) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		ArrayList<Service> serviceList;
		Service temp = null;
		while((temp = processService.getNextService())!=null)
		{
			if(temp.getServiceType()==serviceType)
			{
				result += processExpenses.getExpensesByServiceType(serviceType);
			}
		}
		return result;
	}

	@Override
	protected String[] getTableColumnNames() 
	{
		return tableColumnNames;
	}

	@Override
	protected int[] getTableWidths() 
	{
		return tableWidths;
	}

	@Override
	protected void viewContractList() 
	{
		Composite jccContractList = SwitchScreen.getContentContainer();
		new JCCContractScreenDrawer( jccContractList );
		SwitchScreen.switchContent( jccContractList );
	}

	@Override
	protected void viewServiceList() 
	{
		Composite jccServiceList = SwitchScreen.getContentContainer();
		new JCCServiceScreenDrawer( jccServiceList );
		SwitchScreen.switchContent( jccServiceList );
	}

	@Override
	protected void viewClientList() 
	{
		Composite jccClientList = SwitchScreen.getContentContainer();
		new JCCClientScreenDrawer( jccClientList );
		SwitchScreen.switchContent( jccClientList );
	}

	@Override
	protected void viewSelectedItem()
	{
	}

	@Override
	protected void addSurveyInfo() 
	{
		Composite jccAddSurvey = SwitchScreen.getContentContainer();
		new JCCSurveyScreenDrawer( jccAddSurvey );
		SwitchScreen.switchContent( jccAddSurvey );
	}

	@Override
	protected void addExpenses() {
		Composite jccExpenses = SwitchScreen.getContentContainer();
		new AddExpensesScreenDrawer( jccExpenses );
		SwitchScreen.switchContent( jccExpenses );
	}
	
}

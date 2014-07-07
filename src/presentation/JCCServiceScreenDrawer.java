package presentation;

import java.util.ArrayList;

import objects.Service;

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
		
		table.removeAll();
		
		if(processService == null)
			processService = new ProcessService();
		
		while((service = processService.getNextService())!=null)
		{			
			item = new TableItem(table, SWT.NULL);

			double expense = getExpenses(service);
			double total = getRevenue(service);
			double profit = getProfit(expense, total);

			item.setText(0, service.getID() + "");
			item.setText(1, service.getTitle() + "");
			item.setText(2, "$ "+(int)service.getRate());
			item.setText(3, "$ "+expense);
			item.setText(4, "$ "+profit);
			item.setText(5, getEM(expense, total)+"% ");
			item.setText(6, getPM(profit, total)+"% ");
		}
	}
	
	/*
	 * @return The total income of this service
	 */
	protected double getRevenue(Service service)
	{
		double result = 0;
		ProcessService processService = new ProcessService();
		ProcessClient processClient = new ProcessClient();
		ArrayList<Service> serviceList;
		Service temp = null;
		while((temp = processService.getNextService())!=null)
		{
			if(temp.getID()==service.getID())
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
	protected double getExpenses(Service service) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		result = processExpenses.getExpensesByService(service);
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

package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessExpenses;

/**
 * Clients screen
 */
public class JCCClientScreenDrawer extends BaseJCCScreenDrawer
{
	private static final String[] tableColumnNames = { "Client ID", "Business", "Revenue", "Expenses", "Profit", "Contribution Margin", "Profit Margin" };
	private static final int[] tableWidths = { 0, 150, 150, 150, 150, 150, 150 };
	private ProcessClient processClient;
	
	/*
	 * Call the constructor 
	 */
	public JCCClientScreenDrawer( Composite container )
	{
		super(container);
		
		if(processClient == null)
			processClient = new ProcessClient();	    
	}	
	
	/**
	 * Populates the table with the clients data
	 */
	protected void populateTable()
	{
		Client client = null;
		TableItem item;
		
		table.removeAll();
		
		if(processClient == null)
			processClient = new ProcessClient();
		
		while((client = processClient.getNextClient())!=null)
		{			
			item = new TableItem(table, SWT.NULL);

			double expense = getExpenses(client);
			double total = getRevenue(client);
			double profit = getProfit(expense, total);

			item.setText(0, client.getID() + "");
			item.setText(1, client.getBusinessName() + "");
			item.setText(2, "$ "+total);
			item.setText(3, "$ "+expense);
			item.setText(4, "$ "+profit);
			item.setText(5, "% "+getCM());
			item.setText(6, getPM(profit, total)+"%");
		}
	}

	/*
	 * @return The total revenue generated off this client (includes all contracts/services)
	 */
	protected double getRevenue(Client client) 
	{
		int result = 0;
		ArrayList<Contract> contractList = null;
		ProcessClient processClient2 = new ProcessClient();
		ProcessContract processContract = new ProcessContract();
		contractList = processContract.getContractsByClient(client);
		Iterator<Contract> it = contractList.iterator();
		Contract contract = null;
		while(it.hasNext())
		{
			contract = it.next();
			result += contract.getValue();
		}
		return result;
	}

	/*
	 * @return The total profit margin (%) made off this client
	 */
	protected double getPM(double profit, double total) 
	{
		double result = 0;
		result = Math.round((profit/total)*100.0)/100.0;
		return result;
	}

	/*
	 * @return The total contribution margin (%) of this client
	 */
	protected String getCM() 
	{
		return null;
	}

	/*
	 * @return The total profit made off this client
	 */
	protected double getProfit(double expense, double total) 
	{
		double result = 0;
		result = total - expense;
		return result;
	}

	/*
	 * @return The total expenses of this client
	 */
	protected double getExpenses(Client client) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		result = processExpenses.getExpensesByClient(client);
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

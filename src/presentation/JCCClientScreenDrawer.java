package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;

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
	private static final String[] tableColumnNames = { "Client ID", "Business", "Revenue", "Expenses", "Profit", "Expense Margin", "Profit Margin" };
	private static final int[] tableWidths = { 0, 150, 150, 150, 150, 150, 150 };
	private ProcessClient processClient;
	
	/**
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
			item.setText(2, String.format("$ %-10.2f", total));
			item.setText(3, String.format("$ %-10.2f", expense));
			item.setText(4, String.format("$ %-10.2f", profit));
			item.setText(5, String.format("%-3.2f%%", getEM(expense, total)));
			item.setText(6, String.format("%-3.2f%%", getEM(profit, total)));
		}
	}

	/**
	 * @return The total revenue generated off this client (includes all contracts/services)
	 */
	protected double getRevenue(Client client) 
	{
		int result = 0;
		ArrayList<Contract> contractList = null;
		ProcessContract processContract = new ProcessContract();
		Iterator<Contract> it;
		Contract contract = null;
		
		if(client != null)
		{
			contractList = processContract.getContractsByClient(client);
			if(contractList != null)
			{
				it = contractList.iterator();
				while(it.hasNext())
				{
					contract = it.next();
					result += contract.getValue();
				}
			}
		}
		return result;
	}

	/**
	 * @return The total expenses of this client
	 */
	protected double getExpenses(Client client) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		
		if(client != null)
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
	protected void viewSelectedItem()
	{
		int index, id;
		Composite analysisScreen;
		Client client;
		
		if((index = table.getSelectionIndex()) != -1)
		{
			try
			{
				//Extract the service ID from the table
				id = Integer.parseInt(table.getItem(index).getText(0));
				client = processClient.getClientByID(id);
				
				if(client != null)
				{
					//Open the client performance tracking screen
					analysisScreen = SwitchScreen.getContentContainer();
					new ClientAnalysisScreenDrawer( analysisScreen, client );
					SwitchScreen.switchContent( analysisScreen );
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe);
			}
		}
	}	
}

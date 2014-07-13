package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessContract;
import business.ProcessExpenses;

/**
 * Contracts screen
 */
public class JCCContractScreenDrawer extends BaseJCCScreenDrawer
{
	private static final String[] tableColumnNames = { "Contract ID", "Client", "Revenue", "Expenses", "Profit", "Expense Margin", "Profit Margin" };
	private static final int[] tableWidths = { 100, 150, 150, 150, 150, 150, 150 };
	private ProcessContract processContract;
	private ArrayList<Contract> contracts;
	
	/**
	 * Call the constructor 
	 */
	public JCCContractScreenDrawer( Composite container )
	{
		super(container);
		
		if(processContract == null)
			processContract = new ProcessContract();	    
	}	
	
	/**
	 * Populates the table with the contract data
	 */
	protected void populateTable()
	{
		Contract contract = null;
		TableItem item;
		Iterator<Contract> it;
		
		table.removeAll();
		
		if(processContract == null)
			processContract = new ProcessContract();
		
		contracts = processContract.getContracts();

		if(contracts != null)
		{
			it = contracts.iterator();
			
			while(it.hasNext())
			{
				contract = it.next();
				
				item = new TableItem(table, SWT.NULL);
				
				double expense = getExpenses(contract);
				double total = contract.getValue();
				double profit = getProfit(expense, total);
	
				item.setText(0, contract.getID() + "");
				item.setText(1, contract.getBusinessName() + "");
				item.setText(2, String.format("$ %-10.2f", total));
				item.setText(3, String.format("$ %-10.2f", expense));
				item.setText(4, String.format("$ %-10.2f", profit));
				item.setText(5, String.format("%-3.2f%%", getEM(expense, total)));
				item.setText(6, String.format("%-3.2f%%", getEM(profit, total)));
			}
		}
	}

	/**
	 * @return The total expenses of this contract
	 */
	protected double getExpenses(Contract contract) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		
		if(contract != null)
			result = processExpenses.getExpensesByContract(contract);
		
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
		int index;
		Composite analysisScreen;
		Client client;
		Contract contract;
		ProcessContract processContract = new ProcessContract();
		
		if((index = table.getSelectionIndex()) != -1)
		{
			try
			{
				TableItem select = table.getItem(index);
				int x = Integer.parseInt(select.getText(0));
				
				contract = processContract.getContractByID(x);
				client = processContract.getContractClient(contract);		
				
				if(client==null) 
					System.out.println("Client is null");
				if(contract==null) 
					System.out.println("Contract is null");
				
				if(client != null && contract != null)
				{
					analysisScreen = SwitchScreen.getContentContainer();
					new ContractAnalysisScreenDrawer( analysisScreen, contract, client );
					SwitchScreen.switchContent( analysisScreen );
				}
				else
				{
					System.out.println("error");
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe);
			}
		}
	}
}

package presentation;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
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
	
	/*
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
		
		table.removeAll();
		
		if(processContract == null)
			processContract = new ProcessContract();
		
		contracts = processContract.getContracts();

		if(contracts != null)
		{
			Iterator<Contract> it = contracts.iterator();
			
			while(it.hasNext())
			{
				contract = (Contract) it.next();
				
				item = new TableItem(table, SWT.NULL);
				
				double expense = getExpenses(contract);
				double total = contract.getValue();
				double profit = getProfit(expense, total);
	
				item.setText(0, contract.getID() + "");
				item.setText(1, contract.getBusinessName() + "");
				item.setText(2, "$ "+total);
				item.setText(3, "$ "+expense);
				item.setText(4, "$ "+profit);
				item.setText(5, getEM(expense, total)+"% ");
				item.setText(6, getPM(profit, total)+"% ");
			}
		}
	}

	/*
	 * @return The total profit margin (%) of this contract
	 */
	protected double getPM(double profit, double total) 
	{
		double result = 0;
		result = Math.round((profit/total)*100.0)/100.0;
		return result;
	}

	/*
	 * @return The total expense margin (%) of this contract
	 */
	protected double getEM(double expense, double total) 
	{
		double result = 0;
		result = Math.round((expense/total)*100.0)/100.0;
		return result;
	}

	/*
	 * @return The total profit of this contract
	 */
	protected double getProfit(double expense, double total) 
	{
		double result = 0;
		result = total - expense;
		return result;
	}

	/*
	 * @return The total expenses of this contract
	 */
	protected double getExpenses(Contract contract) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
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

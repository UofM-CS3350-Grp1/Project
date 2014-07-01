package presentation;

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

/**
 * Contracts screen
 */
public class JCCContractScreenDrawer extends BaseJCCScreenDrawer
{
	private static final String[] tableColumnNames = { "Contract ID", "Client", "Revenue", "Expenses", "Profit", "Contribution Margin", "Profit Margin" };
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

		Iterator<Contract> it = contracts.iterator();
		
		while(it.hasNext())
		{
			contract = (Contract) it.next();
			
			item = new TableItem(table, SWT.NULL);

			item.setText(0, contract.getID() + "");
			item.setText(1, contract.getBusinessName() + "");
			item.setText(2, "$ "+(int)contract.getValue());
			item.setText(3, "$ "+getExpenses());
			item.setText(4, "$ "+getProfit());
			item.setText(5, "% "+getCM());
			item.setText(6, "% "+getPM());
		}
	}

	/*
	 * @return The total profit margin (%) of this contract
	 */
	protected String getPM() 
	{
		return null;
	}

	/*
	 * @return The total contribution margin (%) of this contract
	 */
	protected String getCM() 
	{
		return null;
	}

	/*
	 * @return The total profit of this contract
	 */
	protected String getProfit() 
	{
		return null;
	}

	/*
	 * @return The total expenses of this contract
	 */
	protected String getExpenses() 
	{
		return null;
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
	
}

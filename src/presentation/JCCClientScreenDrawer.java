package presentation;

import objects.Client;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;

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

			item.setText(0, client.getID() + "");
			item.setText(1, client.getBusinessName() + "");
			item.setText(2, "$ "+getRevenue(client));
			item.setText(3, "$ "+getExpenses());
			item.setText(4, "$ "+getProfit());
			item.setText(5, "% "+getCM());
			item.setText(6, "% "+getPM());
		}
	}

	/*
	 * @return The total revenue generated off this client (includes all contracts/services)
	 */
	protected int getRevenue(Client client) 
	{
		int result = 0;
		Service temp = null;
		ProcessClient processClient2 = new ProcessClient();
		while((temp = processClient2.getNextClientService(client))!=null)
		{
			result += (int)temp.getRate();
		}
		return result;
	}

	/*
	 * @return The total profit margin (%) of this client
	 */
	protected String getPM() 
	{
		return null;
	}

	/*
	 * @return The total contribution margin (%) of this client
	 */
	protected String getCM() 
	{
		return null;
	}

	/*
	 * @return The total profit of this client
	 */
	protected String getProfit() 
	{
		return null;
	}

	/*
	 * @return The total expenses of this client
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

	@Override
	protected void addExpenses() {
		// TODO Auto-generated method stub
		
	}
	
}

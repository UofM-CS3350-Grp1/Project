package presentation;

import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessExpenses;
import business.ProcessService;

/**
 * Services screen
 */
public class JCCServiceScreenDrawer extends BaseJCCScreenDrawer
{
	private static final String[] tableColumnNames = { "Service ID", "Service", "Revenue", "Expenses", "Profit", "Contribution Margin", "Profit Margin" };
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

			item.setText(0, service.getID() + "");
			item.setText(1, service.getTitle() + "");
			item.setText(2, "$ "+(int)service.getRate());
			item.setText(3, "$ "+getExpenses(service));
			item.setText(4, "$ "+getProfit());
			item.setText(5, "% "+getCM());
			item.setText(6, "% "+getPM());
		}
	}

	/*
	 * @return The total profit margin (%) of this service
	 */
	protected String getPM() 
	{
		return null;
	}

	/*
	 * @return The total contribution margin (%) of this service
	 */
	protected String getCM() 
	{
		return null;
	}

	/*
	 * @return The total profit of this service
	 */
	protected String getProfit() 
	{
		return null;
	}

	/*
	 * @return The total expenses of this service
	 */
	protected String getExpenses(Service service) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		result = processExpenses.getExpensesByService(service);
		return ""+result;
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

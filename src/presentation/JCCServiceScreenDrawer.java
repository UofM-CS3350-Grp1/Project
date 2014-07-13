package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Service;
import objects.ServiceType;

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
			
			item = new TableItem(table, SWT.NULL);
			
			double expense = getExpenses(serviceType);
			double total = getRevenue(serviceType);
			double profit = getProfit(expense, total);

			item.setText(0, serviceType.getID() + "");
			item.setText(1, serviceType.getType() + "");
			item.setText(2, String.format("$ %-10.2f", total));
			item.setText(3, String.format("$ %-10.2f", expense));
			item.setText(4, String.format("$ %-10.2f", profit));
			item.setText(5, String.format("%-3.2f%%", getEM(expense, total)));
			item.setText(6, String.format("%-3.2f%%", getEM(profit, total)));
		}
	}
	
	/**
	 * @return The total income of this service
	 */
	protected double getRevenue(ServiceType serviceType)
	{
		double result = 0;
		ProcessService processService = new ProcessService();
		Service temp = null;
		
		if(serviceType != null)
		{		
			while((temp = processService.getNextService())!=null)
			{
				if(temp.getServiceType().getType()==serviceType.getType())
				{
					result += temp.getRate();
				}
			}
		}
		
		return result;
	}

	/**
	 * @return The total expenses of this service
	 */
	protected double getExpenses(ServiceType serviceType) 
	{
		double result = 0;
		ProcessExpenses processExpenses = new ProcessExpenses();
		Service temp = null;
		
		if(serviceType != null)
		{
			while((temp = processService.getNextService())!=null)
			{
				if(temp.getServiceType().getType()==serviceType.getType())
				{
					result += processExpenses.getExpensesByService(temp);
				}
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
	protected void viewSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		int id;
		Composite viewService;
		ServiceType serviceType;

		if(selectedIndex != -1)
		{
			try
			{
				//Extract the service ID from the table
				id = Integer.parseInt(table.getItem(selectedIndex).getText(0));
				serviceType = processService.getServiceTypeByID(id);
				
				if(serviceType != null)
				{
					viewService = SwitchScreen.getContentContainer();
					new PerformanceServiceScreenDrawer( viewService, serviceType );
					SwitchScreen.switchContent( viewService );
				}
			}
			catch(NumberFormatException nfe) 
			{
				System.out.println(nfe);
			}
		}
	}	
}

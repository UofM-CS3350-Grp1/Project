package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Service;
import objects.ServiceType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessService;

/**
 * Responsible for drawing the services in a nice looking table.
 * To edit the look of the page see the BaseStorableScreenDrawer
 */
public class ServiceScreenDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "ID", "Title", "Description", "Rate", "Type" };
	private static final int[] tableWidths = { 0, 150, 250, 100, 150 };
	private ProcessService processService;
	private ServiceType serviceType;
	private TableItem item;
	
	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */	
	public ServiceScreenDrawer( Composite container )
	{
		super(container);

		btnAdd.setText("New Service");
		btnUpdate.setText("Edit Service");;
		btnDelete.setText("Cancel Service");
		btnView.setText("View Service");
		
		if(processService == null)
			processService = new ProcessService();	    
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		Service service = null;
		ArrayList<ServiceType> serviceTypeList = null;
		
		table.removeAll();

		processService = new ProcessService();	 
		serviceTypeList = processService.getServiceTypes();
		
		if(serviceTypeList!=null)
		{
			Iterator<ServiceType> it = serviceTypeList.iterator();

			while(it.hasNext())
			{
				serviceType = it.next();
				
				item = new TableItem(table, SWT.NULL);
				
				item.setText(0, serviceType.getID() + "");
				item.setText(1, serviceType.getType());
				item.setText(2, serviceType.getDescription());
				item.setText(3, "NA");
				item.setText(4, serviceType.getType());
			}
		}
	}
	
	/**
	 * Adds a new client through the new service composite
	 */
	protected void addNew()
	{
		Composite addNewService = SwitchScreen.getContentContainer();
		new AddServiceScreenDrawer( addNewService );
		SwitchScreen.switchContent( addNewService );
	}
	
	/**
	 * Edits a new client through the edit client composite
	 */
	protected void editSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		Service service;
		
		if(selectedIndex != -1)
		{
			service = processService.getServiceByID(Integer.parseInt(table.getItem(selectedIndex).getText(0)));
			if(service != null)
			{
				Composite editServiceScreen = SwitchScreen.getContentContainer();
				new UpdateServiceScreenDrawer( editServiceScreen, service );
				SwitchScreen.switchContent( editServiceScreen );
			}
		}
	}
	
	/**
	 * View the selected service
	 */
	protected void viewSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		int id;
		Composite viewService;
		Service service;
		ArrayList<Service> serviceList = null;
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
	
	/**
	 * Deletes the selected item in the table
	 */
	protected void deleteSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		Service service;
		TableItem selectedItem;
		
		if(selectedIndex != -1)
		{
			selectedItem = table.getItem(selectedIndex);
			
			//Ensure that the user actually wants to delete the item
			dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
			dialog.setText("Confirmation");
			dialog.setMessage("Are you sure you want to delete " + selectedItem.getText(1) + "?");
			
			buttonID = dialog.open();
			switch(buttonID)
			{
				case SWT.YES:
					service = processService.getServiceByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(service != null)
						processService.delete(service);
					
					table.remove(selectedIndex);
					
					break;
					
				case SWT.NO:
					break;
			}
		}
	}
	
	/**
	 * @return Gets the names of the columns in each table
	 * 		   NOTE: ID should be column 0
	 */
	protected String[] getTableColumnNames()
	{
		return tableColumnNames;
	}
	
	/**
	 * @return Gets the widths of each table
	 * 		   NOTE: ID should be column 0
	 */
	protected int[] getTableWidths()
	{
		return tableWidths;
	}
}

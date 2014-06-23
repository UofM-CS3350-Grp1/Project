package presentation;

import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
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
	TableItem item;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	
	public ServiceScreenDrawer( Composite container )
	{
		super(container);
		
		btnAdd.setText("New Service");
		
		if(processService == null)
			processService = new ProcessService();	    
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		Service service = null;
		
		table.removeAll();
		
		if(processService == null)
			processService = new ProcessService();
		
		//Populate our services
		while((service = processService.getNextService()) != null)
		{
			item = new TableItem(table, SWT.NULL);
			
			item.setText(0, service.getID() + "");
			item.setText(1, service.getTitle());
			item.setText(2, service.getDescription());
			item.setText(3, service.getRate() + "");
			item.setText(4, service.getType());
		}
	}
	
	/**
	 * Adds a new client through the new service composite
	 */
	protected void addNew()
	{
		Composite addNewService;
		Service service = null;

		addNewService = new Composite(SwitchScreen.getContent(), SWT.None);
		addNewService.setLayout(new FillLayout());
		new AddServiceScreenDrawer(addNewService);
		SwitchScreen.setcontentLayoutTopControl(addNewService);
		SwitchScreen.getContent().layout();
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
		int index, id;
		Composite viewService;
		Service service;

		if(selectedIndex != -1)
		{
			try
			{
				//Extract the service ID from the table
				id = Integer.parseInt(table.getItem(selectedIndex).getText(0));
				service = processService.getServiceByID(id);
				
				if(service != null)
				{
					viewService = new Composite(SwitchScreen.getContent(), SWT.None);
					viewService.setLayout(new FillLayout());
					new PerformanceServiceScreenDrawer(viewService, service);
					SwitchScreen.setcontentLayoutTopControl(viewService);
					SwitchScreen.getContent().layout();
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
						processService.deleteService(service);
					
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

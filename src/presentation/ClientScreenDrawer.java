package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import objects.Client;
import business.ProcessClient;
import acceptanceTests.Register;

/**
 * Draws the beautiful client screen
 * To edit the look of the page see the BaseStorableScreenDrawer
 */
public class ClientScreenDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "ID", "Client", "Contact", "Status", "Address", "Email", "Phone Number" };
	private static final int[] tableWidths = { 0, 150, 100, 100, 150, 150, 100 };
	private ProcessClient processClient;
	
	//Acceptance test controls
	@SuppressWarnings("unused")
	private Table _table;	
	@SuppressWarnings("unused")
	private Shell shell;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public ClientScreenDrawer( Composite container )
	{
		super(container);
		
		btnAdd.setText("New Client");
		btnUpdate.setText("Edit Client");;
		btnDelete.setText("Cancel Client");
		btnView.setText("View Client");
		
		_table = table;
		
		if(processClient == null)
			processClient = new ProcessClient();	  
		
		Register.newWindow(this);
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		Client client = null;
		TableItem item;
		
		table.removeAll();
		
		if(processClient == null)
			processClient = new ProcessClient();
		
		//Populate our clients
		while((client = processClient.getNextClient()) != null)
		{
			item = new TableItem(table, SWT.NULL);
			
			item.setText(0, client.getID() + "");
			item.setText(1, client.getBusinessName());
			item.setText(2, client.getName());
			item.setText(3, client.getStatus().toString());
			item.setText(4, client.getAddress());
			item.setText(5, client.getEmail().toString());
			item.setText(6, client.getPhoneNumber().formattedPhoneNumber());
		}
	}
	
	/**
	 * Adds a new client through the new client composite
	 */
	protected void addNew()
	{
		Composite addClientScreen = SwitchScreen.getContentContainer();
		new AddClientScreenDrawer( addClientScreen );
		SwitchScreen.switchContent( addClientScreen );
	}
	
	/**
	 * Edits a new client through the edit client composite
	 */
	protected void editSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		Client client;
		
		if(selectedIndex != -1)
		{
			client = processClient.getClientByID(Integer.parseInt(table.getItem(selectedIndex).getText(0)));
			if(client != null)
			{
				Composite editClientScreen = SwitchScreen.getContentContainer();
				new UpdateClientScreenDrawer( editClientScreen, client );
				SwitchScreen.switchContent( editClientScreen );
			}
		}
	}
	
	/**
	 * View the selected client through one of the analysis windows
	 */
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
	
	/**
	 * Deletes the selected item in the table
	 */
	protected void deleteSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		Client client;
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
					client = processClient.getClientByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(client != null)
						processClient.delete(client);
					
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

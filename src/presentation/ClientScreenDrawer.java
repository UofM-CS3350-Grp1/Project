package presentation;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;

import objects.Client;
import business.ProcessClient;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.FillLayout;

public class ClientScreenDrawer
{
	private static final String[] tableColumnNames = { "ID", "Client", "Contact", "Status", "Address", "Email", "Phone Number" };
	private static final int[] tableWidths = { 0, 150, 100, 100, 150, 150, 100 };
	private Composite composite;
	private Table clientsTable;
	private Composite btnComposite;
	private Button btnUpdate;
	private Button btnDelete;
	private ProcessClient processClient;
	private Button btnAdd;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public ClientScreenDrawer( Composite container )
	{
		processClient = new ProcessClient();
		
		composite = new Composite( container, SWT.NONE );
		composite.setLayout(new GridLayout(2, false));
		
		btnComposite = new Composite(composite, SWT.NONE);
		GridData gd_btnComposite = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_btnComposite.widthHint = 111;
		gd_btnComposite.heightHint = 232;
		btnComposite.setLayoutData(gd_btnComposite);
		
		btnAdd = new Button(btnComposite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				addNewClient();
			}
		});
		btnAdd.setBounds(0, 0, 111, 40);
		btnAdd.setText("New Client");
		
		btnUpdate = new Button(btnComposite, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				editSelectedItem();
			}
		});
		btnUpdate.setText("Edit Selected");
		btnUpdate.setBounds(0, 46, 111, 40);
		
		btnDelete = new Button(btnComposite, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				deleteSelectedItem();
			}
		});
		btnDelete.setText("Delete Selected");
		btnDelete.setBounds(0, 92, 111, 40);
		
		clientsTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		clientsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		clientsTable.setHeaderVisible(true);
		clientsTable.setLinesVisible(true);
		clientsTable.setSortDirection(SWT.UP);
	    
		initializeTable();
		populateTable();
		
		clientsTable.getColumn(1).notifyListeners(SWT.Selection, null);
	    
	}
	
	/**
	 * Initializes the columns in the table
	 */
	private void initializeTable()
	{
		TableColumn column;
		
		//sort listener
		Listener sortListener = new Listener() {  
	         public void handleEvent(Event e) {  
	             TableItem[] items = clientsTable.getItems();  
	             Collator collator = Collator.getInstance(Locale.getDefault());  
	             TableColumn column = (TableColumn)e.widget;
	             int index = 0;
	             
	             for(int i = 0; i < tableColumnNames.length; i++)
	             {
	            	 if(column.getText() == tableColumnNames[i])
	            	 {
	            		 index = i;
	            		 break;
	            	 }
	             }
	             
	             for (int i = 1; i < items.length; i++) 
	             {  
	                 String value1 = items[i].getText(index);  
	                 for (int j = 0; j < i; j++)
	                 {  
	                     String value2 = items[j].getText(index);
	                     
	                     if(clientsTable.getSortColumn() == column && clientsTable.getSortDirection() == SWT.UP)
	    	             {
	    	            	 if (collator.compare(value1, value2) > 0) 
	                         {  
	                             String[] values = {items[i].getText(0), items[i].getText(1), items[i].getText(2),
	                            		 items[i].getText(3), items[i].getText(4), items[i].getText(5), items[i].getText(6)};  
	                             items[i].dispose();  
	                             TableItem item = new TableItem(clientsTable, SWT.NONE, j);  
	                             item.setText(values);  
	                             items = clientsTable.getItems();
	                             break;
	                         }   
	    	             }
	                     else if (collator.compare(value1, value2) < 0) 
	                     {  
	                         String[] values = {items[i].getText(0), items[i].getText(1), items[i].getText(2),
	                        		 items[i].getText(3), items[i].getText(4), items[i].getText(5), items[i].getText(6)};  
	                         items[i].dispose();  
	                         TableItem item = new TableItem(clientsTable, SWT.NONE, j);  
	                         item.setText(values);  
	                         items = clientsTable.getItems();
	                         break;
	                     } 
	                     
	                 }  
	             }  
	             
	             if(clientsTable.getSortColumn() == column && clientsTable.getSortDirection() == SWT.UP)
	             {
	            	 clientsTable.setSortDirection(SWT.DOWN);
	             }
	             else
	             {
	            	 clientsTable.setSortDirection(SWT.UP);
	             }
	             clientsTable.setSortColumn(column);
	         }  
	     }; 
		
		//Create the columns of the table
		for(int i = 0; i < tableColumnNames.length; i++)
		{
			column = new TableColumn(clientsTable, SWT.NULL);
			column.setText(tableColumnNames[i]);
			column.setWidth(tableWidths[i]);
			column.addListener(SWT.Selection, sortListener);  
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = clientsTable.getColumn(0);
		column.setResizable(false);
	}
	
	/**
	 * Populates the table with the client data
	 */
	private void populateTable()
	{
		Client client = null;
		TableItem item;
		
		clientsTable.removeAll();
		
		//Populate our clients
		while((client = processClient.getNextClient()) != null)
		{
			item = new TableItem(clientsTable, SWT.NULL);
			
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
	private void addNewClient()
	{
		//TODO Open new client composite
		Composite addClientScreen = new Composite( SwitchScreen.content, SWT.None );
		addClientScreen.setLayout( new FillLayout() );
		AddClientScreenDrawer acsd = new AddClientScreenDrawer( addClientScreen );
		SwitchScreen.contentLayout.topControl = addClientScreen;
		SwitchScreen.content.layout();
	}
	
	/**
	 * Edits a new client through the edit client composite
	 */
	private void editSelectedItem()
	{
		int selectedIndex = clientsTable.getSelectionIndex();
		Client client;
		
		if(selectedIndex != -1)
		{
			client = processClient.getClientByID(Integer.parseInt(clientsTable.getItem(selectedIndex).getText(0)));
			if(client != null)
			{
				//TODO Open the edit client composite and supply it with the client to edit
			}
		}
	}
	
	/**
	 * Deletes the selected item in the table
	 */
	private void deleteSelectedItem()
	{
		int selectedIndex = clientsTable.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		Client client;
		TableItem selectedItem;
		
		if(selectedIndex != -1)
		{
			selectedItem = clientsTable.getItem(selectedIndex);
			
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
						processClient.deleteClient(client);
					
					clientsTable.remove(selectedIndex);
					
					break;
					
				case SWT.NO:
					break;
			}
		}
	}
}

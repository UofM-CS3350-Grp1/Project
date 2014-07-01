package presentation;

import java.text.Collator;
import java.util.Locale;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

/**
 * Handles the drawing of table based financial breakdowns
 * including contracts, clients and services
 */
public abstract class BaseJCCScreenDrawer
{
	protected Composite composite;
	protected Table table;
	protected Composite btnComposite;
	protected Button btnContract;
	protected Button btnClient;
	protected Button btnService;
	protected Button btnView;
	protected Button btnSurvey;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public BaseJCCScreenDrawer( Composite container )
	{		
		composite = new Composite( container, SWT.NONE );
		composite.setLayout(new GridLayout(2, false));
		
		btnComposite = new Composite(composite, SWT.NONE);
		GridData gd_btnComposite = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_btnComposite.widthHint = 111;
		gd_btnComposite.heightHint = 313;
		btnComposite.setLayoutData(gd_btnComposite);
		
		btnContract = new Button(btnComposite, SWT.NONE);
		btnContract.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				viewContractList();
			}
		});
		btnContract.setBounds(0, 0, 111, 40);
		btnContract.setText("Contracts");
		
		btnClient = new Button(btnComposite, SWT.NONE);
		btnClient.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				viewClientList();
			}
		});
		btnClient.setText("Clients");
		btnClient.setBounds(0, 92, 111, 40);
		
		btnService = new Button(btnComposite, SWT.NONE);
		btnService.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				viewServiceList();
			}
		});
		btnService.setText("Services");
		btnService.setBounds(0, 46, 111, 40);
		
		btnView = new Button(btnComposite, SWT.NONE);
		btnView.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				viewSelectedItem();
			}
		});
		btnView.setText("View selected");
		btnView.setBounds(0, 146, 111, 40);
		
		btnSurvey = new Button(btnComposite, SWT.NONE);
		btnSurvey.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				addSurveyInfo();
			}
		});
		btnSurvey.setText("Add Survey Info");
		btnSurvey.setBounds(0, 246, 111, 40);
		
		Label label = new Label(btnComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(25, 238, 64, 2);
		
		Label label_1 = new Label(btnComposite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(25, 138, 64, 2);
		
		Button btnEdit = new Button(btnComposite, SWT.NONE);
		btnEdit.setText("Edit Selected");
		btnEdit.setBounds(0, 192, 111, 40);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSortDirection(SWT.UP);
	    
		initializeTable();
		populateTable();
		
		table.getColumn(1).notifyListeners(SWT.Selection, null);
	    
	}
	
	/**
	 * Initializes the columns in the table
	 */
	private void initializeTable()
	{
		final String[] tableColumnNames = getTableColumnNames();
		final int[] tableWidths = getTableWidths();
		TableColumn column;		
		
		//sort listener
		Listener sortListener = new Listener() 
		{  
	         public void handleEvent(Event e)
	         {  
	             TableItem[] items = table.getItems();  
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
	                     
	                     if(table.getSortColumn() == column && table.getSortDirection() == SWT.UP)
	    	             {
	    	            	 if (collator.compare(value1, value2) > 0) 
	                         {  
	                             String[] values = {items[i].getText(0), items[i].getText(1), items[i].getText(2),
	                            		 items[i].getText(3), items[i].getText(4), items[i].getText(5), items[i].getText(6)};  
	                             items[i].dispose();  
	                             TableItem item = new TableItem(table, SWT.NONE, j);  
	                             item.setText(values);  
	                             items = table.getItems();
	                             break;
	                         }   
	    	             }
	                     else if (collator.compare(value1, value2) < 0) 
	                     {  
	                         String[] values = {items[i].getText(0), items[i].getText(1), items[i].getText(2),
	                        		 items[i].getText(3), items[i].getText(4), items[i].getText(5), items[i].getText(6)};  
	                         items[i].dispose();  
	                         TableItem item = new TableItem(table, SWT.NONE, j);  
	                         item.setText(values);  
	                         items = table.getItems();
	                         break;
	                     } 
	                     
	                 }  
	             }  
	             
	             if(table.getSortColumn() == column && table.getSortDirection() == SWT.UP)
	             {
	            	 table.setSortDirection(SWT.DOWN);
	             }
	             else
	             {
	            	 table.setSortDirection(SWT.UP);
	             }
	             table.setSortColumn(column);
	         }  
	     }; 
		
		//Create the columns of the table
		for(int i = 0; i < tableColumnNames.length; i++)
		{
			column = new TableColumn(table, SWT.NULL);
			column.setText(tableColumnNames[i]);
			column.setWidth(tableWidths[i]);
			column.addListener(SWT.Selection, sortListener);  
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = table.getColumn(0);
		column.setResizable(false);
	}
	
	/**
	 * @return Gets the names of the columns in each table
	 * 		   NOTE: ID should be column 0
	 */
	protected abstract String[] getTableColumnNames();
	
	/**
	 * @return Gets the widths of each table
	 * 		   NOTE: ID should be column 0
	 */
	protected abstract int[] getTableWidths();
	
	/**
	 * Populates the tables with the appropriate items (contracts, clients, and services)
	 */
	protected abstract void populateTable();
	
	/**
	 * View a summary of the financial details of all the contracts
	 */
	protected abstract void viewContractList();
	
	/**
	 * View a summary of the financial details of all the services
	 */
	protected abstract void viewServiceList();
	
	/**
	 * View a summary of the financial details of all the clients
	 */
	protected abstract void viewClientList();
	
	/**
	 * View the financial details of the selected item through on of the analysis windows
	 */
	protected abstract void viewSelectedItem();
	
	/**
	 * Add information received from surveys
	 */
	protected abstract void addSurveyInfo();
}

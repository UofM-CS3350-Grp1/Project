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

/**
 * Handles the drawing of table based Storable object screens. Each subclass
 * is responsible for opening its own composites
 */
public abstract class BaseStorableScreenDrawer
{
	protected Composite composite;
	protected Table table;
	protected Composite btnComposite;
	protected Button btnUpdate;
	protected Button btnDelete;
	protected Button btnAdd;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public BaseStorableScreenDrawer( Composite container )
	{		
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
				addNew();
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
	 * Populates the table with the client data
	 */
	protected abstract void populateTable();
	
	/**
	 * Adds a new client through the new client composite
	 */
	protected abstract void addNew();
	
	/**
	 * Edits a new client through the edit client composite
	 */
	protected abstract void editSelectedItem();
	
	/**
	 * Deletes the selected item in the table
	 */
	protected abstract void deleteSelectedItem();
}

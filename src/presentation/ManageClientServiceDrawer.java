package presentation;

import objects.FeatureHistory;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessFeatureHistory;

public class ManageClientServiceDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "ID", "Period", "Value", "Notes" };
	private static final int[] tableWidths = { 0, 150, 150, 300 };
	private ProcessFeatureHistory processHistory;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public ManageClientServiceDrawer(Composite container)
	{
		super(container);
		
		btnAdd.setText("New Record");
		btnUpdate.setText("Edit Record");
		btnDelete.setText("Remove Record");
		
		//We do not need the view button but we will need to relocate 
		//the edit and delete buttons
		btnView.setVisible(false);
		btnView.setEnabled(false);
		btnDelete.setLocation(btnUpdate.getLocation());
		btnUpdate.setLocation(btnView.getLocation());
		
		if(processHistory == null)
			processHistory = new ProcessFeatureHistory();	    
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		FeatureHistory history = null;
		TableItem item;
		
		table.removeAll();
		
		if(processHistory == null)
			processHistory = new ProcessFeatureHistory();
		
		//Populate our features
		while((history = processHistory.getNextHistory()) != null)
		{
			item = new TableItem(table, SWT.NULL);
			
			item.setText(0, history.getID() + "");			
			item.setText(1, history.getDate().toString());
			item.setText(2, history.getValue() + "");
			item.setText(3, history.getNotes());
		}
	}
	
	/**
	 * Adds a new history object
	 */
	protected void addNew()
	{
		/*Composite addHistoryScreen = SwitchScreen.getContentContainer();
		new AddFeatureHistoryDrawer(addHistoryScreen);
		SwitchScreen.switchContent(addHistoryScreen);*/
	}
	
	/**
	 * Edits a new client through the edit client composite
	 */
	protected void editSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		FeatureHistory history;
		
		if(selectedIndex != -1)
		{
			history = processHistory.getHistoryByID(Integer.parseInt(table.getItem(selectedIndex).getText(0)));
			if(history != null)
			{
				Composite editFeatureScreen = SwitchScreen.getContentContainer();
				new UpdateFeatureHistoryDrawer(editFeatureScreen, history);
				SwitchScreen.switchContent(editFeatureScreen);
			}
		}
	}
	
	/**
	 * View the selected history
	 */
	protected void viewSelectedItem() { }
	
	/**
	 * Deletes the selected item in the table
	 */
	protected void deleteSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		FeatureHistory history;
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
					history = processHistory.getHistoryByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(history != null)
						processHistory.delete(history);
					
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

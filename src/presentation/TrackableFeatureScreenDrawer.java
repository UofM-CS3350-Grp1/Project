package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.TrackedFeature;
import objects.TrackedFeatureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessAddFeature;

/**
 * Responsible for drawing the features screen. This is for any feature that
 * a service wants to track.
 */
public class TrackableFeatureScreenDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "ID", "Feature Name", "Notes" };
	private static final int[] tableWidths = { 0, 150, 300 };
	private ProcessAddFeature processFeature;
	
	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public TrackableFeatureScreenDrawer( Composite container )
	{
		super(container);
		
		btnAdd.setText("New Feature");
		btnUpdate.setText("Edit Feature");
		btnDelete.setText("Cancel Feature");
		
		//We do not need the view button but we will need to relocate 
		//the edit and delete buttons
		btnView.setVisible(false);
		btnView.setEnabled(false);
		btnDelete.setLocation(btnUpdate.getLocation());
		btnUpdate.setLocation(btnView.getLocation());
		
		if(processFeature == null)
			processFeature = new ProcessAddFeature();	    
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		ArrayList<TrackedFeatureType> featureList = null;
		TrackedFeatureType feature = null;
		TableItem item;
		
		table.removeAll();
		
		if(processFeature == null)
			processFeature = new ProcessAddFeature();
		
		featureList = processFeature.getFeatureTypes();
		
		if(featureList!=null)
		{
			Iterator<TrackedFeatureType> it = featureList.iterator();
			
			
			//Populate our features
			while(it.hasNext())
			{
				feature = it.next();
				item = new TableItem(table, SWT.NULL);
				
				item.setText(0, feature.getID() + "");
				item.setText(1, feature.getID() + "");
				item.setText(2, feature.getTitle());
			}
		}
	}
	
	/**
	 * Adds a new client through the new client composite
	 */
	protected void addNew()
	{
		Composite addFeatureScreen = SwitchScreen.getContentContainer();
		new AddTrackableFeatureDrawer(addFeatureScreen);
		SwitchScreen.switchContent(addFeatureScreen);
	}
	
	/**
	 * Edits a new client through the edit client composite
	 */
	protected void editSelectedItem()
	{
		int selectedIndex = table.getSelectionIndex();
		TrackedFeature feature;
		
		if(selectedIndex != -1)
		{
			feature = processFeature.getFeatureByID(Integer.parseInt(table.getItem(selectedIndex).getText(0)));
			if(feature != null)
			{
				Composite editFeatureScreen = SwitchScreen.getContentContainer();
				new UpdateTrackableFeatureDrawer(editFeatureScreen, feature);
				SwitchScreen.switchContent(editFeatureScreen);
			}
		}
	}
	
	/**
	 * View the selected trackable feature
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
		TrackedFeature feature;
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
					feature = processFeature.getFeatureByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(feature != null)
						processFeature.delete(feature);
					
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

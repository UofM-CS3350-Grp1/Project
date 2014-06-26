package presentation;

import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import business.ProcessAddFeature;

public class TrackFeaturesTableDrawer
{
	private static final String[] featureNames = { "ID", "Feature Name" };
	private static final int[] featureWidths = { 0, 300 };
	
	private Table featuresTable;
	private Composite featureButtonComposite;
	private Service service;
	private ProcessAddFeature processFeature;
	
	public TrackFeaturesTableDrawer(Composite composite, Service service) throws IllegalArgumentException
	{
		Composite featureDataComposite = new Composite(composite, SWT.NONE);
		featureDataComposite.setLayout(new GridLayout(1, false));
		GridData gd_featureDataComposite = new GridData(GridData.FILL_BOTH);
		gd_featureDataComposite.heightHint = 273;
		gd_featureDataComposite.widthHint = 319;
		featureDataComposite.setLayoutData(gd_featureDataComposite);
		
		assert (service != null);
		if(service != null)
			this.service = service;
		else
			throw new IllegalArgumentException();
		
		processFeature = new ProcessAddFeature();
		
		Label lblFeatures = new Label(featureDataComposite, SWT.NONE);
		lblFeatures.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblFeatures.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblFeatures.setText("Tracked Features");
		
		featuresTable = new Table(featureDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_featuresTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_featuresTable.heightHint = 200;
		featuresTable.setLayoutData(gd_featuresTable);
		featuresTable.setHeaderVisible(true);
		featuresTable.setLinesVisible(true);
		
		featureButtonComposite = new Composite(featureDataComposite, SWT.NONE);
		featureButtonComposite.setLayout(new GridLayout(3, false));
		GridData gd_featureButtonComposite = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
		gd_featureButtonComposite.heightHint = 44;
		gd_featureButtonComposite.widthHint = 215;
		featureButtonComposite.setLayoutData(gd_featureButtonComposite);
		
		Button btnNewFeature = new Button(featureButtonComposite, SWT.NONE);
		btnNewFeature.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				addFeature();
			}
		});
		btnNewFeature.setText("Track new feature");
		
		Button btnCancelFeature = new Button(featureButtonComposite, SWT.NONE);
		btnCancelFeature.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				removeFeature();
			}
		});
		btnCancelFeature.setText("Cancel feature");
		new Label(featureButtonComposite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		populateTable();
	}
	
	/**
	 * Populates the table with all of the features related to the service
	 */
	private void populateTable()
	{
		TrackedFeature feature = null;		
		TableItem item;
		TableColumn column;	
		
		for(int i = 0; i < featureNames.length; i++)
		{
			column = new TableColumn(featuresTable, SWT.NULL);
			column.setText(featureNames[i]);
			column.setWidth(featureWidths[i]);
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = featuresTable.getColumn(0);
		column.setResizable(false);
		
		while((feature = processFeature.getNextFeatureForService(service)) != null)
		{
			item = new TableItem(featuresTable, SWT.NULL);
			
			item.setText(0, feature.getID() + "");
			item.setText(1, feature.getFeatureName());
		}
	}
	
	/**
	 * Adds a feature to the service
	 */
	private void addFeature()
	{
		Composite addFeature = SwitchScreen.getContentContainer();
		new AddFeatureToServiceDrawer(addFeature, service);
		SwitchScreen.switchContent(addFeature);	
	}
	
	/**
	 * Removes a feature from the service
	 */
	private void removeFeature()
	{
		int selectedIndex = featuresTable.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		TableItem selectedItem;
		
		if(selectedIndex != -1)
		{
			selectedItem = featuresTable.getItem(selectedIndex);
			
			//Ensure that the user actually wants to delete the item
			dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
			dialog.setText("Confirmation");
			dialog.setMessage("Are you sure you want to delete " + selectedItem.getText(1) + "?");
			
			buttonID = dialog.open();
			switch(buttonID)
			{
				case SWT.YES:
					if(processFeature.removeTrackedFeatureFromService(service, processFeature.getFeatureByID(Integer.parseInt(selectedItem.getText(0)))))
						featuresTable.remove(selectedIndex);
					
					break;
					
				case SWT.NO:
					break;
			}
		}
	}
}

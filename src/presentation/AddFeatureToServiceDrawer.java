package presentation;

import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import business.ProcessAddFeature;

/**
 * Draws the interface to add features to services
 */
public class AddFeatureToServiceDrawer
{
	private Composite composite;
	private Service service;
	private ProcessAddFeature processFeature;
	private Combo featureCombo;
	
	/**
	 * Creates a new composite to show the add feature list
	 * @param container	The parent composite
	 * @param service	The service to add to
	 * @throws IllegalArgumentException
	 */
	public AddFeatureToServiceDrawer(Composite container, Service service) throws IllegalArgumentException
	{		
		composite = new Composite( container, SWT.NONE );
		composite.setLayout(new GridLayout(2, false));
		
		if(service != null)
			this.service = service;
		else 
			throw new IllegalArgumentException();
		
		processFeature = new ProcessAddFeature();
		
		Label lblSelectNewFeature = new Label(composite, SWT.NONE);
		lblSelectNewFeature.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectNewFeature.setText("Select new feature to track");
		
		featureCombo = new Combo(composite, SWT.NONE);
		featureCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compositeButton = new Composite(composite, SWT.NONE);
		GridData gd_compositeButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_compositeButton.widthHint = 437;
		compositeButton.setLayoutData(gd_compositeButton);
		
		Button btnAdd = new Button(compositeButton, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				processAddNew();
			}
		});
		btnAdd.setBounds(10, 29, 75, 25);
		btnAdd.setText("Add");
		
		Button btnCancel = new Button(compositeButton, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				goBackToPreviousScreen();
			}
		});
		btnCancel.setBounds(91, 29, 75, 25);
		btnCancel.setText("Cancel");
		
		populateComboBox();
	}
	
	/**
	 * Populates the combo box with feature objects
	 */
	private void populateComboBox()
	{
		TrackedFeature feature = null;
		
		while((feature = processFeature.getNextFeature()) != null)
		{
			featureCombo.add(feature.getFeatureName());
			featureCombo.setData(feature.getFeatureName(), feature);
		}
	}
	
	/**
	 * Adds a new feature to the service
	 */
	private void processAddNew()
	{
		int index;
		TrackedFeature feature;
		
		//Ensure that our form data is valid
		if((index = featureCombo.getSelectionIndex()) != -1)
		{
			feature = (TrackedFeature) featureCombo.getData(featureCombo.getItem(index));
			if(feature != null)
			{
				//Finally add the service to the client
				if(processFeature.addTrackedFeatureToService(service, feature))
					goBackToPreviousScreen();
			}
		}
	}
	
	/**
	 * Goes back to the previous screen
	 */
	private void goBackToPreviousScreen()
	{
		Composite servicePerformanceScreen = SwitchScreen.getContentContainer();
		new PerformanceServiceScreenDrawer(servicePerformanceScreen, service);
		SwitchScreen.switchContent(servicePerformanceScreen);
	}
}

package presentation;

import objects.TrackedFeatureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import acceptanceTests.Register;

import business.ProcessAddFeature;

/**
 * Responsible for drawing the add feature interface. Allows users
 * to add features that they wish to track.
 */
public class AddTrackableFeatureDrawer
{
	protected Composite composite;	
	protected Text txtName;
	protected Button btnAction;
	protected Composite buttonComposite;
	protected Button btnCancel;
	protected ProcessAddFeature processAddFeature;
	
	@SuppressWarnings("unused")
	private Shell shell;
	
	/**
	 * Adds a given tracked feature
	 * @param container 	The composite
	 */
	public AddTrackableFeatureDrawer( Composite container )
	{
		Register.newWindow(this);
		processAddFeature = new ProcessAddFeature();
		
		composite = new Composite( container, SWT.BORDER );
		
		// organizes the component
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		composite.setLayout( compositeLayout );
		
		// organizes how components look within composites
		GridData componentTweaker = null;		
		
		// Name
		Label lblName = new Label( composite, SWT.None );
		lblName.setText( "Name" );
		
		txtName = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		txtName.setLayoutData( componentTweaker );
		
		buttonComposite = new Composite(composite, SWT.NONE);
		GridData gd_buttonComposite = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_buttonComposite.heightHint = 48;
		gd_buttonComposite.widthHint = 155;
		buttonComposite.setLayoutData(gd_buttonComposite);
		
		btnAction = new Button(buttonComposite, SWT.NONE);
		btnAction.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processActionButton();				
			}
		});
		btnAction.setBounds(10, 10, 64, 25);
		btnAction.setText("Add");
		
		btnCancel = new Button(buttonComposite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				backToPreviousScreen();
			}
		});
		btnCancel.setText("Cancel");
		btnCancel.setBounds(80, 10, 64, 25);
	}
	
	/**
	 * Processes an action on button press
	 */
	protected void processActionButton()
	{
		TrackedFeatureType featureType;
		MessageBox dialog;
		
		try
		{
			featureType = new TrackedFeatureType(txtName.getText());
			
			if(processAddFeature.insert(featureType))
				backToPreviousScreen();
		}
		catch(Exception e) 
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Could not add feature");
			dialog.setMessage("Could not add a new feature. Please check the data and try again.");
			dialog.open();
		}	
	}
	
	/**
	 * Go back to the previous screen
	 */
	protected void backToPreviousScreen()
	{
		Composite featureScreen = SwitchScreen.getContentContainer();
		new TrackableFeatureScreenDrawer( featureScreen );
		SwitchScreen.switchContent( featureScreen );
	}
}

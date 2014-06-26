package presentation;

import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import business.ProcessAddFeature;

/**
 * Responsible for drawing the add feature interface. Allows users
 * to add features that they wish to track.
 */
public class AddTrackableFeatureDrawer
{
	protected Composite composite;	
	protected Text txtName;
	protected Text txtNotes;
	protected Button btnAction;
	protected Composite buttonComposite;
	protected Button btnCancel;
	protected ProcessAddFeature processAddFeature;
	protected GridData gd_txtNotes;
	
	/**
	 * Adds a given tracked feature
	 * @param container 	The composite
	 */
	public AddTrackableFeatureDrawer( Composite container )
	{
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
		
		// Notes
		Label lblNotes = new Label( composite, SWT.None );
		lblNotes.setText( "Additional Details" );
		new Label(composite, SWT.NONE);
		
		txtNotes = new Text( composite, SWT.BORDER );
		gd_txtNotes = new GridData( GridData.FILL_BOTH );
		gd_txtNotes.horizontalSpan = 2;
		txtNotes.setLayoutData( gd_txtNotes );		
		
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
		TrackedFeature feature;
		MessageBox dialog;
		
		try
		{
			feature = new TrackedFeature(txtName.getText(), txtNotes.getText());
			
			if(processAddFeature.insert(feature))
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

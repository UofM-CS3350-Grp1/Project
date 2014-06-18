package presentation;

import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import business.ProcessAddFeature;

public class UpdateTrackableFeatureDrawer
{
	private Composite composite;	
	
	private Text txtName;
	private Text txtNotes;
	private Button btnAction;
	private Composite buttonComposite;
	private Button btnCancel;
	private ProcessAddFeature processAddFeature;
	private TrackedFeature feature;
	private GridData gd_txtNotes;
		
	/**
	 * Updates a given tracked feature
	 * @param container 	The composite
	 *
	 * NOTE: Because the window builder will NOT recognize the constructor
	 * or garbage error message 'no entry point found' if a second parameter
	 * is set. So call setFeature() after construction
	 */
	public UpdateTrackableFeatureDrawer( Composite container ) 
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
		
		Text txtNotes = new Text( composite, SWT.BORDER );
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
		btnAction.setText("Update");
		
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
	 * @param feature the feature to set
	 */
	public void setFeature(TrackedFeature feature)
	{
		assert (feature != null);
		if(feature != null)
			this.feature = feature;
	}
	
	/**
	 * Processes an action on button press
	 */
	protected void processActionButton()
	{		
		if(feature != null)
		{
			feature.setFeatureName(txtName.getText());
			feature.setNotes(txtNotes.getText());
			
			processAddFeature.updateFeature(feature);
			backToPreviousScreen();
		}
	}
	
	/**
	 * Go back to the previous screen
	 */
	protected void backToPreviousScreen()
	{
		//TODO Go back to previous screen
	}
}

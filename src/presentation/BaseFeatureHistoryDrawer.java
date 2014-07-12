package presentation;

import business.ValidateTextbox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;


/**
 * Manages the adding of data for features at a given time period
 */
public abstract class BaseFeatureHistoryDrawer
{
	protected Composite composite;
	protected Text txtNotes;
	protected Button btnAction;
	protected Composite buttonComposite;
	protected Button btnCancel;
	protected GridData gd_txtNotes;
	protected Label lblValue;
	protected Text txtValue;
	protected Label lblDatePeriod;
	protected DateTime dateTime;
	
	/**
	 * Adds a given tracked feature
	 * @param container 	The composite
	 */
	public BaseFeatureHistoryDrawer( Composite container )
	{		
		composite = new Composite( container, SWT.BORDER );
		
		// organizes the component
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		composite.setLayout( compositeLayout );
		
		lblValue = new Label(composite, SWT.NONE);
		lblValue.setText("Value");
		
		txtValue = new Text(composite, SWT.BORDER);
		txtValue.addVerifyListener(new VerifyListener() 
		{
			public void verifyText(VerifyEvent event) 
			{
				ValidateTextbox.verifyMonetaryValue(event);
			}
		});
		txtValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblDatePeriod = new Label(composite, SWT.NONE);
		lblDatePeriod.setText("Date Period");
		
		dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		// Notes
		Label lblNotes = new Label( composite, SWT.None );
		lblNotes.setText( "Additional Details" );
		new Label(composite, SWT.NONE);
		
		txtNotes = new Text( composite, SWT.BORDER | SWT.MULTI );
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
	protected abstract void processActionButton();
	
	/**
	 * Go back to the previous screen
	 */
	protected abstract void backToPreviousScreen();
}

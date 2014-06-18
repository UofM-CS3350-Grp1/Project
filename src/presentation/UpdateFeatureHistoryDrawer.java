package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import business.ProcessFeatureHistory;
import objects.FeatureHistory;

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

/**
 * Manages the adding of data for features at a given time period
 */
public class UpdateFeatureHistoryDrawer
{
	private Composite composite;
	private Text txtNotes;
	private Button btnAction;
	private Composite buttonComposite;
	private Button btnCancel;
	private ProcessFeatureHistory processFeatureHistory;
	private GridData gd_txtNotes;
	private Label lblValue;
	private Text txtValue;
	private Label lblDatePeriod;
	private DateTime dateTime;
	private FeatureHistory history;
	
	/**
	 * Adds a given tracked feature
	 * @param container 	The composite
	 * 
	 * NOTE: Because the window builder will NOT recognize the constructor
	 * [due to a garbage error message 'no entry point found'] if a second parameter
	 * is set. So call setupUpdateFeature() after construction
	 */
	public UpdateFeatureHistoryDrawer( Composite container )
	{
		processFeatureHistory = new ProcessFeatureHistory();
		
		composite = new Composite( container, SWT.BORDER );
		
		// organizes the component
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		composite.setLayout( compositeLayout );
		
		lblValue = new Label(composite, SWT.NONE);
		lblValue.setText("Value");
		
		txtValue = new Text(composite, SWT.BORDER);
		txtValue.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		lblDatePeriod = new Label(composite, SWT.NONE);
		lblDatePeriod.setText("Date Period");
		
		dateTime = new DateTime(composite, SWT.BORDER);
		dateTime.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
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
	 * Finishes the setup for updating feature history
	 * @param history			The history object to update
	 */
	public void setupUpdateFeature(FeatureHistory history)
	{
		assert (history != null);
		if(history != null)
		{
			this.history = history;
			
			populateFields();
		}
	}
	
	/**
	 * Processes an action on button press
	 */
	private void processActionButton()
	{
		Date date;
		SimpleDateFormat formatter;
		
		try
		{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(dateTime.getDay() + "/" + dateTime.getMonth() + "/" + dateTime.getYear());
			
			history.setValue(Integer.parseInt(txtValue.getText()));
			history.setDate(date);
			history.setNotes(txtNotes.getText());
			
			if(processFeatureHistory.updateFeatureHistory(history))
				backToPreviousScreen();	
		}
		catch(Exception e) {}	
	}
	
	/**
	 * Populates the fields
	 */
	private void populateFields()
	{
		if(history != null)
		{
			txtValue.setText("" + history.getValue());
			dateTime.setDate(history.getDate().getYear(), history.getDate().getMonth(), history.getDate().getDay());
			txtNotes.setText(history.getNotes());
		}
	}
	
	/**
	 * Go back to the previous screen
	 */
	private void backToPreviousScreen()
	{
		//TODO Go back to previous screen
	}
}

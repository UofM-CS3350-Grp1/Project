package presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

import business.ProcessAddFeature;
import business.ProcessClient;

import org.eclipse.swt.widgets.Text;

public class JCCSurveyScreenDrawer
{
	private final String DATE_FORMAT = "yyyy-MM-dd";
	protected Table table;
	protected Composite composite;
	protected Composite btnComposite;
	protected Combo comboClient;
	protected Combo comboFeature;
	protected Combo comboMonth;
	protected Combo comboYear;
	private Label lblSelectClient;
	private Label lblSelectFeature;
	private Label lblSelectMonth;
	private Text txtValue;
	private Text txtDetails;
	private Button btnSave;
	private Button btnCancel;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public JCCSurveyScreenDrawer( Composite container )
	{		
		composite = new Composite( container, SWT.NONE );
		composite.setLayout(new GridLayout(3, false));

		Composite btnSurvey = new Composite(composite, SWT.NONE);
		GridData gd_btnSurvey = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_btnSurvey.widthHint = 456;
		gd_btnSurvey.heightHint = 400;
		btnSurvey.setLayoutData(gd_btnSurvey);

		new Label(composite, SWT.NONE);

		comboClient = new Combo(btnSurvey, SWT.READ_ONLY);
		comboClient.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		comboClient.setBounds(104, 0, 96, 23);

		// Fills the drop down with client business names
		Client client = null;
		ProcessClient processClient = new ProcessClient();
		while((client = processClient.getNextClient()) != null)
		{
			comboClient.add(client.getBusinessName());
		}

		new Label(composite, SWT.NONE);

		comboFeature = new Combo(btnSurvey, SWT.READ_ONLY);
		comboFeature.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		comboFeature.setBounds(104, 40, 96, 23);
		
		fillComboFeature(comboFeature);

		// Fill the Month drop down
		comboMonth = new Combo(btnSurvey, SWT.READ_ONLY);
		for (int i = 1; i < 13; i++)
		{
			comboMonth.add(""+i+"");
		}

		comboMonth.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		comboMonth.setBounds(104, 80, 35, 24);

		// Fill the Year drop down
		comboYear = new Combo(btnSurvey, SWT.READ_ONLY);
		for (int i = 2014; i < 2021; i++)
		{
			comboYear.add(""+i+"");
		}

		comboYear.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		comboYear.setBounds(140, 80, 60, 24);

		lblSelectClient = new Label(btnSurvey, SWT.NONE);
		lblSelectClient.setBounds(21, 8, 77, 15);
		lblSelectClient.setText("Select Client");

		lblSelectFeature = new Label(btnSurvey, SWT.NONE);
		lblSelectFeature.setBounds(21, 43, 76, 15);
		lblSelectFeature.setText("Select Feature");

		lblSelectMonth = new Label(btnSurvey, SWT.NONE);
		lblSelectMonth.setText("Select Date");
		lblSelectMonth.setBounds(21, 83, 75, 15);

		txtValue = new Text(btnSurvey, SWT.BORDER);
		txtValue.setBounds(104, 129, 76, 21);

		Label lblValue = new Label(btnSurvey, SWT.NONE);
		lblValue.setBounds(52, 132, 45, 15);
		lblValue.setText("Value");

		txtDetails = new Text(btnSurvey, SWT.BORDER);
		txtDetails.setBounds(104, 176, 342, 104);

		Label lblDetails = new Label(btnSurvey, SWT.NONE);
		lblDetails.setBounds(52, 179, 45, 15);
		lblDetails.setText("Details");

		btnSave = new Button(btnSurvey, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				addSurvey();
			}
		});
		btnSave.setBounds(119, 312, 75, 25);
		btnSave.setText("Add");

		btnCancel = new Button(btnSurvey, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				goBackToJCCScreen();
			}
		});
		btnCancel.setBounds(274, 312, 75, 25);
		btnCancel.setText("Cancel");

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
	}

	/**
	 * Saves the recorded survey information
	 */
	private void addSurvey()
	{
		ProcessClient processClient;
		Client client;
		ProcessAddFeature processFeature;
		ArrayList<TrackedFeatureType> featureList;
		TrackedFeatureType featureType;
		boolean inserted = false;
		TrackedFeature feature;

		if (isFormDataValid())
		{
			SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
			Date date = new Date();
			String theDate = comboYear.getText() + "-" + comboMonth.getText() + "-01";

			try
			{
				date = formatter.parse(theDate);

				processClient = new ProcessClient();
				client = processClient.getClientByBusinessName(comboClient.getText());
				
				if(client != null)
				{
					processFeature = new ProcessAddFeature();					
					featureList = processFeature.getFeatureTypeByTitle(comboFeature.getText());
					featureType = null;
					
					if(featureList != null && featureList.size() >= 1)
					{
						featureType = featureList.get(0);
												
						feature = new TrackedFeature(txtDetails.getText(), client.getID(), featureType, date, Double.parseDouble(txtValue.getText()));						
						inserted = processFeature.insert(feature);

						if(inserted)
							goBackToJCCScreen();
						else
							displayMessageBox("Survey Information", "There was an error processing the survey");
					}
				}
			}
			catch(Exception e)
			{
				displayMessageBox("Survey Info", "Form validation error");
				System.out.println("Exception caught: " + e);
			}
		}
		else
		{
			displayMessageBox("Form validation error", "Please fill out all fields");
		}
	}
	
	/**
	 * Displays a message box with a message
	 * @param title		The title of the message box
	 * @param message	The message to show
	 */
	private void displayMessageBox(String title, String message)
	{
		MessageBox dialog;
		
		if(title == null || (title != null && title.isEmpty()))
			title = "Processing Error";
		
		if(message == null || (message != null && message.isEmpty()))
			message = "Error processing request";
		
		dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
		dialog.setText(title);
		dialog.setMessage(message);
		dialog.open();
	}

	/**
	 * Fills the combo box with the available features to add data to
	 * @param comboFeature	The combo box
	 */
	public void fillComboFeature(Combo comboFeature)
	{
		ProcessAddFeature processFeature = new ProcessAddFeature();
		TrackedFeatureType feature = null;
		ArrayList<TrackedFeatureType> featureList;
		comboFeature.removeAll();
		featureList = processFeature.getFeatureTypes();
		
		if(featureList!=null)
		{
			Iterator<TrackedFeatureType> it = featureList.iterator();
			
			while(it.hasNext())
			{
				feature = it.next();
				comboFeature.add(feature.getTitle());
			}
		}
	}

	/**
	 * Go back to the main JCC page
	 */
	private void goBackToJCCScreen()
	{
		Composite jccContractList = SwitchScreen.getContentContainer();
		new JCCContractScreenDrawer( jccContractList );
		SwitchScreen.switchContent( jccContractList );
	}

	/**
	 * Checks if the form fields are valid
	 * @return True if valid
	 */
	protected boolean isFormDataValid()
	{
		boolean isValid = true;

		// check if the fields have something in them
		isValid = (comboClient.getText() != "");
		if (isValid) isValid = (comboFeature.getText() != "");
		if (isValid) isValid = (comboMonth.getText() != "");
		if (isValid) isValid = (comboYear.getText() != "");
		if (isValid) isValid = (txtValue.getText() != "");

		return isValid;
	}
}

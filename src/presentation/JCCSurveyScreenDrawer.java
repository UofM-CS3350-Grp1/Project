package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.Client;
import objects.FeatureHistory;

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

import business.ProcessClient;
import business.ProcessFeatureHistory;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;

public class JCCSurveyScreenDrawer
{
		protected Table table;
		protected Composite composite;
		protected Composite btnComposite;
		protected Combo comboClient;
		protected Combo comboFeature;
		private DateTime dateField;
		private Label lblSelectClient;
		private Label lblSelectFeature;
		private Label lblSelectMonth;
		private Text txtValue;
		private Text txtDetails;
		private Button btnSave;
		private Button btnCancel;

		/*
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
			comboClient.setBounds(104, 0, 90, 23);

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
			comboFeature.setBounds(104, 40, 90, 23);

			// Fills the drop down with tracking features
			FeatureHistory feature = null;
			ProcessFeatureHistory processFeature = new ProcessFeatureHistory();
			while((feature = processFeature.getNextHistory()) != null)
			{
				comboFeature.add(feature.getFeature().getFeatureName());
			}

			lblSelectClient = new Label(btnSurvey, SWT.NONE);
			lblSelectClient.setBounds(21, 8, 76, 15);
			lblSelectClient.setText("Select Client");

			lblSelectFeature = new Label(btnSurvey, SWT.NONE);
			lblSelectFeature.setBounds(10, 43, 88, 15);
			lblSelectFeature.setText("Select Feature");

			lblSelectMonth = new Label(btnSurvey, SWT.NONE);
			lblSelectMonth.setText("Select Date");
			lblSelectMonth.setBounds(20, 86, 65, 15);

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
			btnSave.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					addSurvey();
				}
			});
			btnSave.setBounds(119, 312, 75, 25);
			btnSave.setText("Add");

			btnCancel = new Button(btnSurvey, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					goBackToJCCScreen();
				}
			});
			btnCancel.setBounds(274, 312, 75, 25);
			btnCancel.setText("Cancel");

			dateField = new DateTime(btnSurvey, SWT.BORDER);
			dateField.setBounds(104, 77, 80, 24);

			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
		}

		/*
		 * Saves the recorded survey information
		 */
		private void addSurvey() 
		{
			MessageBox dialog;

/*
			Combo comboClient;
			Combo comboFeature;
			DateTime dateField;
			Text txtValue;
			Text txtDetails;
*/

			if (isFormDataValid())
			{
				dialog = new MessageBox(new Shell(), SWT.ICON_INFORMATION | SWT.OK);
				dialog.setText("Survey Info");
				dialog.setMessage("comboClient: " + comboClient.getText() + "\ncomboFeature: " + comboFeature.getText() + "\ndateField: " + dateField.toString() + "\ntxtValue: " + txtValue.getText() + "\ntxtDetails: " + txtDetails.getText());
				dialog.open();

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				try 
				{
					Date date = new Date();
					date = formatter.parse(dateField.getMonth() + "/" + dateField.getYear());
				}
				catch(Exception e)
				{

				}

				goBackToJCCScreen();
			}
			else
			{
				dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
				dialog.setText("Form validation error");
				dialog.setMessage("Please fill out all fields");
				dialog.open();				
			}
		}

		/*
		 * Go back to the main JCC page
		 */
		private void goBackToJCCScreen()
		{
			Composite jccContractList = SwitchScreen.getContentContainer();
			new JCCContractScreenDrawer( jccContractList );
			SwitchScreen.switchContent( jccContractList );
		}

		protected boolean isFormDataValid()
		{
			boolean isValid = true;

			// check if the fields have something in them
			isValid = (comboClient.getText() != "");
			if (isValid) isValid = (comboFeature.getText() != "");
			if (isValid) isValid = (dateField.getMonth() != 0);  // Fix validation for dateField (DateTime)
			if (isValid) isValid = (txtValue.getText() != "");
			// if (isValid) isValid = (txtDetails.getText() != ""); // Is details a required field?

			return isValid;
		}	
}

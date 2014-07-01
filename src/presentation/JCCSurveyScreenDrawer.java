package presentation;

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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Label;

import business.ProcessClient;
import business.ProcessFeatureHistory;

import org.eclipse.swt.widgets.Text;

public class JCCSurveyScreenDrawer
{
		protected Composite composite;
		protected Table table;
		protected Composite btnComposite;
		protected Combo comboClient;
		private Label lblSelectClient;
		private Label lblSelectFeature;
		private Text value;
		private Text details;
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
			
			//fills the dropdown with client business names
			Client client = null;
			ProcessClient processClient = new ProcessClient();
			while((client = processClient.getNextClient()) != null)
			{
				comboClient.add(client.getBusinessName());
			}
			
			new Label(composite, SWT.NONE);

			Combo comboFeature = new Combo(btnSurvey, SWT.READ_ONLY);
			comboFeature.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
			comboFeature.setBounds(104, 40, 90, 23);
			
			lblSelectClient = new Label(btnSurvey, SWT.NONE);
			lblSelectClient.setBounds(21, 8, 76, 15);
			lblSelectClient.setText("Select Client");
			
			lblSelectFeature = new Label(btnSurvey, SWT.NONE);
			lblSelectFeature.setBounds(10, 43, 88, 15);
			lblSelectFeature.setText("Select Feature");
			
			Combo comboMonth = new Combo(btnSurvey, SWT.READ_ONLY);
			comboMonth.setItems(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"});
			comboMonth.setBounds(104, 78, 90, 23);
			
			Label lblSelectMonth = new Label(btnSurvey, SWT.NONE);
			lblSelectMonth.setText("Select Month");
			lblSelectMonth.setBounds(10, 86, 88, 15);
			
			value = new Text(btnSurvey, SWT.BORDER);
			value.setBounds(104, 129, 76, 21);
			
			Label lblValue = new Label(btnSurvey, SWT.NONE);
			lblValue.setBounds(52, 132, 45, 15);
			lblValue.setText("Value");
			
			details = new Text(btnSurvey, SWT.BORDER);
			details.setBounds(104, 176, 342, 104);
			
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
			btnSave.setText("SAVE");
			
			btnCancel = new Button(btnSurvey, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					goBackToJCCScreen();
				}
			});
			btnCancel.setBounds(274, 312, 75, 25);
			btnCancel.setText("CANCEL");
			
			//fills the dropdown with client business names
			FeatureHistory feature = null;
			ProcessFeatureHistory processFeature = new ProcessFeatureHistory();
			while((feature = processFeature.getNextHistory()) != null)
			{
				comboFeature.add(feature.getFeature().getFeatureName());
			}
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
		}

		/*
		 * Saves the recorded survey information
		 */
		private void addSurvey() 
		{
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
}

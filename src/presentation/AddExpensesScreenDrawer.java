package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
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
import business.ProcessContract;
import business.ProcessFeatureHistory;

import org.eclipse.swt.widgets.Text;

public class AddExpensesScreenDrawer
{
		protected Composite composite;
		protected Table table;
		protected Composite btnComposite;
		protected Combo comboClient;
		private Label lblSelectClient;
		private Text value;
		private Text details;
		private Button btnSave;
		private Button btnCancel;
		private Label lblSelectContract;
		private Combo comboService;
		private Label lblNewLabel;
		private Combo comboContract;
		private Label lblCompanysupplier;
		private Text text;
		
		/*
		 * Call the constructor with a shell's main component as <container>
		 * and it will be added to that component;
		 */
		public AddExpensesScreenDrawer( Composite container )
		{		
			composite = new Composite( container, SWT.NONE );
			composite.setLayout(new GridLayout(3, false));

			Composite btnSurvey = new Composite(composite, SWT.NONE);
			GridData gd_btnSurvey = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
			gd_btnSurvey.widthHint = 480;
			gd_btnSurvey.heightHint = 420;
			btnSurvey.setLayoutData(gd_btnSurvey);

			comboClient = new Combo(btnSurvey, SWT.READ_ONLY);
			comboClient.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
			comboClient.setBounds(141, 8, 90, 23);
			
			//fills the dropdown with client business names
			Client client = null;
			ProcessClient processClient = new ProcessClient();
			while((client = processClient.getNextClient()) != null)
			{
				comboClient.add(client.getBusinessName());
			}
			
			ActionListener clientActionListener = new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String selected = comboClient.getText();
					setContracts(selected);
				}
				
			};
			
			new Label(composite, SWT.NONE);
			
			lblSelectClient = new Label(btnSurvey, SWT.NONE);
			lblSelectClient.setBounds(52, 11, 76, 15);
			lblSelectClient.setText("Select Client");
			
			value = new Text(btnSurvey, SWT.BORDER);
			value.setBounds(140, 195, 76, 21);
			
			Label lblValue = new Label(btnSurvey, SWT.NONE);
			lblValue.setBounds(64, 198, 45, 15);
			lblValue.setText("Amount");
			
			details = new Text(btnSurvey, SWT.BORDER);
			details.setBounds(138, 242, 342, 104);
			
			Label lblDetails = new Label(btnSurvey, SWT.NONE);
			lblDetails.setBounds(64, 245, 45, 15);
			lblDetails.setText("Details");
			
			btnSave = new Button(btnSurvey, SWT.NONE);
			btnSave.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					addSurvey();
				}
			});
			btnSave.setBounds(141, 378, 75, 25);
			btnSave.setText("SAVE");
			
			btnCancel = new Button(btnSurvey, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					goBackToJCCScreen();
				}
			});
			btnCancel.setBounds(296, 378, 75, 25);
			btnCancel.setText("CANCEL");
			
			lblSelectContract = new Label(btnSurvey, SWT.NONE);
			lblSelectContract.setBounds(37, 51, 90, 15);
			lblSelectContract.setText("Select Contract");
			
			comboService = new Combo(btnSurvey, SWT.READ_ONLY);
			comboService.setBounds(141, 90, 90, 23);
			
			lblNewLabel = new Label(btnSurvey, SWT.NONE);
			lblNewLabel.setBounds(40, 93, 76, 15);
			lblNewLabel.setText("Select Service");
			
			comboContract = new Combo(btnSurvey, SWT.READ_ONLY);
			comboContract.setBounds(141, 48, 90, 23);
			
			lblCompanysupplier = new Label(btnSurvey, SWT.NONE);
			lblCompanysupplier.setBounds(10, 137, 106, 15);
			lblCompanysupplier.setText("Company/Supplier");
			
			text = new Text(btnSurvey, SWT.BORDER);
			text.setBounds(141, 137, 305, 21);
			
			//fills the dropdown with client business names
			FeatureHistory feature = null;
			ProcessFeatureHistory processFeature = new ProcessFeatureHistory();
			while((feature = processFeature.getNextHistory()) != null)
			{
				Combo comboFeature = null;
				comboFeature.add(feature.getFeature().getFeatureName());
			}
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
			new Label(composite, SWT.NONE);
		}
		
		/*
		 * fills the dropdown with contracts of the selected client
		 */
		private void setContracts(String business)
		{
			Contract contract = null;
			ProcessContract processContract = new ProcessContract();
			ProcessClient processClient = new ProcessClient();
			Client client = processClient.getClientByBusinessName(business);
			ArrayList<Contract> contractList = processContract.getContractsByClient(client);
			Iterator<Contract> it = contractList.iterator();
			while(it.hasNext())
			{
				contract = it.next();
				comboContract.add(String.valueOf(contract.getID()));
			}
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

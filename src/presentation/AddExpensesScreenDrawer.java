package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.FeatureHistory;
import objects.Service;
import objects.TrackedFeature;
import objects.TrackedFeatureType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import business.ProcessService;

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
		private Text supplier;
		
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
			
			SelectionListener clientActionListener = new SelectionListener()
			{
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) 
				{
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) 
				{
					String selected = comboClient.getText();
					setContracts(selected);
				}
				
			};
			comboClient.addSelectionListener(clientActionListener);
			
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
					addSurvey((double)Integer.parseInt(value.getText()), details.getText());
				}
			});
			btnSave.setBounds(141, 378, 75, 25);
			btnSave.setText("Add");
			
			btnCancel = new Button(btnSurvey, SWT.NONE);
			btnCancel.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					goBackToJCCScreen();
				}
			});
			btnCancel.setBounds(296, 378, 75, 25);
			btnCancel.setText("Cancel");
			
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
			
			SelectionListener contractActionListener = new SelectionListener()
			{
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) 
				{
				}

				@Override
				public void widgetSelected(SelectionEvent arg0) 
				{
					String selected = comboContract.getText();
					setServices(selected);
				}
				
			};
			comboContract.addSelectionListener(contractActionListener);
			
			lblCompanysupplier = new Label(btnSurvey, SWT.NONE);
			lblCompanysupplier.setBounds(10, 137, 106, 15);
			lblCompanysupplier.setText("Company/Supplier");
			
			supplier = new Text(btnSurvey, SWT.BORDER);
			supplier.setBounds(141, 137, 305, 21);
			
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
			ArrayList<Contract> contractList = null;
			contractList = processContract.getContractsByClient(client);
			Iterator<Contract> it = contractList.iterator();
			comboContract.removeAll();
			while(it.hasNext())
			{
				contract = it.next();
				comboContract.add(String.valueOf(contract.getID()));
			}
		}
		
		/*
		 * fills the dropdown with services of the selected contract
		 */
		private void setServices(String contractID)
		{
			Service service = null;
			ProcessContract processContract = new ProcessContract();
			Contract contract = processContract.getContractByID(Integer.parseInt(contractID));
			ArrayList<Service> serviceList = null;
			serviceList = processContract.getServices(contract);
			Iterator<Service> it = serviceList.iterator();
			comboService.removeAll();
			while(it.hasNext())
			{
				service = it.next();
				comboService.add(String.valueOf(service.getTitle()));
			}
		}

		/*
		 * Saves the recorded survey information
		 */
		private void addSurvey(double value, String details) 
		{
			TrackedFeatureType featureType = new TrackedFeatureType("Expense type", "Expense title");
			TrackedFeature trackedFeature = new TrackedFeature("Expense", featureType);
			Client client;
			ProcessClient processClient = new ProcessClient();
			client = processClient.getClientByBusinessName(comboClient.getText());
			Date date = new Date();
			FeatureHistory history = new FeatureHistory(trackedFeature, client, value, date);
			ProcessFeatureHistory processHistory = new ProcessFeatureHistory();
			processHistory.insert(history);
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

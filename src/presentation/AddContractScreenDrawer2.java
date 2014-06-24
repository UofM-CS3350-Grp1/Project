package presentation;

import java.util.ArrayList;
import java.util.Date;

import objects.Client;
import objects.Contract;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;

public class AddContractScreenDrawer2 
{

	protected Composite composite;

	protected ProcessClient processClient;
	protected ProcessService processService;
	private Table table;
	private TableColumn column;		
	private Table table_1;
	protected TableItem item;
	private Label label;
	private Combo combo;
	private Label lblStart;
	private Label lblEnd;
	private Text inputDetails;
	private DateTime startData;
	
	public AddContractScreenDrawer2(Composite container) 
	{
		composite = new Composite(container, SWT.BORDER);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(24, 112, 165, 204);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		column = new TableColumn(table, SWT.NULL);
		column.setText("ID");
		column.setWidth(0);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Service");
		column.setWidth(110);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Rate");
		column.setWidth(50);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(320, 112, 334, 204);

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("ID");

		tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Contract");

		TableColumn tableColumn2 = new TableColumn(table_1, SWT.NONE);
		tableColumn2.setWidth(60);
		tableColumn2.setText("Rate");

		TableColumn tableColumn3 = new TableColumn(table_1, SWT.NONE);
		tableColumn3.setWidth(160);
		tableColumn3.setText("Details");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addSelectedItem();
			}
		});
		btnAdd.setBounds(221, 167, 75, 25);
		btnAdd.setText("ADD >>");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				removeSelectedItem();
			}
		});
		button.setBounds(221, 246, 75, 25);
		button.setText("<< REMOVE");
		
		label = new Label(composite, SWT.NONE);
		label.setText("Select Business");
		label.setBounds(24, 13, 79, 15);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(115, 10, 90, 23);
		
		Label lblStatus = new Label(composite, SWT.NONE);
		lblStatus.setBounds(24, 47, 55, 15);
		lblStatus.setText("Status");
		
		Combo comboStatus = new Combo(composite, SWT.READ_ONLY);
		comboStatus.setBounds(115, 39, 90, 23);
		comboStatus.add("Pending");
		comboStatus.add("Signed");
		comboStatus.add("Cancelled");
		comboStatus.add("Terminated");
		
		lblStart = new Label(composite, SWT.NONE);
		lblStart.setBounds(272, 13, 55, 15);
		lblStart.setText("Start Date");
		
		lblEnd = new Label(composite, SWT.NONE);
		lblEnd.setBounds(272, 47, 55, 15);
		lblEnd.setText("End Date");
		
		startData = new DateTime(composite, SWT.BORDER);
		startData.setBounds(333, 9, 80, 24);
		
		DateTime endData = new DateTime(composite, SWT.BORDER);
		endData.setBounds(333, 47, 80, 24);
		
		Label lblAvailableServices = new Label(composite, SWT.NONE);
		lblAvailableServices.setBounds(56, 91, 103, 15);
		lblAvailableServices.setText("Available Services");
		
		Label lblServicesOnThe = new Label(composite, SWT.NONE);
		lblServicesOnThe.setBounds(425, 91, 135, 15);
		lblServicesOnThe.setText("Services on the contract");
		
		Label lblAdditionalDetails = new Label(composite, SWT.NONE);
		lblAdditionalDetails.setBounds(56, 340, 103, 15);
		lblAdditionalDetails.setText("Additional details");
		
		inputDetails = new Text(composite, SWT.BORDER);
		inputDetails.setBounds(24, 361, 630, 102);
		
		Button btnCreate = new Button(composite, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				createContract();
			}
		});
		btnCreate.setBounds(221, 469, 75, 25);
		btnCreate.setText("CREATE");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(347, 469, 75, 25);
		btnCancel.setText("CANCEL");
		
		processClient = new ProcessClient();
		processService = new ProcessService();
		
		//fills the dropdown with client business names
		Client client = null;
		int i = 0;
		while((client = processClient.getNextClient()) != null)
		{
			combo.add(client.getBusinessName());
			i++;
		}
		
		populateTable();
	}
	
	/*
	 * creates contract
	 * issue: not saving/attaching services to contract
	 */
	@SuppressWarnings("null")
	private void createContract()
	{
		double value = 0;
		int items = table_1.getItemCount();
		
		for(int i=0; i<items; i++)
		{
			value += (double)Integer.parseInt(table_1.getItem(i).getText());
		}
		
		Contract contract = null;
		Date date = new Date();
		contract = new Contract(combo.getText(), inputDetails.getText(), value, date); //Date-DateTime issue
		
		ProcessContract processContract = new ProcessContract();
		processContract.insert(contract);
		
		int totalNumServices = table_1.getItemCount();
		ArrayList<Service> services = new ArrayList<Service>();;
		Service service = null;
		int id = 0;
		
		for(int i=0; i<totalNumServices; i++)
		{
			id = Integer.parseInt(table_1.getItem(i).getText());
			ProcessService processService = new ProcessService();
			service = processService.getServiceByID(id);
			if(service!=null)
			{
				services.add(i, service);
			}
		}
		
		processContract.setServices(contract, services);
		processContract.update(contract);
		
		backToContractsScreen();
	}
	
	/*
	 * after successfully creating contract, go back to main contract page
	 */
	private void backToContractsScreen()
	{
		Composite contractScreen = SwitchScreen.getContentContainer();
		new ContractScreenDrawer( contractScreen );
		SwitchScreen.switchContent( contractScreen );
	}

	/*
	 * adds a service to the contract
	 */
	private void addSelectedItem() 
	{
		int selectedIndex = table.getSelectionIndex();
		int id = Integer.parseInt(table.getItem(selectedIndex).getText());
		ProcessService processService = new ProcessService();
		Service service = processService.getServiceByID(id);

		if(selectedIndex != -1)
		{
			item = new TableItem(table_1, SWT.NULL);

			item.setText(0, String.valueOf(service.getID()));
			item.setText(1, service.getTitle());
			item.setText(2, String.valueOf(service.getRate()));
			item.setText(3, service.getDescription());

			table.remove(selectedIndex);
		}
	}

	/*
	 * removes a service from the contract
	 */
	private void removeSelectedItem() 
	{
		int selectedIndex = table_1.getSelectionIndex();
		int id = Integer.parseInt(table_1.getItem(selectedIndex).getText());
		ProcessService processService = new ProcessService();
		Service service = processService.getServiceByID(id);

		if(selectedIndex != -1)
		{
			item = new TableItem(table, SWT.NULL);

			item.setText(0, String.valueOf(service.getID()));
			item.setText(1, service.getTitle());
			item.setText(2, String.valueOf(service.getRate()));

			table_1.remove(selectedIndex);
		}
	}

	/*
	 * populates the services table with all existing services
	 */
	protected void populateTable() 
	{
		Service service = null;
		
		table.removeAll();
		
		if(processService == null)
			processService = new ProcessService();
		
		while((service = processService.getNextService()) != null)
		{
			item = new TableItem(table, SWT.NULL);

			item.setText(0, String.valueOf(service.getID()));
			item.setText(1, service.getTitle());
			item.setText(2, service.getRate() + "");
		}
		
	}
}

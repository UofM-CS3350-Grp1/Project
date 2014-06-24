package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;

public class UpdateContractScreenDrawer {

	protected Composite composite;
	private Table serviceTable;
	private Table contractTable;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	private TableColumn tableColumn_4;
	private TableItem item;

	private Label lblStatus;
	private Label lblStatusData;
	private Label lblEnd;
	private Label lblEndData;
	private Label lblSigned;
	private Label lblSignedData;
	private Label lblClient;
	private Label lblClientData;
	private Label lblValueData;
	
	private Client client;
	private Contract contract;
	private Service service;
	private ProcessContract processContract;
	private ProcessClient processClient;
	private ProcessService processService;
	private Label lblAvailableServices;
	private Label lblContractServices;
	
	public UpdateContractScreenDrawer(Composite container, Contract contract, Client client)
	{
		this.client = client;
		this.contract = contract;
		
		composite = new Composite(container, SWT.BORDER);
		
		lblStatus = new Label(composite, SWT.NONE);
		lblStatus.setBounds(299, 10, 55, 15);
		lblStatus.setText("Status:");
		
		lblStatusData = new Label(composite, SWT.NONE);
		lblStatusData.setBounds(390, 10, 55, 15);
		lblStatusData.setText("New Label");
		
		lblEnd = new Label(composite, SWT.NONE);
		lblEnd.setBounds(299, 31, 55, 15);
		lblEnd.setText("End date:");
		
		lblEndData = new Label(composite, SWT.NONE);
		lblEndData.setBounds(390, 31, 55, 15);
		lblEndData.setText("New Label");
		
		lblSigned = new Label(composite, SWT.NONE);
		lblSigned.setBounds(299, 52, 73, 15);
		lblSigned.setText("Signed Date:");
		
		lblSignedData = new Label(composite, SWT.NONE);
		lblSignedData.setBounds(390, 52, 55, 15);
		lblSignedData.setText("New Label");
		
		serviceTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		serviceTable.setLinesVisible(true);
		serviceTable.setHeaderVisible(true);
		serviceTable.setBounds(10, 108, 165, 204);
		
		tableColumn = new TableColumn(serviceTable, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Service");
		
		tableColumn_1 = new TableColumn(serviceTable, SWT.NONE);
		tableColumn_1.setWidth(50);
		tableColumn_1.setText("Rate");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addSelectedItem();
			}
		});
		button.setText("ADD >>");
		button.setBounds(217, 145, 75, 25);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				removeSelectedItem();
			}
		});
		button_1.setText("<< REMOVE");
		button_1.setBounds(217, 224, 75, 25);
		
		contractTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		contractTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		contractTable.setLinesVisible(true);
		contractTable.setHeaderVisible(true);
		contractTable.setBounds(330, 108, 335, 204);

		tableColumn_2 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_2.setWidth(110);
		tableColumn_2.setText("Service");

		tableColumn_3 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_3.setWidth(60);
		tableColumn_3.setText("Rate");

		tableColumn_4 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_4.setWidth(160);
		tableColumn_4.setText("Details");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(295, 318, 75, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite contractScreenDrawer = SwitchScreen.getContentContainer();
				new ContractScreenDrawer( contractScreenDrawer );
				SwitchScreen.switchContent( contractScreenDrawer );
			}
		});
		
		Button btnUpdate = new Button(composite, SWT.NONE);
		btnUpdate.setBounds(123, 318, 75, 25);
		btnUpdate.setText("Update");
		
		lblClient = new Label(composite, SWT.NONE);
		lblClient.setBounds(22, 10, 55, 15);
		lblClient.setText("Client:");
		

		lblClientData = new Label(composite, SWT.NONE);
		lblClientData.setBounds(105, 10, 165, 15);
		lblClientData.setText("New Label");
		
		Label lblValue = new Label(composite, SWT.NONE);
		lblValue.setBounds(22, 31, 55, 15);
		lblValue.setText("Value:");
		
		lblValueData = new Label(composite, SWT.NONE);
		lblValueData.setBounds(105, 31, 55, 15);
		lblValueData.setText("New Label");
		
		lblAvailableServices = new Label(composite, SWT.NONE);
		lblAvailableServices.setBounds(44, 87, 103, 15);
		lblAvailableServices.setText("Available Services");
		
		lblContractServices = new Label(composite, SWT.NONE);
		lblContractServices.setBounds(441, 87, 103, 15);
		lblContractServices.setText("Contract Services");

		populateContractFields();
		populateContractTable();
		populateServiceFields();
		populateServiceTable();
	}
	
	
	/*
	 * populates the the left table with all available services
	 */
	public void populateServiceTable()
	{
		Service service = null;
		
		serviceTable.removeAll();
		
		if(processService == null)
		{
			processService = new ProcessService();
		}
		
		while((service = processService.getNextService()) != null)
		{
			item = new TableItem(serviceTable, SWT.NULL);
			
			item.setText(0, service.getTitle());
			item.setText(1, service.getRate() + "");
		}
	}
	
	/*
	 * populates the right table with all services currently in the contract
	 */
	public void populateContractTable() 
	{
		Service service = null;
		ArrayList<Service> serviceList;
		contractTable.removeAll();
/*
		serviceList = processContract.getServices(contract);
		Iterator it = serviceList.iterator();
		
		while(it.hasNext())
		{
			service = (Service) it.next();
			
			item = new TableItem(contractTable, SWT.NULL);
			item.setText(0, service.getTitle());
			//item.setText(1, service.getRate() + "");
		}
*/
	}
	
	/*
	 * adds the selected service to the contract
	 */
	public void addSelectedItem()
	{
		int selectedIndex = serviceTable.getSelectionIndex();
		Service service = processService.getServiceByID(selectedIndex);
		if(selectedIndex != -1)
		{
			item = new TableItem(contractTable, SWT.NULL);

			item.setText(0, serviceTable.getItem(selectedIndex).getText());
			item.setText(1, serviceTable.getItem(selectedIndex).getText());
			item.setText(2, service.getDescription());

			serviceTable.remove(selectedIndex);
		}
	}
	
	/*
	 * Removes a service from the contract
	 */
	public void removeSelectedItem()
	{
		int selectedIndex = contractTable.getSelectionIndex();

		if(selectedIndex != -1)
		{
			item = new TableItem(serviceTable, SWT.NULL);

			item.setText(0, contractTable.getItem(selectedIndex).getText());
			item.setText(1, serviceTable.getItem(selectedIndex).getText());

			contractTable.remove(selectedIndex);
		}
	}

	/*
	 * This populates all of the contract-related fields
	 */
	public void populateContractFields()
	{
		//lblClientNameData.setText(client.getBusinessName());
		lblValueData.setText(String.valueOf(contract.getValue()));
		lblStatusData.setText("NULL");
		lblEndData.setText("NULL");
		lblSignedData.setText("NULL");
	}
	
	/*
	 * This populates the service fields
	 */
	public void populateServiceFields()
	{
		lblClientData.setText(client.getBusinessName());
	}
}

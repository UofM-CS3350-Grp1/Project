package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.Client;
import objects.Contract;
import objects.Service;
import objects.ServiceType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessContract;
import business.ProcessService;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Combo;

/**
 * Displays the edit contract window.
 */
public class UpdateContractScreenDrawer
{
	private ScrolledComposite scrollComposite;
	protected Composite composite;
	private Table serviceTable;
	private Table contractTable;
	private TableColumn tableColumn;
	private TableColumn tableColumn_1;
	private TableColumn tableColumn_2;
	private TableColumn tableColumn_3;
	private TableColumn tableColumn_4;
	private TableItem item;
	private Label lblEnd;
	private Label lblSigned;
	private Label lblClient;
	private Label lblClientData;
	private Label lblValueData;
	
	private Client client;
	private Contract contract;
	private ProcessService processService;
	private Label lblAvailableServices;
	private Text inputDetails;
	private Label label;
	private Label label_1;
	private DateTime startDate;
	private DateTime endDate;
	private Label label_2;
	private Combo combo;
	
	/**
	 * Creates a new update contract window
	 * @param container	The parent composite
	 * @param contract	The contract to edit
	 * @param client	The client to work with
	 */
	public UpdateContractScreenDrawer(Composite container, Contract contract, Client client) throws IllegalArgumentException
	{
		assert (client != null && contract != null);
		if(client != null && contract != null)
		{
			this.client = client;
			this.contract = contract;
		}
		else
			throw new IllegalArgumentException();
		
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		composite = new Composite(scrollComposite, SWT.BORDER);
		scrollComposite.setContent(composite);
		
		lblEnd = new Label(composite, SWT.NONE);
		lblEnd.setBounds(272, 49, 55, 15);
		lblEnd.setText("End date:");
		
		lblSigned = new Label(composite, SWT.NONE);
		lblSigned.setBounds(270, 19, 57, 15);
		lblSigned.setText("Start Date:");
		
		serviceTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		serviceTable.setLinesVisible(true);
		serviceTable.setHeaderVisible(true);
		serviceTable.setBounds(22, 108, 286, 204);

		tableColumn = new TableColumn(serviceTable, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("ID");
		
		tableColumn = new TableColumn(serviceTable, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Service");
		
		tableColumn_1 = new TableColumn(serviceTable, SWT.NONE);
		tableColumn_1.setWidth(70);
		tableColumn_1.setText("Rate");
		
		tableColumn_1 = new TableColumn(serviceTable, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("Category");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				addSelectedItem();
			}
		});
		button.setText("ADD >>");
		button.setBounds(338, 146, 75, 25);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				removeSelectedItem();
			}
		});
		button_1.setText("<< REMOVE");
		button_1.setBounds(338, 225, 75, 25);
		
		contractTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		contractTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		contractTable.setLinesVisible(true);
		contractTable.setHeaderVisible(true);
		contractTable.setBounds(443, 108, 446, 204);

		tableColumn_2 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_2.setWidth(0);
		tableColumn_2.setText("ID");

		tableColumn_2 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_2.setWidth(110);
		tableColumn_2.setText("Service");

		tableColumn_3 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_3.setWidth(70);
		tableColumn_3.setText("Rate");

		tableColumn_3 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Category");

		tableColumn_4 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_4.setWidth(160);
		tableColumn_4.setText("Details");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.setBounds(483, 517, 75, 25);
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
		btnUpdate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				processActionButton();
			}
		});
		btnUpdate.setBounds(311, 517, 75, 25);
		btnUpdate.setText("Update");
		
		lblClient = new Label(composite, SWT.NONE);
		lblClient.setBounds(22, 19, 55, 15);
		lblClient.setText("Client:");
		

		lblClientData = new Label(composite, SWT.NONE);
		lblClientData.setBounds(105, 19, 140, 15);
		lblClientData.setText("New Label");
		
		Label lblValue = new Label(composite, SWT.NONE);
		lblValue.setBounds(487, 19, 44, 15);
		lblValue.setText("Value:");
		
		lblValueData = new Label(composite, SWT.NONE);
		lblValueData.setBounds(548, 19, 55, 15);
		lblValueData.setText("New Label");
		
		lblAvailableServices = new Label(composite, SWT.NONE);
		lblAvailableServices.setBounds(44, 87, 103, 15);
		lblAvailableServices.setText("Available Services");
		
		inputDetails = new Text(composite, SWT.BORDER);
		inputDetails.setBounds(22, 359, 867, 142);
		
		label = new Label(composite, SWT.NONE);
		label.setText("Additional details");
		label.setBounds(54, 338, 103, 15);
		
		label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Services on the contract");
		label_1.setBounds(465, 87, 135, 15);
		
		startDate = new DateTime(composite, SWT.BORDER);
		startDate.setBounds(333, 10, 80, 24);
		
		endDate = new DateTime(composite, SWT.BORDER);
		endDate.setBounds(333, 40, 80, 24);
		
		label_2 = new Label(composite, SWT.NONE);
		label_2.setText("Status");
		label_2.setBounds(23, 44, 55, 15);
		
		combo = new Combo(composite, SWT.READ_ONLY);
		combo.setBounds(108, 41, 90, 23);
		combo.add("Pending");
		combo.add("Signed");
		combo.add("Cancelled");
		combo.add("Terminated");

		populateContractFields();
		populateContractTable();
		populateServiceFields();
		populateServiceTable();
		
		scrollComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
	}

	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		MessageBox dialog;
		double value = computeContractValue();
		ProcessService processService = new ProcessService();
		Service newService = null;
		Date date;
		ProcessContract processContract = new ProcessContract();
		SimpleDateFormat formatter;
		int cID, id, totalNumServices;
		Service service = null;
		
		try
		{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(endDate.getDay() + "/" + endDate.getMonth() + "/" + endDate.getYear());
			
			//Update the contract's actual data
			contract.setBusinessName(combo.getText());
			contract.setDetails(inputDetails.getText());
			contract.setValue(value);
			contract.setPeriod(date);
			
			if(processContract.update(contract))
			{				
				totalNumServices = serviceTable.getItemCount();	

				//Add/ update the services in the services list
				for(int i = 0; i < totalNumServices; i++)
				{
					id = Integer.parseInt(serviceTable.getItem(i).getText(0));
					service = processService.getServiceByID(id);
					ServiceType serviceType;
					
					if(service != null)
					{
						serviceType = new ServiceType(service.getServiceType().getType(), service.getDescription());
						newService = new Service(service.getTitle(), service.getDescription(), service.getRate(), serviceType);
						cID = contract.getID();
						newService.setContractID(cID);
						
						if(!processService.update(newService))
						{
							//The service does not already exist. Add it!
							processService.insert(newService);
						}
					}
				}
				
				goBackToContractScreen();
			}
		}
		catch(Exception e)
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Could not update contract");
			dialog.setMessage("Could not update the contract. Please check the data and try again.");
			dialog.open();
		}
	}
	
	/**
	 * Goes back to the contract screen
	 */
	protected void goBackToContractScreen()
	{
		Composite contractScreen = SwitchScreen.getContentContainer();
		new ContractScreenDrawer( contractScreen );
		SwitchScreen.switchContent( contractScreen );
	}
	
	/**
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

			item.setText(0, String.valueOf(service.getID()));
			item.setText(1, service.getTitle());
			item.setText(2, service.getRate() + "");
			item.setText(3, service.getServiceType().getType());
		}
	}
	
	/**
	 * populates the right table with all services currently in the contract
	 */
	public void populateContractTable() 
	{
		Service service = null;
		contractTable.removeAll();
		ProcessService processService = new ProcessService();
		
		while((service = processService.getNextService())!=null)
		{
			if(service.getContractID() == contract.getID())
			{
				item = new TableItem(contractTable, SWT.NULL);
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getTitle());
				item.setText(2, String.valueOf(service.getRate()));
				item.setText(1, service.getServiceType().getType());
				item.setText(1, service.getDescription());
			}
		}
		
		inputDetails.setText(contract.getDetails());
	}
	
	/**
	 * Adds the selected service to the contract
	 */
	public void addSelectedItem()
	{
		int selectedIndex = serviceTable.getSelectionIndex();
		
		if(selectedIndex != -1)
		{
			TableItem select = serviceTable.getItem(selectedIndex);
			int x = Integer.parseInt(select.getText(0));
			Service service = processService.getServiceByID(x);
			
			if(service != null)
			{
				item = new TableItem(contractTable, SWT.NULL);
	
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getTitle());
				item.setText(2, String.valueOf(service.getRate()));
				item.setText(3, service.getServiceType().getType());
				item.setText(4, service.getDescription());
	
				serviceTable.remove(selectedIndex);
				lblValueData.setText(String.format("$%8.2f", computeContractValue()));
			}
		}
	}
	
	/**
	 * Removes a service from the contract
	 */
	public void removeSelectedItem()
	{
		int selectedIndex = contractTable.getSelectionIndex();
		
		if(selectedIndex != -1)
		{
			TableItem select = contractTable.getItem(selectedIndex);
			int x = Integer.parseInt(select.getText(0));
			Service service = processService.getServiceByID(x);

			if(service != null)
			{
				item = new TableItem(serviceTable, SWT.NULL);
	
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getTitle());
				item.setText(2, String.valueOf(service.getRate()));
				item.setText(3, service.getServiceType().getType());
	
				contractTable.remove(selectedIndex);
				lblValueData.setText(String.format("$%8.2f", computeContractValue()));
			}
		}
	}

	/**
	 * This populates all of the contract-related fields
	 */
	public void populateContractFields()
	{
		//lblClientNameData.setText(client.getBusinessName());
		lblValueData.setText(String.format("$%8.2f", contract.getValue()));
	}
	
	/**
	 * This populates the service fields
	 */
	public void populateServiceFields()
	{
		lblClientData.setText(client.getBusinessName());
	}
	
	/**
	 * Computes the value of a contract given the added services
	 * @return The contract value
	 */
	private double computeContractValue()
	{
		double value = 0.0;
		int items = serviceTable.getItemCount();
		
		for(int i=0; i<items; i++)
		{
			int z = (serviceTable.getItem(i).getText(2)).length()-2;
			value += Double.parseDouble((serviceTable.getItem(i).getText(2)).substring(0, z));
		}
		
		return value;
	}
}

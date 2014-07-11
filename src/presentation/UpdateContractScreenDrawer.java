package presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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

import business.DateTimeUtil;
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
	private TableColumn tableColumn_2;
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
	private Text text;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private String[] months = {"null", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String[] years = {"2013", "2014", "2015", "2016", "2017", "2018"};
	
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
		scrollComposite.getVerticalBar().setIncrement(15);
		
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
		serviceTable.setBounds(22, 108, 116, 204);

		tableColumn = new TableColumn(serviceTable, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("ID");
		
		tableColumn = new TableColumn(serviceTable, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Service");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				addSelectedItem();
			}
		});
		button.setText("Add >>");
		button.setBounds(162, 146, 75, 25);
		
		Button button_1 = new Button(composite, SWT.NONE);
		button_1.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				removeSelectedItem();
			}
		});
		button_1.setText("<< Remove");
		button_1.setBounds(162, 225, 75, 25);
		
		contractTable = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		contractTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		contractTable.setLinesVisible(true);
		contractTable.setHeaderVisible(true);
		contractTable.setBounds(267, 108, 276, 204);

		tableColumn_2 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_2.setWidth(0);
		tableColumn_2.setText("ID");

		tableColumn_2 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_2.setWidth(110);
		tableColumn_2.setText("Service");

		tableColumn_4 = new TableColumn(contractTable, SWT.NONE);
		tableColumn_4.setWidth(160);
		tableColumn_4.setText("Details");

		text = new Text(composite, SWT.BORDER);
		text.setBounds(549, 132, 76, 21);
		Label lblPrice = new Label(composite, SWT.NONE);
		lblPrice.setBounds(569, 111, 34, 15);
		lblPrice.setText("Rate");
		
		
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
		label_1.setBounds(311, 87, 135, 15);
		
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

	/*
	 * Add rate fields as necessary
	 */
	public void addValueField(int number)
	{
		switch(number)
		{
		case 1:
			break;
		case 2:
			text_2 = new Text(composite, SWT.BORDER);
			text_2.setBounds(549, 152, 76, 21);
			break;
		case 3:
			text_3 = new Text(composite, SWT.BORDER);
			text_3.setBounds(549, 171, 76, 21);
			break;
		case 4:
			text_4 = new Text(composite, SWT.BORDER);
			text_4.setBounds(549, 190, 76, 21);
			break;
		case 5:
			text_5 = new Text(composite, SWT.BORDER);
			text_5.setBounds(549, 209, 76, 21);
			break;
		case 6:
			text_6 = new Text(composite, SWT.BORDER);
			text_6.setBounds(549, 228, 76, 21);
			break;
		case 7:
			text_7 = new Text(composite, SWT.BORDER);
			text_7.setBounds(549, 248, 76, 21);
			break;
		case 8:
			text_8 = new Text(composite, SWT.BORDER);
			text_8.setBounds(549, 267, 76, 21);
			break;
		case 9:
			text_9 = new Text(composite, SWT.BORDER);
			text_9.setBounds(549, 286, 30, -4);
			break;
		}
	}
	
	/*
	 * @return returns the number of months of the contract
	 */
	public int getMultiplier(Date end, Date start)
	{
		int multiplier = 0;
		int monthStart = 0;
		int yearStart = 0;
		int monthEnd = 0;
		int yearEnd = 0;
		for(int i=1; i<13; i++)
		{
			if(start.toString().contains(months[i]))
			{
				monthStart = i;
				for(int x=0; x<years.length; x++)
				{
					if(start.toString().contains(years[x]))
					{
						yearStart = Integer.parseInt(years[x]);
					}
				}
			}
			if(end.toString().contains(months[i]))
			{
				monthEnd = i;
				for(int x=0; x<years.length; x++)
				{
					if(end.toString().contains(years[x]))
					{
						yearEnd = Integer.parseInt(years[x]);
					}
				}
			}
		}
		if(yearStart!=yearEnd)
		{
			int yearsDifference = yearEnd-yearStart;
			multiplier = 12-monthStart;
			multiplier += monthEnd;
			multiplier += ((yearsDifference*12)-12);
		}else{
			multiplier = monthEnd-monthStart;
		}
		return multiplier;
	}

	/*
	 * Add rate fields as necessary
	 */
	public double getValueField(int number, int bool)
	{
		int multiplier = 1;
		try
		{
			Date dateEnd = DateTimeUtil.getDate(endDate);
			Date dateStart = DateTimeUtil.getDate(startDate);
			
			multiplier = getMultiplier(dateEnd, dateStart);
		}
		catch(Exception e)
		{
			System.out.println("Error with dates!!");
		}
		
		if(contractTable.getItem(number-1).getText(1)=="Web Design")
		{
			multiplier = 1;
		}
		
		if(bool==0)
		{
			multiplier = 1;
		}
		
		switch(number)
		{
			case 1:
				return Double.parseDouble(text.getText())*multiplier;
			case 2:
				return Double.parseDouble(text_2.getText())*multiplier;
			case 3:
				return Double.parseDouble(text_3.getText())*multiplier;
			case 4:
				return Double.parseDouble(text_4.getText())*multiplier;
			case 5:
				return Double.parseDouble(text_5.getText())*multiplier;
			case 6:
				return Double.parseDouble(text_6.getText())*multiplier;
			case 7:
				return Double.parseDouble(text_7.getText())*multiplier;
			case 8:
				return Double.parseDouble(text_8.getText())*multiplier;
			case 9:
				return Double.parseDouble(text_9.getText())*multiplier;
		}
		
		return 0;
	}

	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		MessageBox dialog;
		double value = 0;
		ProcessService processService = new ProcessService();
		Service newService = null;
		Date dateEnd, today;
		ProcessContract processContract = new ProcessContract();
		int cID, id, totalNumServices;
		
		try
		{
			dateEnd = DateTimeUtil.getDate(endDate);
			today = new Date();
			totalNumServices = contractTable.getItemCount();	
			System.out.println("formatting date done");

			System.out.println("trying to add up contract value");
			for(int i=1; i<=totalNumServices; i++)
			{
				value += getValueField(i, 1);
			}
			System.out.println("Contract value calculated");

			System.out.println("trying to update contract");
			//Update the contract's actual data
			contract.setDetails(inputDetails.getText());
			contract.setValue(value);
			contract.setPeriod(dateEnd);
			System.out.println("contract updated");
			
			if(combo.getText()=="Signed")
			{
				contract.setSignedDate(today);
			}
			System.out.println("before inserting contract");
			if(processContract.update(contract))
			{				

				System.out.println("after updating contract");
				//Add/ update the services in the services list
				for(int i = 0; i < totalNumServices; i++)
				{
					id = Integer.parseInt(contractTable.getItem(i).getText(0));
					ServiceType serviceType = processService.getServiceTypeByID(id);
					
					
					if(serviceType != null)
					{
						System.out.println("creating service object");
						newService = new Service(serviceType.getType(), serviceType.getDescription(), getValueField(i+1, 0), serviceType);
						cID = contract.getID();
						newService.setContractID(cID);
						System.out.println("service type found. Trying to update service");
						
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
		ServiceType serviceType = null;
		ArrayList<ServiceType> list = null;
		
		serviceTable.removeAll();

		processService = new ProcessService();
		list = processService.getServiceTypes();
		Iterator<ServiceType> it = list.iterator();
		
		while(it.hasNext())
		{
			serviceType = it.next();
			
			item = new TableItem(serviceTable, SWT.NULL);

			item.setText(0, String.valueOf(serviceType.getID()));
			item.setText(1, serviceType.getType());
			item.setText(3, serviceType.getDescription());
		}
	}
	
	/*
	 * populate existing services with their respective values/rates
	 */
	public void setValueField(int number, double value)
	{
		switch(number)
		{
		case 1:
			text.setText(""+value);
			break;
		case 2:
			text_2.setText(""+value);
			break;
		case 3:
			text_3.setText(""+value);
			break;
		case 4:
			text_4.setText(""+value);
			break;
		case 5:
			text_5.setText(""+value);
			break;
		case 6:
			text_6.setText(""+value);
			break;
		case 7:
			text_7.setText(""+value);
			break;
		case 8:
			text_8.setText(""+value);
			break;
		case 9:
			text_9.setText(""+value);
			break;
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
		int i = 0;
		while((service = processService.getNextService())!=null)
		{
			if(service.getContractID() == contract.getID())
			{
				i++;
				addValueField(i);
				setValueField(i, service.getRate());
				item = new TableItem(contractTable, SWT.NULL);
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getServiceType().getType());
				item.setText(2, service.getDescription());
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
			try
			{
				TableItem select = serviceTable.getItem(selectedIndex);
				int x = Integer.parseInt(select.getText(0));
				ServiceType serviceType = processService.getServiceTypeByID(x);
				
				addValueField(contractTable.getItemCount()+1);
				
				if(serviceType != null)
				{
					item = new TableItem(contractTable, SWT.NULL);
		
					item.setText(0, String.valueOf(serviceType.getID()));
					item.setText(1, serviceType.getType());
					item.setText(2, serviceType.getDescription());
				}
			}
			catch(Exception e)
			{
				System.out.println(e);
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
			contractTable.remove(selectedIndex);
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
}

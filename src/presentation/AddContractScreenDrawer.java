package presentation;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Service;
import objects.ServiceType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.DateTime;

/**
 * Handles the drawing and adding of contracts
 */
public class AddContractScreenDrawer 
{
	private ScrolledComposite scrollComposite;
	protected Composite composite;

	protected ProcessClient processClient;
	protected ProcessService processService;
	protected Client selectedClient;
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
	private DateTime endData;
	private String[] months = {"null", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String[] years = {"2013", "2014", "2015", "2016", "2017", "2018"};
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	
	/**
	 * Create a new contract drawer
	 * @param container The parent composite
	 */
	public AddContractScreenDrawer(Composite container) 
	{
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrollComposite.getVerticalBar().setIncrement(15);
		
		composite = new Composite(scrollComposite, SWT.BORDER);
		scrollComposite.setContent(composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(24, 112, 215, 197);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		column = new TableColumn(table, SWT.NULL);
		column.setText("ID");
		column.setWidth(0);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Service");
		column.setWidth(110);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Category");
		column.setWidth(100);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(369, 112, 375, 197);

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("ID");

		tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Service");

		TableColumn tableColumn3 = new TableColumn(table_1, SWT.NONE);
		tableColumn3.setWidth(100);
		tableColumn3.setText("Category");

		TableColumn tableColumn4 = new TableColumn(table_1, SWT.NONE);
		tableColumn4.setWidth(160);
		tableColumn4.setText("Details");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				addSelectedItem();
			}
		});
		btnAdd.setBounds(270, 173, 75, 25);
		btnAdd.setText("Add >>");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				removeSelectedItem();
			}
		});
		button.setBounds(270, 228, 75, 25);
		button.setText("<< Remove");
		
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
		lblStart.setBounds(307, 14, 55, 15);
		lblStart.setText("Start Date");
		
		lblEnd = new Label(composite, SWT.NONE);
		lblEnd.setBounds(307, 48, 55, 15);
		lblEnd.setText("End Date");
		
		startData = new DateTime(composite, SWT.BORDER);
		startData.setBounds(368, 10, 80, 24);
		
		endData = new DateTime(composite, SWT.BORDER);
		endData.setBounds(368, 48, 80, 24);
		
		Label lblAvailableServices = new Label(composite, SWT.NONE);
		lblAvailableServices.setBounds(56, 91, 103, 15);
		lblAvailableServices.setText("Available Services");
		
		Label lblServicesOnThe = new Label(composite, SWT.NONE);
		lblServicesOnThe.setBounds(453, 91, 135, 15);
		lblServicesOnThe.setText("Services on the contract");
		
		Label lblAdditionalDetails = new Label(composite, SWT.NONE);
		lblAdditionalDetails.setBounds(56, 340, 103, 15);
		lblAdditionalDetails.setText("Additional details");
		
		inputDetails = new Text(composite, SWT.BORDER);
		inputDetails.setBounds(24, 361, 867, 142);
		
		Button btnCreate = new Button(composite, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(combo.getText()!=null && combo.getText()!="")
				{
					createContract();
				}
			}
		});
		btnCreate.setBounds(307, 525, 75, 25);
		btnCreate.setText("Create");
		
		Button btnCancel = new Button(composite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				backToContractsScreen();
			}
		});
		btnCancel.setBounds(433, 525, 75, 25);
		btnCancel.setText("Cancel");
				
		processClient = new ProcessClient();
		processService = new ProcessService();
		
		//fills the dropdown with client business names
		Client client = null;
		while((client = processClient.getNextClient()) != null)
		{
			combo.add(client.getBusinessName());
		}
		
		populateTable();
		
		scrollComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
	}
	
	/**
	 * creates contract
	 * issue: not saving/attaching services to contract
	 */
	private void createContract()
	{
		double value = 0;
		ProcessService processService = null;
		Service newService = null;
		MessageBox dialog;	

		ProcessContract processContract = new ProcessContract();
		int newID = processContract.getUnusedContractID();
		Contract contract = null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		try 
		{
			Date end = formatter.parse(endData.getMonth() + "-" + endData.getDay() + "-" + endData.getYear());
			Date start = formatter.parse(startData.getMonth() + "-" + startData.getDay() + "-" + startData.getYear());

			int totalNumServices = table_1.getItemCount();
			
			int multiplier = getMultiplier(end, start);
			
			for(int i=0; i<totalNumServices; i++)
			{
				if(table_1.getItem(i).getText(1).contains("Web Design")) 
				{
					if(multiplier<12)
					{
						multiplier = 12;
					}else{
						multiplier = (int)(multiplier/12);
					}
				}
				if(i==0) value += Double.parseDouble(text.getText())*multiplier;
				if(i==1) value += Double.parseDouble(text_1.getText())*multiplier;
				if(i==2) value += Double.parseDouble(text_2.getText())*multiplier;
				if(i==3) value += Double.parseDouble(text_3.getText())*multiplier;
				if(i==4) value += Double.parseDouble(text_4.getText())*multiplier;
				if(i==5) value += Double.parseDouble(text_5.getText())*multiplier;
				if(i==6) value += Double.parseDouble(text_6.getText())*multiplier;
				if(i==7) value += Double.parseDouble(text_7.getText())*multiplier;
				if(i==8) value += Double.parseDouble(text_8.getText())*multiplier;
				if(i==9) value += Double.parseDouble(text_9.getText())*multiplier;
				multiplier = getMultiplier(end, start);
			}
			contract = new Contract(newID, combo.getText(), inputDetails.getText(), value, end, start, start);
			processContract.insert(contract);
			
			value = 0;

			ServiceType serviceType = null;
			int id = 0;
	
			for(int i=0; i<totalNumServices; i++)
			{
				id = Integer.parseInt(table_1.getItem(i).getText(0));
				processService = new ProcessService();
				serviceType = processService.getServiceTypeByID(id);
				
				selectedClient = processClient.getClientByBusinessName(combo.getText());

				if(i==0) value = Double.parseDouble(text.getText());
				if(i==1) value = Double.parseDouble(text_1.getText());
				if(i==2) value = Double.parseDouble(text_2.getText());
				if(i==3) value = Double.parseDouble(text_3.getText());
				if(i==4) value = Double.parseDouble(text_4.getText());
				if(i==5) value = Double.parseDouble(text_5.getText());
				if(i==6) value = Double.parseDouble(text_6.getText());
				if(i==7) value = Double.parseDouble(text_7.getText());
				if(i==8) value = Double.parseDouble(text_8.getText());
				if(i==9) value = Double.parseDouble(text_9.getText());
				
				newService = new Service(serviceType.getType(), serviceType.getDescription(), value, serviceType);
				
				int cID = contract.getID();
				newService.setContractID(cID);
				newService.setClientID(selectedClient.getID());
				processService.insert(newService);
			}
			
			backToContractsScreen();
		}
		catch(Exception e)
		{
			System.out.println(e);
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Could not add contract");
			dialog.setMessage("Could not create a new contract. Please check the data and try again.");
			dialog.open();
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
	
	/**
	 * after successfully creating contract, go back to main contract page
	 */
	private void backToContractsScreen()
	{
		Composite contractScreen = SwitchScreen.getContentContainer();
		new ContractScreenDrawer( contractScreen );
		SwitchScreen.switchContent( contractScreen );
	}

	/**
	 * adds a service to the contract
	 */
	private void addSelectedItem() 
	{
		int selectedIndex = table.getSelectionIndex();
		
		if(selectedIndex != -1)
		{
			TableItem select = table.getItem(selectedIndex);
			int x = Integer.parseInt(select.getText(0));
			ServiceType servicetype = processService.getServiceTypeByID(x);
			
			if(servicetype != null)
			{
				item = new TableItem(table_1, SWT.NULL);
	
				item.setText(0, String.valueOf(servicetype.getID()));
				item.setText(1, servicetype.getType());
				item.setText(2, String.valueOf(servicetype.getType()));
				item.setText(3, servicetype.getDescription());
	
				table.remove(selectedIndex);
			}
		}
		addValueField(table_1.getItemCount());
	}
	
	public void addValueField(int number)
	{
		switch(number)
		{
		case 1:
			text = new Text(composite, SWT.BORDER);
			text.setBounds(750, 137, 76, 21);
			Label lblPrice = new Label(composite, SWT.NONE);
			lblPrice.setBounds(761, 118, 34, 15);
			lblPrice.setText("Price");
			break;
		case 2:
			text_1 = new Text(composite, SWT.BORDER);
			text_1.setBounds(750, 155, 76, 21);
			break;
		case 3:
			text_2 = new Text(composite, SWT.BORDER);
			text_2.setBounds(750, 175, 76, 21);
			break;
		case 4:
			text_3 = new Text(composite, SWT.BORDER);
			text_3.setBounds(750, 193, 76, 21);
			break;
		case 5:
			text_4 = new Text(composite, SWT.BORDER);
			text_4.setBounds(750, 212, 76, 21);
			break;
		case 6:
			text_5 = new Text(composite, SWT.BORDER);
			text_5.setBounds(750, 251, 76, 21);
			break;
		case 7:
			text_6 = new Text(composite, SWT.BORDER);
			text_6.setBounds(750, 232, 76, 21);
			break;
		case 8:
			text_7 = new Text(composite, SWT.BORDER);
			text_7.setBounds(750, 270, 76, 21);
			break;
		case 9:
			text_8 = new Text(composite, SWT.BORDER);
			text_8.setBounds(750, 295, 30, -4);
			break;
		case 10:
			text_9 = new Text(composite, SWT.BORDER);
			text_9.setBounds(750, 288, 76, 21);
			break;
		}
	}

	/**
	 * removes a service from the contract
	 */
	private void removeSelectedItem() 
	{
		int selectedIndex = table_1.getSelectionIndex();
		
		if(selectedIndex != -1)
		{
			TableItem select = table_1.getItem(selectedIndex);
			int x = Integer.parseInt(select.getText(0));
			Service service = processService.getServiceByID(x);

			if(service != null)
			{
				item = new TableItem(table, SWT.NULL);
	
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getTitle());
				item.setText(2, String.valueOf(service.getServiceType().getType()));
	
				table_1.remove(selectedIndex);
			}
		}
	}

	/**
	 * populates the services table with all existing services
	 */
	protected void populateTable() 
	{
		ArrayList<ServiceType> serviceTypeList = null;
		ServiceType serviceType = null;
		
		table.removeAll();
			
		processService = new ProcessService();
		
		serviceTypeList = processService.getServiceTypes();
		
		if(serviceTypeList!=null)
		{
			Iterator<ServiceType> it = serviceTypeList.iterator();
			
			while(it.hasNext())
			{
				serviceType = it.next();
				
				item = new TableItem(table, SWT.NULL);
	
				item.setText(0, String.valueOf(serviceType.getID()));
				item.setText(1, serviceType.getType());
				item.setText(2, "NA");
				item.setText(3, String.valueOf(serviceType.getType()));
			}
		}
	}
}

package presentation;

import java.text.SimpleDateFormat;
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
	private Label lblValueData;
	private Combo combo;
	private Label lblStart;
	private Label lblEnd;
	private Text inputDetails;
	private DateTime startData;
	private DateTime endData;
	
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
		table.setBounds(24, 112, 284, 204);
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
		column.setWidth(70);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Category");
		column.setWidth(100);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(447, 112, 444, 204);

		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(0);
		tableColumn.setText("ID");

		tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Service");

		TableColumn tableColumn2 = new TableColumn(table_1, SWT.NONE);
		tableColumn2.setWidth(70);
		tableColumn2.setText("Rate");

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
		btnAdd.setBounds(348, 167, 75, 25);
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
		button.setBounds(348, 246, 75, 25);
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
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setText("Value:");
		label_1.setBounds(540, 13, 44, 15);
		
		lblValueData = new Label(composite, SWT.NONE);
		lblValueData.setText(String.format("$%8.2f", 0.00));
		lblValueData.setBounds(590, 13, 55, 15);
				
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
		double value = computeContractValue();
		ProcessService processService = null;
		Service newService = null;
		MessageBox dialog;	

		ProcessContract processContract = new ProcessContract();
		int newID = processContract.getUnusedContractID();
		Contract contract = null;
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try 
		{
			date = formatter.parse(endData.getDay() + "/" + endData.getMonth() + "/" + endData.getYear());

			//---------------------------------------
			//Error here Please add valid start Date
			//---------------------------------------
			contract = new Contract(newID, combo.getText(), inputDetails.getText(), value, date, null, null, date, new Date()); //<---- Change This
			
			processContract.insert(contract);
			
			int totalNumServices = table_1.getItemCount();
			Service service = null;
			int id = 0;
	
			for(int i=0; i<totalNumServices; i++)
			{
				id = Integer.parseInt(table_1.getItem(i).getText(0));
				processService = new ProcessService();
				service = processService.getServiceByID(id);
				
				selectedClient = processClient.getClientByBusinessName(combo.getText());
				
				newService = new Service(service.getTitle(), service.getDescription(), service.getRate(), service.getServiceType());
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
			Service service = processService.getServiceByID(x);
			
			if(service != null)
			{
				item = new TableItem(table_1, SWT.NULL);
	
				item.setText(0, String.valueOf(service.getID()));
				item.setText(1, service.getTitle());
				item.setText(2, String.valueOf(service.getRate()));
				item.setText(3, String.valueOf(service.getServiceType()));
				item.setText(4, service.getDescription());
	
				table.remove(selectedIndex);
				
				lblValueData.setText(String.format("$%8.2f", computeContractValue()));
			}
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
				item.setText(2, String.valueOf(service.getRate()));
				item.setText(3, String.valueOf(service.getServiceType()));
	
				table_1.remove(selectedIndex);
				lblValueData.setText(String.format("$%8.2f", computeContractValue()));
			}
		}
	}

	/**
	 * populates the services table with all existing services
	 */
	protected void populateTable() 
	{
		Service service = null;
		
		table.removeAll();
		
		if(processService == null)
		{
			processService = new ProcessService();
		}
		
		while((service = processService.getNextService()) != null)
		{
			item = new TableItem(table, SWT.NULL);

			item.setText(0, String.valueOf(service.getID()));
			item.setText(1, service.getTitle());
			item.setText(2, service.getRate() + "");
			item.setText(3, String.valueOf(service.getServiceType()));
		}		
	}
	
	/**
	 * Computes the value of a contract given the added services
	 * @return The contract value
	 */
	private double computeContractValue()
	{
		double value = 0.0;
		int items = table_1.getItemCount();
		
		for(int i=0; i<items; i++)
		{
			int z = (table_1.getItem(i).getText(2)).length()-2;
			value += Double.parseDouble((table_1.getItem(i).getText(2)).substring(0, z));
		}
		
		return value;
	}
}

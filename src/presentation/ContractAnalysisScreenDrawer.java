package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;

import business.ProcessClient;
import business.ProcessContract;
import business.ProcessService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;


/**
 * Responsible for drawing the detailed client information including all of the
 * client's data in a read only format, the services that the client uses and
 * the tracking analysis
 */
public class ContractAnalysisScreenDrawer
{
	private ScrolledComposite scrollComposite;
	private Composite composite;
	private Composite buttonComposite;
	private Table servicesTable;
	private Client client;
	private Contract contract;
	private ProcessContract processContract;
	private ProcessClient processClient;
	
	//Client vars
	private Label lblClientNameData;
	private Label lblContractIDData;
	private Label lblClientName2;
	private Label lblEmailData;
	private Label lblSignedData;
	private Label lblAddressData;
	private Label lblValueData;
	private Label lblPhoneNumberData;
	private Label lblStartData;
	private Label lblStatusData;
	private Label lblEndData;
	private Label lblPhoneNumberData2;
	
	//Contract vars
	private Label lblContractIDHeader;
	private Label lblContactData;
	
	private ProcessService processService;
	private Button btnViewSelected;
	private Label lblEnd;
	private Label lblContact;
	private Label lblContact2;
	private Label lblContactData2;
	private Label lblAddress;
	private Label lblPhone;
	private Label lblPhone_1;
	private Label lblEmail_1;
	private Button btnPrint;
	private Text detailsData;
	private Label lblDetails;
	private Button btnSave;
	private Label lblServicesInThis;
	
	/**
	 * Creates a new client analysis screen
	 * @param container 	The composite
	 */
	public ContractAnalysisScreenDrawer(Composite container, Contract contract, Client client) throws IllegalArgumentException 
	{
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		composite = new Composite(scrollComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));	
		
		scrollComposite.setContent(composite);
		

		if(contract != null)
			this.contract = contract;
		else
			throw new IllegalArgumentException();

		if(client != null)
			this.client = client;
		else
			throw new IllegalArgumentException();
		
		processContract = new ProcessContract();
		processClient = new ProcessClient();
		
		Composite contractDataComposite = new Composite(composite, SWT.NONE);
		contractDataComposite.setLayout(new GridLayout(9, false));
		GridData gd_contractDataComposite = new GridData(GridData.FILL_BOTH);
		gd_contractDataComposite.heightHint = 245;
		gd_contractDataComposite.widthHint = 486;
		contractDataComposite.setLayoutData(gd_contractDataComposite);
		
		lblContractIDHeader = new Label(contractDataComposite, SWT.NONE);
		lblContractIDHeader.setAlignment(SWT.CENTER);
		lblContractIDHeader.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));
		lblContractIDHeader.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblContractIDHeader.setText("CONTRACT_ID");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		
		Label lblContractID = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblContractID = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblContractID.widthHint = 67;
		lblContractID.setLayoutData(gd_lblContractID);
		lblContractID.setText("Contract #:");
		
		lblContractIDData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblContractIDData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblContractIDData.widthHint = 137;
		lblContractIDData.setLayoutData(gd_lblContractIDData);
		lblContractIDData.setText("CONTRACT_ID");
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblSigned = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblSigned = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSigned.widthHint = 80;
		lblSigned.setLayoutData(gd_lblSigned);
		lblSigned.setText("Signed date:");
		
		lblSignedData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblSignedData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblSignedData.widthHint = 162;
		lblSignedData.setLayoutData(gd_lblSignedData);
		lblSignedData.setText("SIGNED");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblValue = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblValue = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblValue.widthHint = 67;
		lblValue.setLayoutData(gd_lblValue);
		lblValue.setText("Value:");
		
		lblValueData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblValueData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblValueData.widthHint = 137;
		lblValueData.setLayoutData(gd_lblValueData);
		lblValueData.setText("VALUE");
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblStart = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblStart = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblStart.widthHint = 103;
		lblStart.setLayoutData(gd_lblStart);
		lblStart.setText("Start date:");
		
		lblStartData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblStartData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblStartData.widthHint = 162;
		lblStartData.setLayoutData(gd_lblStartData);
		lblStartData.setText("START_DATE");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblStatus = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblStatus = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblStatus.widthHint = 57;
		lblStatus.setLayoutData(gd_lblStatus);
		lblStatus.setText("Status:");
		
		lblStatusData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblStatusData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblStatusData.widthHint = 137;
		lblStatusData.setLayoutData(gd_lblStatusData);
		lblStatusData.setText("STATUS");
		new Label(contractDataComposite, SWT.NONE);
		
		lblEnd = new Label(contractDataComposite, SWT.NONE);
		lblEnd.setText("End date:");
		
		lblEndData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblEndData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblEndData.widthHint = 162;
		lblEndData.setLayoutData(gd_lblEndData);
		lblEndData.setText("END");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);

		lblClientNameData = new Label(contractDataComposite, SWT.NONE);
		lblClientNameData.setAlignment(SWT.CENTER);
		lblClientNameData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));
		lblClientNameData.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblClientNameData.setText("CLIENT_BUSINESS_NAME");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblAddress = new Label(contractDataComposite, SWT.NONE);
		lblAddress.setText("Address:");
		
		lblAddressData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblAddressData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblAddressData.widthHint = 137;
		lblAddressData.setLayoutData(gd_lblAddressData);
		lblAddressData.setText("ADDRESS");
		new Label(contractDataComposite, SWT.NONE);
		
		lblEmail_1 = new Label(contractDataComposite, SWT.NONE);
		lblEmail_1.setText("Email:");

		lblEmailData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblEmailData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblEmailData.widthHint = 162;
		lblEmailData.setLayoutData(gd_lblEmailData);
		lblEmailData.setText("EMAIL");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblContact = new Label(contractDataComposite, SWT.NONE);
		lblContact.setText("Name:");
		
		lblContactData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblContactData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblContactData.widthHint = 137;
		lblContactData.setLayoutData(gd_lblContactData);
		new Label(contractDataComposite, SWT.NONE);
		
		
		
		lblPhone = new Label(contractDataComposite, SWT.NONE);
		lblPhone.setText("Phone#:");
		
		lblPhoneNumberData = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblPhoneNumberData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblPhoneNumberData.widthHint = 162;
		lblPhoneNumberData.setLayoutData(gd_lblPhoneNumberData);
		lblPhoneNumberData.setText("PHONE_NUMBER");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblContact2 = new Label(contractDataComposite, SWT.NONE);
		lblContact2.setText("Name:");

		
		lblContactData2 = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblContactData2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblContactData2.widthHint = 137;
		lblContactData2.setLayoutData(gd_lblContactData2);
		new Label(contractDataComposite, SWT.NONE);
		
		lblPhone_1 = new Label(contractDataComposite, SWT.NONE);
		lblPhone_1.setText("Phone#:");
		
		lblPhoneNumberData2 = new Label(contractDataComposite, SWT.NONE);
		GridData gd_lblPhoneNumberData2 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblPhoneNumberData2.widthHint = 162;
		lblPhoneNumberData2.setLayoutData(gd_lblPhoneNumberData2);
		lblPhoneNumberData2.setText("PHONE_NUMBER");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblDetails = new Label(contractDataComposite, SWT.NONE);
		lblDetails.setText("Details");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		detailsData = new Text(contractDataComposite, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_detailsData = new GridData(SWT.FILL, SWT.CENTER, true, false, 9, 1);
		gd_detailsData.heightHint = 78;
		detailsData.setLayoutData(gd_detailsData);
		
		lblServicesInThis = new Label(contractDataComposite, SWT.NONE);
		lblServicesInThis.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblServicesInThis.setText("Services in this contract");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		servicesTable = new Table(contractDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_servicesTable = new GridData(SWT.FILL, SWT.TOP, true, false, 9, 1);
		gd_servicesTable.widthHint = 628;
		gd_servicesTable.heightHint = 142;
		servicesTable.setLayoutData(gd_servicesTable);
		servicesTable.setHeaderVisible(true);
		servicesTable.setLinesVisible(true);
		
		TableColumn tableColumn_1 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("Service");
		
		TableColumn tableColumn_2 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_2.setWidth(70);
		tableColumn_2.setText("Rate");
		
		TableColumn tableColumn_3 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Category");
		
		TableColumn tableColumn_4 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_4.setWidth(300);
		tableColumn_4.setText("Details");
		
		
		buttonComposite = new Composite(contractDataComposite, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(2, false));
		GridData gd_serviceButtonComposite = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
		gd_serviceButtonComposite.heightHint = 44;
		gd_serviceButtonComposite.widthHint = 215;
		buttonComposite.setLayoutData(gd_serviceButtonComposite);
		
		btnPrint = new Button(buttonComposite, SWT.NONE);
		btnPrint.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				createContractPDF();
			}
		});
		btnPrint.setText("Print");
		
		btnSave = new Button(buttonComposite, SWT.NONE);
		btnSave.setText("Save");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);

		
		populateClientData();
		populateServiceData();
		populateContractData();
		
		scrollComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
	}
	
	/*
	 * creates, saves and prints contract PDF
	 */
	public void createContractPDF()
	{
		//Document doc = new Document();
	}
	
	/**
	 * Fills in the fields with the contract data
	 */
	private void populateContractData()
	{
		String value = String.valueOf(contract.getValue());
		
		lblContractIDHeader.setText("Contract");
		lblContractIDData.setText(String.valueOf(contract.getID()));
		lblValueData.setText("$"+(value).substring(0, value.length()-2));
		lblSignedData.setText("NULL");//(contract.getSignedDate()).toString());
		lblStartData.setText("NULL");//(contract.getSignedDate()).toString());
		lblEndData.setText("NULL");//(contract.getPeriod()).toString());
		lblStatusData.setText("NULL");
		detailsData.setText(contract.getDetails());
	}
	
	/**
	 * Fills in the fields with the client data
	 */
	private void populateClientData()
	{
		lblClientNameData.setText(client.getBusinessName());
		lblContractIDData.setText(client.getName());
		lblContactData.setText(client.getName());
		lblAddressData.setText(client.getAddress());
		lblEmailData.setText(client.getEmail().toString());
		lblPhoneNumberData.setText(client.getPhoneNumber().formattedPhoneNumber());
		lblPhoneNumberData2.setText(client.getPhoneNumber().formattedPhoneNumber());
		lblContactData2.setText(client.getName());
	}
	
	/**
	 * Populates the services table with the client's services
	 */
	private void populateServiceData()
	{		
		TableItem item;
		TableColumn column;
		Service service;

		ProcessService processService = new ProcessService();
		ArrayList<Service> serviceList = new ArrayList<Service>();
		
		while((service = processService.getNextService())!=null)
		{
			if(service.getContractID()==contract.getID())
			{
				System.out.println("Service FOUND");
				item = new TableItem(servicesTable, SWT.NULL);
				item.setText(0, service.getTitle());
				item.setText(1, String.valueOf(service.getRate()));
				item.setText(2, service.getType());
				item.setText(3, service.getDescription());
			}
			System.out.println("Service NOT FOUND");
		}
	}
}
























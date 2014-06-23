package presentation;

import objects.Client;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import business.ProcessService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/*
 * To Do:
 * 	List services related to the client
 * 	Generate performance reports based on all services
 */

/**
 * Responsible for drawing the detailed client information including all of the
 * client's data in a read only format, the services that the client uses and
 * the tracking analysis
 */
public class ClientAnalysisScreenDrawer
{
	private ScrolledComposite scrollComposite;
	private Composite composite;
	private Table servicesTable;
	private Client client;
	
	private Label lblClientNameData;
	private Label lblContactData;
	private Label lblEmailData;
	private Label lblAddressData;
	private Label lblPhoneNumberData;
	private Label lblStatusData;
	
	private ProcessService processService;
	private Button btnViewSelected;
	
	/**
	 * Creates a new client analysis screen
	 * @param container 	The composite
	 */
	public ClientAnalysisScreenDrawer(Composite container, Client client) throws IllegalArgumentException 
	{
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		composite = new Composite(scrollComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));	
		
		scrollComposite.setContent(composite);
		
		if(client != null)
			this.client = client;
		else
			throw new IllegalArgumentException();
		
		processService = new ProcessService();
		
		Composite clientDataComposite = new Composite(composite, SWT.NONE);
		clientDataComposite.setLayout(new GridLayout(5, false));
		GridData gd_clientDataComposite = new GridData(GridData.FILL_BOTH);
		gd_clientDataComposite.heightHint = 129;
		gd_clientDataComposite.widthHint = 486;
		clientDataComposite.setLayoutData(gd_clientDataComposite);
		
		lblClientNameData = new Label(clientDataComposite, SWT.NONE);
		lblClientNameData.setAlignment(SWT.CENTER);
		lblClientNameData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 5, 1));
		lblClientNameData.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblClientNameData.setText("CLIENT_NAME");
		
		Label lblContact = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblContact = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblContact.widthHint = 67;
		lblContact.setLayoutData(gd_lblContact);
		lblContact.setText("Contact:");
		
		lblContactData = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblContactData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblContactData.widthHint = 137;
		lblContactData.setLayoutData(gd_lblContactData);
		lblContactData.setText("CONTACT");
		new Label(clientDataComposite, SWT.NONE);
		
		Label lblEmail = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblEmail = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblEmail.widthHint = 51;
		lblEmail.setLayoutData(gd_lblEmail);
		lblEmail.setText("Email:");
		
		lblEmailData = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblEmailData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblEmailData.widthHint = 162;
		lblEmailData.setLayoutData(gd_lblEmailData);
		lblEmailData.setText("EMAIL");
		
		Label lblAddress = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblAddress = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblAddress.widthHint = 67;
		lblAddress.setLayoutData(gd_lblAddress);
		lblAddress.setText("Address:");
		
		lblAddressData = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblAddressData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblAddressData.widthHint = 137;
		lblAddressData.setLayoutData(gd_lblAddressData);
		lblAddressData.setText("ADDRESS");
		new Label(clientDataComposite, SWT.NONE);
		
		Label lblPhoneNumber = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblPhoneNumber = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblPhoneNumber.widthHint = 103;
		lblPhoneNumber.setLayoutData(gd_lblPhoneNumber);
		lblPhoneNumber.setText("Phone Number:");
		
		lblPhoneNumberData = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblPhoneNumberData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblPhoneNumberData.widthHint = 162;
		lblPhoneNumberData.setLayoutData(gd_lblPhoneNumberData);
		lblPhoneNumberData.setText("PHONE_NUMBER");
		
		Label lblStatus = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblStatus = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblStatus.widthHint = 57;
		lblStatus.setLayoutData(gd_lblStatus);
		lblStatus.setText("Status:");
		
		lblStatusData = new Label(clientDataComposite, SWT.NONE);
		GridData gd_lblStatusData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblStatusData.widthHint = 137;
		lblStatusData.setLayoutData(gd_lblStatusData);
		lblStatusData.setText("STATUS");
		new Label(clientDataComposite, SWT.NONE);
		new Label(clientDataComposite, SWT.NONE);
		new Label(clientDataComposite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Composite serviceDataComposite = new Composite(composite, SWT.NONE);
		serviceDataComposite.setLayout(new GridLayout(1, false));
		GridData gd_serviceDataComposite = new GridData(GridData.FILL_BOTH);
		gd_serviceDataComposite.heightHint = 220;
		gd_serviceDataComposite.widthHint = 319;
		serviceDataComposite.setLayoutData(gd_serviceDataComposite);
		
		Label lblServices = new Label(serviceDataComposite, SWT.NONE);
		lblServices.setAlignment(SWT.CENTER);
		lblServices.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblServices.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblServices.setText("Services");
		
		servicesTable = new Table(serviceDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		servicesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		servicesTable.setHeaderVisible(true);
		servicesTable.setLinesVisible(true);
		
		btnViewSelected = new Button(serviceDataComposite, SWT.NONE);
		btnViewSelected.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				viewSelected();
			}
		});
		btnViewSelected.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnViewSelected.setText("View Selected");
		new Label(composite, SWT.NONE);
		
		Composite performanceComposite = new Composite(composite, SWT.NONE);
		performanceComposite.setLayout(new GridLayout(1, false));
		GridData gd_performanceComposite = new GridData(GridData.FILL_BOTH);
		gd_performanceComposite.heightHint = 100;
		performanceComposite.setLayoutData(gd_performanceComposite);
		
		Label lblPerformance = new Label(performanceComposite, SWT.NONE);
		lblPerformance.setAlignment(SWT.CENTER);
		lblPerformance.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		GridData gd_lblPerformance = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblPerformance.widthHint = 471;
		lblPerformance.setLayoutData(gd_lblPerformance);
		lblPerformance.setText("Performance");
		
		/*chartComposite = new ChartComposite(composite, SWT.HORIZONTAL);
		GridData gd_chartComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_chartComposite.heightHint = 85;
		chartComposite.setLayoutData(gd_chartComposite);	*/
				
		populateClientData();
		populateServiceData();
		generateReports();
		
		scrollComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
	}
	
	/**
	 * Fills in the capitalized fields with the client data
	 */
	private void populateClientData()
	{
		lblClientNameData.setText(client.getBusinessName());
		lblContactData.setText(client.getName());
		lblAddressData.setText(client.getAddress());
		lblEmailData.setText(client.getEmail().toString());
		lblPhoneNumberData.setText(client.getPhoneNumber().formattedPhoneNumber());
		lblStatusData.setText(client.getStatus().toString());
	}
	
	/**
	 * Populates the services table with the client's services
	 */
	private void populateServiceData()
	{
		//TODO Get all services that the client is using
		//TODO Pull the financial data for the most recent period - Revenue and Expenses
		
		//TEMPORARY TEST DATA
		TableItem item;
		TableColumn column;
		Service service;
		final String[] names = { "ID", "Service" };
		final int[] widths = { 0, 300 };
		
		for(int i = 0; i < 2; i++)
		{
			column = new TableColumn(servicesTable, SWT.NULL);
			column.setText(names[i]);
			column.setWidth(widths[i]);
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = servicesTable.getColumn(0);
		column.setResizable(false);
		
		for(int i = 1; i <= 3; i++)
		{
			item = new TableItem(servicesTable, SWT.NULL);
			service = processService.getServiceByID(i);
			
			item.setText(0, service.getID() + "");
			item.setText(1, service.getTitle());
		}
	}
	
	/**
	 * Generates the reports based on each service's performance
	 */
	private void generateReports()
	{
		//TODO Pull all records of each service
		//TODO Plot each service financial/ feature history on a line graph
		//Abstract the building of the reports into some new business layer object
	}
	
	/**
	 * View the performance data for the selected service item
	 */
	private void viewSelected()
	{
		int index, id;
		Composite viewServicePerformance;
		Service service;

		if((index = servicesTable.getSelectionIndex()) != -1)
		{
			try
			{
				//Extract the service ID from the table
				id = Integer.parseInt(servicesTable.getItem(index).getText(0));
				service = processService.getServiceByID(id);
				
				if(service != null)
				{
					//Open the service performance tracking screen
					viewServicePerformance = new Composite(SwitchScreen.getContent(), SWT.None);
					viewServicePerformance.setLayout(new FillLayout());
					new PerformanceServiceScreenDrawer(viewServicePerformance, service);
					SwitchScreen.setcontentLayoutTopControl(viewServicePerformance);
					SwitchScreen.getContent().layout();
				}
			}
			catch(NumberFormatException nfe) 
			{
				System.out.println(nfe);
			}
		}
	}
}
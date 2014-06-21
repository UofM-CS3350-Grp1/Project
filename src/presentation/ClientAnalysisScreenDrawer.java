package presentation;

import objects.Client;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;

/*
 * To Do:
 * 	Implement scrolling feature on panel
 * 	List services related to the client
 * 	Generate performance reports based on all services
 * 
 * Notes:
 * 	This should be opened from the ClientScreenDrawer's View Client Button
 */

/**
 * Responsible for drawing the detailed client information including all of the
 * client's data in a read only format, the services that the client uses and
 * the tracking analysis
 */
public class ClientAnalysisScreenDrawer
{
	private Composite composite;
	private Table servicesTable;
	private Client client;
	
	private Label lblClientNameData;
	private Label lblContactData;
	private Label lblEmailData;
	private Label lblAddressData;
	private Label lblPhoneNumberData;
	private Label lblStatusData;
	
	/**
	 * Creates a new client analysis screen
	 * @param container 	The composite
	 */
	public ClientAnalysisScreenDrawer(Composite container, Client client) throws IllegalArgumentException 
	{
		composite = new Composite(container, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));	
		
		if(client != null)
			this.client = client;
		else
			throw new IllegalArgumentException();
		
		Composite clientDataComposite = new Composite(composite, SWT.NONE);
		clientDataComposite.setLayout(new GridLayout(5, false));
		GridData gd_clientDataComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
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
		GridData gd_serviceDataComposite = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_serviceDataComposite.heightHint = 220;
		gd_serviceDataComposite.widthHint = 319;
		serviceDataComposite.setLayoutData(gd_serviceDataComposite);
		
		Label lblServices = new Label(serviceDataComposite, SWT.NONE);
		lblServices.setAlignment(SWT.CENTER);
		lblServices.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblServices.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblServices.setText("Services");
		
		servicesTable = new Table(serviceDataComposite, SWT.BORDER);
		servicesTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		servicesTable.setHeaderVisible(true);
		servicesTable.setLinesVisible(true);
		new Label(composite, SWT.NONE);
		
		Composite performanceComposite = new Composite(composite, SWT.NONE);
		performanceComposite.setLayout(new GridLayout(1, false));
		GridData gd_performanceComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_performanceComposite.heightHint = 100;
		performanceComposite.setLayoutData(gd_performanceComposite);
		
		Label lblPerformance = new Label(performanceComposite, SWT.NONE);
		lblPerformance.setAlignment(SWT.CENTER);
		lblPerformance.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		GridData gd_lblPerformance = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblPerformance.widthHint = 471;
		lblPerformance.setLayoutData(gd_lblPerformance);
		lblPerformance.setText("Performance");
		
		populateClientData();
		populateServiceData();
		generateReports();
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
}

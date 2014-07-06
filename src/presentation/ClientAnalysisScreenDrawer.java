package presentation;

import java.util.ArrayList;

import objects.Client;
import objects.Contract;
import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;

import business.AccessFinancialRecords;
import business.GenerateGraph;
import business.ProcessAddFeature;
import business.ProcessContract;

import org.jfree.experimental.chart.swt.ChartComposite;

/**
 * Responsible for drawing the detailed client information including all of the
 * client's data in a read only format, the services that the client uses and
 * the tracking analysis
 */
public class ClientAnalysisScreenDrawer
{
	private final String[] serviceNames = { "ID", "Service", "Rate", "Category", "Details" };
	private final int[] serviceWidths = { 0, 150, 70, 100, 300 };
	
	private ScrolledComposite scrollComposite;
	private Composite composite;
	private Composite performanceComposite;
	private Table servicesTable;
	private Client client;
	private ProcessAddFeature processFeature;
	
	private Label lblClientNameData;
	private Label lblContactData;
	private Label lblEmailData;
	private Label lblAddressData;
	private Label lblPhoneNumberData;
	private Label lblStatusData;
	private Label lblRevenueToDate;
	private Label lblRevenue;
	private Label lblExpensesToDate;
	private Label lblExpenses;
		
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
		
		processFeature = new ProcessAddFeature();
		
		Composite clientDataComposite = new Composite(composite, SWT.NONE);
		clientDataComposite.setLayout(new GridLayout(5, false));
		GridData gd_clientDataComposite = new GridData(GridData.FILL_BOTH);
		gd_clientDataComposite.heightHint = 129;
		gd_clientDataComposite.widthHint = 486;
		clientDataComposite.setLayoutData(gd_clientDataComposite);
		
		lblClientNameData = new Label(clientDataComposite, SWT.NONE);
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
		gd_serviceDataComposite.heightHint = 273;
		gd_serviceDataComposite.widthHint = 319;
		serviceDataComposite.setLayoutData(gd_serviceDataComposite);
		
		Label lblServices = new Label(serviceDataComposite, SWT.NONE);
		lblServices.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblServices.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblServices.setText("Services");
		
		servicesTable = new Table(serviceDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_servicesTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_servicesTable.heightHint = 200;
		servicesTable.setLayoutData(gd_servicesTable);
		servicesTable.setHeaderVisible(true);
		servicesTable.setLinesVisible(true);
		new Label(composite, SWT.NONE);
		
		performanceComposite = new Composite(composite, SWT.NONE);
		performanceComposite.setLayout(new GridLayout(2, false));
		GridData gd_performanceComposite = new GridData(GridData.FILL_BOTH);
		gd_performanceComposite.heightHint = 100;
		performanceComposite.setLayoutData(gd_performanceComposite);
		
		Label lblPerformance = new Label(performanceComposite, SWT.NONE);
		lblPerformance.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		GridData gd_lblPerformance = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_lblPerformance.widthHint = 147;
		lblPerformance.setLayoutData(gd_lblPerformance);
		lblPerformance.setText("Performance");
		
		lblRevenueToDate = new Label(performanceComposite, SWT.NONE);
		GridData gd_lblRevenueToDate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRevenueToDate.widthHint = 113;
		lblRevenueToDate.setLayoutData(gd_lblRevenueToDate);
		lblRevenueToDate.setText("Revenue to date:");
		
		lblRevenue = new Label(performanceComposite, SWT.NONE);
		lblRevenue.setText("REVENUE");
		
		lblExpensesToDate = new Label(performanceComposite, SWT.NONE);
		lblExpensesToDate.setText("Expenses to date:");
		
		lblExpenses = new Label(performanceComposite, SWT.NONE);
		lblExpenses.setText("EXPENSES");
						
		populateClientData();
		populateServiceData();
		generateAnalytics();
		
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
		TableItem item;
		TableColumn column;
		Service service;
		ArrayList<Contract> contracts;
		ArrayList<Service> services;
		ProcessContract processContract = new ProcessContract();
		int contractsSize, servicesSize;
		
		for(int i = 0; i < serviceNames.length; i++)
		{
			column = new TableColumn(servicesTable, SWT.NULL);
			column.setText(serviceNames[i]);
			column.setWidth(serviceWidths[i]);
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = servicesTable.getColumn(0);
		column.setResizable(false);
		
		//Populate our service table with all of the services
		//on the active contracts
		contracts = processContract.getContractsByClient(client);
		if(contracts != null)
		{
			contractsSize = contracts.size();
			for(int i = 0; i < contractsSize; i++)
			{
				services = processContract.getServices(contracts.get(i));
				if(services != null)
				{
					servicesSize = services.size();
					for(int j = 0; j < servicesSize; j++)
					{
						service = services.get(j);
						item = new TableItem(servicesTable, SWT.NULL);
						
						item.setText(0, service.getID() + "");
						item.setText(1, service.getTitle());
						item.setText(2, String.valueOf(service.getRate()));
						item.setText(3, service.getServiceType().getType());
						item.setText(4, service.getDescription());
					}
				}
			}
		}
	}
	
	/**
	 * Generates the analytics for the client and their tracked features by creating
	 * graphs and data printouts
	 */
	private void generateAnalytics()
	{
		generateFinancialData();
		generateFeatureData();
	}
	
	/**
	 * Generates the charts and analysis for the client
	 */
	private void generateFinancialData()
	{
		GenerateGraph graphGenerator = new GenerateGraph();	
		AccessFinancialRecords financialRecords = new AccessFinancialRecords();
		GridData gd_chartComposite;
		ChartComposite chartComp;
		double revenue, expenses;
		
		//Generate the written analysis including the revenue, expenses, etc.
		revenue = financialRecords.calcClientRevenueToDate(client);
		expenses = financialRecords.calcClientExpensesToDate(client);
		
		lblRevenue.setText(String.format("$%8.2f", revenue));
		lblExpenses.setText(String.format("$%8.2f", expenses));
		
		//Draw the graphs that correspond to the written analysis		
		chartComp = new ChartComposite(composite, SWT.NONE, graphGenerator.generateRevenueLineChartForClient(client));
		gd_chartComposite = new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1);
		gd_chartComposite.heightHint = 500;
		chartComp.setLayoutData(gd_chartComposite);
	}
	
	/**
	 * Generates the charts and analysis for each feature
	 */
	private void generateFeatureData()
	{
		GenerateGraph graphGenerator = new GenerateGraph();	
		ArrayList<TrackedFeature> features;
		ChartComposite chartComp;
		GridData gd_chartComposite;
		int size;
		
		features = processFeature.getFeaturesByClient(client);
		if(features != null)
		{
			size = features.size();
			for(int i = 0; i < size; i++)
			{
				//Generate the composite
				chartComp = new ChartComposite(composite, SWT.NONE, graphGenerator.generateFeatureLineChart(features.get(i)));
				gd_chartComposite = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
				gd_chartComposite.heightHint = 500;
				chartComp.setLayoutData(gd_chartComposite);
			}
		}
	}
}

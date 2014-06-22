package presentation;

import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.jfree.experimental.chart.swt.ChartComposite;

import business.GenerateLineGraph;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Draws all of the relative information for a given service
 */
public class PerformanceServiceScreenDrawer 
{
	private Composite composite;
	private Service service;
	private Label lblTypeData;
	private Label lblRateData;
	private Label lblServiceNameData;
	private Label lblDescription;
	private Label lblDescriptionData;
	private Composite chartHolderComposite;
	private ChartComposite chartComposite;
	
	private GenerateLineGraph graphGenerator;
	private Button btnBack;
	
	/**
	 * Creates a new performance screen for a service
	 * @param container	The parent composite
	 * @param service	The service to track performance
	 * @throws IllegalArgumentException
	 */
	public PerformanceServiceScreenDrawer(Composite container, Service service) throws IllegalArgumentException 
	{
		composite = new Composite( container, SWT.None );
		composite.setLayout(new GridLayout(1, false));
		
		assert (service != null);
		if(service != null)
			this.service = service;
		else
			throw new IllegalArgumentException();
		
		graphGenerator = new GenerateLineGraph();
		
		Composite serviceDataComposite = new Composite(composite, SWT.NONE);
		serviceDataComposite.setLayout(new GridLayout(4, false));
		GridData gd_serviceDataComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_serviceDataComposite.widthHint = 444;
		serviceDataComposite.setLayoutData(gd_serviceDataComposite);
		
		lblServiceNameData = new Label(serviceDataComposite, SWT.NONE);
		lblServiceNameData.setAlignment(SWT.CENTER);
		GridData gd_lblServiceName = new GridData(SWT.FILL, SWT.CENTER, false, false, 4, 1);
		gd_lblServiceName.widthHint = 214;
		lblServiceNameData.setLayoutData(gd_lblServiceName);
		lblServiceNameData.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblServiceNameData.setText("SERVICE_NAME");
		
		Label lblRate = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblRate = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblRate.widthHint = 56;
		lblRate.setLayoutData(gd_lblRate);
		lblRate.setText("Rate:");
		
		lblRateData = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblRateData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblRateData.widthHint = 84;
		lblRateData.setLayoutData(gd_lblRateData);
		lblRateData.setText("RATE");
		
		Label lblType = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblType = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblType.widthHint = 48;
		lblType.setLayoutData(gd_lblType);
		lblType.setText("Type:");
		
		lblTypeData = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblTypeData = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblTypeData.widthHint = 162;
		lblTypeData.setLayoutData(gd_lblTypeData);
		lblTypeData.setText("TYPE");
		
		lblDescription = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblDescription = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_lblDescription.widthHint = 78;
		lblDescription.setLayoutData(gd_lblDescription);
		lblDescription.setText("Description:");
		
		lblDescriptionData = new Label(serviceDataComposite, SWT.NONE);
		GridData gd_lblDescriptionData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1);
		gd_lblDescriptionData.heightHint = 60;
		lblDescriptionData.setLayoutData(gd_lblDescriptionData);
		lblDescriptionData.setText("DESCRIPTION");
		new Label(composite, SWT.NONE);
		
		chartHolderComposite = new Composite(composite, SWT.NONE);
		GridData gd_chartHolderComposite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_chartHolderComposite.heightHint = 272;
		chartHolderComposite.setLayoutData(gd_chartHolderComposite);
		
		chartComposite = new ChartComposite(chartHolderComposite, SWT.HORIZONTAL);
		GridData gd_chartComposite = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_chartComposite.heightHint = 85;
		chartComposite.setLayoutData(gd_chartComposite);	
		
		btnBack = new Button(composite, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				goBackToPreviousScreen();
			}
		});
		GridData gd_btnBack = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnBack.widthHint = 79;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("Back");
		
		populateServiceData();
		generateCharts();
	}
	
	/**
	 * Fills in the capitalized fields with the client data
	 */
	private void populateServiceData()
	{
		lblServiceNameData.setText(service.getTitle());
		lblRateData.setText(service.getRate() + "");
		lblTypeData.setText(service.getType());
		
		if(!service.getDescription().isEmpty())
			lblDescriptionData.setText(service.getDescription());
		else
		{
			//Hide the description fields
			lblDescription.setEnabled(false);
			lblDescriptionData.setEnabled(false);
		}
	}
	
	/**
	 * Generates the charts for the tracked data to visually show the user
	 */
	private void generateCharts()
	{
		chartComposite.setChart(graphGenerator.GenerateChartForService(service));
		chartComposite.setSize(600, 600);
	}
	
	/**
	 * Go back to the previous screen
	 */
	private void goBackToPreviousScreen()
	{
		//TODO Go back a screen
	}
}

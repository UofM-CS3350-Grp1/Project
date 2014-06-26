package presentation;

import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.jfree.experimental.chart.swt.ChartComposite;

import business.GenerateGraph;
import business.ProcessAddFeature;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * Draws all of the relative information for a given service
 */
public class PerformanceServiceScreenDrawer 
{
	private static final int CHART_HEIGHT = 500;			//Height of a chart
	
	private ScrolledComposite scrollComposite;
	private Composite composite;
	private Service service;
	private Label lblTypeData;
	private Label lblRateData;
	private Label lblServiceNameData;
	private Label lblDescription;
	private Label lblDescriptionData;
	private ChartComposite chartComposite;
	
	private GenerateGraph graphGenerator;
	private ProcessAddFeature processFeature;
	private Button btnBack;
	
	/**
	 * Creates a new performance screen for a service
	 * @param container	The parent composite
	 * @param service	The service to track performance
	 * @throws IllegalArgumentException
	 */
	public PerformanceServiceScreenDrawer(Composite container, Service service) throws IllegalArgumentException 
	{
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		
		composite = new Composite( scrollComposite , SWT.None );
		composite.setLayout(new GridLayout(1, false));
		
		scrollComposite.setContent(composite);
		
		assert (service != null);
		if(service != null)
			this.service = service;
		else
			throw new IllegalArgumentException();
		
		graphGenerator = new GenerateGraph();
		processFeature = new ProcessAddFeature();
		
		Composite serviceDataComposite = new Composite(composite, SWT.NONE);
		serviceDataComposite.setLayout(new GridLayout(4, false));
		GridData gd_serviceDataComposite = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_serviceDataComposite.widthHint = 444;
		serviceDataComposite.setLayoutData(gd_serviceDataComposite);
		
		lblServiceNameData = new Label(serviceDataComposite, SWT.NONE);
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
		
		populateServiceData();
		generateCharts();
		
		btnBack = new Button(composite, SWT.NONE);
		btnBack.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				goBackToPreviousScreen();
			}
		});
		GridData gd_btnBack = new GridData(SWT.LEFT, SWT.LEFT, false, false, 1, 1);
		gd_btnBack.widthHint = 79;
		btnBack.setLayoutData(gd_btnBack);
		btnBack.setText("Back");
		
		scrollComposite.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
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
	 * the performance as it progresses
	 */
	private void generateCharts()
	{
		TrackedFeature feature = null;
		ChartComposite chartComp;
		GridData gd_chartComposite;
		
		//Display the general all feature summary
		chartComposite = new ChartComposite(composite, SWT.NONE, graphGenerator.GenerateChartForService(service));
		gd_chartComposite = new GridData(SWT.FILL, SWT.LEFT, true, false, 1, 1);
		gd_chartComposite.heightHint = CHART_HEIGHT;
		chartComposite.setLayoutData(gd_chartComposite);
		
		//Display a chart for each tracked feature
		while((feature = processFeature.getNextFeatureForService(service)) != null)
		{
			//Generate the composite
			chartComp = new ChartComposite(composite, SWT.NONE, graphGenerator.GenerateChartForFeature(service, feature));
			gd_chartComposite = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
			gd_chartComposite.heightHint = CHART_HEIGHT;
			chartComp.setLayoutData(gd_chartComposite);
			
		}
	}
	
	/**
	 * Go back to the previous screen
	 */
	protected void goBackToPreviousScreen()
	{
		Composite serviceScreen = SwitchScreen.getContentContainer();
		new ServiceScreenDrawer( serviceScreen );
		SwitchScreen.switchContent( serviceScreen );
	}
}

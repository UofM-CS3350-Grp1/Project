package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.Client;
import objects.FeatureHistory;
import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.widgets.Composite;

/**
 * Manages the adding of data for features at a given time period
 */
public class AddFeatureHistoryDrawer extends BaseFeatureHistoryDrawer
{	
	private TrackedFeature feature;
	private Service trackedService;
	
	/**
	 * Adds a given tracked feature
	 * @param container 		The composite
	 * @param feature			The feature being recorded
	 * @param trackedService	The corresponding service/ client being served
	 * 
	 * NOTE: You cannot edit the structure of the FeatureWindow unless editing the Base
	 */
	public AddFeatureHistoryDrawer(Composite container, TrackedFeature feature, Service trackedService) throws IllegalArgumentException
	{
		super(container);
		
		assert (feature != null && trackedService != null);
		if(feature != null && trackedService != null)
		{
			this.feature = feature;
			this.trackedService = trackedService;
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Processes an action on button press
	 */
	protected void processActionButton()
	{
		FeatureHistory history;
		Date date;
		SimpleDateFormat formatter;

		try
		{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(dateTime.getDay() + "/" + dateTime.getMonth() + "/" + dateTime.getYear());
			
			history = new FeatureHistory(feature, trackedService, Integer.parseInt(txtValue.getText()), date, txtNotes.getText());
			
			if(processFeatureHistory.insert(history))
				backToPreviousScreen();
		}
		catch(Exception e) { }	
	}

	/**
	 * Go back to the screen we were at previously
	 */
	protected void backToPreviousScreen() 
	{
		Client client = trackedService.getAssociatedClient();
		
		if(client != null)
		{
			Composite servicePerformanceScreen = SwitchScreen.getContentContainer();
			new PerformanceClientServiceScreenDrawer(servicePerformanceScreen, client, trackedService);
			SwitchScreen.switchContent(servicePerformanceScreen);
		}		
	}
}

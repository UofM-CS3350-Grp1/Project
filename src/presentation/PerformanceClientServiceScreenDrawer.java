package presentation;

import objects.Client;
import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.widgets.Composite;

/**
 * Manages the drawing of the performance screen related to a client and
 * their services.
 */
public class PerformanceClientServiceScreenDrawer extends PerformanceServiceScreenDrawer
{
	private Client client;
	
	/**
	 * Creates a new performance screen for a service that belongs to a client
	 * @param container	The parent composite
	 * @param client 	The client the service belongs to
	 * @param service	The service to track performance
	 * @throws IllegalArgumentException
	 */
	public PerformanceClientServiceScreenDrawer(Composite container, Client client, Service service) throws IllegalArgumentException 
	{
		super(container, service);
		
		assert (client != null);
		if(client != null)
			this.client = client;
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Draws the history for a given client's service
	 * 
	 * @param feature The feature to draw history for
	 */
	protected void drawHistoryTable(TrackedFeature feature) 
	{
		new HistoryTableDrawer(composite, service, feature);
	}
	
	/**
	 * Go back to the previous screen
	 */
	protected void goBackToPreviousScreen()
	{
		Composite analysisScreen = SwitchScreen.getContentContainer();
		new ClientAnalysisScreenDrawer(analysisScreen, client);
		SwitchScreen.switchContent(analysisScreen);
	}
	
	/**
	 * Draws the table to manipulate the features that a service tracks.
	 * We do not need this feature in the client version of the window
	 * since only services should be allowed to edit this information.
	 */
	protected void drawTrackFeatureTable() { }
}

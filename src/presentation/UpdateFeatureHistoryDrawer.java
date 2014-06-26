package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.Client;
import objects.FeatureHistory;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
/**
 * Manages the adding of data for features at a given time period
 */
public class UpdateFeatureHistoryDrawer extends BaseFeatureHistoryDrawer
{
	private FeatureHistory history;
	
	/**
	 * Adds a given tracked feature
	 * @param container 	The composite
	 * @param history		The history object to edit
	 * 
	 * NOTE: You cannot edit the structure of the FeatureWindow unless editing the Base
	 */
	public UpdateFeatureHistoryDrawer(Composite container, FeatureHistory history) throws IllegalArgumentException
	{
		super(container);
		
		assert (history != null);
		if(history != null)
		{
			this.history = history;
			btnAction.setText("Update");
			
			populateFields();
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
	 * Processes an action on button press
	 */
	protected void processActionButton()
	{
		Date date;
		SimpleDateFormat formatter;
		MessageBox dialog;
		
		try
		{
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			date = formatter.parse(dateTime.getDay() + "/" + dateTime.getMonth() + "/" + dateTime.getYear());
			
			history.setValue(Integer.parseInt(txtValue.getText()));
			history.setDate(date);
			history.setNotes(txtNotes.getText());
			
			if(processFeatureHistory.update(history))
				backToPreviousScreen();	
		}
		catch(Exception e)
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Could not update history");
			dialog.setMessage("Could not update history. Please check the data and try again.");
			dialog.open();
		}	
	}
	
	/**
	 * Populates the fields
	 */
	@SuppressWarnings("deprecation")
	private void populateFields()
	{
		if(history != null)
		{
			txtValue.setText("" + history.getValue());
			dateTime.setDate(history.getDate().getYear(), history.getDate().getMonth(), history.getDate().getDay());
			txtNotes.setText(history.getNotes());
		}
	}
	
	/**
	 * Go back to the screen we were at previously
	 */
	protected void backToPreviousScreen() 
	{
		Service service = (Service) history.getTrackedService();
		Client client;
		
		if(service != null)
		{
			client = service.getAssociatedClient();
			if(client != null)
			{
				Composite servicePerformanceScreen = SwitchScreen.getContentContainer();
				new PerformanceClientServiceScreenDrawer(servicePerformanceScreen, client, service);
				SwitchScreen.switchContent(servicePerformanceScreen);
			}
		}		
	}
}

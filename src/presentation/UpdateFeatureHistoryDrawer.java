package presentation;

import java.text.SimpleDateFormat;
import java.util.Date;

import objects.FeatureHistory;

import org.eclipse.swt.widgets.Composite;
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
		catch(Exception e) {}	
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
}

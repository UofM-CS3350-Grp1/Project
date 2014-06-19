package presentation;

import objects.TrackedFeature;
import org.eclipse.swt.widgets.Composite;

public class UpdateTrackableFeatureDrawer extends AddTrackableFeatureDrawer
{
	private TrackedFeature feature;
		
	/**
	 * Updates a given tracked feature
	 * @param container 	The composite
	 * @param feature		The feature to set
	 *
	 * NOTE: You cannot edit the structure of the FeatureWindow unless editing the Base
	 */
	public UpdateTrackableFeatureDrawer(Composite container, TrackedFeature feature) throws IllegalArgumentException
	{
		super(container);
		
		assert (feature != null);
		if(feature != null)
		{
			this.feature = feature;
			
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
		if(feature != null)
		{
			feature.setFeatureName(txtName.getText());
			feature.setNotes(txtNotes.getText());
			
			if(processAddFeature.updateFeature(feature))
				backToPreviousScreen();
		}
	}
	
	/**
	 * Populates the fields
	 */
	private void populateFields()
	{
		if(feature != null)
		{
			txtName.setText(feature.getFeatureName());
			txtNotes.setText(feature.getNotes());
		}
	}
}

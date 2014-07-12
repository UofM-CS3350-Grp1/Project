package presentation;

import objects.TrackedFeatureType;

import org.eclipse.swt.widgets.Composite;

/**
 * Responsible for drawing the update interface for features
 */
public class UpdateTrackableFeatureDrawer extends AddTrackableFeatureDrawer
{
	private TrackedFeatureType feature;
		
	/**
	 * Updates a given tracked feature
	 * @param container 	The composite
	 * @param feature		The feature to set
	 *
	 * NOTE: You cannot edit the structure of the FeatureWindow unless editing the Base
	 */
	public UpdateTrackableFeatureDrawer(Composite container, TrackedFeatureType feature) throws IllegalArgumentException
	{
		super(container);
		
		assert (feature != null);
		if(feature != null)
		{
			this.feature = feature;
			
			populateFields();
			
			btnAction.setText("Update");
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
			feature.setTitle(txtName.getText());
			
			if(processAddFeature.update(feature))
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
			txtName.setText(feature.getTitle());
		}
	}
}

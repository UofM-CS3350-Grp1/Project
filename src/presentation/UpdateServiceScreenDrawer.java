package presentation;

import objects.ServiceType;

import org.eclipse.swt.widgets.Composite;

/**
 * Handles the adding of services.
 * NOTE: To edit the structure you must edit the parent
 */
public class UpdateServiceScreenDrawer extends AddServiceScreenDrawer
{
	private ServiceType serviceType;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public UpdateServiceScreenDrawer(Composite container, ServiceType serviceType) throws IllegalArgumentException
	{
		super(container);
		
		if(serviceType != null)
		{
			this.serviceType = serviceType;
			btnAdd.setText("Update");
			
			populateFields();
		}
		else
			throw new IllegalArgumentException();
	}
	
	/**
     * Populates the given service data on the form
     */
    private void populateFields()
    { 
        svcName.setText(serviceType.getType());
        svcDescription.setText(serviceType.getDescription());
    }
	
	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		serviceType.setType(svcName.getText());
		serviceType.setDescription(svcDescription.getText());
		
		if(processService.update(serviceType))
			goBackToServiceScreen();
	}
	
	/**
	 * Goes back to the service screen composite
	 */
	protected void goBackToServiceScreen()
	{
		Composite serviceScreen = SwitchScreen.getContentContainer();
		new ServiceScreenDrawer( serviceScreen );
		SwitchScreen.switchContent( serviceScreen );
	}
}

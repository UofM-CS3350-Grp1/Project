package presentation;

import objects.Service;
import objects.ServiceType;

import org.eclipse.swt.widgets.Composite;

/**
 * Handles the adding of services.
 * NOTE: To edit the structure you must edit the parent
 */
public class UpdateServiceScreenDrawer extends AddServiceScreenDrawer
{
	private Service service;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public UpdateServiceScreenDrawer(Composite container, Service service) throws IllegalArgumentException
	{
		super(container);
		
		if(service != null)
		{
			this.service = service;
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
        svcName.setText(service.getTitle());
        svcType.setText(service.getServiceType().getType());
        rateAmount.setText(String.valueOf(service.getRate()));
        svcDescription.setText(service.getDescription());
    }
	
	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		ServiceType serviceType = new ServiceType(svcType.getText(), svcDescription.getText());
		service.setTitle(svcName.getText());
		service.setDescription(svcDescription.getText());
		service.setRate(Double.parseDouble(rateAmount.getText()));
		service.setType(serviceType);
		
		processService.update(service);
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

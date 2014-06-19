package presentation;

import org.eclipse.swt.widgets.Composite;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;

public class UpdateClientScreenDrawer extends AddClientScreenDrawer
{
	private Client client = null;
	
	/**
	 * Updates the given client
	 * 
	 * @param container 	The composite
	 * @param client 		The client to edit
	 * NOTE: Cannot edit in windowbuilder. Therefore layout is the same
	 * @wbp.parser.entryPoint
	 */
	public UpdateClientScreenDrawer(Composite container, Client client) throws IllegalArgumentException
	{
		super(container);
		
		assert (client != null);
		if(client != null)
		{		
			this.client = client;
			btnAction.setText("Update");
					
			populateFields();
		}
		else
			throw new IllegalArgumentException();
	}		
	
	/**
	 * Creates a client given the data supplied on the form
	 * @return True if the client was created
	 */
	protected boolean processActionButton()
	{		
		ClientStatus status = null;
		boolean processed = false;
		
		if (isFormDataValid())
		{
			//Find our client's status
			status = (btnActive.getSelection()) ? ClientStatus.Active : ClientStatus.Potential;
			
			//Update all the information. There is no doubt a better way...
			client.setName(txtClientName.getText());
			client.setPhoneNumber(new PhoneNumber(txtPhoneNumberA.getText() + txtPhoneNumberB.getText() + txtPhoneNumberC.getText()));
			client.setEmail(new Email(txtEmail.getText()));
			client.setAddress(txtAddress.getText());
			client.setBusinessName(txtBusinessName.getText());
			client.setStatus(status);	

			if(processClient.updateClient(client))
			{	
				processed = true;
			}
		}
		
		return processed;
	}		
	
	/**
	 * Populates all of the fields for a specific client
	 * @param client The client to show
	 */
	private void populateFields()
	{
		txtClientName.setText(client.getName());
		txtBusinessName.setText(client.getBusinessName());
		txtAddress.setText(client.getAddress());
		txtEmail.setText(client.getAddress());
		
		txtPhoneNumberA.setText(client.getPhoneNumber().getAreaCode());
		txtPhoneNumberB.setText(client.getPhoneNumber().getPrefix());
		txtPhoneNumberC.setText(client.getPhoneNumber().getLineNumber());
		
		// Set the given client's status
		if(client.getStatus() == ClientStatus.Active)
		{
			btnActive.setSelection(true);
			btnPotential.setSelection(false);
		}
		else if(client.getStatus() == ClientStatus.Potential)
		{
			btnActive.setSelection(false);
			btnPotential.setSelection(true);
		}
	}
}

package presentation;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import objects.Client;
import objects.Client.ClientStatus;
import objects.Email;
import objects.PhoneNumber;

import org.eclipse.swt.widgets.List;

import business.ProcessClient;

/**
 * Draws the Client information screen
 */
public class ClientScreen
{		
	private Shell shell;
	private Display display;
	
	private Text txtClientName;
	private Text txtBusinessName;
	private Text txtAddress;
	private Text txtEmail;
	private Text txtPhoneNumberA;
	private Text txtPhoneNumberB;
	private Text txtPhoneNumberC;
	
	private Button btnActive;
	private Button btnPotential;
	private Button btnAdd;
	private Button btnUpdate;
	
	private List listClients;
	private Client currentClient;
	private ArrayList<Client> clients = new ArrayList<Client>();
	private ProcessClient processClient;

	/**
	 * Create the shell.
	 * @param display
	 */
	public ClientScreen() 
	{
		display = Display.getDefault();
		currentClient = null;
		processClient = new ProcessClient();
		
		createWindow();
	}
	
	/**
	 * Creates a new Client window
	 */
	private void createWindow()
	{
		shell = new Shell(display);
		shell.setText("Client");
		shell.setSize(640, 480);
		
		initializeClientInfoFields();
		initializeClientListFields();
		
		shell.open();
	}
	
	/**
	 * Initialized the new client information form
	 */
	private void initializeClientInfoFields()
	{
		//Name
		Label lblName = new Label(shell, SWT.NONE);
		lblName.setBounds(204, 34, 91, 15);
		lblName.setText("Contact Name");
		
		txtClientName = new Text(shell, SWT.BORDER);
		txtClientName.setBounds(301, 31, 172, 21);
		
		//Business name
		Label lblBusinessName = new Label(shell, SWT.NONE);
		lblBusinessName.setBounds(204, 74, 92, 15);
		lblBusinessName.setText("Business Name");
		
		txtBusinessName = new Text(shell, SWT.BORDER);
		txtBusinessName.setBounds(301, 71, 172, 21);
		
		//Address
		Label lblAddress = new Label(shell, SWT.NONE);
		lblAddress.setText("Address");
		lblAddress.setBounds(204, 116, 92, 15);
		
		txtAddress = new Text(shell, SWT.BORDER);
		txtAddress.setBounds(301, 113, 172, 21);
		
		//Email
		Label lblEmail = new Label(shell, SWT.NONE);
		lblEmail.setText("Email");
		lblEmail.setBounds(204, 159, 92, 15);
		
		txtEmail = new Text(shell, SWT.BORDER);
		txtEmail.setBounds(301, 156, 172, 21);
		
		//Phone number fields
		initializePhoneNumberFields();
		
		//Client status
		Label lblClientStatus = new Label(shell, SWT.NONE);
		lblClientStatus.setBounds(204, 239, 74, 15);
		lblClientStatus.setText("Client Status");
		
		//Status grouping
		Group group = new Group(shell, SWT.NONE);
		group.setBounds(301, 227, 96, 75);
		
		//Active radio button
		btnActive = new Button(group, SWT.RADIO);
		btnActive.setBounds(10, 43, 83, 16);
		btnActive.setText("Active");
		
		//Potential radio button
		btnPotential = new Button(group, SWT.RADIO);
		btnPotential.setBounds(10, 23, 76, 16);
		btnPotential.setSelection(true);
		btnPotential.setText("Potential");
		
		//Add button
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				createClient();
			}
		});
		btnAdd.setBounds(272, 391, 75, 25);
		btnAdd.setText("Add");
		
		//Update button
		btnUpdate = new Button(shell, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				updateClient();
			}
		});
		btnUpdate.setBounds(272, 391, 75, 25);
		btnUpdate.setText("Update");
		btnUpdate.setVisible(false);
		
		//Clear button
		Button btnClear = new Button(shell, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				//Clear all of the form's fields
				clearFields();
			}
		});
		btnClear.setBounds(369, 391, 75, 25);
		btnClear.setText("Clear");
	}	
	
	/**
	 * Initializes the phone number fields
	 */
	private void initializePhoneNumberFields()
	{	
		//Label
		Label lblPhoneNumber = new Label(shell, SWT.NONE);
		lblPhoneNumber.setText("Phone Number");
		lblPhoneNumber.setBounds(204, 197, 92, 15);
		
		//Open Parenthesis
		Label lblOpenBracket = new Label(shell, SWT.NONE);
		lblOpenBracket.setBounds(301, 197, 5, 15);
		lblOpenBracket.setText("(");
		
		//Area Code
		txtPhoneNumberA = new Text(shell, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberA.addVerifyListener(new VerifyListener() 
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.AREA_CODE_LENGTH);
			}
		});
		txtPhoneNumberA.setBounds(310, 194, 35, 21);
		
		//Close parenthesis
		Label lblCloseBracket = new Label(shell, SWT.NONE);
		lblCloseBracket.setBounds(351, 197, 5, 15);
		lblCloseBracket.setText(")");
		
		//Area separator
		Label lblPhoneSep1 = new Label(shell, SWT.NONE);
		lblPhoneSep1.setBounds(357, 197, 5, 15);
		lblPhoneSep1.setText("-");
		
		//Prefix section
		txtPhoneNumberB = new Text(shell, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberB.addVerifyListener(new VerifyListener() 
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.PREFIX_CODE_LENGTH);
			}
		});
		txtPhoneNumberB.setBounds(368, 194, 35, 21);
		
		//Prefix separator
		Label lblPhoneSep2 = new Label(shell, SWT.NONE);
		lblPhoneSep2.setText("-");
		lblPhoneSep2.setBounds(409, 197, 5, 15);
		
		//Line number section
		txtPhoneNumberC = new Text(shell, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberC.addVerifyListener(new VerifyListener() 
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.LINE_NUMBER_CODE);
			}
		});
		txtPhoneNumberC.setBounds(418, 194, 55, 21);
	}
	
	/**
	 * Initialize the client list and associated button controls
	 */
	private void initializeClientListFields()
	{
		Client client = null;
		
		//Client List
		Label lblClients = new Label(shell, SWT.NONE | SWT.SINGLE);
		lblClients.setBounds(10, 10, 55, 15);
		lblClients.setText("Client List");
		
		listClients = new List(shell, SWT.BORDER);
		listClients.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent event)
            {
                editSelectedClient();
            }
        });
		listClients.setBounds(10, 31, 177, 370);
		
		//Add our clients to the list
		while((client = processClient.getNextClient()) != null)
		{
			clients.add(client);
			listClients.add(client.getName() + " - " + client.getBusinessName());
		}
		
		//New client button
		Button btnNew = new Button(shell, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				processNewButton();
			}
		});
		btnNew.setBounds(10, 407, 86, 25);
		btnNew.setText("New");
		
		//Delete client button
		Button btnDelete = new Button(shell, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				int selectedIndex;

				//Find the selected client in our list, if
				//one is selected
				selectedIndex = listClients.getSelectionIndex();
				if(selectedIndex != -1)
				{
					//Delete the client
					if(processClient.delete(currentClient))
					{
						//Remove the clients from our client list
						clients.remove(selectedIndex);
						listClients.remove(selectedIndex);
						
						//After we have deleted select the entry above where we
	                    //just deleted
	                    selectedIndex--;
	                    
	                    if(selectedIndex < 0 && listClients.getItemCount() > 0)
	                        selectedIndex = 0;
	                    
	                    listClients.setSelection(selectedIndex);
	                    
	                    if(selectedIndex >= 0)
	                    {
	                        //Show the client record
	                        editSelectedClient();
	                    }
	                    else
	                    {
	                        //There are no entries in the list
	                        processNewButton();
	                    }
					}
				}
			}
		});
		btnDelete.setBounds(101, 407, 86, 25);
		btnDelete.setText("Delete");
		
		Button btnClose = new Button(shell, SWT.NONE);
		btnClose.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				shell.setVisible(false);
				shell.dispose();
			}
		});
		btnClose.setBounds(467, 391, 75, 25);
		btnClose.setText("Close");
	}
	
	/**
    * Processes the new button and sets up the window for a new client
    */
    private void processNewButton()
    {
        //Swap the Add/ Update buttons
        btnUpdate.setVisible(false);
        btnAdd.setVisible(true);
        currentClient = null;
        
        //Remove the selection from the client list
        listClients.setSelection(-1);
        
        //Clear all of the fields
        clearFields();
    }
    
    /**
    * Opens the currently selected client for editing
    */
    private void editSelectedClient()
    {
        Client client;
        int selectedIndex;
        
        //Find the selected client in our list, if
        //one is selected
        selectedIndex = listClients.getSelectionIndex();
        if(selectedIndex != -1 && selectedIndex < clients.size())
        {
            client = clients.get(selectedIndex);
            currentClient = client;
            
            //Swap the Add/ Update buttons
            btnUpdate.setVisible(true);
            btnAdd.setVisible(false);
            
            //Populate the client fields with our client data
            populateFields(client);
        }
    }
	
	/**
	 * Clears all of the fields on the window
	 */
	private void clearFields()
	{
		txtClientName.setText("");
		txtBusinessName.setText("");
		txtAddress.setText("");
		txtEmail.setText("");
		txtPhoneNumberA.setText("");
		txtPhoneNumberB.setText("");
		txtPhoneNumberC.setText("");
		
		//Default the client status to potential
		btnActive.setSelection(false);
		btnPotential.setSelection(true);
	}
	
	/**
	 * Populates all of the fields for a specific client
	 * @param client The client to show
	 */
	private void populateFields(Client client)
	{
		PhoneNumber phoneNumber;
		
		assert (client != null);
		if(client != null)
		{
			txtClientName.setText(client.getName());
			txtBusinessName.setText(client.getBusinessName());
			txtAddress.setText(client.getAddress());
			txtEmail.setText(client.getEmail().toString());
			
			//Split the phone number into the three components
			phoneNumber = client.getPhoneNumber();
			txtPhoneNumberA.setText(phoneNumber.getAreaCode());
			txtPhoneNumberB.setText(phoneNumber.getPrefix());
			txtPhoneNumberC.setText(phoneNumber.getLineNumber());
			
			//Set our client's status
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
	
	/**
	 * Provides very simple validation to ensure that the form data
	 * contains some data.
	 * @return True if valid
	 */
	private boolean isFormDataValid()
	{
		boolean isValid = true;
		
		//Check if the fields have something in them
		isValid = (txtClientName.getText() != "");
		if(isValid) isValid = (txtBusinessName.getText() != "");
		if(isValid) isValid = (txtAddress.getText() != "");
		if(isValid) isValid = (txtEmail.getText() != "");
		if(isValid) isValid = (txtPhoneNumberA.getText() != "");
		if(isValid) isValid = (txtPhoneNumberB.getText() != "");
		if(isValid) isValid = (txtPhoneNumberC.getText() != "");
		
		return isValid;
	}
	
	/**
	 * Creates a client given the data supplied on the form
	 * @return True if the client was created
	 */
	private boolean createClient()
	{
		Client client = null;
		ClientStatus status;
		int index;
		
		//Basic error checking
		if(isFormDataValid())
		{			
			try
			{
				//Find our client's status
				status = (btnActive.getSelection()) ? ClientStatus.Active : ClientStatus.Potential;
				
				//Create the client
				client = new Client(txtClientName.getText(), new PhoneNumber(txtPhoneNumberA.getText() + txtPhoneNumberB.getText() + txtPhoneNumberC.getText()), 
									new Email(txtEmail.getText()), txtAddress.getText(), txtBusinessName.getText(), status);
				
				//For now we will use the client list above
				if(processClient.insert(client))
				{
					clients.add(client);
					index = clients.indexOf(client);
					listClients.add(client.getName() + " - " + client.getBusinessName(), index);
				
					//Clear the fields
					clearFields();
				}				
			}
			catch(Exception e)
			{
				client = null;
			}
		}
		
		return (client != null);
	}
	
	/**
	 * Updates an existing client given the data supplied on the form
	 * @return True if the client was updated
	 */
	private boolean updateClient()
	{
		Client client = null;
		ClientStatus status;
		int index;
		
		//Basic error checking
		if(isFormDataValid())
		{
			//Find our client
			index = listClients.getSelectionIndex();
			if(index != -1 && index < clients.size())
			{
				client = clients.get(index);
				if(client != null)
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

					if(processClient.update(client))
					{						
						//For now we will use the client list above
						listClients.setItem(index, client.getName() + " - " + client.getBusinessName());
					}
				}
			}
		}
		
		return (client != null);
	}
	
	/**
	 * Validates a textbox event to ensure that its textbox is only numeric
	 * @param event The textbox event to validate
	 * @param maxLength	The maximum length of the numeric string
	 */
	private void verifyNumericTextbox(VerifyEvent event, int maxLength)
	{
		Text text;
		boolean valid = false;
		
		assert (event != null);
		if(event != null)
		{
			text = (Text) event.widget;
			
			if(text != null && maxLength > 0)
			{
				//Check if the textbox is numeric
				if(event.character == SWT.BS || event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT || 
						event.keyCode == SWT.DEL || event.keyCode == SWT.NULL)
					valid = true;
				else if(Character.isDigit(event.character) && text.getText().length() < maxLength)
					valid = true;
				
				event.doit = valid;
			}
		}
	}
}

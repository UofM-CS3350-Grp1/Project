package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.StackLayout;

import java.util.ArrayList;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;
import business.ProcessClient;

public class ClientScreenDrawer
{
	private Composite composite;
	
	private Text txtClientName;
	private Text txtBusinessName;
	private Text txtAddress;
	private Text txtEmail;
	
	private Text txtPhoneNumberA;
	private Text txtPhoneNumberB;
	private Text txtPhoneNumberC;
	
	private Button btnPotential;
	private Button btnActive;
	
	private List listClients;
	private ArrayList<Client> clients;
	private ProcessClient processClient;
	
	private Composite addUpdateHolder;
	private StackLayout addUpdateSwitcher;
	private Button btnAdd;
	private Button btnUpdate;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public ClientScreenDrawer( Composite container )
	{
		composite = new Composite( container, SWT.BORDER );
		
		// units = grid columns
		final int GRID_WIDTH = 11;
		final int LIST_WIDTH = 3;
		final int LIST_HEIGHT = 7;
		final int TEXT_WIDTH = 7;
		
		/*
		 * organizes the component
		 */
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = GRID_WIDTH;
		composite.setLayout( compositeLayout );
		
		
		/*
		 * organizes how components look within composites
		 */
		GridData componentTweaker = null;
		
		
		/*
		 * client list header
		 */
		Label lblClientList = new Label( composite, SWT.None );
		lblClientList.setText( "Client List" );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = GRID_WIDTH;
		lblClientList.setLayoutData( componentTweaker );
		
		
		/*
		 * client list
		 */
		clients = new ArrayList< Client >();
		listClients = new List( composite, SWT.BORDER );
		listClients.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				editSelectedClient();
			}
		});
		componentTweaker = new GridData( GridData.FILL_BOTH ); // each component needs its own instance of GridData
		componentTweaker.horizontalSpan = LIST_WIDTH;
		componentTweaker.verticalSpan = LIST_HEIGHT;
		listClients.setLayoutData( componentTweaker );
		
		/*
		 * populate the list
		 */
		processClient = new ProcessClient();
		Client client = processClient.getNextClient();
		while ( client != null ) {
			clients.add( client );
			listClients.add( client.getName() + " - " + client.getBusinessName() );
			client = processClient.getNextClient();
		}
		
		
		/*
		 *  contact name
		 */
		Label lblName = new Label( composite, SWT.None );
		lblName.setText( "Contact Name" );
		
		txtClientName = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtClientName.setLayoutData( componentTweaker );
		
		
		/*
		 *  business name
		 */
		Label lblBusinessName = new Label( composite, SWT.None );
		lblBusinessName.setText( "Business Name" );
		
		txtBusinessName = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtBusinessName.setLayoutData( componentTweaker );
		
		
		/*
		 *  address
		 */
		Label lblAddress = new Label( composite, SWT.None );
		lblAddress.setText( "Address" );
		
		txtAddress = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtAddress.setLayoutData( componentTweaker );
		
		
		/*
		 *  email
		 */
		Label lblEmail = new Label( composite, SWT.None );
		lblEmail.setText( "Email" );
		
		txtEmail = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtEmail.setLayoutData( componentTweaker );
		
		
		/*
		 *  phone number fields
		 */
		final int AREA_CODE_LENGTH = 3;
		final int PREFIX_CODE_LENGTH = 3;
		final int LINE_NUMBER_CODE = 4;
		
		Label lblPhoneNumber = new Label( composite, SWT.None );
		lblPhoneNumber.setText( "Phone Number" );
		
		Label lblOpenBracket = new Label( composite, SWT.None );
		lblOpenBracket.setText( "(" );
		
		txtPhoneNumberA = new Text( composite, SWT.BORDER | SWT.CENTER );
		txtPhoneNumberA.addVerifyListener( new VerifyListener()
		{
			public void verifyText( VerifyEvent event )
			{
				verifyNumericTextbox( event, AREA_CODE_LENGTH );
			}
		});
		
		Label lblCloseBracket = new Label( composite, SWT.None );
		lblCloseBracket.setText( ")" );
		
		Label lblPhoneSep1 = new Label( composite, SWT.None );
		lblPhoneSep1.setText( "-" );
		
		txtPhoneNumberB = new Text( composite, SWT.BORDER | SWT.CENTER );
		txtPhoneNumberB.addVerifyListener( new VerifyListener()
		{
			public void verifyText( VerifyEvent event )
			{
				verifyNumericTextbox( event, PREFIX_CODE_LENGTH );
			}
		});
		
		Label lblPhoneSep2 = new Label( composite, SWT.None );
		lblPhoneSep2.setText( "-" );
		
		txtPhoneNumberC = new Text( composite, SWT.BORDER | SWT.CENTER );
		txtPhoneNumberC.addVerifyListener( new VerifyListener()
		{
			public void verifyText( VerifyEvent event )
			{
				verifyNumericTextbox( event, LINE_NUMBER_CODE );
			}
		});
		
		
		/*
		 *  client status
		 */
		final int CLIENT_STATUS_OPTION_COUNT = 2;
		final int GROUP_SIZE = 7; // this lets this components around it format properly
		Label lblClientStatus = new Label( composite, SWT.None );
		lblClientStatus.setText( "Client Status" );
		
		Group group = new Group( composite, SWT.None );
		group.setLayout( new FillLayout( SWT.VERTICAL ) );
		componentTweaker = new GridData();
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		group.setLayoutData( componentTweaker );
		
		btnPotential = new Button( group, SWT.RADIO );
		btnPotential.setSelection( true );
		btnPotential.setText( "Potential" );
		
		btnActive = new Button( group, SWT.RADIO );
		btnActive.setText( "Active" );
		
		
		/*
		 * buttons clear and add/update
		 */
		final int NON_LIST_GRID_WIDTH = 8;
		final int ADD_UPDATE = 1;
		final int CLEAR = 1;
		Composite buttonHolder = new Composite( composite, SWT.RIGHT_TO_LEFT );
		GridLayout rightJustifiedButtons = new GridLayout();
		rightJustifiedButtons.numColumns = ADD_UPDATE + CLEAR;
		buttonHolder.setLayout( rightJustifiedButtons );
		GridData oneRowAndStretchesHorizontally = new GridData( GridData.FILL_HORIZONTAL );
		oneRowAndStretchesHorizontally.horizontalSpan = NON_LIST_GRID_WIDTH;
		buttonHolder.setLayoutData( oneRowAndStretchesHorizontally );
		
		addUpdateHolder = new Composite( buttonHolder, SWT.None );
		addUpdateSwitcher = new StackLayout();
		addUpdateHolder.setLayout( addUpdateSwitcher );
		
		btnAdd = new Button( addUpdateHolder, SWT.None );
		componentTweaker = new GridData();
		btnAdd.setLayoutData( componentTweaker );
		btnAdd.setText( "Add" );
		btnAdd.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				createClient();
			}
		});
		
		btnUpdate = new Button( addUpdateHolder, SWT.None );
		componentTweaker = new GridData();
		btnUpdate.setLayoutData( componentTweaker );
		btnUpdate.setText( "Update" );
		
		addUpdateSwitcher.topControl = btnAdd;
		addUpdateHolder.layout();
		
		Button btnClear = new Button( buttonHolder, SWT.None );
		componentTweaker = new GridData();
		btnClear.setLayoutData( componentTweaker );
		btnClear.setText( "Clear" );
		btnClear.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				clearFields();
			}
		});
		
		
		
		// list buttons
		Composite listButtons = new Composite( composite, SWT.BORDER );
		FillLayout listButtonsLayout = new FillLayout();
		listButtons.setLayout( listButtonsLayout );
		GridData listButtonsGridFormat = new GridData( GridData.FILL_HORIZONTAL );
		listButtonsGridFormat.horizontalSpan = LIST_WIDTH; // sizes the list
		listButtons.setLayoutData( listButtonsGridFormat );
		
		Button btnNew = new Button( listButtons, SWT.None );
		btnNew.setText( "New" );
		btnNew.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				processNewButton();
			}
			
		});
		
		
		Button btnDelete = new Button( listButtons, SWT.None );
		btnDelete.setText( "Delete" );
		btnDelete.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				int selectedIndex = listClients.getSelectionIndex();
				if ( selectedIndex != -1 )
				{
					/*
					 * delete the client
					 */
					clients.remove( selectedIndex );
					listClients.remove( selectedIndex );
					
					/*
					 * with the current client deleted, select some other entry
					 */
					selectedIndex--;
					if ( selectedIndex < 0 && listClients.getItemCount() > 0 ) selectedIndex = 0;
					
					listClients.setSelection( selectedIndex );
					
					if ( selectedIndex >= 0 )
					{
						// show some other client's data
						editSelectedClient();
					}
					else
					{
						// no list entries
						processNewButton();
					}
				}
			}
		});
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
	 * Creates a client given the data supplied on the form
	 * @return True if the client was created
	 */
	private boolean createClient()
	{
		Client client = null;
		
		if ( isFormDataValid() )
		{
			ClientStatus status = null;
			try {
				if ( btnActive.getSelection() ) status = ClientStatus.Active;
				else status = ClientStatus.Potential;
				
				client = new Client(txtClientName.getText(), new PhoneNumber(txtPhoneNumberA.getText() + txtPhoneNumberB.getText() + txtPhoneNumberC.getText()), 
						new Email(txtEmail.getText()), txtAddress.getText(), txtBusinessName.getText(), status);
				
				if ( processClient.insertClient( client ) )
				{
					clients.add( client );
					int index = clients.indexOf( client );
					listClients.add( client.getName() + " - " + client.getBusinessName(), index );
					
					clearFields();
				}
			}
			catch ( Exception e ) {
				client = null;
			}
		}
		
		return ( client != null );
	}
	
	
	
	/**
	 * Provides very simple validation to ensure that the form data
	 * contains some data.
	 * @return True if valid
	 */
	private boolean isFormDataValid()
	{
		// check if the fields have something in them
		if ( txtClientName.getText() == "" ) return false;
		if ( txtBusinessName.getText() == "" ) return false;
		if ( txtEmail.getText() == "" ) return false;
		if ( txtPhoneNumberA.getText() == "" ) return false;
		if ( txtPhoneNumberB.getText() == "" ) return false;
		if ( txtPhoneNumberC.getText() == "" ) return false;
		
		return true;
	}
	
	
	/**
	 * Opens the currently selected client for editing
	 */
	private void editSelectedClient()
	{
		int selectedIndex = listClients.getSelectionIndex();
		if ( selectedIndex != -1 && selectedIndex < clients.size() )
		{
			Client client = clients.get( selectedIndex );
			
			addUpdateSwitcher.topControl = btnUpdate;
			addUpdateHolder.layout();
			
			populateFields( client );
		}
	}
	
	
	/**
	 * Populates all of the fields for a specific client
	 * @param client The client to show
	 */
	private void populateFields( Client client )
	{
		assert( client != null );
		if ( client != null )
		{
			txtClientName.setText( client.getName() );
			txtBusinessName.setText( client.getBusinessName() );
			txtAddress.setText( client.getAddress() );
			txtEmail.setText( client.getAddress() );
			/*
			// Split the phone number into the three components
			String phoneNumber = client.getPhoneNumber();
			if(phoneNumber.length() == Client.PHONE_NUMBER_LENGTH)
			{
				txtPhoneNumberA.setText(phoneNumber.substring(0, 3));
				txtPhoneNumberB.setText(phoneNumber.substring(3, 6));
				txtPhoneNumberC.setText(phoneNumber.substring(6));
			}
			*/
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
	
	
	/**
	 * Processes the new button and sets up the window for a new client
	 */
	private void processNewButton()
	{
		addUpdateSwitcher.topControl = btnAdd;
		listClients.setSelection( -1 );
		clearFields();
	}
	
	
	/**
	 * Validates a textbox event to ensure that its textbox is only numeric
	 * @param event The textbox event to validate
	 * @param maxLength	The maximum length of the numeric string
	 */
	private void verifyNumericTextbox( VerifyEvent event, int maxLength )
	{
		System.out.println( "VERIFYING NUMERIC TEXTBOX" );
	}
}

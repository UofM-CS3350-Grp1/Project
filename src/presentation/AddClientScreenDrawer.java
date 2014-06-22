package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;
import business.ProcessClient;

public class AddClientScreenDrawer
{
	protected Composite composite;
	
	protected Text txtClientName;
	protected Text txtBusinessName;
	protected Text txtAddress;
	protected Text txtEmail;
	
	protected Text txtPhoneNumberA;
	protected Text txtPhoneNumberB;
	protected Text txtPhoneNumberC;
	
	protected Button btnPotential;
	protected Button btnActive;
	protected ProcessClient processClient;
	protected Button btnAction;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public AddClientScreenDrawer(Composite container)
	{
		composite = new Composite(container, SWT.BORDER);
		processClient = new ProcessClient();
		
		// units = grid columns
		final int TEXT_WIDTH = 7;
		
		/*
		 * organizes the component
		 */
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 8;
		composite.setLayout(compositeLayout);
		
		/*
		 * organizes how components look within composites
		 */
		GridData componentTweaker = null;
				
		/*
		 *  contact name
		 */
		Label lblName = new Label(composite, SWT.None);
		lblName.setText("Contact Name");
		
		txtClientName = new Text(composite, SWT.BORDER);
		componentTweaker = new GridData(GridData.FILL_HORIZONTAL);
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtClientName.setLayoutData(componentTweaker);
		
		
		/*
		 *  business name
		 */
		Label lblBusinessName = new Label(composite, SWT.None);
		lblBusinessName.setText("Business Name");
		
		txtBusinessName = new Text(composite, SWT.BORDER);
		componentTweaker = new GridData(GridData.FILL_HORIZONTAL);
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtBusinessName.setLayoutData(componentTweaker);
		
		
		/*
		 *  address
		 */
		Label lblAddress = new Label(composite, SWT.None);
		lblAddress.setText("Address");
		
		txtAddress = new Text(composite, SWT.BORDER);
		componentTweaker = new GridData(GridData.FILL_HORIZONTAL);
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtAddress.setLayoutData(componentTweaker);
		
		
		/*
		 *  email
		 */
		Label lblEmail = new Label(composite, SWT.None);
		lblEmail.setText("Email");
		
		txtEmail = new Text(composite, SWT.BORDER);
		componentTweaker = new GridData(GridData.FILL_HORIZONTAL);
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtEmail.setLayoutData(componentTweaker);
		
		
		/*
		 *  phone number fields
		 */
		
		Label lblPhoneNumber = new Label(composite, SWT.None);
		lblPhoneNumber.setText("Phone Number");
		
		Label lblOpenBracket = new Label(composite, SWT.None);
		lblOpenBracket.setText("(");
		
		txtPhoneNumberA = new Text(composite, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberA.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.AREA_CODE_LENGTH);
			}
		});
		
		Label lblCloseBracket = new Label(composite, SWT.None);
		lblCloseBracket.setText(")");
		
		Label lblPhoneSep1 = new Label(composite, SWT.None);
		lblPhoneSep1.setText("-");
		
		txtPhoneNumberB = new Text(composite, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberB.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.PREFIX_CODE_LENGTH);
			}
		});
		
		Label lblPhoneSep2 = new Label(composite, SWT.None);
		lblPhoneSep2.setText("-");
		
		txtPhoneNumberC = new Text(composite, SWT.BORDER | SWT.CENTER);
		txtPhoneNumberC.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				verifyNumericTextbox(event, PhoneNumber.LINE_NUMBER_CODE);
			}
		});
		
		
		/*
		 *  client status
		 */
		Label lblClientStatus = new Label(composite, SWT.None);
		lblClientStatus.setText("Client Status");
		
		Group group = new Group(composite, SWT.None);
		group.setLayout(new FillLayout(SWT.VERTICAL));
		componentTweaker = new GridData();
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		group.setLayoutData(componentTweaker);
		
		btnPotential = new Button(group, SWT.RADIO);
		btnPotential.setSelection(true);
		btnPotential.setText("Potential");
		
		btnActive = new Button(group, SWT.RADIO);
		btnActive.setText("Active");
		
		
		/*
		 * buttons clear and add/update
		 */
		final int NON_LIST_GRID_WIDTH = 8;
		Composite buttonHolder = new Composite(composite, SWT.RIGHT_TO_LEFT);
		GridLayout rightJustifiedButtons = new GridLayout();
		buttonHolder.setLayout(rightJustifiedButtons);
		GridData oneRowAndStretchesHorizontally = new GridData(GridData.FILL_HORIZONTAL);
		oneRowAndStretchesHorizontally.horizontalSpan = NON_LIST_GRID_WIDTH;
		buttonHolder.setLayoutData(oneRowAndStretchesHorizontally);
		componentTweaker = new GridData();
		componentTweaker = new GridData();
		
		Composite internalHolder = new Composite(buttonHolder, SWT.NONE);
		GridData gd_internalHolder = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_internalHolder.heightHint = 39;
		gd_internalHolder.widthHint = 124;
		internalHolder.setLayoutData(gd_internalHolder);
		
		btnAction = new Button(internalHolder, SWT.None);
		btnAction.setLocation(54, 10);
		btnAction.setSize(48, 25);
		btnAction.setLayoutData(componentTweaker);
		btnAction.setText("Add");
		btnAction.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processActionButton();
			}
		});
		
		Button btnCancel = new Button(internalHolder, SWT.None);
		btnCancel.setLocation(0, 10);
		btnCancel.setSize(48, 25);
		btnCancel.setText("Cancel");
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				goBackToClientScreen();
			}
		});
	}
	
	/**
	 * Go back to the client screen
	 */
	protected void goBackToClientScreen()
	{
		Composite clientScreen = SwitchScreen.getContentContainer();
		new ClientScreenDrawer( clientScreen );
		SwitchScreen.switchContent( clientScreen );
		/*
		Composite clientScreen = new Composite( SwitchScreen.content, SWT.None );
		clientScreen.setLayout( new FillLayout() );
		ClientScreenDrawer acsd = new ClientScreenDrawer( clientScreen );
		SwitchScreen.contentLayout.topControl = clientScreen;
		SwitchScreen.content.layout();
		*/
	}
	
	/**
	 * Creates a client given the data supplied on the form
	 * @return True if the client was created
	 */
	protected void processActionButton()
	{
		Client client = null;
		
		if (isFormDataValid())
		{
			ClientStatus status = null;
			try
			{
				if (btnActive.getSelection()) status = ClientStatus.Active;
				else status = ClientStatus.Potential;
				
				client = new Client(txtClientName.getText(), new PhoneNumber(txtPhoneNumberA.getText() + txtPhoneNumberB.getText() + txtPhoneNumberC.getText()), 
						new Email(txtEmail.getText()), txtAddress.getText(), txtBusinessName.getText(), status);
				
				if (processClient.insertClient(client))
				{					
					goBackToClientScreen();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				client = null;
			}
		}
	}	
	
	/**
	 * Provides very simple validation to ensure that the form data
	 * contains some data.
	 * @return True if valid
	 */
	protected boolean isFormDataValid()
	{
		// check if the fields have something in them
		if (txtClientName.getText() == "") return false;
		if (txtBusinessName.getText() == "") return false;
		if (txtEmail.getText() == "") return false;
		if (txtPhoneNumberA.getText() == "") return false;
		if (txtPhoneNumberB.getText() == "") return false;
		if (txtPhoneNumberC.getText() == "") return false;
		
		return true;
	}	
	
	/**
	 * Validates a textbox event to ensure that its textbox is only numeric
	 * @param event The textbox event to validate
	 * @param maxLength	The maximum length of the numeric string
	 */
	protected void verifyNumericTextbox(VerifyEvent event, int maxLength)
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

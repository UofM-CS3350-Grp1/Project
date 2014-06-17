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

public class ClientScreenDrawer
{
	private Composite composite;
	
	private Text txtClientName;
	
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
		
		// organizes the component
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = GRID_WIDTH;
		composite.setLayout( compositeLayout );
		
		// organizes how components look within composites
		GridData componentTweaker = null;
		
		// client list header
		Label lblClientList = new Label( composite, SWT.None );
		lblClientList.setText( "Client List" );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = GRID_WIDTH;
		lblClientList.setLayoutData( componentTweaker );
		
		// client list
		List listClients = new List( composite, SWT.BORDER );
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
		
		
		// contact name
		Label lblName = new Label( composite, SWT.None );
		lblName.setText( "Contact Name" );
		
		txtClientName = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtClientName.setLayoutData( componentTweaker );
		
		
		// business name
		Label lblBizName = new Label( composite, SWT.None );
		lblBizName.setText( "Business Name" );
		
		Text txtBizName = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtBizName.setLayoutData( componentTweaker );
		
		
		// address
		Label lblAddress = new Label( composite, SWT.None );
		lblAddress.setText( "Address" );
		
		Text txtAddress = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtAddress.setLayoutData( componentTweaker );
		
		
		// email
		Label lblEmail = new Label( composite, SWT.None );
		lblEmail.setText( "Email" );
		
		Text txtEmail = new Text( composite, SWT.BORDER );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		txtEmail.setLayoutData( componentTweaker );
		
		
		// phone number fields
		final int AREA_CODE_LENGTH = 3;
		final int PREFIX_CODE_LENGTH = 3;
		final int LINE_NUMBER_CODE = 4;
		
		Label lblPhoneNumber = new Label( composite, SWT.None );
		lblPhoneNumber.setText( "Phone Number" );
		
		Label lblOpenBracket = new Label( composite, SWT.None );
		lblOpenBracket.setText( "(" );
		
		Text txtPhoneNumberA = new Text( composite, SWT.BORDER );
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
		
		Text txtPhoneNumberB = new Text( composite, SWT.BORDER | SWT.CENTER );
		txtPhoneNumberB.addVerifyListener( new VerifyListener()
		{
			public void verifyText( VerifyEvent event )
			{
				verifyNumericTextbox( event, PREFIX_CODE_LENGTH );
			}
		});
		
		Label lblPhoneSep2 = new Label( composite, SWT.None );
		lblPhoneSep2.setText( "-" );
		
		Text txtPhoneNumberC = new Text( composite, SWT.BORDER | SWT.CENTER );
		txtPhoneNumberC.addVerifyListener( new VerifyListener()
		{
			public void verifyText( VerifyEvent event )
			{
				verifyNumericTextbox( event, LINE_NUMBER_CODE );
			}
		});
		
		
		// client status
		final int CLIENT_STATUS_OPTION_COUNT = 2;
		final int GROUP_SIZE = 7; // this lets this components around it format properly
		Label lblClientStatus = new Label( composite, SWT.None );
		lblClientStatus.setText( "Client Status" );
		
		Group group = new Group( composite, SWT.None );
		group.setLayout( new FillLayout( SWT.VERTICAL ) );
		componentTweaker = new GridData();
		componentTweaker.horizontalSpan = TEXT_WIDTH;
		group.setLayoutData( componentTweaker );
		
		Button btnPotential = new Button( group, SWT.RADIO );
		btnPotential.setSelection( true );
		btnPotential.setText( "Potential" );
		
		Button btnActive = new Button( group, SWT.RADIO );
		btnActive.setText( "Active" );
		
		
		// buttons
		final int BUTTON_HOLDER_WIDTH = 8;
		Composite buttonHolder = new Composite( composite, SWT.None );
		GridLayout buttonHolderLayout = new GridLayout();
		buttonHolderLayout.numColumns = 5;
		buttonHolder.setLayout( buttonHolderLayout );
		GridData buttonHolderData = new GridData( GridData.FILL_HORIZONTAL );
		buttonHolderData.horizontalSpan = BUTTON_HOLDER_WIDTH;
		buttonHolder.setLayoutData( buttonHolderData );
		
		Label filler1 = new Label( buttonHolder, SWT.None );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = 1;
		filler1.setLayoutData( componentTweaker );
		Label filler2 = new Label( buttonHolder, SWT.None );
		componentTweaker = new GridData( GridData.FILL_HORIZONTAL );
		componentTweaker.horizontalSpan = 1;
		filler2.setLayoutData( componentTweaker );
		
		Button btnAdd = new Button( buttonHolder, SWT.None );
		componentTweaker = new GridData();
		btnAdd.setLayoutData( componentTweaker );
		btnAdd.setText( "Add" );
		
		Button btnClear = new Button( buttonHolder, SWT.None );
		componentTweaker = new GridData();
		btnClear.setLayoutData( componentTweaker );
		btnClear.setText( "Clear" );
		
		Button btnUpdate = new Button( buttonHolder, SWT.None );
		componentTweaker = new GridData();
		btnUpdate.setLayoutData( componentTweaker );
		btnUpdate.setText( "Update" );
		
		// list buttons
		Composite listButtons = new Composite( composite, SWT.BORDER );
		FillLayout listButtonsLayout = new FillLayout();
		listButtons.setLayout( listButtonsLayout );
		GridData listButtonsGridFormat = new GridData( GridData.FILL_HORIZONTAL );
		listButtonsGridFormat.horizontalSpan = LIST_WIDTH; // sizes the list
		listButtons.setLayoutData( listButtonsGridFormat );
		
		Button btnNew = new Button( listButtons, SWT.None );
		btnNew.setText( "New" );
		
		Button btnDelete = new Button( listButtons, SWT.None );
		btnDelete.setText( "Delete" );
	}
	
	private void editSelectedClient()
	{
		System.out.println( "EDITING SELECTED CLIENT" );
	}
	
	private void processNewButton()
	{
		System.out.println( "LIST: NEW BUTTON CLICKED" );
	}
	
	private void verifyNumericTextbox( VerifyEvent event, int maxLength )
	{
		System.out.println( "VERIFYING NUMERIC TEXTBOX" );
	}
}

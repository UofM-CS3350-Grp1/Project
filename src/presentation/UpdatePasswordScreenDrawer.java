package presentation;

import business.ProcessUser;
import objects.User;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class UpdatePasswordScreenDrawer 
{
	private Composite composite;
	private ProcessUser processUser;
	protected Button btnSubmit;
	protected Button btnCancel;
	protected Text txtPass1;
	protected Text txtPass2;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public UpdatePasswordScreenDrawer ( Composite container ) 
	{
		composite = new Composite( container, SWT.None );

		// units = grid columns
		final int COMPOSITE_WIDTH = 2;

		// organizer
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		compositeLayout.makeColumnsEqualWidth = true;
		composite.setLayout( compositeLayout );

		GridData componentTweaker = null;

		GridData gd_lblField1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblField1.widthHint = 85;

		GridData gd_lblField2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblField2.widthHint = 85;

		// New password
		Label lblPass1 = new Label( composite, SWT.None );
		lblPass1.setText( "New password: " );
		lblPass1.setLayoutData( componentTweaker );

		txtPass1 = new Text( composite, SWT.BORDER | SWT.PASSWORD );
		txtPass1.setLayoutData( gd_lblField1 );

		// Password confirmation
		Label lblPass2 = new Label( composite, SWT.None );
		lblPass2.setText( "Confirm password: " );
		lblPass2.setLayoutData( componentTweaker );

		txtPass2 = new Text( composite, SWT.BORDER | SWT.PASSWORD );
		txtPass2.setLayoutData( gd_lblField2 );

		// Submit button
		btnSubmit = new Button( composite, SWT.None );
		GridData gd_btnSubmit = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnSubmit.widthHint = 100;
		btnSubmit.setLayoutData(gd_btnSubmit);
		btnSubmit.setText( "Submit" );

		btnSubmit.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processSubmitButton();
			}
		});

		// Cancel button
		btnCancel = new Button( composite, SWT.None );
		GridData gd_btnCancel = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnCancel.widthHint = 100;
		btnCancel.setLayoutData(gd_btnCancel);
		btnCancel.setText( "Cancel" );

		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processCancelButton();
			}
		});

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
	}

	protected void processSubmitButton()
	{
		if (isFormDataValid())
		{
			updateUserPassword();

			/*
			 * draws the logged in screen
			 */
			Composite loggedInScreen = SwitchScreen.getContentContainer();
			LoggedInDrawer ldd = new LoggedInDrawer( loggedInScreen );
			SwitchScreen.switchContent( loggedInScreen );
		}
	}

	protected void processCancelButton()
	{
		/*
		 * draws the logged in screen
		 */
		Composite loggedInScreen = SwitchScreen.getContentContainer();
		LoggedInDrawer ldd = new LoggedInDrawer( loggedInScreen );
		SwitchScreen.switchContent( loggedInScreen );

	}

	protected boolean isFormDataValid()
	{
		boolean isValid = false;
		MessageBox dialog;

		// check if the fields have something in them
		if ((txtPass1.getText() != "") && (txtPass2.getText() != ""))
		{
			// the check if they match
			if (!(txtPass1.getText()).equals(txtPass2.getText()))
			{
				dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
				dialog.setText("Input Error");
				dialog.setMessage("Passwords do not match!");
				dialog.open();
			}
			else
			{
				isValid = true;
			}
		}
		else
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Login Error");
			dialog.setMessage("User and/or Password cannot be blank");
			dialog.open();
		}

		return isValid;
	}

	private void updateUserPassword()
	{
		String currentUser = User.getCurrentUser();
		MessageBox dialog;

		/*
		 * Need function to update 'password' for
		 * USER table in the database
		 * 
		 */

		dialog = new MessageBox(new Shell(), SWT.ICON_INFORMATION | SWT.OK);
		dialog.setText("Success");
		dialog.setMessage("Password was updated successfully.");
		dialog.open();
	}
}

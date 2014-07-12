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

import acceptanceTests.Register;

public class LoginDrawer 
{
	private Composite composite;
	private ProcessUser processUser;
	protected Button btnLogin;
	protected Button btnForgot;
	protected Text txtUser;
	protected Text txtPass;
	
	@SuppressWarnings("unused")
	private Shell shell;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoginDrawer( Composite container ) 
	{
		Register.newWindow(this);
		composite = new Composite( container, SWT.None );

		// units = grid columns
		final int COMPOSITE_WIDTH = 2;

		// organizer
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		compositeLayout.makeColumnsEqualWidth = true;
		composite.setLayout( compositeLayout );

		GridData componentTweaker = null;

		GridData gd_lblFields = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblFields.widthHint = 85;

		// username
		Label lblUser = new Label( composite, SWT.None );
		lblUser.setText( "User: " );
		lblUser.setLayoutData( componentTweaker );

		txtUser = new Text( composite, SWT.BORDER );
		txtUser.setLayoutData( gd_lblFields );

		// password
		Label lblPass = new Label( composite, SWT.None );
		lblPass.setText( "Password: " );
		lblPass.setLayoutData( componentTweaker );

		txtPass = new Text( composite, SWT.BORDER | SWT.PASSWORD );
		txtPass.setLayoutData( gd_lblFields );

		// login button
		Button btnLogin = new Button( composite, SWT.None );
		GridData gd_btnLogin = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLogin.widthHint = 100;
		btnLogin.setLayoutData(gd_btnLogin);
		btnLogin.setText( "Login" );

		btnLogin.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processLoginButton();
			}
		});

		// forgot pw button
		Button btnForgot_1 = new Button( composite, SWT.None );
		btnForgot_1.setText( "Forgot Password" );

		btnForgot_1.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processForgotButton();
			}
		});

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		// List usernames and pw's for now
		Label lblLoginInfo = new Label( composite, SWT.None );
		lblLoginInfo.setText( "\nUsers\tPasswords\n\nAdrian\tpassword\nDell\tpassword\nDerek\tpassword\nJason\tpassword\nKarl\tpassword\nTim\tpassword\n" );
		lblLoginInfo.setLayoutData( componentTweaker );
		new Label(composite, SWT.NONE);
	}

	protected void processLoginButton()
	{
		MessageBox dialog;
		boolean validCredentials = false;

		if (isFormDataValid())
		{
			processUser = new ProcessUser();

			validCredentials = processUser.validateUser(txtUser.getText(), txtPass.getText());
			if (validCredentials)
			{
				/*
				 * draws the logged in screen
				 */
				User.setCurrentUser(txtUser.getText());
				SwitchScreen.enableButtons();
				SwitchScreen.enableMenus();
				Composite loggedInScreen = SwitchScreen.getContentContainer();
				LoggedInDrawer ldd = new LoggedInDrawer( loggedInScreen );
				SwitchScreen.switchContent( loggedInScreen );
			}
			else
			{
				dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
				dialog.setText("Login Error");
				dialog.setMessage("Invalid user name and/or password");
				dialog.open();
			}
		}
		else
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Login Error");
			dialog.setMessage("User and/or Password cannot be blank");
			dialog.open();
		}
	}

	protected void processForgotButton()
	{
		MessageBox dialog;
		dialog = new MessageBox(new Shell(), SWT.ICON_INFORMATION | SWT.OK);
		dialog.setText("Sorry");
		dialog.setMessage("Not implemented yet...");
		dialog.open();
	}

	protected boolean isFormDataValid()
	{
		boolean isValid = true;

		// check if the fields have something in them
		isValid = ((txtUser.getText() != "") && (txtPass.getText() != ""));

		return isValid;
	}
}

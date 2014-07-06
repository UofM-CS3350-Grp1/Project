package presentation;

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

public class LoginDrawer 
{
	private Composite composite;
	private String loggedInAs;
	protected Button btnLogin;
	protected Text txtUser;
	protected Text txtPass;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoginDrawer( Composite container ) 
	{
		composite = new Composite( container, SWT.None );
		loggedInAs = null;

		// units = grid columns
		final int COMPOSITE_WIDTH = 2;
		
		// organizer
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = COMPOSITE_WIDTH;
		compositeLayout.makeColumnsEqualWidth = true;
		composite.setLayout( compositeLayout );
		
		GridData componentTweaker = null;

		// username
		Label lblUser = new Label( composite, SWT.None );
		lblUser.setText( "User: ( Type admin )" );
		lblUser.setLayoutData( componentTweaker );
		
		txtUser = new Text( composite, SWT.BORDER );
		txtUser.setLayoutData( componentTweaker );
		
		// password
		Label lblPass = new Label( composite, SWT.None );
		lblPass.setText( "Password: ( Type password )" );
		lblPass.setLayoutData( componentTweaker );
		
		txtPass = new Text( composite, SWT.BORDER | SWT.PASSWORD );
		txtPass.setLayoutData( componentTweaker );

		// login button
		Button btnLogin = new Button( composite, SWT.None );
		btnLogin.setText( "Login" );

		btnLogin.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processLoginButton();
			}
		});
	}

	protected void processLoginButton()
	{
		MessageBox dialog;
		
		if (isFormDataValid())
		{
			// Validate Login Credentials from DB(?)
		}
		else
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Login Error");
			dialog.setMessage("User and/or Password cannot be blank");
			dialog.open();
		}
	}

	protected boolean isFormDataValid()
	{
		boolean isValid = true;

		// check if the fields have something in them
		isValid = ((txtUser.getText() != "") && (txtPass.getText() != ""));

		return isValid;
	}

	public String getCurrentUser()
	{
		return loggedInAs;
	}
}

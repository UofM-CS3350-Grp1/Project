package presentation;

import business.ProcessUser;
import objects.User;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
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
	private Label lblBuzzinDigitalMarketing;

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
		compositeLayout.numColumns = 4;
		composite.setLayout( compositeLayout );

		GridData componentTweaker = null;

		
		//Image
		Display display = Display.getCurrent();
		Image myImage = new Image( display, "C:/Users/"+System.getProperty("user.name")+"/git/Project/src/Presentation/logo.png" );
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label spareTop = new Label(composite, SWT.NONE);
		GridData gd_spareTop = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spareTop.heightHint = 88;
		spareTop.setLayoutData(gd_spareTop);
		new Label(composite, SWT.NONE);
		Label x = new Label(composite, SWT.NONE);
		GridData gd_x = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_x.widthHint = 328;
		x.setLayoutData(gd_x);
		
		new Label(composite, SWT.NONE);
		Label myLabel = new Label( composite, SWT.NONE );
		myLabel.setImage( myImage );
		
		lblBuzzinDigitalMarketing = new Label(composite, SWT.NONE);
		lblBuzzinDigitalMarketing.setText("BUZZIN' DIGITAL MARKETING");
		
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		
		
		
		// username
		Label lblUser = new Label( composite, SWT.None );
		lblUser.setText( "User: " );
		lblUser.setLayoutData( componentTweaker );
				
						GridData gd_lblFields = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
						gd_lblFields.widthHint = 85;
						
								txtUser = new Text( composite, SWT.BORDER );
								txtUser.setLayoutData( gd_lblFields );
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
				// password
				Label lblPass = new Label( composite, SWT.None );
				lblPass.setText( "Password: " );
				lblPass.setLayoutData( componentTweaker );
		GridData gd_lblFields2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblFields2.widthHint = 85;
		
				txtPass = new Text( composite, SWT.BORDER | SWT.PASSWORD );
				txtPass.setLayoutData( gd_lblFields2 );
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
				// login button
				Button btnLogin_1 = new Button( composite, SWT.None );
				GridData gd_btnLogin_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
				gd_btnLogin_1.widthHint = 60;
				btnLogin_1.setLayoutData(gd_btnLogin_1);
				btnLogin_1.setText( "Login" );
				
						btnLogin_1.addSelectionListener(new SelectionAdapter()
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

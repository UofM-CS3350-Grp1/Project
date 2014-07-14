package presentation;

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


public class LoggedInDrawer 
{
	private Composite composite;
	protected Button btnLogout;
	protected Button btnChangePW;
	protected Label lblActiveUser;
	
	@SuppressWarnings("unused")
	private Shell shell;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoggedInDrawer( Composite container ) 
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

		String loggedInAs = User.getCurrentUser();

		// username
		Label lblUser = new Label( composite, SWT.None );
		lblUser.setText( "Logged in as: ");
		lblUser.setLayoutData( componentTweaker );

		lblActiveUser = new Label( composite, SWT.None );
		lblActiveUser.setText(loggedInAs);
		lblActiveUser.setLayoutData( componentTweaker );

		composite.setLayout( compositeLayout );
		
		// Change PW button
		btnChangePW = new Button( composite, SWT.None );
		btnChangePW.setText( "Update Password" );
		
		btnChangePW.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processChangePWButton();
			}
		});

		// logout button
		btnLogout = new Button( composite, SWT.None );
		GridData gd_btnLogout = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLogout.widthHint = 102;
		btnLogout.setLayoutData(gd_btnLogout);
		btnLogout.setText( "Logout" );

		btnLogout.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processLogoutButton();
			}
		});

	}

	protected void processLogoutButton()
	{
		User.logout();
		SwitchScreen.disableButtons();
		SwitchScreen.disableMenus();
		Composite loginScreen = SwitchScreen.getContentContainer();
		LoginDrawer ld = new LoginDrawer( loginScreen );
		SwitchScreen.switchContent( loginScreen );
		
		SwitchScreen.eraseBackHistory();
	}

	protected void processChangePWButton()
	{
		Composite updatePW = SwitchScreen.getContentContainer();
		UpdatePasswordScreenDrawer pwd = new UpdatePasswordScreenDrawer( updatePW );
		SwitchScreen.switchContent( updatePW );
	}
}

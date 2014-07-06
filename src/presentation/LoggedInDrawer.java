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

public class LoggedInDrawer 
{
	private Composite composite;
	private String loggedInAs;
	protected Button btnLogout;
	private LoginDrawer ld;
	private Composite loginScreen;
	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoggedInDrawer( Composite container ) 
	{
		loginScreen = SwitchScreen.getContentContainer();
		ld = new LoginDrawer( loginScreen );

		composite = new Composite( container, SWT.None );
		loggedInAs = ld.getCurrentUser();

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
		lblUser.setText( "Logged in as: " + loggedInAs );
		lblUser.setLayoutData( componentTweaker );

		composite.setLayout( compositeLayout );

		// logout button
		Button btnLogout = new Button( composite, SWT.None );
		btnLogout.setText( "Logout" );

		btnLogout.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				processLogoutButton(loginScreen);
			}
		});
	}

	protected void processLogoutButton(Composite loginScreen)
	{
		loggedInAs = null;
		ld.logout();
		SwitchScreen.switchContent( loginScreen );
	}

	public String getCurrentUser()
	{
		return loggedInAs;
	}
}
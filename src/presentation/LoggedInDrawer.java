package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import objects.User;

public class LoggedInDrawer 
{
	private Composite composite;
	protected Button btnLogout;

	/**
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoggedInDrawer( Composite container ) 
	{
		composite = new Composite( container, SWT.None );

		// units = grid columns
		final int COMPOSITE_WIDTH = 2;

		// organizer
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.makeColumnsEqualWidth = true;
		composite.setLayout( compositeLayout );

		GridData componentTweaker = null;

		String loggedInAs = User.getCurrentUser();
		// username
		Label lblUser = new Label( composite, SWT.None );
		lblUser.setText( "Logged in as: " + loggedInAs );
		lblUser.setLayoutData( componentTweaker );

		composite.setLayout( compositeLayout );
		
		// logout button
		Button btnLogout_1 = new Button( composite, SWT.None );
		btnLogout_1.setText( "Logout" );
		
		btnLogout_1.addSelectionListener(new SelectionAdapter()
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
		Composite loginScreen = SwitchScreen.getContentContainer();
		LoginDrawer ld = new LoginDrawer( loginScreen );
		SwitchScreen.switchContent( loginScreen );
	}
}

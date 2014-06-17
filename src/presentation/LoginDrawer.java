package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class LoginDrawer {
	private Composite composite;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public LoginDrawer( Composite container ) {
		composite = new Composite( container, SWT.None );
		
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
		
		Text txtUser = new Text( composite, SWT.BORDER );
		txtUser.setLayoutData( componentTweaker );
		
		// password
		Label lblPass = new Label( composite, SWT.None );
		lblPass.setText( "Password: ( Type password )" );
		lblPass.setLayoutData( componentTweaker );
		
		Text txtPass = new Text( composite, SWT.BORDER );
		txtPass.setLayoutData( componentTweaker );
		
		// login button
		Button btnLogin = new Button( composite, SWT.None );
		btnLogin.setText( "Login" );
	}
}

package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swt.layout.GridData; //
import org.eclipse.swt.layout.GridLayout; //
import org.eclipse.swt.layout.RowLayout; //
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import presentation.NewClientScreenDrawer;

public class SwitchScreen {
	private static final int WIN_WIDTH = 640;
	private static final int WIN_HEIGHT = 480;
	private static final String WIN_TEXT = "Buzzin' Digital Marketing";
	
	static int pageNum = -1;
	static StackLayout contentLayout;
	static Composite content;
	
	public static void main( String[] args ) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		initShell( shell );
		
		/*
		 * Create the navigation bar
		 */
		final int NUM_NAV_BUTTONS = 7;
		Composite navBar = new Composite( shell, SWT.BORDER );
		GridLayout navLayout = new GridLayout();
		navLayout.numColumns = NUM_NAV_BUTTONS;
		navLayout.makeColumnsEqualWidth = true;
		navBar.setLayout( navLayout );
		GridData navData = new GridData( GridData.FILL_HORIZONTAL ); // expand to shell width
		navBar.setLayoutData( navData );
		
		/*
		 * adds the buttons to the nav bar; more than six will cause it to overflow
		 * to a new row; if you need more buttons, adjust num columns above
		 */
		Button bBack = new Button( navBar, SWT.FLAT );
		tuneNavButton( bBack, "BACK" );
		
		Button bHome = new Button( navBar, SWT.FLAT );
		tuneNavButton( bHome, "HOME" );

		Button bClients = new Button( navBar, SWT.FLAT );
		tuneNavButton( bClients, "CLIENTS" );
		
		Button bServices = new Button( navBar, SWT.FLAT );
		tuneNavButton( bServices, "SERVICES" );
		
		Button bSettings = new Button( navBar, SWT.FLAT );
		tuneNavButton( bSettings, "SETTINGS" );
		
		Button bLogin = new Button( navBar, SWT.FLAT );
		tuneNavButton( bLogin, "LOG IN" );
		
		Button bExit = new Button( navBar, SWT.FLAT );
		tuneNavButton( bExit, "EXIT" );
		
		/*
		 * create the switching composite
		 */
		content = new Composite( shell, SWT.BORDER  );
		GridData contentFormatter = new GridData( GridData.FILL_BOTH ); // expands the composite
		content.setLayoutData( contentFormatter );
		contentLayout = new StackLayout(); // allows switching between composites
		content.setLayout( contentLayout );
		
		
		/*
		 *  draws the client screen
		 */
		final Composite clientScreen = new Composite( content, SWT.None );
		clientScreen.setLayout( new FillLayout() );
		//ClientScreenDrawer csd = new ClientScreenDrawer( clientScreen );
		NewClientScreenDrawer csd = new NewClientScreenDrawer( clientScreen );
		
		/*
		 *  gives the clients button the ability to switch to the client composite
		 */
		bClients.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				contentLayout.topControl = clientScreen;
				content.layout();
			}
		});
		
		
		/*
		 *  draws the service screen
		 */
		final Composite serviceScreen = new Composite( content, SWT.None );
		serviceScreen.setLayout( new FillLayout() );
		ServiceScreenDrawer ssd = new ServiceScreenDrawer( serviceScreen );
		
		/*
		 *  gives the clients button the ability to switch to the client composite
		 */
		bServices.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				contentLayout.topControl = serviceScreen;
				content.layout();
			}
		});
		
		
		/*
		 *  draws the login screen
		 */
		final Composite loginScreen = new Composite( content, SWT.None );
		loginScreen.setLayout( new FillLayout() );
		LoginDrawer ld = new LoginDrawer( loginScreen );
		
		/*
		 *  gives the login button the ability to switch to the client composite
		 */
		bLogin.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				contentLayout.topControl = loginScreen;
				content.layout();
			}
		});
		
		/*
		 *  exits the program
		 */
		bExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				((Control) event.getSource()).getShell().dispose();
			}
		});
		
		
		
		
		shell.open();
		shell.layout();
		
		contentLayout.topControl = loginScreen;
		content.layout();
		
		
		while ( ! shell.isDisposed() ) {
			if ( ! display.readAndDispatch() ) {
				display.sleep();
			}
		}
		
		// System.out.println( "END." );
	}
	
	/*
	 * all your shell tweaking needs should go here
	 */
	private static void initShell( Shell srcShell ) {
		srcShell.setSize( WIN_WIDTH, WIN_HEIGHT );
		srcShell.setText( WIN_TEXT );
		
		GridLayout shellLayout = new GridLayout();
		shellLayout.numColumns = 1;
		srcShell.setLayout( shellLayout );
	}
	
	/*
	 * all your button tweaking needs for the nav bar should go here
	 */
	private static void tuneNavButton( Button navButton, String label ) {
		GridData navButtonData = new GridData( GridData.FILL_HORIZONTAL );
		navButtonData.heightHint = 50;
		navButtonData.widthHint = 60;
		navButtonData.horizontalSpan = 1;
		
		navButton.setText( label );
		navButton.setLayoutData( navButtonData );
	}
}

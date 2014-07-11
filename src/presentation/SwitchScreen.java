package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import presentation.ClientScreenDrawer;
import presentation.ServiceScreenDrawer;
import objects.User;

import acceptanceTests.Register;
import acceptanceTests.EventLoop;

import java.util.Deque;
import java.util.ArrayDeque;

public class SwitchScreen
{
	private static final int WIN_WIDTH = 680;
	private static final int WIN_HEIGHT = 480;
	private static final String WIN_TEXT = "Buzzin' Digital Marketing";

	static int pageNum = -1;
	private static StackLayout contentLayout;
	private static Composite content;

	private static Deque< Composite > backStack;

	private static Button bBack;
	private static Button bClients;
	private static Button bContract;
	private static Button bServices;
	private static Button bFeatures;
	private static Button bJCC;
	private static Button bLogin;
	private static Button bExit;
	private static Display display;
	private static Shell shell;

	SwitchScreen()
	{	
		backStack = new ArrayDeque< Composite >();

		display = Display.getDefault();
		shell = new Shell();
		initShell( shell );
		Register.newWindow(this);

		/*
		 * Create the navigation bar
		 */
		final int NUM_NAV_BUTTONS = 8;
		Composite navBar = new Composite( shell, SWT.BORDER );
		GridLayout navLayout = new GridLayout();
		navLayout.numColumns = NUM_NAV_BUTTONS;
		navLayout.makeColumnsEqualWidth = true;
		navBar.setLayout( navLayout );
		navBar.setLayoutData( new GridData( GridData.FILL_HORIZONTAL ) );

		/*
		 * adds the buttons to the nav bar; more than six will cause it to overflow
		 * to a new row; if you need more buttons, adjust num columns above
		 */
		bBack = new Button( navBar, SWT.FLAT );
		tuneNavButton( bBack, "BACK" );

		bClients = new Button( navBar, SWT.FLAT );
		tuneNavButton( bClients, "CLIENTS" );

		bContract = new Button( navBar, SWT.FLAT );
		tuneNavButton( bContract, "CONTRACTS" );

		bServices = new Button( navBar, SWT.FLAT );
		tuneNavButton( bServices, "SERVICES" );

		bFeatures = new Button( navBar, SWT.FLAT );
		tuneNavButton( bFeatures, "FEATURES" );

		bJCC = new Button( navBar, SWT.FLAT );
		tuneNavButton( bJCC, "JCC's" );

		bLogin = new Button( navBar, SWT.FLAT );
		tuneNavButton( bLogin, "LOG IN" );

		bExit = new Button( navBar, SWT.FLAT );
		tuneNavButton( bExit, "EXIT" );

		disableButtons();

		/*
		 * Back button listener
		 */
		bBack.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				switchBack();
			}
		});

		/*
		 * create the switching composite
		 */
		content = new Composite( shell, SWT.BORDER  );
		content.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		contentLayout = new StackLayout(); // allows switching between composites
		content.setLayout( contentLayout );

		/*
		 *  draws the contracts screen
		 */
		Composite contractScreen = SwitchScreen.getContentContainer();
		SwitchScreen.switchContent( contractScreen );
		bContract.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite contractScreen = SwitchScreen.getContentContainer();
				ContractScreenDrawer conSD = new ContractScreenDrawer( contractScreen );
				SwitchScreen.switchContent( contractScreen );
			}
		});

		/*
		 *  draws the client screen
		 */
		Composite clientScreen = SwitchScreen.getContentContainer();
		ClientScreenDrawer csd = new ClientScreenDrawer( clientScreen );
		SwitchScreen.switchContent( clientScreen );
		bClients.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite clientScreen = SwitchScreen.getContentContainer();
				ClientScreenDrawer csd = new ClientScreenDrawer( clientScreen );
				SwitchScreen.switchContent( clientScreen );
			}
		});

		/*
		 * draws the service screen
		 */
		Composite serviceScreen = SwitchScreen.getContentContainer();
		ServiceScreenDrawer ssd = new ServiceScreenDrawer( serviceScreen );
		SwitchScreen.switchContent( serviceScreen );
		bServices.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite serviceScreen = SwitchScreen.getContentContainer();
				ServiceScreenDrawer ssd = new ServiceScreenDrawer( serviceScreen );
				SwitchScreen.switchContent( serviceScreen );
			}
		});

		/*
		 * draws the feature screen
		 */
		Composite featuresScreen = SwitchScreen.getContentContainer();
		TrackableFeatureScreenDrawer tfsd = new TrackableFeatureScreenDrawer( featuresScreen );
		SwitchScreen.switchContent( featuresScreen );
		bFeatures.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite featuresScreen = SwitchScreen.getContentContainer();
				TrackableFeatureScreenDrawer tfsd = new TrackableFeatureScreenDrawer( featuresScreen );
				SwitchScreen.switchContent( featuresScreen );
			}
		});

		/*
		 * draws the JCC screen
		 */
		Composite jccContractScreen = SwitchScreen.getContentContainer();
		JCCContractScreenDrawer jcc = new JCCContractScreenDrawer( jccContractScreen );
		SwitchScreen.switchContent( jccContractScreen );
		bJCC.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite jccContractScreen = SwitchScreen.getContentContainer();
				JCCContractScreenDrawer tfsd = new JCCContractScreenDrawer( jccContractScreen );
				SwitchScreen.switchContent( jccContractScreen );
			}
		});

		/*
		 * draws the login screen
		 */
		Composite loginScreen = SwitchScreen.getContentContainer();
		LoginDrawer ld = new LoginDrawer( loginScreen );
		SwitchScreen.switchContent( loginScreen );
		bLogin.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				if (!User.loggedIn())
				{
					Composite loginScreen = SwitchScreen.getContentContainer();
					LoginDrawer ld = new LoginDrawer( loginScreen );
					SwitchScreen.switchContent( loginScreen );
				}
				else
				{
					Composite loggedInScreen = SwitchScreen.getContentContainer();
					LoggedInDrawer ldd = new LoggedInDrawer( loggedInScreen );
					SwitchScreen.switchContent( loggedInScreen );
				}
			}
		});

		backStack.clear();

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

		if(EventLoop.isEnabled())
		{
			while (!shell.isDisposed())
			{
				if (!display.readAndDispatch())
					display.sleep();
			}
		}
		// System.out.println( "END." );
	}

	/**
	 * provide a container for new content
	 */
	/* PACKAGE */ static Composite getContentContainer()
	{
		Composite container = new Composite( content, SWT.None );
		container.setLayout( new FillLayout() );
		
		return container;
	}

	/**
	 * switch content with container
	 */
	/* PACKAGE */ static void switchContent( Composite container )
	{
		if ( contentLayout.topControl != null )
			backStack.push( ( Composite ) contentLayout.topControl );
			
		contentLayout.topControl = container;
		content.layout();
	}

	/**
	 * Back button functionality
	 */
	private static void switchBack()
	{
		if ( ! backStack.isEmpty() )
		{
			Composite lastContainer = backStack.pop();
		
			contentLayout.topControl = lastContainer;
			content.layout();
		}
	}

	/**
	 * all your shell tweaking needs should go here
	 */
	private static void initShell( Shell srcShell )
	{
		srcShell.setSize( WIN_WIDTH, WIN_HEIGHT );
		srcShell.setText( WIN_TEXT );
		
		GridLayout shellLayout = new GridLayout();
		shellLayout.numColumns = 1;
		srcShell.setLayout( shellLayout );
		
		srcShell.setMinimumSize( WIN_WIDTH, WIN_HEIGHT);
		srcShell.setLocation(0,0);
	}

	/**
	 * all your button tweaking needs for the nav bar should go here
	 */
	private static void tuneNavButton( Button navButton, String label )
	{
		GridData navButtonData = new GridData( GridData.FILL_HORIZONTAL );
		navButtonData.heightHint = 50;
		navButtonData.widthHint = 60;
		navButtonData.horizontalSpan = 1;
		
		navButton.setText( label );
		navButton.setLayoutData( navButtonData );
	}

	public static void disableButtons()
	{
		bBack.setEnabled(false);
		bClients.setEnabled(false);
		bContract.setEnabled(false);
		bServices.setEnabled(false);
		bFeatures.setEnabled(false);
		bJCC.setEnabled(false);
	}

	public static void enableButtons()
	{
		bBack.setEnabled(true);
		bClients.setEnabled(true);
		bContract.setEnabled(true);
		bServices.setEnabled(true);
		bFeatures.setEnabled(true);
		bJCC.setEnabled(true);
	}

	/**
	 * Set the active composite
	 * @param viewServicePerformance	The composite to set
	 * @return The new top control
	 */
	public static Object setcontentLayoutTopControl(Composite viewServicePerformance)
	{
		return contentLayout.topControl = viewServicePerformance;
	}
}

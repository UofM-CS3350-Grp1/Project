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

import javax.imageio.ImageIO;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;

public class SwitchScreen
{
	private static final int WIN_WIDTH = 680;
	private static final int WIN_HEIGHT = 480;
	private static final String WIN_TEXT = "Buzzin' Digital Marketing";

	static int pageNum = -1;
	private static StackLayout contentLayout;
	private static Composite content;

	private static Deque< Composite > backStack;

	private static MenuItem bBack;
	/*
	private static Button bClients;
	private static Button bContract;
	private static Button bServices;
	private static Button bFeatures;
	private static Button bExit;
	*/
	private static Button bJCC;
	private static Button bLogin;
	private static Display display;
	private static Shell shell;
	private static MenuItem mntmMenu;
	private static MenuItem mntmNewSubmenu;
	private static MenuItem mntmJccs_1;
	private static MenuItem mntmLogOut_1;
	private static MenuItem mntmChangePassword;
	private static Menu menu;
	
	private static Menu menu_1;
	private static MenuItem bClients;
	private static MenuItem bContract;
	private static MenuItem bServices;
	private static MenuItem bFeatures;
	private static MenuItem bExit;
	private static Menu menu_2;
	private static MenuItem mntmNewClient;
	private static MenuItem mntmNewContract;
	private static MenuItem mntmNewService;
	private static MenuItem mntmNewFeature;
	private static MenuItem mntmNewSurvey;
	private static Menu menu_3;
	private static MenuItem mntmViewContracts;
	private static MenuItem mntmViewServices;
	private static MenuItem mntmViewClients ;
	private static MenuItem mntmAddExpenses ;
	private static MenuItem mntmSettings;
	private static Menu menu_4;

	SwitchScreen()
	{	
		backStack = new ArrayDeque< Composite >();

		display = Display.getDefault();
		shell = new Shell(display);
		initShell( shell );
		Register.newWindow(this);
		shell.setMaximized(true);

		/*
		 * Create the navigation bar
		 */
		/*final int NUM_NAV_BUTTONS = 8;
		Composite navBar = new Composite( shell, SWT.BORDER );
		GridLayout navLayout = new GridLayout();
		navLayout.numColumns = 8;
		navLayout.makeColumnsEqualWidth = true;
		navBar.setLayout( navLayout );
		GridData gd_navBar = new GridData( GridData.FILL_HORIZONTAL );
		gd_navBar.heightHint = 78;
		navBar.setLayoutData( gd_navBar );*/
		
		//bBack = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bBack, "BACK" );

		//bClients = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bClients, "CLIENTS" );

		//bContract = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bContract, "CONTRACTS" );

		//bServices = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bServices, "SERVICES" );

		//bFeatures = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bFeatures, "FEATURES" );

		//bJCC = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bJCC, "JCC's" );

		//bLogin = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bLogin, "LOG IN" );

		//bExit = new Button( navBar, SWT.FLAT );
		//tuneNavButton( bExit, "EXIT" );
		/*new Label(navBar, SWT.NONE);
		new Label(navBar, SWT.NONE);
		new Label(navBar, SWT.NONE);
		new Label(navBar, SWT.NONE);
		new Label(navBar, SWT.NONE);*/

		disableButtons();

		/*
		 * Back button listener
		 */
		/*bBack.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				switchBack();
			}
		});*/

		/*
		 * create the switching composite
		 */
		content = new Composite( shell, SWT.BORDER  );
		content.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		contentLayout = new StackLayout(); // allows switching between composites
		content.setLayout( contentLayout );

/*
 * This is the added code for the top menu bar
 */
		menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		mntmMenu = new MenuItem(menu, SWT.CASCADE);
		mntmMenu.setText("Menu");

		menu_1 = new Menu(mntmMenu);
		mntmMenu.setMenu(menu_1);

		bClients = new MenuItem(menu_1, SWT.NONE);
		bClients.setText("Clients");

		bContract = new MenuItem(menu_1, SWT.NONE);
		bContract.setText("Contracts");

		bServices = new MenuItem(menu_1, SWT.NONE);
		bServices.setText("Services");

		bFeatures = new MenuItem(menu_1, SWT.NONE);
		bFeatures.setText("Features");

//  This menu item is redundant, JCC's already has its own top level menu
//		MenuItem bJCC = new MenuItem(menu_1, SWT.NONE);
//		bJCC.setText("JCC's");


		mntmNewSubmenu = new MenuItem(menu, SWT.CASCADE);
		mntmNewSubmenu.setText("New");

		menu_2 = new Menu(mntmNewSubmenu);
		mntmNewSubmenu.setMenu(menu_2);

		mntmNewClient = new MenuItem(menu_2, SWT.NONE);
		mntmNewClient.setText("New Client");

		mntmNewClient.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addClientScreen = SwitchScreen.getContentContainer();
				new AddClientScreenDrawer( addClientScreen );
				SwitchScreen.switchContent( addClientScreen );
			}
		});

		mntmNewContract = new MenuItem(menu_2, SWT.NONE);
		mntmNewContract.setText("New Contract");

		mntmNewContract.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addContractScreen = SwitchScreen.getContentContainer();
				new AddContractScreenDrawer( addContractScreen );
				SwitchScreen.switchContent( addContractScreen );
			}
		});

		mntmNewService = new MenuItem(menu_2, SWT.NONE);
		mntmNewService.setText("New Service");

		mntmNewService.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addServiceScreen = SwitchScreen.getContentContainer();
				new AddServiceScreenDrawer( addServiceScreen );
				SwitchScreen.switchContent( addServiceScreen );
			}
		});

		new MenuItem(menu_2, SWT.SEPARATOR);

		mntmNewFeature = new MenuItem(menu_2, SWT.NONE);
		mntmNewFeature.setText("New Feature");

		mntmNewFeature.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addFeatureScreen = SwitchScreen.getContentContainer();
				new AddTrackableFeatureDrawer( addFeatureScreen );
				SwitchScreen.switchContent( addFeatureScreen );
			}
		});

		mntmNewSurvey = new MenuItem(menu_2, SWT.NONE);
		mntmNewSurvey.setText("New Survey");

		mntmNewSurvey.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addSurveyScreen = SwitchScreen.getContentContainer();
				new JCCSurveyScreenDrawer( addSurveyScreen );
				SwitchScreen.switchContent( addSurveyScreen );
			}
		});

		mntmJccs_1 = new MenuItem(menu, SWT.CASCADE);
		mntmJccs_1.setText("JCC's");

		menu_3 = new Menu(mntmJccs_1);
		mntmJccs_1.setMenu(menu_3);

		mntmViewContracts = new MenuItem(menu_3, SWT.NONE);
		mntmViewContracts.setText("View Contracts");

		mntmViewContracts.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite viewJCCContractScreen = SwitchScreen.getContentContainer();
				new JCCContractScreenDrawer( viewJCCContractScreen );
				SwitchScreen.switchContent( viewJCCContractScreen );
			}
		});

		mntmViewServices = new MenuItem(menu_3, SWT.NONE);
		mntmViewServices.setText("View Services");

		mntmViewServices.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite viewJCCServiceScreen = SwitchScreen.getContentContainer();
				new JCCServiceScreenDrawer( viewJCCServiceScreen );
				SwitchScreen.switchContent( viewJCCServiceScreen );
			}
		});

		mntmViewClients = new MenuItem(menu_3, SWT.NONE);
		mntmViewClients.setText("View Clients");

		mntmViewClients.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite viewJCCClientScreen = SwitchScreen.getContentContainer();
				new JCCClientScreenDrawer( viewJCCClientScreen );
				SwitchScreen.switchContent( viewJCCClientScreen );
			}
		});

		new MenuItem(menu_3, SWT.SEPARATOR);

		mntmAddExpenses = new MenuItem(menu_3, SWT.NONE);
		mntmAddExpenses.setText("Add Expenses");

		mntmAddExpenses.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite addExpensesScreen = SwitchScreen.getContentContainer();
				new AddExpensesScreenDrawer( addExpensesScreen );
				SwitchScreen.switchContent( addExpensesScreen );
			}
		});

		mntmSettings = new MenuItem(menu, SWT.CASCADE);
		mntmSettings.setText("Settings");

		menu_4 = new Menu(mntmSettings);
		mntmSettings.setMenu(menu_4);

		mntmChangePassword = new MenuItem(menu_4, SWT.NONE);
		mntmChangePassword.setText("Change Password");

		mntmChangePassword.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				Composite updatePW = SwitchScreen.getContentContainer();
				new UpdatePasswordScreenDrawer( updatePW );
				SwitchScreen.switchContent( updatePW );
			}
		});

		new MenuItem(menu_4, SWT.SEPARATOR);
		
		bBack = new MenuItem(menu_4, SWT.NONE);
		bBack.setText("Back");
		
		bBack.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				switchBack();
			}
		});

		mntmLogOut_1 = new MenuItem(menu_4, SWT.NONE);
		mntmLogOut_1.setText("Log Out");

		mntmLogOut_1.addSelectionListener( new SelectionAdapter()
		{
			@Override
			public void widgetSelected( SelectionEvent event )
			{
				User.logout();
				disableButtons();
				disableMenus();
				Composite loginScreen = SwitchScreen.getContentContainer();
				new LoginDrawer( loginScreen );
				SwitchScreen.switchContent( loginScreen );
				
				eraseBackHistory();
			}
		});
		
		bExit = new MenuItem(menu_4, SWT.NONE);
		bExit.setText("Exit");

/*
 * Top menu bar code ends here
 */

		disableMenus();

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
		/* Not needed for now (if using menus instead of navbar)
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
		*/

		/*
		 * draws the login screen
		 */
		Composite loginScreen = SwitchScreen.getContentContainer();
		LoginDrawer ld = new LoginDrawer( loginScreen );
		SwitchScreen.switchContent( loginScreen );
		/* Not needed for now (if using menus instead of navbar)
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
		*/

		backStack.clear();

		/*
		 *  exits the program
		 */
		bExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				//((Control) event.getSource()).getShell().dispose();
				System.exit(0);
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

	/* PACKAGE */ static void eraseBackHistory() {
		backStack.clear();
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

	public static void enableButtons()
	{
		/*bBack.setEnabled(true);
		bClients.setEnabled(true);
		bContract.setEnabled(true);
		bServices.setEnabled(true);
		bFeatures.setEnabled(true);
		bJCC.setEnabled(true);*/
	}

	public static void disableButtons()
	{
		/*bBack.setEnabled(false);
		bClients.setEnabled(false);
		bContract.setEnabled(false);
		bServices.setEnabled(false);
		bFeatures.setEnabled(false);
		bJCC.setEnabled(false);*/
	}

	public static void enableMenus()
	{
		mntmMenu.setEnabled(true);
		mntmNewSubmenu.setEnabled(true);
		mntmJccs_1.setEnabled(true);

		mntmChangePassword.setEnabled(true);
		mntmLogOut_1.setEnabled(true);
	}

	public static void disableMenus()
	{
		mntmMenu.setEnabled(false);
		mntmNewSubmenu.setEnabled(false);
		mntmJccs_1.setEnabled(false);

		mntmChangePassword.setEnabled(false);
		mntmLogOut_1.setEnabled(false);
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

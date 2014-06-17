package presentation;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

public class ProgramShell {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgramShell window = new ProgramShell();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	
	/*
	 * CURRENT ISSUES - note that this is still a work in progress, and may even be thrown out (again)
	 * 	Location of the navigation buttons doesn't feel right
	 * 	"hover" listeners change colour back when hovering over the label in the menu button composites
	 * 	nowhere to put "Logged in as:..." or a logout button
	 * 	what is the homescreen supposed to be?
	 * 	client and service screens haven't been turned into composites yet
	 * 	what is the best way to switch between composites and keep track of past ones to go back to
	 * 	loginComp should be made into a separate file
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(700, 500);
		shell.setText("Buzzin' Digital Marketing");
		shell.setLayout(new FormLayout());
		
		Composite menuBar = new Composite(shell, SWT.NONE);
		FormData fd_menuBar = new FormData();
		fd_menuBar.bottom = new FormAttachment(0, 100);
		fd_menuBar.right = new FormAttachment(0, 684);
		fd_menuBar.top = new FormAttachment(0);
		fd_menuBar.left = new FormAttachment(0);
		menuBar.setLayoutData(fd_menuBar);
		menuBar.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		Composite workingArea = new Composite(shell, SWT.NONE);
		workingArea.setLayout(null);
		FormData fd_workingArea = new FormData();
		fd_workingArea.top = new FormAttachment(menuBar);
		fd_workingArea.right = new FormAttachment(menuBar, 0, SWT.RIGHT);
		fd_workingArea.left = new FormAttachment(menuBar, 0, SWT.LEFT);
		fd_workingArea.bottom = new FormAttachment(0, 462);
		workingArea.setLayoutData(fd_workingArea);
		
		Composite homeBtn = new Composite(menuBar, SWT.BORDER);
		homeBtn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(0, 191, 255));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(30, 144, 255));
			}
		});
		homeBtn.setBackgroundMode(SWT.INHERIT_FORCE);
		homeBtn.setBackground(SWTResourceManager.getColor(30, 144, 255));
		
		Label homeLabel = new Label(homeBtn, SWT.SHADOW_NONE);
		homeLabel.setText("HOME");
		homeLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		homeLabel.setFont(SWTResourceManager.getFont("Microsoft PhagsPa", 15, SWT.BOLD));
		homeLabel.setAlignment(SWT.CENTER);
		homeLabel.setBounds(10, 28, 115, 35);
		
		Composite backBtn = new Composite(menuBar, SWT.BORDER);
		backBtn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(0, 191, 255));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(30, 144, 255));
			}
		});
		backBtn.setBackgroundMode(SWT.INHERIT_FORCE);
		backBtn.setBackground(SWTResourceManager.getColor(30, 144, 255));
		
		Label backLabel = new Label(backBtn, SWT.NONE);
		backLabel.setText("BACK");
		backLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		backLabel.setFont(SWTResourceManager.getFont("Microsoft PhagsPa", 15, SWT.BOLD));
		backLabel.setAlignment(SWT.CENTER);
		backLabel.setBounds(10, 28, 115, 35);
		
		Composite clientsBtn = new Composite(menuBar, SWT.BORDER);
		clientsBtn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(0, 191, 255));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(30, 144, 255));
			}
		});
		clientsBtn.setBackgroundMode(SWT.INHERIT_FORCE);
		clientsBtn.setBackground(SWTResourceManager.getColor(30, 144, 255));
		
		Label clientsLabel = new Label(clientsBtn, SWT.NONE);
		clientsLabel.setText("CLIENTS");
		clientsLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		clientsLabel.setFont(SWTResourceManager.getFont("Microsoft PhagsPa", 15, SWT.BOLD));
		clientsLabel.setAlignment(SWT.CENTER);
		clientsLabel.setBounds(10, 28, 115, 35);
		
		Composite servicesBtn = new Composite(menuBar, SWT.BORDER);
		servicesBtn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(0, 191, 255));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(30, 144, 255));
			}
		});
		servicesBtn.setBackgroundMode(SWT.INHERIT_FORCE);
		servicesBtn.setBackground(SWTResourceManager.getColor(30, 144, 255));
		
		Label servicesLabel = new Label(servicesBtn, SWT.NONE);
		servicesLabel.setText("SERVICES");
		servicesLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		servicesLabel.setFont(SWTResourceManager.getFont("Microsoft PhagsPa", 15, SWT.BOLD));
		servicesLabel.setAlignment(SWT.CENTER);
		servicesLabel.setBounds(10, 28, 115, 35);
		
		Composite settingsBtn = new Composite(menuBar, SWT.BORDER);
		settingsBtn.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(0, 191, 255));
			}
			@Override
			public void mouseExit(MouseEvent arg0) {
				((Control) arg0.widget).setBackground(SWTResourceManager.getColor(30, 144, 255));
			}
		});
		settingsBtn.setBackgroundMode(SWT.INHERIT_FORCE);
		settingsBtn.setBackground(SWTResourceManager.getColor(30, 144, 255));
		
		Label settingsLabel = new Label(settingsBtn, SWT.NONE);
		settingsLabel.setText("SETTINGS");
		settingsLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		settingsLabel.setFont(SWTResourceManager.getFont("Microsoft PhagsPa", 15, SWT.BOLD));
		settingsLabel.setAlignment(SWT.CENTER);
		settingsLabel.setBounds(10, 28, 115, 35);
		
		
		// login
		Composite loginComp = new Composite(workingArea, SWT.NONE );
		loginComp.setBounds(0, 0, 684, 362);
		GridLayout loginGrid = new GridLayout();
		loginGrid.numColumns = 2;
		loginComp.setLayout( loginGrid );
		
		final Label userLabel = new Label( loginComp, SWT.NONE );
		userLabel.setText( "User: (Type admin)" );
		final Text userIn = new Text( loginComp, SWT.BORDER );
		GridData gd_userIn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_userIn.widthHint = 143;
		userIn.setLayoutData(gd_userIn);
		userIn.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent event)
			{
				if((userIn.getText()).equals("admin")) 
				{
					userLabel.setText("User: (Correct):");
				}
				else
				{
					userLabel.setText("User: (Type admin)");
				}
			}
		});
		
		final Label passLabel = new Label( loginComp, SWT.NONE );
		passLabel.setText( "Password: (Type password)" );
		final Text passIn = new Text( loginComp, SWT.BORDER | SWT.PASSWORD );
		
		GridData gd_passIn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_passIn.widthHint = 143;
		
		passIn.setLayoutData(gd_passIn);
		passIn.addModifyListener( new ModifyListener() 
		{
			public void modifyText( ModifyEvent event )
			{
				if ( ( passIn.getText() ).equals( "password" ) ) 
				{
					passLabel.setText( "Password: (Correct)" );
				}
				else 
				{
					passLabel.setText( "Password: (Type password)" );
				}
			}
		});
		
		Button btnLogin = new Button( loginComp, SWT.None );
		GridData gd_btnLogin = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnLogin.widthHint = 69;
		
		btnLogin.setLayoutData(gd_btnLogin);
		btnLogin.setText( "Login" );
		
		new Label(loginComp, SWT.NONE);
		btnLogin.addSelectionListener( new SelectionAdapter() 
		{
			public void widgetSelected( SelectionEvent event ) 
			{
				if ( ( userIn.getText() ).equals( "admin" ) 
						&& ( passIn.getText()).equals( "password" ) )
				{
					// log in - match credentials with DB maybe?
				}
				else
				{
					// invalid user prompt?
				}
			}
		});
		loginComp.layout();
	}
}

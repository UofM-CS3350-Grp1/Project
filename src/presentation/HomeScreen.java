package presentation;

import objects.Client.ClientStatus;
import objects.Client;
import objects.Contract;
import objects.Service;
import persistence.StubDBInterface;
import persistence.DBInterface;

import java.util.Date;
import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;


public class HomeScreen {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		int dbDebug = 1;
		StubDBInterface test = new StubDBInterface("Test");
		//DBInterface testDB = new DBInterface("Test");
/*
		if(dbDebug == 2)
		{
			
			testDB.connect();
			
			ArrayList<String> testQuery = testDB.blindSQLQuery("SELECT * FROM SERVICES");
			
			for(int i = 0; i < testQuery.size(); i++)
			{
				System.out.println(testQuery.get(i));
			}
			
			System.out.println("Service By ID");
			System.out.println(testDB.getServiceByID(1));
			System.out.println(testDB.getServiceByID(2));
			System.out.println(testDB.getServiceByID(3));
			System.out.println(testDB.getServiceByID(4));
			System.out.println(testDB.getServiceByID(7)); //Inexistant ID
			System.out.println(testDB.getServicesByTitle("Service 2"));
			
			System.out.println();
			
			System.out.println("Client By ID");
			System.out.println(testDB.getClientByID(1));
			System.out.println(testDB.getClientByID(2));
			System.out.println(testDB.getClientByID(3));
			System.out.println(testDB.getClientByID(4));
			System.out.println(testDB.getClientByID(7)); //Inexistant ID
			System.out.println(testDB.getClientsByStatus(ClientStatus.Active));
			
			System.out.println();
			
			System.out.println("Contract By ID");
			System.out.println(testDB.getContractByID(1));
			System.out.println(testDB.getContractByID(2));
			System.out.println(testDB.getContractByID(3));
			System.out.println(testDB.getContractByID(4));
			System.out.println(testDB.getContractByID(7)); //Inexistant ID
			System.out.println(testDB.getContractsByBusiness("Business 3"));
			
			Contract samply = testDB.getContractByID(1);
			testDB.drop(samply);
			System.out.println("Samply is " + samply);
			
			testDB.insert(samply);
			System.out.println(testDB.getContractByID(4));
			System.out.println("Samply is " + samply);
			
			samply = testDB.getContractByID(4);
			samply.setBusinessName("Business 5");
			testDB.update(samply);
			System.out.println(testDB.getContractByID(4));
			System.out.println("Samply is " + samply);
			
			testDB.disconnect();
		}
		*/
		/*
		if(dbDebug == 1)
		{
			test.connect();
			//Client clientTest = new Client(4, "Joe Doe", "5552222", "joedoe@gmail.com", "223 Main St.", "Joe's Business", 1);
			Contract contractTest = new Contract(4, "Joe's Business", "Details go here...", 3500.00, new Date());
			Service serviceTest = new Service(4, "Service 3", "This one is new", 5.5, "Monthly", 0.0, null, "Yearly", "Marketing");
	
			//test.insert(clientTest);
			test.insert(contractTest);
			test.insert(serviceTest);
			
			test.drop(test.getServiceByID(3));
			test.drop(test.getContractByID(3));
			test.drop(test.getClientByID(3));
			
		//	clientTest.setAddress("225 Main St.");
			contractTest.setDetails("Recently moved.");
			serviceTest.setDescription("Now its REALLY new");
			
		//	test.update(clientTest);
		//	test.update(contractTest);
		//	test.update(serviceTest);
			
			System.out.println(test.getServiceByID(1));
			System.out.println(test.getServiceByID(2));
			System.out.println(test.getServiceByID(3));
			System.out.println(test.getServiceByID(4));
			System.out.println(test.getServiceByID(7)); //Inexistant ID
			System.out.println(test.getServicesByTitle("Service 2"));
			
			System.out.println();
			
			System.out.println(test.getClientByID(1));
			System.out.println(test.getClientByID(2));
			System.out.println(test.getClientByID(3));
			System.out.println(test.getClientByID(4));
			System.out.println(test.getClientByID(7)); //Inexistant ID
			System.out.println(test.getClientsByStatus(ClientStatus.Active));
			
			System.out.println();
			
			System.out.println(test.getContractByID(1));
			System.out.println(test.getContractByID(2));
			System.out.println(test.getContractByID(3));
			System.out.println(test.getContractByID(4));
			System.out.println(test.getContractByID(7)); //Inexistant ID
			System.out.println(test.getContractsByBusiness("Jane's Business"));
			
			System.out.println();
			
			test.disconnect();
		}
		*/
		try {
			HomeScreen window = new HomeScreen();
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
		System.out.println("End of program.");
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 280);
		shell.setText("Buzzin' Digital Marketing");
		shell.setLayout(new GridLayout(1, false));
		
		// separator
		Label line1 = new Label(shell, SWT.HORIZONTAL | SWT.SEPARATOR);
		line1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		// login
		Composite loginComp = new Composite( shell, SWT.NONE );
		GridLayout loginGrid = new GridLayout();
		loginGrid.numColumns = 2;
		loginComp.setLayout( loginGrid );
		
		final Label userLabel = new Label( loginComp, SWT.NONE );
		userLabel.setText( "User: (Type admin)" );
		final Text userIn = new Text( loginComp, SWT.BORDER );
		GridData gd_userIn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_userIn.widthHint = 143;
		userIn.setLayoutData(gd_userIn);
		userIn.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent event ) {
				if ( ( userIn.getText() ).equals( "admin" ) ) {
					userLabel.setText( "User: (Correct):" );
				}
				else {
					userLabel.setText( "User: (Type admin)" );
				}
			}
		});
		
		final Label passLabel = new Label( loginComp, SWT.NONE );
		passLabel.setText( "Password: (Type password)" );
		final Text passIn = new Text( loginComp, SWT.BORDER | SWT.PASSWORD );
		GridData gd_passIn = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_passIn.widthHint = 143;
		passIn.setLayoutData(gd_passIn);
		passIn.addModifyListener( new ModifyListener() {
			public void modifyText( ModifyEvent event ) {
				if ( ( passIn.getText() ).equals( "password" ) ) {
					passLabel.setText( "Password: (Correct)" );
				}
				else {
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
		btnLogin.addSelectionListener( new SelectionAdapter() {
			public void widgetSelected( SelectionEvent event ) {
				if ( ( userIn.getText() ).equals( "admin" ) 
						&& ( passIn.getText()).equals( "password" ) ) {
					// log in - match credentials with DB maybe?
				}
				else {
					// invalid user prompt?
				}
			}
		});
		
		// separator
		Label line2 = new Label(shell, SWT.HORIZONTAL | SWT.SEPARATOR);
		line2.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.NONE);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_composite.heightHint = 128;
		gd_composite.widthHint = 424;
		composite.setLayoutData(gd_composite);
		
		Button btnManageServices = new Button(composite, SWT.CENTER);
		btnManageServices.setBounds(171, 36, 100, 50);
		btnManageServices.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new ServiceScreen();
			}
		});
		btnManageServices.setText("Manage Services");
		
		Button btnManageClients = new Button(composite, SWT.CENTER);
		btnManageClients.setBounds(54, 36, 94, 50);
		btnManageClients.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event) 
			{
				new ClientScreen();
			}
		});
		btnManageClients.setText("Manage Clients");
		
		Button btnExit = new Button(composite, SWT.CENTER);
		btnExit.setBounds(294, 36, 69, 50);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				shell.dispose();
			}
		});
		btnExit.setText("Exit");

	}

}

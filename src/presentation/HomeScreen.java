package presentation;

import objects.Client.ClientStatus;
import objects.Client;
import objects.Contract;
import objects.Service;
import persistence.StubDBInterface;

import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class HomeScreen {

	protected Shell shell;
	protected ClientScreen clientScreen;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		int dbDebug = 0;
		StubDBInterface test = new StubDBInterface("Test");

		if(dbDebug == 1)
		{
			Client clientTest = new Client(4, "Joe Doe", "5552222", "joedoe@gmail.com", "223 Main St.", "Joe's Business", 1);
			Contract contractTest = new Contract(4, "Joe's Business", "Details go here...", 3500.00, new Date());
			Service serviceTest = new Service(4, "Service 4", "Service description...", 3.0, "Other");
	
			test.insert(clientTest);
			test.insert(contractTest);
			test.insert(serviceTest);
			
			test.drop(test.getServiceByID(3));
			test.drop(test.getContractByID(3));
			test.drop(test.getClientByID(3));
			
			clientTest.setAddress("225 Main St.");
			contractTest.setDetails("Recently moved.");
			serviceTest.setDescription("What does this service do?");
			
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
		}
			
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));
		
		Label lblHelloGroup = new Label(shell, SWT.NONE);
		lblHelloGroup.setText("HELLO GROUP #1!!!");
		
		Label lblEveryoneShouldBe = new Label(shell, SWT.NONE);
		lblEveryoneShouldBe.setText("Everyone should be able to start placing their code into the src folder.");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnManageClients = new Button(shell, SWT.NONE);
		btnManageClients.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event) 
			{
				clientScreen = new ClientScreen(Display.getDefault());
				clientScreen.open();
			}
		});
		btnManageClients.setText("Manage Clients");

	}

}

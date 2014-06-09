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


public class HomeScreen 
{

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try
		{
			HomeScreen window = new HomeScreen();
			window.open();
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while(!shell.isDisposed())
		{
			if(!display.readAndDispatch()) 
			{
				display.sleep();
			}
		}
		System.out.println("End of program.");
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents()
	{
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
		btnManageServices.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
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

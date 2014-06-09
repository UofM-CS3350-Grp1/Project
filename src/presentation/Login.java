package presentation;

/*
*
* Hardcoded user id for now: admin
*
*/


import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login extends Shell 
{
	private Text user_login;
	private Text user_pwd;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) 
	{
		try
		{
			Display display = Display.getDefault();
			Login shell = new Login(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) 
			{
				if (!display.readAndDispatch())
				{
					display.sleep();
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Login(Display display) 
	{
		super(display, SWT.SHELL_TRIM);
		
		Label lblLogIn = new Label(this, SWT.NONE);
		lblLogIn.setBounds(143, 37, 55, 15);
		lblLogIn.setText("Log in");
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setBounds(115, 93, 31, 15);
		lblNewLabel.setText("User");
		
		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setBounds(91, 128, 55, 15);
		lblNewLabel_1.setText("Password");
		
		user_login = new Text(this, SWT.BORDER);
		user_login.setBounds(152, 87, 76, 21);
		
		user_pwd = new Text(this, SWT.PASSWORD | SWT.BORDER);
		user_pwd.setBounds(152, 122, 76, 21);

		Label error_msg = new Label(this, SWT.NONE);
		error_msg.setBounds(115, 58, 89, 15);
		error_msg.setText("Error");
		error_msg.setVisible(false);
		
		Button btnLogIn = new Button(this, SWT.NONE);
		btnLogIn.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
					String a = user_pwd.toString();
					if(a!="admin")
					{
						JOptionPane.showMessageDialog(null, "Invalid login");
					}
					else
					{
						HomeScreen.main(null);
					}
			}
		});

		btnLogIn.setBounds(153, 174, 75, 25);
		btnLogIn.setText("Log in");
		createContents();		
	}
	
	/**
	 * Create contents of the shell.
	 */
	protected void createContents()
	{
		setText("Buzzin' Digital Marketing");
		setSize(352, 277);
	}

	@Override
	protected void checkSubclass() 
	{
		// Disable the check that prevents subclassing of SWT components
	}

}

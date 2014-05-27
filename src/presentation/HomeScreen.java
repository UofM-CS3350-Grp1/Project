package presentation;

import persistence.DBController;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;


public class HomeScreen {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) 
	{	
		DBController test = new DBController("Test");
		System.out.println(test);
		test.connect();
		test.disconnect();
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

	}

}

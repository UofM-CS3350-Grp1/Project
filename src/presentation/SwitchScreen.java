package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridData; //
import org.eclipse.swt.layout.GridLayout; //
import org.eclipse.swt.layout.RowLayout; //
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public class SwitchScreen {
	private static final int WIN_WIDTH = 450;
	private static final int WIN_HEIGHT = 280;
	private static final String WIN_TEXT = "Buzzin' Digital Marketing";
	
	public static void main( String[] args ) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		initShell( shell );
		
		Composite navBar = new Composite( shell, SWT.BORDER );
		GridLayout navLayout = new GridLayout();
		navLayout.numColumns = 6;
		navLayout.makeColumnsEqualWidth = true;
		navBar.setLayout( navLayout );
		GridData navData = new GridData( GridData.FILL_HORIZONTAL );
		navBar.setLayoutData( navData );
		
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
		
		
		Composite content = new Composite( shell, SWT.BORDER );
		FillLayout contentLayout = new FillLayout();
		content.setLayout( contentLayout );
		GridData contentData = new GridData( GridData.FILL_BOTH );
		content.setLayoutData( contentData );
		Button fillerButton = new Button( content, SWT.FLAT );
		/*
		 * REPLACE THIS BUTTON WITH A COMPOSITE
		 */
		fillerButton.setText( "CONTENT" );
		
		shell.open();
		shell.layout();
		
		
		while ( ! shell.isDisposed() ) {
			if ( ! display.readAndDispatch() ) {
				display.sleep();
			}
		}
		
		// System.out.println( "END." );
	}
	
	private static void initShell( Shell srcShell ) {
		srcShell.setSize( WIN_WIDTH, WIN_HEIGHT );
		srcShell.setText( WIN_TEXT );
		
		GridLayout shellLayout = new GridLayout();
		shellLayout.numColumns = 1;
		srcShell.setLayout( shellLayout );
	}
	
	private static void tuneNavButton( Button navButton, String label ) {
		GridData navButtonData = new GridData( GridData.FILL_HORIZONTAL );
		navButtonData.heightHint = 50;
		navButtonData.widthHint = 60;
		navButtonData.horizontalSpan = 1;
		
		navButton.setText( label );
		navButton.setLayoutData( navButtonData );
	}
}

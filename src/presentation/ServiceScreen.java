package presentation;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CCombo;

import objects.Service;

import org.eclipse.swt.widgets.List;

/*
 * 
 * Rough GUI for adding new services 
 * This needs to be added to the original GUI yet and main method removed
 * Currently is only displaying the one sample service offered by the service object
 * Needs to be changed a bit yet to read services into a list
 * 
 */
public class ServiceScreen extends Shell {
	private Text svcType;
	private Text rate_amount;
	private Text svc_terms;
	private Text svc_description;
	private List list_services;
	private Display display;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ServiceScreen shell = new ServiceScreen(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public ServiceScreen(final Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Label lblServiceType = new Label(this, SWT.NONE);
		lblServiceType.setBounds(67, 95, 71, 15);
		lblServiceType.setText("Service Type");
		
		Menu menu = new Menu(this, SWT.BAR);
		setMenuBar(menu);
		
		Label lblAddNewService = new Label(this, SWT.NONE);
		lblAddNewService.setBounds(223, 10, 114, 15);
		lblAddNewService.setText("ADD / EDIT SERVICE");
		
		svcType = new Text(this, SWT.BORDER);
		svcType.setBounds(186, 95, 263, 21);
		
		Label lblServiceRate = new Label(this, SWT.NONE);
		lblServiceRate.setBounds(67, 130, 71, 15);
		lblServiceRate.setText("Service Rate");
		
		rate_amount = new Text(this, SWT.BORDER);
		rate_amount.setBounds(186, 130, 76, 21);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(283, 136, 16, 15);
		label.setText("/");
		
		Combo rate_lenght = new Combo(this, SWT.NONE);
		rate_lenght.setItems(new String[] {"Year", "Month", "Week", "Session"});
		rate_lenght.setBounds(305, 128, 91, 23);
		
		Label lblServiceTerms = new Label(this, SWT.NONE);
		lblServiceTerms.setBounds(67, 166, 82, 15);
		lblServiceTerms.setText("Service Terms");
		
		svc_terms = new Text(this, SWT.BORDER);
		svc_terms.setBounds(186, 166, 263, 93);
		
		Label lblServiceDescription = new Label(this, SWT.NONE);
		lblServiceDescription.setBounds(67, 275, 106, 15);
		lblServiceDescription.setText("Service Description");
		
		svc_description = new Text(this, SWT.BORDER);
		svc_description.setBounds(186, 275, 263, 93);
		
		Button btnAdd = new Button(this, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		btnAdd.setBounds(214, 391, 75, 25);
		btnAdd.setText("ADD");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				display.close();
			}
		});
		btnCancel.setBounds(348, 391, 75, 25);
		btnCancel.setText("CANCEL");
		
		Label lblSelectService = new Label(this, SWT.NONE);
		lblSelectService.setBounds(67, 63, 82, 15);
		lblSelectService.setText("Select Service");

		final Service svc = new Service();
		
		CCombo select_svc = new CCombo(this, SWT.BORDER);
		select_svc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//int selectedIndex;
				//selectedIndex = select_svc.getSelectionIndex();
				editSelectedService(svc);
			}
		});

		String test = svc.getTitle();
		select_svc.setItems(new String[] {test});
		select_svc.setBounds(186, 57, 168, 21);
		createContents();
	}
	
	private void editSelectedService(Service svc2){
		populateSvcFields(svc2);
	}
	
	private void populateSvcFields(Service svc3)
	{
		
		if(svc3 != null)
		{
			svcType.setText(svc3.getType());
			//rate_amount.set(svc.getRate());
			//svc_terms.setText(svc.get);
			svc_description.setText(svc3.getDescription());
		}
	}
	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Buzzin' Digital Marketing");
		setSize(587, 484);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}

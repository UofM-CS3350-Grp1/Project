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
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import java.util.ArrayList;

import objects.Service;

import org.eclipse.swt.widgets.List;

import persistence.DBController;

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
	final Button svc_del = new Button(this, SWT.NONE);
	final Button svc_edit = new Button(this, SWT.NONE);
	final CCombo select_svc = new CCombo(this, SWT.BORDER);
	Button btnCancel = new Button(this, SWT.NONE);
	final Button btnAdd = new Button(this, SWT.NONE);
	Button svc_new = new Button(this, SWT.NONE);
	int selection = 0;

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
		lblServiceType.setBounds(95, 145, 71, 15);
		lblServiceType.setText("Service Type");
		
		Menu menu = new Menu(this, SWT.BAR);
		setMenuBar(menu);
		
		Label lblAddNewService = new Label(this, SWT.NONE);
		lblAddNewService.setBounds(223, 10, 114, 15);
		lblAddNewService.setText("ADD / EDIT SERVICE");
		
		svcType = new Text(this, SWT.BORDER);
		svcType.setBounds(214, 145, 263, 21);
		svcType.setEnabled(false);
		
		Label lblServiceRate = new Label(this, SWT.NONE);
		lblServiceRate.setBounds(95, 180, 71, 15);
		lblServiceRate.setText("Service Rate");
		
		rate_amount = new Text(this, SWT.BORDER);
		rate_amount.setBounds(214, 180, 76, 21);
		rate_amount.setEnabled(false);
		
		Label label = new Label(this, SWT.NONE);
		label.setBounds(311, 186, 16, 15);
		label.setText("/");
		
		final Combo rate_lenght = new Combo(this, SWT.NONE);
		rate_lenght.setItems(new String[] {"Year", "Month", "Week", "Session"});
		rate_lenght.setBounds(333, 178, 91, 23);
		rate_lenght.setEnabled(false);
		
		Label lblServiceDescription = new Label(this, SWT.NONE);
		lblServiceDescription.setBounds(95, 222, 106, 15);
		lblServiceDescription.setText("Service Description");
		
		svc_description = new Text(this, SWT.BORDER);
		svc_description.setBounds(214, 219, 263, 141);
		svc_description.setEnabled(false);
		
		/*
		 * Submit the selected options and fields - button event
		 * the selection variable specifies if its an add, edit or delete
		 */
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String description = svc_description.toString();
				String servicetype = svcType.toString();
				Service service = new Service(servicetype, description, 100, servicetype);
				submit_svc(service, selection);
			}
		});
		btnAdd.setBounds(214, 391, 75, 25);
		btnAdd.setText("SAVE");
		btnAdd.setEnabled(false);
		
		/*
		 * Canceling the current selection - button event
		 */
		btnCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				clearFields();
				svcType.setEnabled(false);
				svc_description.setEnabled(false);
				rate_amount.setEnabled(false);
				rate_lenght.setEnabled(false);
				select_svc.setEnabled(false);
				svc_del.setEnabled(true);
				svc_edit.setEnabled(true);
				svc_new.setEnabled(true);
				btnAdd.setEnabled(false);
				selection = 0;
			}
		});
		btnCancel.setBounds(348, 391, 75, 25);
		btnCancel.setText("CANCEL");
		
		Label lblSelectService = new Label(this, SWT.NONE);
		lblSelectService.setBounds(95, 113, 82, 15);
		lblSelectService.setText("Select Service");

		final Service svc = new Service();
		
		select_svc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				editSelectedService(svc);
			}
		});

		String test = svc.getTitle();
		select_svc.setItems(new String[] {test});
		select_svc.setBounds(214, 107, 168, 21);
		select_svc.setEnabled(false);
		
		/*
		 * Disables appropriate buttons and enables appropriate fields
		 * for editing services - button event
		 */
		svc_edit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				svcType.setEnabled(true);
				svc_description.setEnabled(true);
				rate_amount.setEnabled(true);
				rate_lenght.setEnabled(true);
				select_svc.setEnabled(true);
				svc_del.setEnabled(false);
				svc_new.setEnabled(false);
				svc_edit.setEnabled(true);
				btnAdd.setEnabled(true);
				btnAdd.setText("UPDATE");
				selection = 2;
			}
		});
		svc_edit.setBounds(262, 49, 75, 25);
		svc_edit.setText("EDIT");
		
		/*
		 * Disables appropriate buttons and enables appropriate fields
		 * for editing services - button event
		 */
		svc_del.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				svcType.setEnabled(false);
				svc_description.setEnabled(false);
				rate_amount.setEnabled(false);
				rate_lenght.setEnabled(false);
				select_svc.setEnabled(true);
				svc_del.setEnabled(false);
				svc_edit.setEnabled(false);
				svc_new.setEnabled(false);
				btnAdd.setEnabled(true);
				btnAdd.setText("DELETE");
				selection = 3;
			}
		});
		svc_del.setBounds(402, 49, 75, 25);
		svc_del.setText("DELETE");
		
		/*
		 * Disables appropriate buttons and enables appropriate fields
		 * for adding a new service - button event
		 */
		svc_new.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent arg0) {
				svcType.setEnabled(true);
				svc_description.setEnabled(true);
				rate_amount.setEnabled(true);
				rate_lenght.setEnabled(true);
				select_svc.setEnabled(false);
				svc_del.setEnabled(false);
				svc_edit.setEnabled(false);
				btnAdd.setEnabled(true);
				selection = 1;
			}
		});
		svc_new.setBounds(126, 49, 75, 25);
		svc_new.setText("NEW");
		svc_new.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				clearFields();
				
			}
		});
		
		createContents();
	}

	/*
	 * Notes: This method submits the selected option and fields.
	 * 
	 * @param param - object containing the parameters of new/update/delete
	 * @param selection - provides direction of what to do
	 * 							- 1: add new service
	 * 							- 2: edit a service
	 * 							- 3: delete a service
	 */
	private void submit_svc(Object param, int selection) {
		DBController db = new DBController("db");
		db.connect();
		Service temp = (Service)param;
		
		/*Remove comments once ready to use with the db*/
		
		/*
		switch(selection){
			case 1://add new
				db.insert("SERVICES", temp);
			case 2://edit
				db.update("SERVICES", temp);
			case 3://delete
				db.drop("SERVICES", temp.getID());
		}*/
		
		db.disconnect();
	}
	
	/*
	 * Notes: clears all input fields
	 */
	private void clearFields() {
		svcType.setText("");
		svc_description.setText("");
		rate_amount.setText("");
		
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

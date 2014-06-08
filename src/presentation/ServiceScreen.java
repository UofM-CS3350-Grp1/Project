package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.custom.CCombo;

import objects.Service;


import persistence.StubDBInterface;
import persistence.DBInterface;

import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

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
	private Shell shell;
	private Text rate_amount;
	private Display display;
	private Text svc_description;
	private ArrayList<Service> list_services = new ArrayList();
	int selection = 0;
	private ArrayList<Service> services;

	/**
	 * Launch the application.
	 * @param args
	 */

	public ServiceScreen() 
	{
		display = Display.getDefault();
		
		createWindow();
	}
	private void createWindow()
	{
		shell = new Shell(display);
		shell.setText("Service");
		shell.setSize(640, 480);
		
		initializeServiceFields();
		
		shell.open();
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public void initializeServiceFields() {
		final Button svc_del = new Button(shell, SWT.NONE);
		final Button svc_edit = new Button(shell, SWT.NONE);
		final CCombo select_svc = new CCombo(shell, SWT.BORDER);
		final Button btnCancel = new Button(shell, SWT.NONE);
		final Button btnAdd = new Button(shell, SWT.NONE);
		final Button svc_new = new Button(shell, SWT.NONE);
		//super(display, SWT.SHELL_TRIM);
		
		Label lblServiceType = new Label(shell, SWT.NONE);
		lblServiceType.setBounds(95, 145, 71, 15);
		lblServiceType.setText("Service Type");
		
		
		Label lblAddNewService = new Label(shell, SWT.NONE);
		lblAddNewService.setBounds(223, 10, 114, 15);
		lblAddNewService.setText("ADD / EDIT SERVICE");
		
		svcType = new Text(shell, SWT.BORDER);
		svcType.setBounds(214, 145, 263, 21);
		svcType.setEnabled(false);
		
		Label lblServiceRate = new Label(shell, SWT.NONE);
		lblServiceRate.setBounds(95, 180, 71, 15);
		lblServiceRate.setText("Service Rate");
		
		rate_amount = new Text(shell, SWT.BORDER);
		rate_amount.setBounds(214, 180, 76, 21);
		rate_amount.setEnabled(false);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(311, 186, 16, 15);
		label.setText("/");
		
		final Combo rate_lenght = new Combo(shell, SWT.NONE);
		rate_lenght.setItems(new String[] {"Year", "Month", "Week", "Session"});
		rate_lenght.setBounds(333, 178, 91, 23);
		rate_lenght.setEnabled(false);
		
		Label lblServiceDescription = new Label(shell, SWT.NONE);
		lblServiceDescription.setBounds(95, 222, 106, 15);
		lblServiceDescription.setText("Service Description");
		
		svc_description = new Text(shell, SWT.BORDER);
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
				/*try {
					Contract.createPDF();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DocumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				//display.close();
			}
		});
		btnCancel.setBounds(348, 391, 75, 25);
		btnCancel.setText("CANCEL");
		
		Label lblSelectService = new Label(shell, SWT.NONE);
		lblSelectService.setBounds(95, 113, 82, 15);
		lblSelectService.setText("Select Service");

		/*final Service svc = new Service();
		final Service svc2 = new Service(02,"Websites", "Host a domain and publish a website", 400.00, "Websites");
		list_services.add(svc);
		list_services.add(svc2);*/

		StubDBInterface db = new StubDBInterface("db");
		services = db.dumpServices();
		Iterator<Service> it = services.iterator();
		String[] servs = new String[3];
		int i = 0;
		while(it.hasNext()){
			servs[i] += it.next().getTitle();
			i++;
		}

		select_svc.setItems(servs);
		
		select_svc.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int selectedIndex;
				selectedIndex = select_svc.getSelectionIndex();
				editSelectedService(services.get(selectedIndex));
			}
		});
		
		//String test = svc.getTitle();
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
				btnAdd.setText("SAVE");
				selection = 1;
			}
		});
		svc_new.setBounds(126, 49, 75, 25);
		svc_new.setText("NEW");
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				shell.dispose();
			}
		});
		btnExit.setBounds(539, 391, 75, 25);
		btnExit.setText("EXIT");
		
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
		StubDBInterface db = new StubDBInterface("db");
		db.connect();
		Service temp = (Service)param;
		
		/*Remove comments once ready to use with the db*/
		
		
		switch(selection){
			case 1://add new
				db.insert(temp);
				break;
			case 2://edit
				db.update(temp);
				break;
			case 3://delete
				db.drop(temp);
				break;
		}
		
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
	
	private void editSelectedService(Service service){
		populateSvcFields(service);
	}
	
	private void populateSvcFields(Service service)
	{
		
		if(service != null)
		{
			svcType.setText(service.getType());
			rate_amount.setText(String.valueOf(service.getRate()));
			//rate_length.setText(service.getTime()s);
			svc_description.setText(service.getDescription());
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


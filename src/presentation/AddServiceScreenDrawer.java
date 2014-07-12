package presentation;

import objects.ServiceType;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import acceptanceTests.Register;
import business.ProcessService;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

/**
 * Handles the adding of services
 */
public class AddServiceScreenDrawer
{
	protected Composite composite;
	protected Text svcDescription;
	protected ProcessService processService;
	protected Text svcName;
	protected Button btnAdd;
	protected Button btnCancel;
	
	@SuppressWarnings("unused")
	private Shell shell;

	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public AddServiceScreenDrawer( Composite container ) 
	{
		Register.newWindow(this);
		composite = new Composite( container, SWT.None );
		composite.setLayout(new GridLayout(3, false));
		
		processService = new ProcessService();
		
		Label lblServiceName = new Label(composite, SWT.NONE);
		lblServiceName.setText("Service Name");
		
		svcName = new Text(composite, SWT.BORDER);
		GridData gd_svcName = new GridData(GridData.FILL_HORIZONTAL);
		gd_svcName.horizontalSpan = 2;
		svcName.setLayoutData(gd_svcName);
		
		Label lblServiceDescription = new Label(composite, SWT.NONE);
		GridData gd_lblServiceDescription = new GridData(GridData.FILL_HORIZONTAL);
		gd_lblServiceDescription.verticalAlignment = SWT.TOP;
		lblServiceDescription.setLayoutData(gd_lblServiceDescription);
		lblServiceDescription.setText("Service Description");
		
		svcDescription = new Text(composite, SWT.BORDER | SWT.MULTI);
		GridData gd_svcDescription = new GridData(GridData.FILL_BOTH);
		gd_svcDescription.horizontalSpan = 2;
		svcDescription.setLayoutData(gd_svcDescription);
		new Label(composite, SWT.NONE);
		
		Composite compositeBtn = new Composite(composite, SWT.NONE);
		GridData gd_compositeBtn = new GridData(GridData.FILL_HORIZONTAL);
		gd_compositeBtn.horizontalAlignment = SWT.CENTER;
		gd_compositeBtn.horizontalSpan = 2;
		compositeBtn.setLayoutData(gd_compositeBtn);
		btnAdd = new Button(compositeBtn, SWT.NONE);
		btnAdd.setBounds(0, 10, 73, 25);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				processActionButton();
			}
		});
		btnAdd.setText("Add");
		
		btnCancel = new Button(compositeBtn, SWT.NONE);
		btnCancel.setBounds(94, 10, 73, 25);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				goBackToServiceScreen();
			}
		});
		btnCancel.setText("Cancel");
	}
	
	/**
	 * Clears all input fields
	 */
	protected void clearFields()
	{
		svcName.setText("");
		svcDescription.setText("");
	}
	
	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		ServiceType serviceType;
		MessageBox dialog;
		
		try
		{
			serviceType = new ServiceType(svcName.getText(), svcDescription.getText());
			if(processService.insert(serviceType))
				goBackToServiceScreen();
		}
		catch(Exception e)
		{
			dialog = new MessageBox(new Shell(), SWT.ERROR | SWT.OK);
			dialog.setText("Could not add service");
			dialog.setMessage("Could not add a new service. Please check the data and try again.");
			dialog.open();
		}
	}
	
	/**
	 * Goes back to the service screen composite
	 */
	protected void goBackToServiceScreen()
	{
		Composite serviceScreen = SwitchScreen.getContentContainer();
		new ServiceScreenDrawer( serviceScreen );
		SwitchScreen.switchContent( serviceScreen );
	}
}

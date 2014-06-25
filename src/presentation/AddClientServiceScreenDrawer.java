package presentation;

import objects.Client;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import business.ProcessClient;
import business.ProcessService;

public class AddClientServiceScreenDrawer
{
	private Composite composite;
	private Client client;
	private Combo serviceCombo;
	private ProcessService processService;
	private ProcessClient processClient;

	/**
	 * Creates a new add service to client window
	 * @param container The parent composite
	 */
	public AddClientServiceScreenDrawer(Composite container, Client client) throws IllegalArgumentException
	{		
		composite = new Composite( container, SWT.NONE );
		composite.setLayout(new GridLayout(2, false));
		
		if(client != null)
			this.client = client;
		else 
			throw new IllegalArgumentException();
		
		processService = new ProcessService();
		processClient  = new ProcessClient();
		
		Label lblSelectNewService = new Label(composite, SWT.NONE);
		lblSelectNewService.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSelectNewService.setText("Select new service to track");
		
		serviceCombo = new Combo(composite, SWT.NONE);
		serviceCombo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Composite compositeButton = new Composite(composite, SWT.NONE);
		GridData gd_compositeButton = new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1);
		gd_compositeButton.widthHint = 437;
		compositeButton.setLayoutData(gd_compositeButton);
		
		Button btnAdd = new Button(compositeButton, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				processAddNew();
			}
		});
		btnAdd.setBounds(10, 29, 75, 25);
		btnAdd.setText("Add");
		
		Button btnCancel = new Button(compositeButton, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				goBackToPreviousScreen();
			}
		});
		btnCancel.setBounds(91, 29, 75, 25);
		btnCancel.setText("Cancel");
		
		populateComboBox();
	}
	
	/**
	 * Populates the combo box with service objects
	 */
	private void populateComboBox()
	{
		Service service = null;
		
		while((service = processService.getNextService()) != null)
		{
			serviceCombo.add(service.getTitle());
			serviceCombo.setData(service.getTitle(), service);
		}
	}
	
	/**
	 * Adds a new service to the client
	 */
	private void processAddNew()
	{
		int index;
		Service service;
		
		//Ensure that our form data is valid
		if((index = serviceCombo.getSelectionIndex()) != -1)
		{
			service = (Service) serviceCombo.getData(serviceCombo.getItem(index));
			if(service != null)
			{
				//Finally add the service to the client
				if(processClient.addServiceToClient(client, service))
					goBackToPreviousScreen();
			}
		}
	}
	
	/**
	 * Goes back to the previous screen
	 */
	private void goBackToPreviousScreen()
	{
		Composite analysisScreen = SwitchScreen.getContentContainer();
		new ClientAnalysisScreenDrawer(analysisScreen, client);
		SwitchScreen.switchContent(analysisScreen);
	}
}

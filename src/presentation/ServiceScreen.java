package presentation;
//testing commit and push from eclipse
import java.util.ArrayList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import objects.Service;

import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.List;

import business.ProcessService;

/*
 * 
 * Rough GUI for adding new services 
 * This needs to be added to the original GUI yet and main method removed
 * Currently is only displaying the one sample service offered by the service object
 * Needs to be changed a bit yet to read services into a list
 * 
 */
public class ServiceScreen
{
	private enum Action
	{
		Add,
		Update, 
		Delete
	}
	
	private Text svcType;
	private Shell shell;
	private Text rateAmount;
	private Display display;
	private Text svcDescription;
	private List list;
	private Service currentService;
	private ArrayList<Service> services;
	private ProcessService processService;
	private Text svcName;

	/**
	 * Launch the application.
	 * @param args
	 */

	public ServiceScreen() 
	{
		display = Display.getDefault();
		services = new ArrayList<Service>();
		processService = new ProcessService();
		
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
	public void initializeServiceFields()
	{
		final Button svc_del = new Button(shell, SWT.NONE);
		final Button btnAdd = new Button(shell, SWT.NONE);
		final Button svc_new = new Button(shell, SWT.NONE);
		Service service;
		
		Label lblServiceType = new Label(shell, SWT.NONE);
		lblServiceType.setBounds(232, 90, 71, 15);
		lblServiceType.setText("Service Type");
		
		
		Label lblAddNewService = new Label(shell, SWT.NONE);
		lblAddNewService.setBounds(52, 20, 114, 15);
		lblAddNewService.setText("Add/ Edit Service");
		
		svcType = new Text(shell, SWT.BORDER);
		svcType.setBounds(351, 90, 263, 21);
		
		Label lblServiceRate = new Label(shell, SWT.NONE);
		lblServiceRate.setBounds(232, 125, 71, 15);
		lblServiceRate.setText("Service Rate");
		
		rateAmount = new Text(shell, SWT.BORDER);
		rateAmount.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				verifyMonetaryValue(event);
			}
		});
		rateAmount.setBounds(351, 125, 76, 21);
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(448, 131, 16, 15);
		label.setText("/");
		
		final Combo rate_length = new Combo(shell, SWT.NONE);
		rate_length.setItems(new String[] {"Year", "Month", "Week", "Session"});
		rate_length.setBounds(470, 123, 91, 23);
		rate_length.select(0);
		
		Label lblServiceDescription = new Label(shell, SWT.NONE);
		lblServiceDescription.setBounds(232, 167, 106, 15);
		lblServiceDescription.setText("Service Description");
		
		svcDescription = new Text(shell, SWT.BORDER);
		svcDescription.setBounds(351, 164, 263, 189);
		
		/**
		 * Submit the selected options and fields - button event
		 * the selection variable specifies if its an add, edit or delete
		 */
		btnAdd.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				if(currentService == null)
				{
					try
					{
						Service service = new Service(svcName.getText(), svcDescription.getText(), Double.parseDouble(rateAmount.getText()), svcType.getText());
						submitSvc(service, Action.Add);
					}
					catch(Exception e)
					{
						//Do nothing, invalid data
					}
				}
				else
				{
					//Update the service's values
					currentService.setTitle(svcName.getText());
					currentService.setDescription(svcDescription.getText());
					currentService.setRate(Double.parseDouble(rateAmount.getText()));
					currentService.setType(svcType.getText());
					
					submitSvc(currentService, Action.Update);
				}
			}
		});
		btnAdd.setBounds(351, 376, 75, 25);
		btnAdd.setText("Save");
		

		/**
		 * Disables appropriate buttons and enables appropriate fields
		 * for editing services - button event
		 */
		svc_del.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				if(currentService != null)
				{
					submitSvc(currentService, Action.Delete);
				}
			}
		});
		svc_del.setBounds(112, 407, 75, 25);
		svc_del.setText("Delete");
		
		svc_new.setBounds(10, 407, 75, 25);
		svc_new.setText("New");
		
		Button btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				shell.dispose();
			}
		});
		btnExit.setBounds(539, 376, 75, 25);
		btnExit.setText("Exit");
		
		list = new List(shell, SWT.BORDER);
		list.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				editSelectedService();
			}
		});
		list.setBounds(10, 57, 177, 339);
		
		Label lblServiceName = new Label(shell, SWT.NONE);
		lblServiceName.setText("Service Name");
		lblServiceName.setBounds(232, 57, 82, 15);
		
		svcName = new Text(shell, SWT.BORDER);
		svcName.setBounds(351, 57, 263, 21);
		
		//Initialize the service list
		while((service = processService.getNextService()) != null)
		{
			services.add(service);
			list.add(service.getTitle());
		}
		
		svc_new.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				//Remove the selection from the service list
		        list.setSelection(-1);
		        
				currentService = null;
				clearFields();				
			}
		});
	}

	/**
	 * This method submits the selected option and fields.
	 * 
	 * @param service - The service to update
	 * @param selection - provides direction of what to do
	 */
	private void submitSvc(Service service, Action selection) 
	{
		int index;
		
		assert (service != null);
		if(service != null)
		{		
			switch(selection)
			{
				case Add://add new
					if(processService.insert(service))
					{
						services.add(service);
						list.add(service.getTitle());
					}
					break;
					
				case Update://edit					
					if(processService.update(service))
					{
						index = services.indexOf(service);
						if(index != -1)
						{
							list.setItem(index, service.getTitle());
						}
					}
					break;
					
				case Delete://delete										
					if(processService.delete(service))
					{
						index = services.indexOf(service);
						
						if(index != -1)
						{	
							services.remove(index);
							list.remove(index);
						}
						
						//After we have deleted select the entry above where we
	                    //just deleted
	                    index--;
	                    
	                    if(index < 0 && list.getItemCount() > 0)
	                    	index = 0;
	                    
	                    list.setSelection(index);
	                    
	                    if(index >= 0)
	                    {
	                        //Show the service record
	                        editSelectedService();
	                    }
	                    else
	                    {
	                        //There are no entries in the list
	                        clearFields();
	                    }
					}
					
					break;
			}
		}
	}
	
	/**
	 * Clears all input fields
	 */
	private void clearFields()
	{
		svcName.setText("");
		svcType.setText("");
		svcDescription.setText("");
		rateAmount.setText("");		
	}
	
	/**
	 * Edits the currently selected service in the list
	 */
	private void editSelectedService()
	{
		Service service;
        int selectedIndex;
        
        //Find the selected service in our list, if
        //one is selected
        selectedIndex = list.getSelectionIndex();
        if(selectedIndex != -1 && selectedIndex < services.size())
        {
            service = services.get(selectedIndex);
            currentService = service;
            
            //Populate the service fields with our service data
            populateSvcFields(service);
        }
	}
	
	/**
	 * Populates the given service data on the form
	 * @param service Service to populate
	 */
	private void populateSvcFields(Service service)
	{		
		if(service != null)
		{
			svcName.setText(service.getTitle());
			svcType.setText(service.getType());
			rateAmount.setText(String.valueOf(service.getRate()));
			//rate_length.setText(service.getTime());
			svcDescription.setText(service.getDescription());
		}
	}
	
	/**
	 * Ensures that characters entered into the text box are valid
	 * characters. Numeric characters, one ., and at most 2 decimal places
	 * @param event The entry event
	 */
	private void verifyMonetaryValue(VerifyEvent event)
	{
		Text text;
		int periodIndex = -1;
		boolean valid = false;
		
		assert (event != null);
		if(event != null)
		{
			text = (Text) event.widget;
			
			if(text != null)
			{
				periodIndex = text.getText().indexOf('.');

				//Check if the textbox is a valid monetary value
				if(event.character == SWT.BS || event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT || 
						event.keyCode == SWT.DEL || event.keyCode == SWT.NULL)
					valid = true;
				else if(Character.isDigit(event.character) && (periodIndex == -1 || (periodIndex != -1 && (event.start < periodIndex + 1 || text.getText().substring(periodIndex + 1).length() < 2))))
					valid = true;
				else if(event.character == '.' && periodIndex == -1)
					valid = true;
				
				event.doit = valid;
			}
		}
	}
}


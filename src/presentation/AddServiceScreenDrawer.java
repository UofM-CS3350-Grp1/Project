package presentation;

import javax.swing.JOptionPane;

import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import business.ProcessService;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

/**
 * Handles the adding of services
 */
public class AddServiceScreenDrawer
{
	protected Text svcType;
	protected Composite composite;
	protected Text rateAmount;
	protected Text svcDescription;
	protected ProcessService processService;
	protected Text svcName;
	protected Button btnAdd;
	protected Button btnCancel;

	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public AddServiceScreenDrawer( Composite container ) 
	{
		composite = new Composite( container, SWT.None );
		composite.setLayout(new GridLayout(3, false));
		
		processService = new ProcessService();
		
		Label lblServiceName = new Label(composite, SWT.NONE);
		lblServiceName.setText("Service Name");
		
		svcName = new Text(composite, SWT.BORDER);
		GridData gd_svcName = new GridData(GridData.FILL_HORIZONTAL);
		gd_svcName.horizontalSpan = 2;
		svcName.setLayoutData(gd_svcName);
		
		Label lblServiceType = new Label(composite, SWT.NONE);
		lblServiceType.setText("Service Type");
		
		svcType = new Text(composite, SWT.BORDER);
		GridData gd_svcType = new GridData(GridData.FILL_HORIZONTAL);
		gd_svcType.horizontalSpan = 2;
		svcType.setLayoutData(gd_svcType);
		
		Label lblServiceRate = new Label(composite, SWT.NONE);
		lblServiceRate.setText("Service Rate");
		
		rateAmount = new Text(composite, SWT.BORDER);
		rateAmount.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rateAmount.addVerifyListener(new VerifyListener()
		{
			public void verifyText(VerifyEvent event)
			{
				verifyMonetaryValue(event);
			}
		});
		
		final Combo rateLength = new Combo(composite, SWT.NONE);
		rateLength.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		rateLength.setItems(new String[] {"Year", "Month", "Week", "Session"});
		rateLength.select(0);
		
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
		svcType.setText("");
		svcDescription.setText("");
		rateAmount.setText("");		
	}
	
	/**
	 * Creates a service given the data supplied on the form
	 */
	protected void processActionButton()
	{
		Service service;
		
		try
		{
			service = new Service(svcName.getText(), svcDescription.getText(), Double.parseDouble(rateAmount.getText()), svcType.getText());
			if(processService.insertService(service))
				goBackToServiceScreen();
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error creating new service");
		}
	}
	
	/**
	 * Goes back to the service screen composite
	 */
	protected void goBackToServiceScreen()
	{
		Composite serviceScreen = SwitchScreen.getContentContainer();
		ServiceScreenDrawer ssd = new ServiceScreenDrawer( serviceScreen );
		SwitchScreen.switchContent( serviceScreen );
	}
	
	/**
	 * Ensures that characters entered into the text box are valid
	 * characters. Numeric characters, one ., and at most 2 decimal places
	 * @param event The entry event
	 */
	protected void verifyMonetaryValue(VerifyEvent event)
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
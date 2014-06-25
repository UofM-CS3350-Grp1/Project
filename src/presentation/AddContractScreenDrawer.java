package presentation;

/*
 * NOT USED/NEEDED
 */
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Combo;

import objects.Client;
import objects.Email;
import objects.PhoneNumber;
import objects.Client.ClientStatus;
import business.ProcessClient;
import business.ProcessContract;

public class AddContractScreenDrawer
{
	protected Composite composite;
	
	protected Combo businesses;
	
	protected Button btnSigned;
	protected Button btnNotSigned;
	protected ProcessContract processContract;
	protected Button btnAction;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public AddContractScreenDrawer(Composite container)
	{
		composite = new Composite(container, SWT.BORDER);
		processContract = new ProcessContract();
		
		// units = grid columns
		final int TEXT_WIDTH = 7;
		
		/*
		 * organizes the component
		 */
		GridLayout compositeLayout = new GridLayout();
		compositeLayout.numColumns = 2;
		composite.setLayout(compositeLayout);
		
		/*
		 * organizes how components look within composites
		 */
		GridData componentTweaker = null;	
		Label lblBusinessName = new Label(composite, SWT.None);
		lblBusinessName.setText("Select Business");
		


		Combo combo = new Combo(composite, SWT.READ_ONLY);
		combo.setLayout(new FillLayout(SWT.VERTICAL));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Button btnCreate = new Button(composite, SWT.NONE);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Composite addContractScreenDrawer2 = SwitchScreen.getContentContainer();
				new AddContractScreenDrawer2( addContractScreenDrawer2 );
				SwitchScreen.switchContent( addContractScreenDrawer2 );
			}
		});
		btnCreate.setText("Create");
		
		/*componentTweaker = new GridData(GridData.FILL_HORIZONTAL);
		componentTweaker.horizontalSpan = TEXT_WIDTH;

		ProcessClient clients = new ProcessClient();
		ArrayList<Client> clientList = clients.getClients();

		
		String[] businessNames = new String[clientList.size()];
		
		for(int i=0; i<clientList.size()-1; i++){
			combo.setItem(i,clientList.get(i).getBusinessName());
		}*/

	}
}

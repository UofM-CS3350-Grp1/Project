package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessContract;

/**
 * Contracts screen
 */
public class ContractScreenDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "Contract ID", "Client", "Status", "Value", "Signed Date" };
	private static final int[] tableWidths = { 150, 150, 150, 150, 200 };
	private ProcessContract processContract;
	private ArrayList<Contract> contracts;
	
	/*
	 * Call the constructor with a shell's main component as <container>
	 * and it will be added to that component;
	 */
	public ContractScreenDrawer( Composite container )
	{
		super(container);
		btnAdd.setText("New Contract");
		btnUpdate.setText("Edit Contract");;
		btnDelete.setText("Cancel Contract");
		btnView.setText("View Contract");
		
		if(processContract == null)
			processContract = new ProcessContract();	    
	}	
	
	/**
	 * Populates the table with the client data
	 */
	protected void populateTable()
	{
		Contract contract = null;
		TableItem item;
		
		table.removeAll();
		
		if(processContract == null)
			processContract = new ProcessContract();
		
		contracts = processContract.getContracts();
		Iterator it = contracts.iterator();
		
		//Populate our clients
		while(it.hasNext())
		{
			contract = (Contract) it.next();
			
			item = new TableItem(table, SWT.NULL);

			item.setText(0, contract.getID() + "");
			item.setText(1, contract.getBusinessName() + "");
			item.setText(2, "NULL");
			item.setText(3, contract.getValue() + "");
			item.setText(4, contract.getSignedDate() + "");
		}
	}

	@Override
	protected String[] getTableColumnNames() 
	{
		return tableColumnNames;
	}

	@Override
	protected int[] getTableWidths() 
	{
		return tableWidths;
	}

	@Override
	protected void addNew() 
	{
		
	}

	@Override
	protected void editSelectedItem() 
	{
		
	}

	@Override
	protected void viewSelectedItem() 
	{
		
	}

	@Override
	protected void deleteSelectedItem() 
	{
		
	}
	
}

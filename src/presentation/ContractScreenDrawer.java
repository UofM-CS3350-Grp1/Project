package presentation;

import java.util.ArrayList;
import java.util.Iterator;

import objects.Client;
import objects.Contract;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessContract;

/**
 * Contracts screen
 */
public class ContractScreenDrawer extends BaseStorableScreenDrawer
{
	private static final String[] tableColumnNames = { "Contract ID", "Client", "Status", "Value", "Signed Date" };
	private static final int[] tableWidths = { 0, 150, 150, 150, 200 };
	private ProcessContract processContract;
	private ArrayList<Contract> contracts;
	
	/*
	 * Call the constructor 
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
	 * Populates the table with the contract data
	 */
	protected void populateTable()
	{
		Contract contract = null;
		TableItem item;
		
		table.removeAll();
		
		if(processContract == null)
			processContract = new ProcessContract();
		
		contracts = processContract.getContracts();

		Iterator<Contract> it = contracts.iterator();
		
		while(it.hasNext())
		{
			contract = (Contract) it.next();
			
			item = new TableItem(table, SWT.NULL);

			item.setText(0, contract.getID() + "");
			item.setText(1, contract.getBusinessName() + "");
			item.setText(2, "Not specified");
			item.setText(3, String.format("$%8.2f", contract.getValue()));
			item.setText(4, contract.getFormattedSignedDate());
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
		Composite addContractScreen = SwitchScreen.getContentContainer();
		new AddContractScreenDrawer( addContractScreen );
		SwitchScreen.switchContent( addContractScreen );
	}

	@Override
	protected void editSelectedItem() 
	{
		int index, id;
		Composite updateContractScreen;
		Client client;
		Contract contract;
		
		if((index = table.getSelectionIndex()) != -1)
		{
			try
			{
				id = Integer.parseInt(table.getItem(index).getText(0));
				contract = processContract.getContractByID(id);
				client = processContract.getContractClient(contract);
				
				if(client != null && contract != null)
				{
					updateContractScreen = SwitchScreen.getContentContainer();
					new UpdateContractScreenDrawer( updateContractScreen, contract, client );
					SwitchScreen.switchContent( updateContractScreen );
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe);
			}
		}
	}

	@Override
	protected void viewSelectedItem() 
	{
		int index;
		Composite analysisScreen;
		Client client;
		Contract contract;
		ProcessContract processContract = new ProcessContract();
		
		if((index = table.getSelectionIndex()) != -1)
		{
			try
			{
				TableItem select = table.getItem(index);
				int x = Integer.parseInt(select.getText(0));
				contract = processContract.getContractByID(x);
				client = processContract.getContractClient(contract);		
				if(client==null) 
					System.out.println("Client is null");
				if(contract==null) 
					System.out.println("Contract is null");
				
				if(client != null && contract != null)
				{
					analysisScreen = SwitchScreen.getContentContainer();
					new ContractAnalysisScreenDrawer( analysisScreen, contract, client );
					SwitchScreen.switchContent( analysisScreen );
				}
				else
				{
					System.out.println("error");
				}
			}
			catch(NumberFormatException nfe)
			{
				System.out.println(nfe);
			}
		}
	}

	@Override
	protected void deleteSelectedItem() 
	{
		int selectedIndex = table.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		Contract contract;
		TableItem selectedItem;
		
		if(selectedIndex != -1)
		{
			selectedItem = table.getItem(selectedIndex);
			
			//Ensure that the user actually wants to delete the item
			dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
			dialog.setText("Confirmation");
			dialog.setMessage("Are you sure you want to delete contract ID #" + selectedItem.getText(0) + "?");
			
			buttonID = dialog.open();
			switch(buttonID)
			{
				case SWT.YES:
					contract = processContract.getContractByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(contract != null)
						processContract.delete(contract);
					
					table.remove(selectedIndex);
					
					break;
					
				case SWT.NO:
					break;
			}
		}
	}
	
}

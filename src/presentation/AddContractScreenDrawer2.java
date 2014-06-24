package presentation;

import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import business.ProcessClient;
import business.ProcessService;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AddContractScreenDrawer2 
{

	protected Composite composite;

	protected ProcessClient processClient;
	protected ProcessService processService;
	private Table table;
	private TableColumn column;		
	private Table table_1;
	protected TableItem item;
	
	public AddContractScreenDrawer2(Composite container) 
	{
		composite = new Composite(container, SWT.BORDER);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(35, 64, 165, 204);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Service");
		column.setWidth(110);

		column = new TableColumn(table, SWT.NULL);
		column.setText("Rate");
		column.setWidth(50);
		
		table_1 = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(432, 64, 115, 204);
		
		TableColumn tableColumn = new TableColumn(table_1, SWT.NONE);
		tableColumn.setWidth(110);
		tableColumn.setText("Contract");
		
		Button btnAdd = new Button(composite, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addSelectedItem();
			}
		});
		btnAdd.setBounds(278, 96, 75, 25);
		btnAdd.setText("ADD >>");
		
		Button button = new Button(composite, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				removeSelectedItem();
			}
		});
		button.setBounds(278, 175, 75, 25);
		button.setText("<< REMOVE");
		
		processClient = new ProcessClient();
		processService = new ProcessService();
		
		populateTable();
	}

	private void addSelectedItem() 
	{
		int selectedIndex = table.getSelectionIndex();

		if(selectedIndex != -1)
		{
			item = new TableItem(table_1, SWT.NULL);

			item.setText(0, table.getItem(selectedIndex).getText());

			table.remove(selectedIndex);
		}
	}

	private void removeSelectedItem() 
	{
		int selectedIndex = table_1.getSelectionIndex();

		if(selectedIndex != -1)
		{
			item = new TableItem(table, SWT.NULL);

			item.setText(0, table_1.getItem(selectedIndex).getText());

			table_1.remove(selectedIndex);
		}
	}

	protected void populateTable() 
	{
		Service service = null;
		
		table.removeAll();
		
		if(processService == null)
			processService = new ProcessService();
		
		//Populate our services
		while((service = processService.getNextService()) != null)
		{
			item = new TableItem(table, SWT.NULL);
			
			item.setText(0, service.getTitle());
			item.setText(1, service.getRate() + "");
		}
		
	}
}

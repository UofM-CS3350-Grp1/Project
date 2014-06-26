package presentation;

import objects.FeatureHistory;
import objects.Service;
import objects.TrackedFeature;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import business.ProcessFeatureHistory;

/**
 * Manages the table of feature history nodes for a particular feature
 * of a given service
 */
public class HistoryTableDrawer
{
	private static final String[] historyNames = { "ID", "Date", "Value", "Notes" };
	private static final int[] historyWidths = { 0, 150, 150, 450 };
	
	private Table historyTable;
	private Composite historyButtonComposite;
	private Service service;
	private TrackedFeature feature;
	private ProcessFeatureHistory processHistory;
	
	/**
	 * Creates a new history table drawing object
	 * @param composite	The parent composite
	 * @param feature	The feature to populate the history for
	 * @throws IllegalArgumentException
	 */
	public HistoryTableDrawer(Composite composite, Service service, TrackedFeature feature) throws IllegalArgumentException
	{
		Composite historyDataComposite = new Composite(composite, SWT.NONE);
		historyDataComposite.setLayout(new GridLayout(1, false));
		GridData gd_historyDataComposite = new GridData(GridData.FILL_BOTH);
		gd_historyDataComposite.heightHint = 273;
		gd_historyDataComposite.widthHint = 319;
		historyDataComposite.setLayoutData(gd_historyDataComposite);
		
		assert (feature != null && service != null);
		if(feature != null && service != null)
		{
			this.feature = feature;
			this.service = service;
		}
		else
			throw new IllegalArgumentException();
		
		processHistory = new ProcessFeatureHistory();
		
		Label lblHistory = new Label(historyDataComposite, SWT.NONE);
		lblHistory.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblHistory.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblHistory.setText("Tracked Features");
		
		historyTable = new Table(historyDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_HistoryTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_HistoryTable.heightHint = 200;
		historyTable.setLayoutData(gd_HistoryTable);
		historyTable.setHeaderVisible(true);
		historyTable.setLinesVisible(true);
		
		historyButtonComposite = new Composite(historyDataComposite, SWT.NONE);
		historyButtonComposite.setLayout(new GridLayout(3, false));
		GridData gd_historyButtonComposite = new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1);
		gd_historyButtonComposite.heightHint = 44;
		gd_historyButtonComposite.widthHint = 215;
		historyButtonComposite.setLayoutData(gd_historyButtonComposite);
		
		Button btnNew = new Button(historyButtonComposite, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				addHistory();
			}
		});
		btnNew.setText("New record");
		
		Button btnCancel = new Button(historyButtonComposite, SWT.NONE);
		btnCancel.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				removeHistory();
			}
		});
		btnCancel.setText("Delete record");
		new Label(historyButtonComposite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		populateTable();
	}
	
	/**
	 * Populates the table with all of the histories related to the feature
	 */
	private void populateTable()
	{
		FeatureHistory history = null;		
		TableItem item;
		TableColumn column;	
		
		for(int i = 0; i < historyNames.length; i++)
		{
			column = new TableColumn(historyTable, SWT.NULL);
			column.setText(historyNames[i]);
			column.setWidth(historyWidths[i]);
		}
		
		//Hide the ID field because the user does not need to see
		//it. It is simply an internal helper to find the associated object.
		column = historyTable.getColumn(0);
		column.setResizable(false);
		
		while((history = processHistory.getNextHistoryForFeature(service, feature)) != null)
		{
			item = new TableItem(historyTable, SWT.NULL);
			
			item.setText(0, history.getID() + "");
			item.setText(1, history.getShortDate());
			item.setText(2, history.getValue() + "");
			item.setText(3, history.getNotes());
		}
	}
	
	/**
	 * Adds a history to the service's feature
	 */
	private void addHistory()
	{
		Composite addFeature = SwitchScreen.getContentContainer();
		new AddFeatureHistoryDrawer(addFeature, feature, service);
		SwitchScreen.switchContent(addFeature);	
	}
	
	/**
	 * Removes a history from the service's feature
	 */
	private void removeHistory()
	{
		int selectedIndex = historyTable.getSelectionIndex();
		MessageBox dialog;
		int buttonID;
		TableItem selectedItem;
		FeatureHistory history;
		
		if(selectedIndex != -1)
		{
			selectedItem = historyTable.getItem(selectedIndex);
			
			//Ensure that the user actually wants to delete the item
			dialog = new MessageBox(new Shell(), SWT.ICON_WARNING | SWT.YES | SWT.NO);
			dialog.setText("Confirmation");
			dialog.setMessage("Are you sure you want to delete " + selectedItem.getText(1) + "?");
			
			buttonID = dialog.open();
			switch(buttonID)
			{
				case SWT.YES:
					history = processHistory.getHistoryByID(Integer.parseInt(selectedItem.getText(0)));
					
					if(history != null)
					{
						if(processHistory.delete(history))
							historyTable.remove(selectedIndex);
					}
					
					break;
					
				case SWT.NO:
					break;
			}
		}
	}
}

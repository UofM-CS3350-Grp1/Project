package presentation;

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

import business.ProcessAddFeature;

/**
 * Responsible for drawing the features table 
 */
public class TrackFeaturesTableDrawer
{
	private static final String[] featureNames = { "ID", "Feature Name" };
	private static final int[] featureWidths = { 0, 300 };
	
	private Table featuresTable;
	private Service service;
	private ProcessAddFeature processFeature;
	
	/**
	 * Creates a new features table drawer
	 * @param composite	The parent composite
	 * @param service	The service to draw features for
	 * @throws IllegalArgumentException
	 */
	public TrackFeaturesTableDrawer(Composite composite, Service service) throws IllegalArgumentException
	{
		Composite featureDataComposite = new Composite(composite, SWT.NONE);
		featureDataComposite.setLayout(new GridLayout(1, false));
		GridData gd_featureDataComposite = new GridData(GridData.FILL_BOTH);
		gd_featureDataComposite.heightHint = 273;
		gd_featureDataComposite.widthHint = 319;
		featureDataComposite.setLayoutData(gd_featureDataComposite);
		
		assert (service != null);
		if(service != null)
			this.service = service;
		else
			throw new IllegalArgumentException();
		
		processFeature = new ProcessAddFeature();
		
		Label lblFeatures = new Label(featureDataComposite, SWT.NONE);
		lblFeatures.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblFeatures.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblFeatures.setText("Tracked Features");
		
		featuresTable = new Table(featureDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_featuresTable = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_featuresTable.heightHint = 200;
		featuresTable.setLayoutData(gd_featuresTable);
		featuresTable.setHeaderVisible(true);
		featuresTable.setLinesVisible(true);
		new Label(composite, SWT.NONE);		
	}
}

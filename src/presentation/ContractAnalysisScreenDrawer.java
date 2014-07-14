package presentation;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import objects.Client;
import objects.Contract;
import objects.Service;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;

import business.ProcessService;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * Responsible for drawing the detailed client information including all of the
 * client's data in a read only format, the services that the client uses and
 * the tracking analysis
 */
public class ContractAnalysisScreenDrawer
{
	private ScrolledComposite scrollComposite;
	private Composite composite;
	private Composite buttonComposite;
	private Table servicesTable;
	private Client client;
	private Contract contract;
	
	//Client vars
	private Label lblClientNameData;
	private Label lblContractIDData;
	private Label lblEmailData;
	private Label lblSignedData;
	private Label lblAddressData;
	private Label lblValueData;
	private Label lblPhoneNumberData;
	private Label lblStartData;
	private Label lblStatusData;
	private Label lblEndData;
	
	//Contract vars
	private Label lblContractIDHeader;
	private Label lblContactData;
	
	private Label lblEnd;
	private Label lblContact;
	private Label lblAddress;
	private Label lblPhone;
	private Label lblEmail_1;
	private Button btnPrint;
	private Text detailsData;
	private Label lblDetails;
	private Label lblServicesInThis;
	
	private String[] months = {"null", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	private String[] years = {"2013", "2014", "2015", "2016", "2017", "2018"};
	
	/**
	 * Creates a new client analysis screen
	 * @param container 	The composite
	 */
	public ContractAnalysisScreenDrawer(Composite container, Contract contract, Client client) throws IllegalArgumentException 
	{
		scrollComposite = new ScrolledComposite(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrollComposite.getVerticalBar().setIncrement(15);
		
		composite = new Composite(scrollComposite, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));	
		
		scrollComposite.setContent(composite);		

		if(contract != null)
			this.contract = contract;
		else
			throw new IllegalArgumentException();

		if(client != null)
			this.client = client;
		else
			throw new IllegalArgumentException();
				
		Composite contractDataComposite = new Composite(composite, SWT.NONE);
		contractDataComposite.setLayout(new GridLayout(9, false));
		GridData gd_contractDataComposite = new GridData(GridData.FILL_BOTH);
		gd_contractDataComposite.heightHint = 245;
		gd_contractDataComposite.widthHint = 486;
		contractDataComposite.setLayoutData(gd_contractDataComposite);
		
		lblContractIDHeader = new Label(contractDataComposite, SWT.NONE);
		lblContractIDHeader.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));
		lblContractIDHeader.setAlignment(SWT.LEFT);
		lblContractIDHeader.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblContractIDHeader.setText("CONTRACT_ID");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		
		Label lblContractID = new Label(contractDataComposite, SWT.NONE);
		lblContractID.setText("Contract #:");
		
		lblContractIDData = new Label(contractDataComposite, SWT.NONE);
		lblContractIDData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblContractIDData.setText("CONTRACT_ID");
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblSigned = new Label(contractDataComposite, SWT.NONE);
		lblSigned.setText("Signed date:");
		
		lblSignedData = new Label(contractDataComposite, SWT.NONE);
		lblSignedData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblSignedData.setText("SIGNED");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblValue = new Label(contractDataComposite, SWT.NONE);
		lblValue.setText("Value:");
		
		lblValueData = new Label(contractDataComposite, SWT.NONE);
		lblValueData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblValueData.setText("VALUE");
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblStart = new Label(contractDataComposite, SWT.NONE);
		lblStart.setText("Start date:");
		
		lblStartData = new Label(contractDataComposite, SWT.NONE);
		lblStartData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblStartData.setText("START_DATE");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		Label lblStatus = new Label(contractDataComposite, SWT.NONE);
		lblStatus.setText("Status:");
		
		lblStatusData = new Label(contractDataComposite, SWT.NONE);
		lblStatusData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblStatusData.setText("STATUS");
		new Label(contractDataComposite, SWT.NONE);
		
		lblEnd = new Label(contractDataComposite, SWT.NONE);
		lblEnd.setText("End date:");
		
		lblEndData = new Label(contractDataComposite, SWT.NONE);
		lblEndData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblEndData.setText("END");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);

		lblClientNameData = new Label(contractDataComposite, SWT.NONE);
		lblClientNameData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 6, 1));
		lblClientNameData.setAlignment(SWT.LEFT);
		lblClientNameData.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblClientNameData.setText("CLIENT_BUSINESS_NAME");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblAddress = new Label(contractDataComposite, SWT.NONE);
		lblAddress.setText("Address:");
		
		lblAddressData = new Label(contractDataComposite, SWT.NONE);
		lblAddressData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblAddressData.setText("ADDRESS");
		new Label(contractDataComposite, SWT.NONE);
		
		lblEmail_1 = new Label(contractDataComposite, SWT.NONE);
		lblEmail_1.setText("Email:");

		lblEmailData = new Label(contractDataComposite, SWT.NONE);
		lblEmailData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblEmailData.setText("EMAIL");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblContact = new Label(contractDataComposite, SWT.NONE);
		lblContact.setText("Name:");
		
		lblContactData = new Label(contractDataComposite, SWT.NONE);
		lblContactData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(contractDataComposite, SWT.NONE);
		
		
		
		lblPhone = new Label(contractDataComposite, SWT.NONE);
		lblPhone.setText("Phone#:");
		
		lblPhoneNumberData = new Label(contractDataComposite, SWT.NONE);
		lblPhoneNumberData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblPhoneNumberData.setText("PHONE_NUMBER");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		lblDetails = new Label(contractDataComposite, SWT.NONE);
		lblDetails.setText("Details");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		detailsData = new Text(contractDataComposite, SWT.BORDER | SWT.READ_ONLY);
		GridData gd_detailsData = new GridData(SWT.FILL, SWT.CENTER, true, false, 9, 1);
		gd_detailsData.heightHint = 109;
		detailsData.setLayoutData(gd_detailsData);
		
		lblServicesInThis = new Label(contractDataComposite, SWT.NONE);
		lblServicesInThis.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblServicesInThis.setText("Services in this contract");
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		servicesTable = new Table(contractDataComposite, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData gd_servicesTable = new GridData(SWT.FILL, SWT.TOP, true, false, 9, 1);
		gd_servicesTable.heightHint = 141;
		servicesTable.setLayoutData(gd_servicesTable);
		servicesTable.setHeaderVisible(true);
		servicesTable.setLinesVisible(true);
		
		TableColumn tableColumn_1 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_1.setWidth(150);
		tableColumn_1.setText("Service");
		
		TableColumn tableColumn_2 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_2.setWidth(70);
		tableColumn_2.setText("Rate");
		
		TableColumn tableColumn_3 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("Category");
		
		TableColumn tableColumn_4 = new TableColumn(servicesTable, SWT.NONE);
		tableColumn_4.setWidth(300);
		tableColumn_4.setText("Details");
		
		
		buttonComposite = new Composite(contractDataComposite, SWT.NONE);
		buttonComposite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		buttonComposite.setLayout(new GridLayout(2, false));
		
		btnPrint = new Button(buttonComposite, SWT.NONE);
		btnPrint.addSelectionListener(new SelectionAdapter() 
		{
			@Override
			public void widgetSelected(SelectionEvent arg0) 
			{
				try {
					createContractPDF();
				} catch (IOException e) {
					System.out.println("Error creating pdf 1");
					e.printStackTrace();
				} catch (DocumentException e) {
					System.out.println("Error creating pdf 2");
					e.printStackTrace();
				}
			}
		});
		btnPrint.setText("Create PDF");
		
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		new Label(contractDataComposite, SWT.NONE);
		
		populateClientData();
		populateServiceData();
		populateContractData();
		
		scrollComposite.setMinSize(contractDataComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrollComposite.setExpandHorizontal(true);
		scrollComposite.setExpandVertical(true);
	}
	
	/**
	 * creates, saves and prints contract PDF
	 * @throws IOException 
	 * @throws DocumentException 
	 */
	public void createContractPDF() throws IOException, DocumentException
	{
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy");
		
		String DEST = "src/presentation/ContractTemplate.pdf";
		String IMAGE = "src/presentation/template.jpg";
		File file = new File("src/presentation/ContractTemplate.pdf");

		Document document = new Document(PageSize.A4);
		FileOutputStream fos = new FileOutputStream(DEST);
		PdfWriter writer = PdfWriter.getInstance(document, fos);
		document.open();
		
		PdfContentByte canvas = writer.getDirectContentUnder();
		Image image = Image.getInstance(IMAGE);
		image.scaleAbsolute(PageSize.A4);
		image.setAbsolutePosition(0, 0);
		canvas.addImage(image);
        BaseFont bf = BaseFont.createFont();
		PdfContentByte over = writer.getDirectContent();

		//Client details
        setTextPosition(over, writer, bf, 150, 730, contract.getBusinessName());
        setTextPosition(over, writer, bf, 150, 708, client.getAddress());
        setTextPosition(over, writer, bf, 150, 688, client.getName());
        setTextPosition(over, writer, bf, 150, 667, client.getPhoneNumber().toString());
        setTextPosition(over, writer, bf, 150, 647, client.getEmail().toString());
        setTextPosition(over, writer, bf, 150, 626, "Signed");
        setTextPosition(over, writer, bf, 150, 606, df.format(contract.getSignedDate()));
        setTextPosition(over, writer, bf, 150, 584, df.format(contract.getPeriod()));

        //Buzzin details
        setTextPosition(over, writer, bf, 415, 730, "Buzzin' Digital Marketing");
        setTextPosition(over, writer, bf, 415, 708, "123 First St.");
        setTextPosition(over, writer, bf, 415, 688, "Christos Vasalirakis");
        setTextPosition(over, writer, bf, 415, 667, "204 960 1538");
        setTextPosition(over, writer, bf, 415, 647, "jasontoews88@gmail.com");
        setTextPosition(over, writer, bf, 415, 626, df.format(contract.getSignedDate()));

        //input services
        inputServices(over, writer, bf);
        
        //Subtotal, GST and Total
        double sub = getSubtotal();
        setTextPosition(over, writer, bf, 510, 280, "$ "+(int)sub);
        setTextPosition(over, writer, bf, 510, 250, "$ "+Math.round((sub*0.05)*100.00)/100.00);
        setTextPosition(over, writer, bf, 510, 215, "$ "+Math.round(((sub*0.05)+sub)*100.00)/100.00);
        
        //Terms of the contract
        ColumnText ct = new ColumnText(over);
        setTextPosition(contract.getDetails(), ct);
        
		
		document.close();
		writer.close();
		over.closePath();
		fos.close();
		Desktop.getDesktop().open(file);
	}
	
	/*
	 * @return returns the subtotal (before GST) of the contract
	 */
	public double getSubtotal()
	{
		TableItem[] items = servicesTable.getItems();
		double result = 0;
		int multiplier = getMultiplier();
		for(int i=0; i<items.length; i++)
		{
			if(!servicesTable.getItem(i).getText(2).contains("Web Design"))
			{
				result += Double.parseDouble(servicesTable.getItem(i).getText(1))*multiplier;
			}else{
				result += Double.parseDouble(servicesTable.getItem(i).getText(1));
			}
		}
		return result;
	}
	
	/*
	 * inputs services to the pdf
	 */
	public void inputServices(PdfContentByte over, PdfWriter writer, BaseFont bf)
	{
		TableItem[] items = servicesTable.getItems();
		int y = 500;
		int multiplier = getMultiplier();
		for(int i=0; i<items.length; i++)
		{
			if(!servicesTable.getItem(i).getText(2).contains("Web Design"))
			{
		        setTextPosition(over, writer, bf, 20, y, servicesTable.getItem(i).getText(2));
		        setTextPosition(over, writer, bf, 155, y, servicesTable.getItem(i).getText(3));
		        setTextPosition(over, writer, bf, 435, y, multiplier+"");
		        setTextPosition(over, writer, bf, 520, y, "$ "+(int)(Double.parseDouble(servicesTable.getItem(i).getText(1)))*(int)multiplier);
		        y -= 48;
			}else{
				if(multiplier<12) multiplier = 12;
		        setTextPosition(over, writer, bf, 20, y, servicesTable.getItem(i).getText(2));
		        setTextPosition(over, writer, bf, 155, y, servicesTable.getItem(i).getText(3));
		        setTextPosition(over, writer, bf, 435, y, ""+(int)(multiplier/12));
		        setTextPosition(over, writer, bf, 520, y, "$ "+(int)Double.parseDouble(servicesTable.getItem(i).getText(1))*(int)(multiplier/12));
		        y -= 48;
				multiplier = getMultiplier();
			}
		}
	}
	
	/*
	 * @return returns the number of months of the contract
	 */
	public int getMultiplier()
	{
		int multiplier = 0;
		int monthStart = 0;
		int yearStart = 0;
		int monthEnd = 0;
		int yearEnd = 0;
		for(int i=1; i<13; i++)
		{
			if(contract.getSignedDate().toString().contains(months[i]))
			{
				monthStart = i;
				for(int x=0; x<years.length; x++)
				{
					if(contract.getSignedDate().toString().contains(years[x]))
					{
						yearStart = Integer.parseInt(years[x]);
					}
				}
			}
			if(contract.getPeriod().toString().contains(months[i]))
			{
				monthEnd = i;
				for(int x=0; x<years.length; x++)
				{
					if(contract.getPeriod().toString().contains(years[x]))
					{
						yearEnd = Integer.parseInt(years[x]);
					}
				}
			}
		}
		if(yearStart!=yearEnd)
		{
			int yearsDifference = yearEnd-yearStart;
			multiplier = 12-monthStart;
			multiplier += monthEnd;
			multiplier += ((yearsDifference*12)-12);
		}else{
			multiplier = monthEnd-monthStart;
		}
		return multiplier;
	}
	
	/*
	 * sets the text position inside the pdf
	 */
	private void setTextPosition(PdfContentByte over, PdfWriter writer, BaseFont bf, int x, int y, String text)
	{
		over.saveState();
		over.beginText();
        over.setLineWidth(1.5f);
        over.setFontAndSize(bf, 12);
        over.moveText(x, y);
        over.showText(text);
        over.endText();
        over.restoreState();
	}
	
	/*
	 * sets the text position inside the pdf (special case for text wrapping)
	 */
	private void setTextPosition(String text, ColumnText ct) throws DocumentException
	{
		ct.setSimpleColumn(new Phrase(new Chunk(text)),
				20, 190, 550, 100, 15, Element.ALIGN_LEFT | Element.ALIGN_TOP | Element.ALIGN_RIGHT | Element.ALIGN_BOTTOM);
		ct.go();
	}
	
	/**
	 * Fills in the fields with the contract data
	 */
	private void populateContractData()
	{
		String value = String.valueOf(contract.getValue());
		
		lblContractIDHeader.setText("Contract");
		lblContractIDData.setText(String.valueOf(contract.getID()));
		lblValueData.setText("$"+(value).substring(0, value.length()-2));
		lblSignedData.setText(contract.getFormattedSignedDate());
		lblStartData.setText(contract.getFormattedSignedDate());
		lblEndData.setText(contract.getFormattedPeriod());
		lblStatusData.setText(contract.getStatus());
		detailsData.setText(contract.getDetails());
	}
	
	/**
	 * Fills in the fields with the client data
	 */
	private void populateClientData()
	{
		lblClientNameData.setText(client.getBusinessName());
		lblContractIDData.setText(client.getName());
		lblContactData.setText(client.getName());
		lblAddressData.setText(client.getAddress());
		lblEmailData.setText(client.getEmail().toString());
		lblPhoneNumberData.setText(client.getPhoneNumber().formattedPhoneNumber());
	}
	
	/**
	 * Populates the services table with the client's services
	 */
	private void populateServiceData()
	{		
		TableItem item;
		Service service;

		ProcessService processService = new ProcessService();
		
		while((service = processService.getNextService())!=null)
		{
			if(service.getContractID() == contract.getID())
			{
				item = new TableItem(servicesTable, SWT.NULL);
				item.setText(0, service.getTitle());
				item.setText(1, String.valueOf(service.getRate()));
				item.setText(2, service.getServiceType().getType());
				item.setText(3, service.getDescription());
			}
		}
	}
}

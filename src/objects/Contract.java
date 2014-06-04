package objects;

import java.util.Date;

/*
*
* these jar files need to be uploaded yet.
* Im looking for another way to do it since
* for some reason im not authorized to do so
* from eclipse.
*
*/
/*
import java.awt.Rectangle;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.sun.xml.internal.txw2.Document;
import com.itextpdf.*;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
*/

/**
 * Handles the service rendered between the company and their client
 */
public class Contract implements Storable
{
	private int contractNumber; 	//Contract ID number
	private String businessName;	//Name of the associated business
	private String details;			//Specific details of the contract
	private double value;			//Value of the contract
	private Date period;			//Contract period (end date)
	
	/**
	 * Creates a new contract
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * 
	 */
	public Contract(int contractNumber, String businessName, String details, double value, Date period)
	{
		this.contractNumber = contractNumber;
		this.businessName = businessName;
		this.details = details;
		this.value = value;
		this.period = period;
	}
	
	/**
	 * Creates a new contract without a preset number. Used for adding new contracts to DBMS.
	 * @param contractNumber 	The number of the contract
	 * @param businessName 	 	The name of the business
	 * @param details			The details of the contract
	 * @param value				The amount the contract is worth
	 * @param period			The period of the contract
	 * 
	 */
	public Contract(String businessName, String details, double value, Date period)
	{
		this.contractNumber = 0;
		this.businessName = businessName;
		this.details = details;
		this.value = value;
		this.period = period;
	}
	
	/*********************************************************
	 * 				Accessors and Mutators
	 ********************************************************/
	
	/**
	 * Get the number of the contract
	 * @return The contract number
	 */
	public int getContractNumber() 
	{
		return contractNumber;
	}

	/**
	 * Gets the business name
	 * @return The business' name
	 */
	public String getBusinessName()
	{
		return businessName;
	}

	/**
	 * Sets the name of the business
	 * @param businessName The business name
	 */
	public void setBusinessName(String businessName) 
	{
		assert (businessName != null && businessName != "");
		if(businessName != null && businessName != "")
			this.businessName = businessName;
	}

	/**
	 * Gets the contract details
	 * @return The contract details
	 */
	public String getDetails() 
	{
		return details;
	}

	/**
	 * Sets the additional data of a contract
	 * @param details The contract details
	 */
	public void setDetails(String details) 
	{
		assert (details != null);
		if(details != null)
			this.details = details;
	}

	/**
	 * Gets the value of the contract
	 * @return The value of the contract
	 */
	public double getValue() 
	{
		return value;
	}

	/**
	 * Sets the amount of the contract
	 * @param value A non-negative value
	 */
	public void setValue(double value) 
	{
		assert (value >= 0);
		if(value >= 0)
			this.value = value;
	}

	/**
	 * Gets the period of the contract
	 * @return The contract period
	 */
	public Date getPeriod() 
	{
		return period;
	}

	/**
	 * Sets the contract period
	 * @param period	Period of the contract
	 */
	public void setPeriod(Date period) 
	{
		assert (period != null);
		if(period != null)
			this.period = period;
	}
	
	public String toString()
	{
		return "(Contract ID: "+this.contractNumber+
				", Business Name: " +this.businessName +
				", Details: " + this.details +
				", Value: " + String.format("%.2f", this.value) +
				", End Date: " + this.period.toString() +")";
	}
	
	/*
	*
	* Calling this method will export a pdf file of the contract.
	* It is not completed yet. Jar file need to be uploaded,
	* as well as the contract data needs to be connected to
	* this method as well. Im working on that now.
	*
	*/
	
	/*
	public static void createPDF() throws FileNotFoundException, DocumentException{
		PageSize size = new PageSize();
		com.itextpdf.text.Document document = new com.itextpdf.text.Document(size.A4, 50, 50, 50, 50); //initialize document obj
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:\\Users/Jason/Desktop/CS/COMP3350/test.pdf"));//creation of pdfwriter obj
		document.open();
		
		//creation of paragraph object
		Anchor anchorTarget = new Anchor("First page of the document.");
	      anchorTarget.setName("BackToTop");
	      Paragraph paragraph1 = new Paragraph();

	      paragraph1.setSpacingBefore(50);

	      paragraph1.add(anchorTarget);
	      document.add(paragraph1);
	
		document.add(new Paragraph("Some more text on the first page with different color and font type.", 
	
		FontFactory.getFont(FontFactory.COURIER, 14, Font.BOLD,	new CMYKColor(0, 255, 0, 0))));
		document.close();
	}
	*/
}

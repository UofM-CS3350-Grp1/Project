package business;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.widgets.Text;

public class ValidateTextbox
{
	/**
	 * Validates a textbox event to ensure that its textbox is only numeric
	 * @param event The textbox event to validate
	 * @param maxLength	The maximum length of the numeric string or -1 for any length
	 */
	public static void verifyNumericTextbox(VerifyEvent event, int maxLength)
	{
		Text text;
		boolean valid = false;
		
		assert (event != null);
		if(event != null)
		{
			text = (Text) event.widget;
			
			if(text != null && maxLength >= -1)
			{
				//Check if the textbox is numeric
				if(event.character == SWT.BS || event.keyCode == SWT.ARROW_LEFT || event.keyCode == SWT.ARROW_RIGHT || 
						event.keyCode == SWT.DEL || event.keyCode == SWT.NULL)
					valid = true;
				else if(Character.isDigit(event.character) && (maxLength == -1 || (maxLength != -1 && text.getText().length() < maxLength)))
					valid = true;
				
				event.doit = valid;
			}
		}
	}
	
	/**
	 * Ensures that characters entered into the text box are valid
	 * characters. Numeric characters, one ., and at most 2 decimal places
	 * @param event The entry event
	 */
	public static void verifyMonetaryValue(VerifyEvent event)
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

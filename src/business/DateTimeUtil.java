package business;

import java.util.Calendar;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DateTime;

/**
 * Converts SWT's DateTime objects into Date objects 
 */
public class DateTimeUtil
{
	/**
	 * Returns the Date from the given DateTime.
	 * @param dateTime the DateTime to get a Date from
	 * @return the Date from the given DateTime
	 */
	public static Date getDate(DateTime dateTime) 
	{
		Calendar calendar;
		Date date = null;
		
		assert (dateTime != null);
		if(dateTime != null)
		{
			calendar = Calendar.getInstance();
			
			if ((dateTime.getStyle() & SWT.CALENDAR) != 0 ||
					(dateTime.getStyle() & SWT.DATE) != 0) 
			{
				date =  getDate(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), 
						calendar.get(Calendar.HOUR_OF_DAY), 
						calendar.get(Calendar.MINUTE), 
						calendar.get(Calendar.SECOND));			
			}
			else if ((dateTime.getStyle() & SWT.TIME) != 0) 
			{
				date =  getDate(calendar.get(Calendar.YEAR), 
						calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 
						dateTime.getHours(), dateTime.getMinutes(), dateTime.getSeconds());			
			}
			else
			{			
				date =  getDate(dateTime.getYear(), dateTime.getMonth(), dateTime.getDay(), 
					dateTime.getHours(), dateTime.getMinutes(), dateTime.getSeconds());
			}
		}
		
		return date;
	}
	
	/**
	 * Creates a Date for the given parameters.
	 * @param year the year
	 * @param month the month (0-based)
	 * @param day the day of the month
	 * @param hour the hour
	 * @param minute the minute
	 * @param second the second
	 * @return the corresponding date
	 */
	public static Date getDate(int year, int month, int day, int hour, int minute, int second) 
	{
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, day, hour, minute, second);
		Date date = calendar.getTime();
		
		return date;
	}
}

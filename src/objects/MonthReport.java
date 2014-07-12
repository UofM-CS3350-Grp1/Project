package objects;

import java.util.Comparator;
import java.util.Date;

/**
 * Stores the total value of a feature/ revenue/ expense for a given month 
 */
public class MonthReport
{
	private Date period;
	private double value;
	
	/**
	 * Creates a new monthly report for a feature/ revenue/ expense
	 * @param period 	The period to report the data for (Primarily a month and a year)
	 * @param value		The value of the period
	 * @throws IllegalArgumentException
	 */
	public MonthReport(Date period, double value) throws IllegalArgumentException
	{
		assert (period != null);
		if(period != null)
		{
			this.period = period;
			this.value = value;
		}
		else
			throw new IllegalArgumentException();
	}

	/**
	 * @return The period (primarily the month and the year)
	 */
	public Date getPeriod()
	{
		return period;
	}

	/**
	 * @return The value of the period
	 */
	public double getValue() 
	{
		return value;
	}
	
	/**
	 * Compares monthly reports by date
	 */
	public static Comparator<MonthReport> compareByDate = new Comparator<MonthReport>() 
    {
        public int compare(MonthReport r1, MonthReport r2)
        {
            int retVal = 0;
            
            if(r1.period.getTime() < r2.period.getTime())
                retVal = -1;
            else if(r1.period.getTime() > r2.period.getTime())
                retVal = 1;
                
            return retVal;
        }
    };
}

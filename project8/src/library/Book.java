package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A Book is a library item that can be checked out for 21 days and renewed at most twice.
 * If overdue, the fine is .25 per day.
 */
public class Book extends Rental
{
  private int renewalCount;
  
  /**
   * Constructs a book with the given title.
   * @param givenTitle
   */
  public Book(String givenTitle)
  {
	super(givenTitle, null, null);
//    title = givenTitle;
//    dueDate = null;
//    checkedOutTo = null;
    renewalCount = 0;
  }
  
  @Override
  public void checkOut(Patron p, Date now)
  {
    if (!isCheckedOut())
    {
      int checkOutDays = 21;
      
      // use a GregorianCalendar to figure out the Date corresponding to
      // midnight, 21 days from now
      GregorianCalendar cal = new GregorianCalendar();
      cal.setTime(now);
      cal.add(Calendar.DAY_OF_YEAR, checkOutDays);
      
      // always set to 11:59:59 pm on the day it's due
      cal.set(Calendar.HOUR_OF_DAY, 23);
      cal.set(Calendar.MINUTE, 59);
      cal.set(Calendar.SECOND, 59);     
      
      // convert back to Date object
      setDueDate(cal.getTime());
      
      setPatron(p);      
    }
  }

  @Override
  public void checkIn()
  {
    if (isCheckedOut())
    {
      setPatron(null);
      setDueDate(null);
      renewalCount = 0;
    }
  }

  @Override
  public void renew(Date now)
  {
    if (isCheckedOut() && !isOverdue(now) && renewalCount < 2)
    {
      checkOut(getPatron(), getDueDate());
      renewalCount += 1;
    }    
  }

  @Override
  public double getFine(Date now)
  {
    if (isCheckedOut() && isOverdue(now))
    {
      // how late is it, in milliseconds
      double elapsed = now.getTime() - getDueDate().getTime();
      
      // how late is it, in days
      int millisPerDay = 24 * 60 * 60 * 1000;
      int daysLate = (int) Math.ceil(elapsed / millisPerDay);
      
      // compute the fine, 25 cents per day
      return daysLate * .25;
    }
    else
    {
      return 0;
    }
  }

}

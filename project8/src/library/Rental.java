package library;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A rental is any item that is being rented from a public library
 * Rentals always have a title of the media, a due date, and a person they are being checked out to.
 * @param title
 * @param dueDate
 * @param checkedOutTo
 */
public abstract class Rental implements Item{

	private String title;
	private Date dueDate;
	private Patron checkedOutTo;

	public Rental(String givenTitle, Date givenDueDate, Patron renter) {
		title = givenTitle;
		dueDate = givenDueDate;
		checkedOutTo = renter;
	}
	
	public void checkOut(Patron p, Date now, int days)
	  {
	    if (!isCheckedOut())
	    {
	      int checkOutDays = days;
	      
	      // use a GregorianCalendar to figure out the Date corresponding to
	      // midnight, a given number of days from now
	      GregorianCalendar cal = new GregorianCalendar();
	      cal.setTime(now);
	      cal.add(Calendar.DAY_OF_YEAR, checkOutDays);
	      
	      // always set to 11:59:59 pm on the day it's due
	      cal.set(Calendar.HOUR_OF_DAY, 23);
	      cal.set(Calendar.MINUTE, 59);
	      cal.set(Calendar.SECOND, 59);     
	      
	      // convert back to Date object
	      dueDate = cal.getTime();
	      
	      checkedOutTo = p;      
	    }
	  }
	
//	public void CheckOutDays(int days) {
//		checkOut(p,now,days);
//	}

	public void checkIn()
	  {
	    if (isCheckedOut())
	    {
	      checkedOutTo = null;
	      dueDate = null; 
	    }
	  }

	public abstract void renew(Date now);
//	  {
//	    if (isCheckedOut() && !isOverdue(now) && renewalCount < 2)
//	    {
//	      checkOut(checkedOutTo, dueDate);
//	      renewalCount += 1;
//	    }    
//	  }

	public abstract double getFine(Date now);
//	  {
//	    if (isCheckedOut() && isOverdue(now))
//	    {
//	      // how late is it, in milliseconds
//	      double elapsed = now.getTime() - dueDate.getTime();
//	      
//	      // how late is it, in days
//	      int millisPerDay = 24 * 60 * 60 * 1000;
//	      int daysLate = (int) Math.ceil(elapsed / millisPerDay);
//	      
//	      // compute the fine, 25 cents per day
//	      return daysLate * .25;
//	    }
//	    else
//	    {
//	      return 0;
//	    }
//	  }

	public boolean isOverdue(Date now)
	  {
	    if (!isCheckedOut())
	    {
	      return false;
	    }
	    return now.after(dueDate);
	  }

	public int compareTo(Item other)
	  {
	    return title.compareTo(other.getTitle());
	  }

	public String getTitle()
	  {
	   return title;
	  }

	public boolean isCheckedOut()
	  {
	    return dueDate != null;
	  }

	public Date getDueDate()
	  {
	    return dueDate;
	  }

	public Patron getPatron()
	  {
	    return checkedOutTo;
	  }

	/*------SETTERS------*/
	protected void setDueDate(Date given) {
		dueDate = given;
	}
	
	protected void setPatron(Patron given) {
		checkedOutTo = given;
	}
}

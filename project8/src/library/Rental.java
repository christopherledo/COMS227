package library;

import java.util.Date;

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

	public abstract void checkOut(Patron p, Date now);

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

package lab8;

/**
 * Subclass of IntList that guarantees that the elements are always
 * in ascending order.
 */
public class IntListSorted extends IntList
{
  /**
   * Constructs an empty list.
   */
  public IntListSorted()
  {
    super();
  }
  
  /**
   * Adds a new item to this list, inserting it so that
   * the list remains sorted.
   */
  @Override
  public void add(int newItem)
  {
    if (size() == 0 || get(size() - 1) <= newItem)
    {
      super.add(newItem);
    }
    else
    {
      int i = size();
      while (i > 0 && newItem < get(i - 1))
      {
        i -= 1;
      }
      
      // now i is 0, or newItem >= list[i - 1], so put the new
      // element at position i
      super.insert(i, newItem);
    }
  }
  
  /**
   * Inserts a new item in this list, inserting it so that
   * the list remains sorted.  (The given index is ignored.)
   */
  @Override
  public void insert(int index, int newItem)
  {
    this.add(newItem);
  }
  
  /*-------------NEW CODE-------------*/
  /**
   * Find the maximum int in the list.
   *
   * @return
   * Maximum value in list.
   */
  public int getMaximum() {
	  return this.get(0);
  }
  
  /**
   * Find the minimum int in the list.
   *
   * @return
   * Minimum value in list.
   */
  public int getMinimum() {
     return this.get(this.size()-1);
  }

public int getMedian() {
	int size = this.size();
	int middleIndex = size / 2;
	int medNum = this.get(middleIndex);
	return medNum;
}

}

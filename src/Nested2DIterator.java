import java.util.*;

public class Nested2DIterator {
	public static class Flatten2D<T> {

	    public Flatten2D(ArrayList<ArrayList<T>> underlying)
	    {
	      this.underlying = underlying;
	      
	      if(underlying == null)
	      {
	        this.current_row_it = null;
	        this.current_column_it = null;
	      }
	      else
	      {
	        this.current_row_it = underlying.iterator();
	        if(this.current_row_it.hasNext())
	          this.current_column_it = this.current_row_it.next().iterator();
	        else
	          this.current_column_it = null;
	      }
	      
	    }
	    
	    public boolean hasNext()
	    {
	      if(underlying == null || current_row_it == null || current_column_it == null)
	        return false;
	      if(current_column_it.hasNext())
	        return true;
	      
	      if(!current_row_it.hasNext())
	        return false;
	      else
	      {
	        while(current_row_it.hasNext())
	        {
	          current_column_it = current_row_it.next().iterator();
	          if(current_column_it.hasNext())
	            return true;
	        }
	      }

	      return false;
	    }
	    
	    public T next()
	    {
	      if(hasNext())
	        return current_column_it.next();
	      else
	        return null;
	    }
	    
	    public void remove()
	    {
	      current_column_it.remove();
	    }
	    
	    private ArrayList<ArrayList<T>> underlying;
	    private Iterator<ArrayList<T>> current_row_it;
	    private Iterator<T> current_column_it;
	    
	  }
	  public static void main(String[] args) {

	    //Integer[][] test = {{1,2,3,4},{},{},{},{2},{3,4,5,6,7,8}};
	    ArrayList<Integer> t1 = new ArrayList<Integer>();
	    ArrayList<Integer> t2 = new ArrayList<Integer>();
	    ArrayList<Integer> t3 = new ArrayList<Integer>();
	    t3.add(1);
	    t3.add(2);
	    t3.add(3);
	    ArrayList<Integer> t4 = new ArrayList<Integer>();
	    ArrayList<Integer> t5 = new ArrayList<Integer>();
	    t5.add(5);
	    t5.add(6);
	    t5.add(7);
	    ArrayList<ArrayList<Integer>> test = new ArrayList<ArrayList<Integer>>(); 
	    
	     test.add(t1);
	     test.add(t2);
	     test.add(t3);
	     test.add(t4);
	     test.add(t5);
	     test.add(t2);

	    //test.add(t2);
	    
	    Flatten2D<Integer> result = new Flatten2D<Integer>(test);
	    
//	    System.out.println(result.next());
//	    System.out.println(result.next());
//	    System.out.println(result.next());
//	    System.out.println(result.next());
////	    System.out.println(result.next());
//	    result.remove();
//	    result.remove();
	    
	    System.out.println(result.next());
	    
//	    for(Integer i:t5)
//	      System.out.println(i);
	    //result.remove();
	    
	    while(result.hasNext())
	      System.out.println(result.next());
	    
	    //System.out.println(result.hasNext());
	    
	    //System.out.println(test[4][0]);
	  }

}

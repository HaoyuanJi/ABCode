import java.util.*;

public class NestedParser {
	private int value;
	private boolean isNumber;
	private List<NestedParser> intList;
	
	public NestedParser()
	{
		this.intList = new ArrayList<NestedParser>();
		this.isNumber = false;
	}
	
	public NestedParser(int v)
	{
		this.value = v;
		this.isNumber = true;
	}
	
	public void add(NestedParser l)
	{
		intList.add(l);
	}
	
	public static NestedParser fromString(String input)
	{
		if(!input.startsWith("["))
		{
			return new NestedParser(Integer.parseInt(input));
		}
		NestedParser result = null;
		Stack<NestedParser> stack = new Stack<NestedParser>();
		int i = 0, left = 1;
		
		while(i < input.length())
		{
			char c = input.charAt(i);
			if(c == '[')
			{
				NestedParser newone = new NestedParser();
				if(!stack.isEmpty())
				{
					stack.peek().add(newone);
				}
				stack.push(newone);
				left = i + 1;
			}
			else if(c == ',' || c == ']')
			{
				if(left != i)
				{
					int num = Integer.parseInt(input.substring(left,i));
					NestedParser newone = new NestedParser(num);
					stack.peek().add(newone);
				}
				left = i+1;
				if(c == ']') result = stack.pop();
			}
			i++;
		}
		return result;
	}
	
	public String toString()
	{
		if(isNumber)
		{
			return String.valueOf(value);
		}
		else
		{
			return intList.toString();
		}
	}
	
	public static void main(String[] args)
	{
		String test1 = "[123,456,[788,799,833],[[]],10,[]]";
		
		NestedParser np = fromString(test1);
		System.out.println(np.toString());
	}

}

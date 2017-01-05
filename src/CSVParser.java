import java.util.*;

public class CSVParser {
	
	public static ArrayList<String> parseCSV(String input)
	{
		ArrayList<String> result = new ArrayList<String>();
		
		boolean inQuote = false;
		
		StringBuilder buffer = new StringBuilder();
		
		for(int i = 0; i < input.length(); i++)
		{
			if(!inQuote)
			{
				if(input.charAt(i) == '"')
				{
					inQuote = true;
				}
				else if(input.charAt(i) == ',')
				{
					result.add(buffer.toString());
					buffer.setLength(0);
				}
				else
				{
					buffer.append(input.charAt(i));
				}
			}
			else
			{
				if(input.charAt(i) == '"')
				{
					if(i == input.length() - 1)
					{
						result.add(buffer.toString());
						return result;
					}
					else if(input.charAt(i + 1) == '"')
					{
						buffer.append('"');
						i++;
					}
					else
					{
						result.add(buffer.toString());
						buffer.setLength(0);
						inQuote = false;
						
						i++;
					}
				}
				else
				{
					buffer.append(input.charAt(i));
				}
			}
		}
		if(buffer.length() > 0)
		{
			result.add(buffer.toString());
		}
		
		return result;
	}
	
	public static String toString(ArrayList<String> inputstr)
	{
		StringBuffer sb = new StringBuffer();
		for(String str:inputstr)
		{
			sb.append(str);
			sb.append('|');
		}
		sb.deleteCharAt(sb.length() - 1);
		
		return sb.toString();
	}
	
	public static void main(String args[])
	{
		ArrayList<String> output1 = parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1");
		System.out.println(toString(output1));
		
		output1 = parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0");
		System.out.println(toString(output1));
	    output1 = parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1");
	    System.out.println(toString(output1));
	}

}

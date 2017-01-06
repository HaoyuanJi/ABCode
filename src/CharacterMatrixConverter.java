
import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class CharacterMatrixConverter {
  
  public static String[][] matrix = {{"B", "AC", "D", "A"},{"D", "BC", "A", ""},{"","","","B"},{"","","",""}};
  
  public static boolean isAccept(String accpt, String input)
  {
    String[][] DP = new String[input.length()][input.length()];
    
    for(int i = 0; i < input.length(); i++)
    {
      char c = input.charAt(i);
      DP[i][i] = matrix[c - 'A'][c - 'A'];
      
      if(i != input.length() - 1)
      {
        char c2 = input.charAt(i + 1);
        DP[i][i + 1] = matrix[c - 'A'][c2 - 'A'];
      }
    }
    

    for(int k = 2; k < input.length(); k++)
    {
        for(int i = 0; i < input.length() - k; i++)
        {
            int j = i + k;
            String left = DP[i][j - 1];
            String right = DP[i + 1][j];
          
            StringBuilder sb = new StringBuilder();
            for(int m = 0; m < left.length(); m++)
            {
              for(int n = 0; n < right.length(); n++)
              {
                char ll = left.charAt(m);
                char rr = right.charAt(n);
                
                String result = matrix[ll - 'A'][rr - 'A'];
                if(sb.indexOf(result) == -1)
                  sb.append(result);                
              }
            }
          
            System.out.println(sb.toString());
            DP[i][j] = sb.toString();
            
          
        }
    }
    
    for(int i = 0; i < matrix.length; i++)
    {
      for(int j = 0; j < matrix[0].length; j++)
        System.out.print(DP[i][j] + "\t");
    
      System.out.print("\n");
    }
    
    Set<Character> acceptSet = new HashSet<Character>();
    
    for(int i = 0; i < accpt.length(); i++)
    {
      acceptSet.add(input.charAt(i));
    }
    
    String res = DP[0][input.length() - 1];
    
    for(int i = 0; i < res.length(); i++)
    {
      if(acceptSet.contains(res.charAt(i)));
        return true;
    }
    return false;
  }
  
  
  public static void main(String[] args) {
    
    System.out.println(isAccept("CD","ABCD"));

  }
}


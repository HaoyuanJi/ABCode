import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 
 Palindrome
 
 1. Given a list of unique words, find the pairs of words that, when concatenated, will form a palindrome. For example:

["gab", "cat", "bag", "alpha"] => [["gab", "bag"], ["bag", "gab"]]

["gab", "cat", "bag", "alpha", "nurses", "race", "car", "run", "nu"] 
=> [["gab", "bag"], ["bag", "gab"], ["race", "car"], ["nurses", "run"], ["nu", "run"]

["aba", ""]

["aba", "aba", "ab", "ab"]
 */

class PalidromPair {
  
  public static ArrayList<ArrayList<String>> palindromePair(List<String> input){
    
    ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
    
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    
    for(int i = 0; i < input.size(); i++)
    {
      map.put(input.get(i),i);
    }
    
    for(int i = 0; i < input.size(); i++)
    {
      String word = input.get(i);
      
//       if(isPalindrome(word))
//       {
//         if(map.containsKey(""))
//         {
//           if(!word.isEmpty())
//           {
//             ArrayList<String> one = new ArrayList<String>();
            
//             one.add(word);
//             one.add("");
            
//             result.add(one);
            
//             one = new ArrayList<String>();
            
//             one.add("");
//             one.add(word);
            
//             result.add(one);
            
//           }
//         }
//       }
      
//       String reverse = new StringBuilder(word).reverse().toString();
      
//       if(map.containsKey(reverse))
//       {
//         if(map.get(reverse) != i)
//         {
//             ArrayList<String> one = new ArrayList<String>();
            
//             one.add(word);
//             one.add(reverse);
            
//             result.add(one);
          
//         }
//       }
      
      for(int j = 0; j < word.length() + 1; j++)
      {
        
        String left = word.substring(0,j);
        String right = word.substring(j);
        
        
        if(isPalindrome(right))
        {
          String left_reverse = new StringBuilder(left).reverse().toString();
          
          if(map.containsKey(left_reverse))
          {
            if(map.get(left_reverse) != i)
            {
              ArrayList<String> one = new ArrayList<String>();
            
              one.add(word);
              one.add(left_reverse);
            
              result.add(one);
            }

          }        
        }
        
        if(isPalindrome(left))
        {
          
          String right_reverse = new StringBuilder(right).reverse().toString();
          
          if(map.containsKey(right_reverse))
          {
            if(map.get(right_reverse) != i)
            {
              ArrayList<String> one = new ArrayList<String>();
            
              one.add(right_reverse);
              one.add(word);
            
              result.add(one);
            }
          }
        }
      }
            
    }
    return result;
  }
  
  public static boolean isPalindrome(String input){
    
    int i = 0;
    int j = input.length() - 1;
    
    while(i < j)
    {
      if(input.charAt(i) != input.charAt(j))
        return false;
      
      i++;
      j--;
    }
    
    return true;
    
  }
  
  public static void main(String[] args) {
    
//     String test1 = "abba";
//     String test2 = "abs";
    
//     String test3 = "";
    
//     System.out.println(isPalindrome(test1));
//     System.out.println(isPalindrome(test2));
//     System.out.println(isPalindrome(test3));
    
    String[] test1 = {"gab", "cat", "bag", "alpha"};
    // List<String> stooges = Arrays.asList("Larry", "Moe", "Curly");
    ArrayList<String> test1_list = new ArrayList<String>();
    
    for(String str:test1)
      test1_list.add(str);
    
    
    List<String> test2 = Arrays.asList("gab", "cat", "bag", "alpha", "nurses", "race", "car", "run", "nu", "aba", "");
    
    ArrayList<ArrayList<String>> result = palindromePair(test2);
    
    for(ArrayList<String> pair:result)
    {
      System.out.println(pair);
    }

  }
}


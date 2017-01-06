import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class TextJustification {
    public String alienOrder(String[] words) {
      if (words == null) return null;
      
      Map<Character, Set<Character>> graph_hm = new HashMap<>();  
      
      for (int i = 0; i < words.length; i++) {
        for (int j = 0; j < words[i].length(); j++) {
          char c = words[i].charAt(j);
          if (!graph_hm.containsKey(c)) {
            graph_hm.put(c,  new HashSet<Character>());
          }
          if(j < words[i].length() - 1)
          {
            graph_hm.get(c).add(words[i].charAt(j + 1));
          }
        }
        if (i > 0) {  // order every two words
          getOrder(words[i-1], words[i], graph_hm);
        }
      }
      return topSort(graph_hm);
      
    }
    
    public void getOrder(String s, String t, Map<Character, Set<Character>> graph_hm) {
          
            for(int i = 0; i < Math.min(s.length(), t.length()); i++) {
                char c1 = s.charAt(i), c2 = t.charAt(i);
                
                if (c1 != c2) {
                    if (!graph_hm.get(c1).contains(c2)) {
                      graph_hm.get(c1).add(c2);
                    }
                    break;    // stop here because after one char different, remaining chars doesn't matter
                }
            }
    }
 
    // standard top sort algorithm
    public String topSort(Map<Character, Set<Character>> graph_hm) {
      
        StringBuilder sb = new StringBuilder();
        
        // count initial indegree for every char vertex
        Map<Character, Integer> indegree = new HashMap<>();
        for(char c : graph_hm.keySet()) {
            for(char a : graph_hm.get(c)) {
                int count = indegree.containsKey(a) ? indegree.get(a) + 1 : 1;
                indegree.put(a, count);
            }
        }
        
        // find indegree==0, initialize the queue
        Queue<Character> queue = new LinkedList<>();
        for(char c : graph_hm.keySet()) {
            if(!indegree.containsKey(c)) {
                queue.offer(c);
            }
        }
        
        // topological sort
        while(!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for(char a : graph_hm.get(c)) {
                indegree.put(a, indegree.get(a) - 1);
                if(indegree.get(a) == 0) {
                    queue.offer(a);
                }
            }
        }
        
        for (int a : indegree.values()) {  // if there is any non sorted, this is not a DAG, return false
            if (a != 0) return "";
        }
        return sb.toString();
    }
    
    public static void main(String [] args) {
    	TextJustification outer = new TextJustification();
      
      String[] words = {"wet", "etra"};
      
      System.out.println(outer.alienOrder(words));
    }
}

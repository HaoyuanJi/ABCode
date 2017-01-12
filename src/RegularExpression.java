
public class RegularExpression {
	// solution4: DP
    public static boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        
        // bug 2: should use boolean instead of int.
        boolean[][] D = new boolean[s.length() + 1][p.length() + 1];
        
        // D[i][j]: i, j, the length of String s and String p.        
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (j == 0) {
                    // when p is empty, the s should be empty.
                    D[i][j] = i == 0;
                } else if (p.charAt(j - 1) == '*' || p.charAt(j - 1) == '+') {
                    /*
                        P has at least one node.
                    */
                    
                    // The last node in p is '*'
                    if (j < 2) {
                        // a error: there should be a character before *.
                        //return false;
                    }
                    
                    // we can match 0 characters or match more characters.
                    for (int k = 0; k <= i; k++) {
                        // BUG 3: severe! Forget to deal with the empty string!!
                        if (k != 0 && !isSame(s.charAt(i - k), p.charAt(j - 2))) {
                            D[i][j] = false;
                            break;
                        }
                        
                        
                        if (p.charAt(j - 1) == '*' && D[i - k][j - 2]) {
//                        	System.out.println(i);
//                        	System.out.println(j);
//                        	System.out.println(k);
//                        	System.out.println("Here!!");
                            D[i][j] = true;
                            break;
                        }
                        if (p.charAt(j - 1) == '+' && k !=0 && D[i - k][j - 2]) {
//                        	System.out.println(i);
//                        	System.out.println(j);
//                        	System.out.println(k);
//                        	System.out.println("Here!!");
                            D[i][j] = true;
                            break;
                        }
                    }
                } else {
                    D[i][j] = i >= 1 
                         && isSame(s.charAt(i - 1), p.charAt(j - 1))
                         && D[i - 1][j - 1];
                }
            }
        }
        
//        for(int i = 0; i < D.length; i++)
//        {
//        	for(int j = 0; j < D[0].length; j++)
//        		System.out.print(D[i][j]+" ");
//        	System.out.print("\n");
//        }
        
        return D[s.length()][p.length()];
    }
    
    public static boolean isSame(char s, char p)
    {
    	return p == '.' || p == s;
    }
    
    public static void main(String[] args)
    {
    	System.out.println(isMatch("aa","a"));
    	System.out.println(isMatch("aa","aa"));
    	System.out.println(isMatch("aaa","aa"));
    	System.out.println(isMatch("a", "a+"));
    	System.out.println(isMatch("a", "a*"));
    	System.out.println(isMatch("aa", ".*"));
    	System.out.println(isMatch("ab", ".*"));
    	System.out.println(isMatch("cccccaab", "c*a*b"));
    }

}

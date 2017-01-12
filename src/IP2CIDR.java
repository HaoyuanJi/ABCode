import java.util.*;

public class IP2CIDR {	
    public static final int[] CIDR2MASK = new int[] { 0x00000000, 0x80000000,             
            0xC0000000, 0xE0000000, 0xF0000000, 0xF8000000, 0xFC000000,             
            0xFE000000, 0xFF000000, 0xFF800000, 0xFFC00000, 0xFFE00000,             
            0xFFF00000, 0xFFF80000, 0xFFFC0000, 0xFFFE0000, 0xFFFF0000,             
            0xFFFF8000, 0xFFFFC000, 0xFFFFE000, 0xFFFFF000, 0xFFFFF800,             
            0xFFFFFC00, 0xFFFFFE00, 0xFFFFFF00, 0xFFFFFF80, 0xFFFFFFC0,             
            0xFFFFFFE0, 0xFFFFFFF0, 0xFFFFFFF8, 0xFFFFFFFC, 0xFFFFFFFE,             
            0xFFFFFFFF }; 
    
    private static long ipToLong(String strIP) {         
        long[] ip = new long[4];         
        String[] ipSec = strIP.split("\\.");         
        for (int k = 0; k < 4; k++) {             
            ip[k] = Long.valueOf(ipSec[k]);         
        }         

        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];     
    }
    
    private static String longToIP(long longIP) {         
        StringBuffer sb = new StringBuffer("");         
        sb.append(String.valueOf(longIP >>> 24));         
        sb.append(".");         
        sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));         
        sb.append(".");         
        sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));         
        sb.append(".");         
        sb.append(String.valueOf(longIP & 0x000000FF));   

        return sb.toString();     
    } 
    
    public static ArrayList<String> range2CIDRList(String startIP, String endIP)
    {
    	long start = ipToLong(startIP);         
        long end = ipToLong(endIP);           

        ArrayList<String> results = new ArrayList<String>();  
        
        while(start <= end)
        {
            byte maxsize = 32;             
            while ( maxsize > 0) {                 
                long mask = CIDR2MASK[ maxsize -1 ];                 
                long maskedBase = start & mask;                 

                if ( maskedBase != start ) {                     
                    break;                 
                }                 

                maxsize--;             
            }               
            double x = Math.log( end - start + 1) / Math.log( 2 );             
            byte maxdiff = (byte)( 32 - Math.floor( x ) ); 
            System.out.println("maxSiez = " + maxsize);
            System.out.println("maxDiff = " +maxdiff);
            
            int round = (int) (end - start + 1);
            System.out.println(Integer.numberOfLeadingZeros(round) + 1);
            if ( maxsize < maxdiff) {                 
                maxsize = maxdiff;             
            }             
            String ip = longToIP(start);             
            results.add( ip + "/" + maxsize);             
            start += Math.pow( 2, (32 - maxsize) );         
        }
        
        return results;
    }
	
	public static void main(String[] args)
	{
		String startIP = "0.0.0.4";
		String endIP = "0.0.0.11";
		//System.out.println(ipToLong(startIP));
		
		System.out.println(range2CIDRList(startIP, endIP));
	}

}

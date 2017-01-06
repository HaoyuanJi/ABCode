
import java.util.*;

public class Round {
	
	public static class PositionOffset implements Comparable
	{
		public int position;
		public double offset;
		
		public PositionOffset(int position, double offset)
		{
			this.position = position;
			this.offset = offset;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			PositionOffset po = (PositionOffset)o;
			return (this.offset - po.offset) > 0? 1:-1;
		}
	}
	public static double sum(double[] numbers)
	{
		double result = 0.0;
		for(double num:numbers)
			result += num;
		return result;
	}
	
	public static double[] smartRound(double[] numbers)
	{
		//double[] output = new double[numbers.length];
		
		double T = sum(numbers);
		
		double[] floorRound = new double[numbers.length];

		double[] offset = new double[numbers.length];
		
		PriorityQueue<PositionOffset> pq = new PriorityQueue<PositionOffset>();
		
		for(int i = 0; i < numbers.length; i++)
		{
			floorRound[i] = Math.floor(numbers[i]);
			offset[i] = Math.abs(Math.ceil(numbers[i]) - numbers[i]);
			//output[i] = numbers[i];
			
			pq.add(new PositionOffset(i,offset[i]));
		}
		
		double TFloor = sum(floorRound);
		
		double diff = T - TFloor;
		
		while(diff > 0)
		{
			PositionOffset po = pq.poll();
			//System.out.println(po.position);
			floorRound[po.position] = Math.ceil(numbers[po.position]);
			diff--;
			
		}
			
		return floorRound;
	}
	
	
	public static void main(String[] args)
	{
		double[] price = {1.2,2.3,3.4};
		
		double[] result = smartRound(price);
		
		for(double num:result)
			System.out.println(num);
		
		//System.out.println(sum(price));
		
		//System.out.println(Math.round(3.4));
	}

}

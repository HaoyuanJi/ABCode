
public class MaximumRoomDays {
	public static int getMaxRentalDays(int[] nums)
	{
		if(nums == null || nums.length == 0)
			return 0;
		
		int prev1 = 0;  //without current reservation
		int prev2 = 0;  //with current reservation
		
		//int max = Math.max(prev1, prev2);
		int max = 0;
		
		for(int i = 0; i < nums.length; i++)
		{
			max = Math.max(prev2 + nums[i], prev1);
			prev2 = prev1;
			prev1 = max;
		}
		return max;
	}
	
	public static void main(String[] args)
	{
		int[] nums = {4,10,3,1,5};
		System.out.println(getMaxRentalDays(nums));
	}

}

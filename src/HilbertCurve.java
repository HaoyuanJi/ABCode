
public class HilbertCurve {
	public static class Point
	{
		public int x = -1;
		public int y = -1;
		
		public Point()
		{}
		
		public Point(int x, int y)
		{
			this.x = x;
			this.y = y;
		}	
		public void swapXY()
		{
			int t = this.x;
			this.x = this.y;
			this.y = t;
		}
	}
	
	public static int xy2d(int iter, int x, int y)
	{		
		int d = 0;
		int order = iter + 1;

		Point copy = new Point(x, y);

		int s = order /2;
		
		 while(s > 0)
		 {
			 int rx = ((Math.abs(copy.x) & s) > 0)? 1 : 0;
			 
			 int ry = ((Math.abs(copy.y) & s) > 0)? 1 : 0;

			 d += s * s * ((3 * rx)^ry);
			 
			 copy = rot(s, copy, rx, ry);			 
			 
			 s = s/2;

		 }
		 return d + 1;
	}
	public static Point rot(int n, Point input, int rx, int ry)
	{
		Point output = new Point(input.x, input.y);
		if(ry == 0)
		{
			if(rx == 1)
			{
				output.x = n - 1 - input.x;
				output.y = n - 1 - input.y;
			}
			output.swapXY();
		}
		return output;
	}
	
	public static void main(String[] args)
	{
		System.out.println(xy2d(4, 2, 2));
	}


}

import java.util.*;

public class MeetingRooms{
	public static class TimePoint implements Comparable
	{
		int time;
		boolean isStart;
		
		public TimePoint(int time, boolean isStart)
		{
			this.time = time;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			TimePoint comp = (TimePoint)o;
			return this.time - comp.time;
		}
		
		public String toString()
		{
			return " "+this.time+" " + this.isStart + " ";
		}
	}
	
	
	public static int miniRooms(int[][] schedules)
	{
		PriorityQueue<TimePoint> pq = new PriorityQueue<TimePoint>();
		for(int i = 0; i < schedules.length; i++)
		{
				TimePoint start = new TimePoint(schedules[i][0], true);
				pq.add(start);
				TimePoint end = new TimePoint(schedules[i][1], false);
				pq.add(end);
		}
		
		//int meeting = 0;
		int currentMeeting = 0;
		int maxCurrentMeeting = 0;
		while(!pq.isEmpty())
		{
			TimePoint tp = pq.poll();
			if(tp.isStart)
			{
				currentMeeting++;
				if(currentMeeting > maxCurrentMeeting)
					maxCurrentMeeting = currentMeeting;
			}
			else
				currentMeeting--;
			
			//System.out.println(pq.poll());
		}
		//System.out.println(pq);
		return maxCurrentMeeting;
	}
	
	public static void main(String[] args)
	{
		int[][] schedules = {{0, 30},{5, 10},{15, 20}};
		
		System.out.println(miniRooms(schedules));
	}

}

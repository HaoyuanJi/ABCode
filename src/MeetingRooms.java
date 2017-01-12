import java.util.*;

public class MeetingRooms{
	public static class Interval
	{
		TimePoint start;
		TimePoint end;
		public Interval(TimePoint start, TimePoint end)
		{
			this.start = start;
			this.end = end;
		}
		public String toString()
		{
			return " ["+this.start.time+" " + this.end.time + "] ";
		}
	}
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
			if(this.time != comp.time)
				return this.time - comp.time;
			else
				return this.isStart?1:-1;
		}
		
		public String toString()
		{
			return " "+this.time+" " + this.isStart + " ";
		}
	}
	
	
	public static ArrayList<Interval> miniRooms(int[][] schedules)
	{
		ArrayList<Interval> result = new ArrayList<Interval>();
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
		TimePoint currentstart = null;
		while(!pq.isEmpty())
		{
			TimePoint tp = pq.poll();
			if(tp.isStart)
			{
				currentMeeting++;
				if(currentMeeting > maxCurrentMeeting)
					maxCurrentMeeting = currentMeeting;
				if(currentMeeting  == 1)
				{
					System.out.println(tp);
					System.out.println("hehehe");

					if(currentstart != null)
					{
						if(currentstart.time == tp.time)
							continue;

						result.add(new Interval(currentstart, new TimePoint(tp.time, false)));
						currentstart = null;
					}
						
				}
			}
			else
			{
				currentMeeting--;
				if(currentMeeting == 0)
				{
					System.out.println(tp);
					System.out.println("hahah");
					currentstart = new TimePoint(tp.time, true);
				}
			}
			
			//System.out.println(pq.poll());
		}
		//System.out.println(pq);
		return result;
	}
	
	public static void main(String[] args)
	{
		int[][] schedules = {{1, 4},{2, 7}, {13,15}, {1, 7}, {18,20}};
		
		System.out.println(miniRooms(schedules));
	}

}

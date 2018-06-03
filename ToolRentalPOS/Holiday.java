/*
	Custom class for indicating specific holidays using a similar format to cron

	Holiday format

	(allowed numbers/range, everyday/doesn't matter)
	Month (0-11, *)
	Day(1-31, *)
	DoW(1-7, #=(-)# *) //Sun-Sat, 6=-1 shift day by number
	DoM(1-5, *) //First DoW - Fifth DoW, Every DoW

	July 4th
	7 4 * *

	Labor Day
	9 * 2 1
*/
import java.util.*;

public class Holiday
{
	private String month;
	private String day;
	private String dow;
	private String dom;

	public Holiday(String holiday)
	{
		String[] parsed = holiday.trim().split(" ");

		month = parsed[0];
		day = parsed[1];
		dow = parsed[2];
		dom = parsed[3];
	}

	//Inclusive search
	public int holidaysInRange(Date start, Date end)
	{
		int count = 0;
		Calendar iterationDate = Calendar.getInstance();
		iterationDate.setTime(start);

		while(!start.after(iterationDate.getTime()) && !end.before(iterationDate.getTime()))
		{
			if(isHoliday(iterationDate))
			{
				count += 1;
			}

			iterationDate.add(Calendar.DATE, 1);
		}

		return count;
	}

	public boolean isHoliday(Calendar c)
	{
		if(containsValue(c, Calendar.MONTH, month))
		{
			if(containsValue(c, Calendar.DAY_OF_MONTH, day))
			{
				if(containsValue(c, Calendar.DAY_OF_WEEK, dow))
				{
					if(containsValue(c, Calendar.DAY_OF_WEEK_IN_MONTH, dom))
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	private boolean containsValue(Calendar c, int type, String range)
	{
		int value = c.get(type);

		//Simplest first
		if(range.contains("*"))
		{
			return true;
		}

		String[] splitRange = range.split(",");

		for(String r : splitRange)
		{
			if(r.contains("="))
			{

				continue;
			}
			//Handle indicated range
			else if(r.contains("-"))
			{
				String[] startAndEnd = r.split("-");
				int start = Integer.parseInt(startAndEnd[0]);
				int end = Integer.parseInt(startAndEnd[1]);

				if(start <= value && value <= end)
				{
					return true;
				}
			}
			//Handle just a single value;
			else
			{
				int rValue = Integer.parseInt(r);
				if(rValue == value)
				{
					return true;
				}
				//if day, check for day shift
				if(type == Calendar.DAY_OF_MONTH && dow.contains("="))
				{
					//find day shifts
					String[] dowSplit = dow.split(",");
					for(String dayShift : dowSplit)
					{
						//If its actually a day shift, process
						if(dayShift.contains("="))
						{
							String[] dayAndChange = dayShift.split("=");
							int dayNumber = Integer.parseInt(dayAndChange[0]);
							int change = Integer.parseInt(dayAndChange[1]);

							//Checks two things
							//1. Does the actual day of the month match
							//2. Is it the correct Day of the week to match
							if(value == rValue + change &&c.get(Calendar.DAY_OF_WEEK) == (dayNumber + change))
							{
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}
}
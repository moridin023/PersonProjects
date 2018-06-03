import java.util.*;

public class ToolManager
{
	private static ToolRetriver retriver = null;

	public static Tool getTool(String toolCode)
	{
		if(retriver == null)
		{
			retriver = new ToolRetriver();
		}

		return retriver.getTool(toolCode);
	}

	//Using this as a substitute for a database
	private static class ToolRetriver
	{
		private Map<String, Tool> toolMap = new HashMap<String, Tool>();

		public ToolRetriver()
		{
			//Initialize tool Map

			//Ladder
			toolMap.put("LADW", new Tool("LADW", "Werner", "Ladder", 1.99, new ArrayList<Holiday>()));

			//Chainsaw
			List<Holiday> chainsawNonChargeDays = new ArrayList<Holiday>();
			chainsawNonChargeDays.add(new Holiday("* * 1,7 *"));
			toolMap.put("CHNS", new Tool("CHNS", "Stihl", "Chainsaw", 1.49, chainsawNonChargeDays));

			//Jackhammer
			List<Holiday> jackhammerNonChargeDays = new ArrayList<Holiday>();
			jackhammerNonChargeDays.add(new Holiday("* * 1,7 *"));
			jackhammerNonChargeDays.add(new Holiday("6 4 2-6,1=1,7=-1 *"));
			jackhammerNonChargeDays.add(new Holiday("8 * 2 1"));
			toolMap.put("JAKR", new Tool("JAKR", "Ridgid", "Jackhammer", 2.99, jackhammerNonChargeDays));
			toolMap.put("JAKD", new Tool("JAKD", "DeWalt", "Jackhammer", 2.99, jackhammerNonChargeDays));
		}

		public Tool getTool(String toolCode)
		{
			return toolMap.get(toolCode);
		}
	}
}
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Matcher
{
	private static List<Node> matchLoops = new ArrayList();

	public static void main(String[] args)
	{
		List<String> names = pullInMatches(args[0]);
		generateMatchLoops(names);
		printMatchLoops();
	}

	private static List<String> pullInMatches(String filepath)
	{
		List<String> names = new ArrayList();

		names.add("Test0");
		names.add("Test1");
		names.add("Test2");
		names.add("Test3");
		names.add("Test4");
		names.add("Test5");
		names.add("Test6");
		names.add("Test7");
		names.add("Test8");
		names.add("Test9");

		return names;
	}

	private static void generateMatchLoops(List<String> names)
	{
		Random rand = new Random();

		//need to choose gifter/giftee
		while(!names.isEmpty())
		{
			boolean loopClosed = false;
			//Pick the first gifter in a loop
			Node originalGifter = new Node(names.get(rand.nextInt(names.size())));
			Node currentGifter = originalGifter;

			System.out.println("Original Gifter: " + originalGifter.name);

			while(!loopClosed)
			{
				int giftee;
				//Run check to make sure loop is not closing and leaving a solo
				if(names.size() == 2)
				{
					giftee = 0;
					if(names.get(giftee).equals(originalGifter.name))
					{
						giftee = 1;
					}
				}
				//Else pick the next giftee
				else
				{
					giftee = rand.nextInt(names.size());
					//If giftee is the current gifter, choose again
					while(names.get(giftee).equals(currentGifter.name))
					{
						giftee = rand.nextInt(names.size());
					}
				}

				System.out.println("Next giftee: " + names.get(giftee));

				//Set giftee node on the current node and remove from list of choices
				Node nextGifter = new Node(names.get(giftee));
				currentGifter.match = nextGifter;
				currentGifter = nextGifter;
				names.remove(giftee);

				//check for closed loop
				loopClosed = currentGifter.name.equals(originalGifter.name);
				System.out.println("Loop closed: " + loopClosed);
			}

			//Once a loop is generated, add it to the matchLoops
			matchLoops.add(originalGifter);
		}

	}

	private static void printMatchLoops()
	{
		for(int i = 0; i < matchLoops.size(); i++)
		{
			Node root = matchLoops.get(i);
			Node nextNode = root.match;

			System.out.println(root.name + " is gifting to " + nextNode.name);

			while(!nextNode.name.equals(root.name))
			{
				System.out.println(nextNode.name + " is gifting to " + nextNode.match.name);
				nextNode = nextNode.match;
			}
		}
	}
}
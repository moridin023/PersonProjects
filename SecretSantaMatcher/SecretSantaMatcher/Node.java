

public class Node
{
	public String name;
	public Node match;

	public Node(String name)
	{
		this.name = name;
	}
	
	public Node(String name, Node match)
	{
		this.name = name;
		this.match = match;
	}
}
// IMPORT LIBRARY PACKAGES NEEDED BY YOUR PROGRAM
// SOME CLASSES WITHIN A PACKAGE MAY BE RESTRICTED
// DEFINE ANY CLASS AND METHOD NEEDED
import java.util.List;
import java.util.*;
// CLASS BEGINS, THIS CLASS IS REQUIRED
public class Solution
{
	public Solution()
	{
		
	}
	
	public static void main(String[] args)
	{
		Solution sol = new Solution();
		
		List<Character> list = new ArrayList();
		list.add('z');
		list.add('z');
		list.add('c');
		list.add('b');
		list.add('z');
		list.add('c');
		list.add('h');
		list.add('f');
		list.add('i');
		list.add('h');
		list.add('i');
		
		for(int i : sol.lengthEachScene(list))
		{
			System.out.println(i);
		}
	}
	
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<Integer> lengthEachScene(List<Character> inputList)
    {
        boolean finished = false;
        int currentHead = 0;
        ArrayList<Integer> sceneSizes = new ArrayList();
        
        while(!finished)
        {
            List<Character> currentScene = new ArrayList();
            char startingChar = inputList.get(currentHead);
            
            //get original starting scene
            currentScene = inputList.subList(currentHead, inputList.lastIndexOf(startingChar)+1);
            System.out.println(currentScene);
            
            //see if scene needs to continue farther
            int tail = getLastInstanceOf(currentScene, inputList);
            
            //if yes, get new current scene and retry
            while((tail-currentHead) > (currentScene.size()-1))
            {
                currentScene = inputList.subList(currentHead, tail+1);
                System.out.println(currentScene);
                tail = getLastInstanceOf(currentScene, inputList);
            }
            
            //add scene length to list
            sceneSizes.add(currentScene.size());
            
            //update currentHead
            currentHead = tail + 1;
            
            //check for exit
            if(tail >= inputList.size()-1)
            {
                finished = true;
            }
        }
        
        return sceneSizes;
    }
    // METHOD SIGNATURE ENDS
    
    private int getLastInstanceOf(List<Character> targetList, List<Character> searchList)
    {
        int farthestIndex = -1;
        for(int i = 0; i < targetList.size(); i++)
        {
            int currentLast = searchList.lastIndexOf(targetList.get(i));
            if(currentLast > farthestIndex)
            {
                farthestIndex = currentLast;
            }
        }
        
        return farthestIndex;
    }
}
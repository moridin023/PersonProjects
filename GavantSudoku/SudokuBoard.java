import java.util.*;


public class SudokuBoard
{
	private Map<String, Character> board = new HashMap<String, Character>();
	private Map<String, Set<Character>> boardNotes = new HashMap<String, Set<Character>>();

	//Create empty board
	public SudokuBoard()
	{
		//For an empty board, initialize notes only
		initBoardNotes();
	}

	//Takes 9x9 integer array
	public SudokuBoard(char[][] initialBoardState)
	{
		//start by initializing the notes
		initBoardNotes();

		//Go through initial board state to set starting numbers
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				if(initialBoardState[x][y] != 'x')
				{
					setStaticNumber(x, y, initialBoardState[x][y]);
				}
			}
		}
	}

	public char getTileValue(int x, int y)
	{
		if(board.containsKey(getKeyString(x, y)))
		{
			return board.get(getKeyString(x, y));
		}

		// Return 0 for no value set
		return 'x';
	}

	public Set<Character> getTileNotes(int x, int y)
	{
		if(boardNotes.containsKey(getKeyString(x, y)))
		{
			return boardNotes.get(getKeyString(x, y));
		}

		// Return NULL for no notes set
		return null;
	}

	private void initBoardNotes()
	{
		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				boardNotes.put(getKeyString(x, y), new HashSet<Character>(Arrays.asList('1','2','3','4','5','6','7','8','9')));
			}
		}
	}

	// Set a number on the board
	public void setStaticNumber(int x, int y, char value)
	{
		String key = getKeyString(x, y);

		// Add the value to the board
		board.put(key, value);
		// Remove from notes board
		boardNotes.remove(key);

		//Adjust note values
		removeColumnNoteValue(x, value);
		removeRowNoteValue(y, value);
		removeSquareNoteValue(x, y, value);
	}

	public boolean allSquaresSet()
	{
		return boardNotes.isEmpty();
	}

	// Return the board as a character array
	public char[][] getBoard()
	{
		char[][] boardArray = new char[9][9];

		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				String key = getKeyString(x, y);
				if(board.containsKey(key))
				{
					boardArray[x][y] = board.get(key);
				}
				else
				{
					boardArray[x][y] = 'x';
				}
			}
		}

		return boardArray;
	}

	// get a string formatted version of the board state
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		char[][] boardArray = getBoard();

		for(int x = 0; x < 9; x++)
		{
			for(int y = 0; y < 9; y++)
			{
				sb.append(boardArray[x][y]);
			}

			sb.append("\r\n");
		}

		return sb.toString();
	}


	// Modify the given column to not have the possibility for the given value
	private void removeColumnNoteValue(int column, char value)
	{
		for(int y = 0; y < 9; y++)
		{
			String key = getKeyString(column, y);
			if(boardNotes.containsKey(key))
			{
				boardNotes.get(key).remove(value);
			}
		}
	}

	// Modify the given row to not have the possibility for the given value
	private void removeRowNoteValue(int row, char value)
	{
		for(int x = 0; x < 9; x++)
		{
			String key = getKeyString(x, row);
			if(boardNotes.containsKey(key))
			{
				boardNotes.get(key).remove(value);
			}
		}
	}

	// Modify the given square group to not have the possibility for the given value
	private void removeSquareNoteValue(int column, int row, char value)
	{
		int xAdjust = (column/3)*3;
		int yAdjust = (row/3)*3;

		for(int x = xAdjust; x < xAdjust+3; x++)
		{
			for(int y = yAdjust; y < yAdjust+3; y++)
			{
				String key = getKeyString(x, y);
				if(boardNotes.containsKey(key))
				{
					boardNotes.get(key).remove(value);
				}
			}
		}
	}

	private String getKeyString(int x, int y)
	{
		return x + "," + y;
	}
}
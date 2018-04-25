import java.io.*;
import java.util.*;

public class SudokuSolver
{
	SudokuBoard board = null;

	public SudokuSolver(File inputFile) throws Exception
	{
		char[][] initState = parseFile(inputFile);
		board = new SudokuBoard(initState);
	}

	public static void main(String[] args)
	{
		//validate input to be file
		if(args != null && args.length == 1)
		{
			File inputFile = new File(args[0]);
			if(inputFile.exists())
			{
				try
				{
					SudokuSolver solver = new SudokuSolver(inputFile);
					boolean solved = solver.solve();

					if(solved)
					{
						solver.printSolution(args[0].substring(0, args[0].lastIndexOf(".")) + ".sln.txt");
					}
					else
					{
						System.out.println("Was unable to find a solution for the initial state provided.");
					}
				}
				catch(Exception ex)
				{
					System.err.println("There was an exception while processing the file: \n" + ex.getMessage());
					System.exit(1);
				}
			}
			else
			{
				System.err.println("The file entered does not exist!");
				System.exit(1);
			}
		}
		else
		{
			System.err.println("Usage: java SudokuSolver <input file>");
			System.exit(1);
		}
	}

	public void printSolution()
	{
		if(board != null)
		{
			System.out.println(board.toString());
		}
	}
	
	public void printSolution(String filePath)
	{
		if(board != null)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

				bw.write(board.toString());
				bw.close();
			}
			catch(IOException ex)
			{
				System.err.println("There was an error writing out the board to the file " + filePath);
				ex.printStackTrace();
			}
		}
	}

	private char[][] parseFile(File inputFile) throws Exception
	{
		char[][] initState = new char[9][9];

		BufferedReader br = new BufferedReader(new FileReader(inputFile));

		for(int i = 0; i < 9; i++)
		{
			String line = br.readLine();
			if(line == null)
			{
				throw new Exception("Not enough lines in the file to properly initialize the board state. Expects 9 lines");
			}
			else if(!line.matches("(\\d|x|X){9}"))
			{
				throw new Exception("Line " + i + " is not formatted properly to initialize the board state. Expects 9 digits(1-9) or X's in each row");
			}
			
			line = line.toLowerCase();
			for(int j = 0; j < 9; j++)
			{
				initState[i][j] = line.charAt(j);
			}
		}

		br.close();

		return initState;
	}

	// Call to solve and sets off recursive solution logic
	private boolean solve()
	{
		this.board = solve(this.board, 0);

		return board.allSquaresSet();
	}

	// Recursive solve function
	private SudokuBoard solve(SudokuBoard currentBoard, int depth)
	{
		boolean updated;

		do
		{
			//reset updated
			updated = false;

			// Try to find values
			for(int x = 0; x < 9; x++)
			{
				for(int y = 0; y < 9; y++)
				{
					if(currentBoard.getTileValue(x, y) == 'x')
					{
						Set<Character> notes = currentBoard.getTileNotes(x, y);

						// If there is only one option, set it as the value.
						if(notes.size() == 1)
						{
							currentBoard.setStaticNumber(x, y, notes.toArray(new Character[0])[0]);
							updated = true;
							break;
						}

						// If there is more than one option, check to see if it has a unique value
						else if(notes.size() > 1)
						{
							char uniqueVal = checkForUniqueColumnValue(x, y, currentBoard);
							if(uniqueVal != 'x')
							{
								currentBoard.setStaticNumber(x, y, uniqueVal);
								updated = true;
								break;
							}
							else
							{
								uniqueVal = checkForUniqueRowValue(x, y, currentBoard);
								if(uniqueVal != 'x')
								{
									currentBoard.setStaticNumber(x, y, uniqueVal);
									updated = true;
									break;
								}
								else
								{
									uniqueVal = checkForUniqueSquareValue(x, y, currentBoard);
									if(uniqueVal != 'x')
									{
										currentBoard.setStaticNumber(x, y, uniqueVal);
										updated = true;
										break;
									}
								}
							}
						}
					}
				}

				// If a number was found, can move on
				if(updated)
				{
					break;
				}
			}

			// Couldn't update. Try picking number.
			if(!updated && depth < 5)
			{
				for(int x = 0; x < 9; x++)
				{
					for(int y = 0; y < 9; y++)
					{
						if(currentBoard.getTileValue(x, y) == 'x')
						{
							for(char choice : currentBoard.getTileNotes(x, y))
							{
								char[][] updatedBoard = currentBoard.getBoard();
								updatedBoard[x][y] = choice;
								SudokuBoard newBoard = solve(new SudokuBoard(updatedBoard), depth+1);

								if(newBoard.allSquaresSet())
								{
									return newBoard;
								}
							}
						}
					}
				}
			}

			//Exit once all the scquares are set or there are no more updates
		} while(!currentBoard.allSquaresSet() && updated);
		
		return currentBoard;
	}

	// Checks given tile for a unique value in the column
	private char checkForUniqueColumnValue(int column, int row, SudokuBoard currentBoard)
	{
		Set<Character> mainList = new HashSet<Character>(currentBoard.getTileNotes(column, row));

		for(int y = 0; y < 9; y++)
		{
			// Don't want to compare some square to itself or already set squares
			if(y != row && currentBoard.getTileValue(column, y) == 'x')
			{
				for(char note : currentBoard.getTileNotes(column, y))
				{
					mainList.remove(note);
				}
			}

			// Break early if no unique values
			if(mainList.size() == 0)
			{
				return 'x';
			}
		}

		// if there is only one unique value, use that
		if(mainList.size() == 1)
		{
			return mainList.toArray(new Character[0])[0];
		}

		return 'x';
	}

	// Checks given tile for a unique value in the row
	private char checkForUniqueRowValue(int column, int row, SudokuBoard currentBoard)
	{
		Set<Character> mainList = new HashSet<Character>(currentBoard.getTileNotes(column, row));

		for(int x = 0; x < 9; x++)
		{
			// Don't want to compare some square to itself or already set squares
			if(x != column && currentBoard.getTileValue(x, row) == 'x')
			{
				for(char note : currentBoard.getTileNotes(x, row))
				{
					mainList.remove(note);
				}
			}

			// Break early if no unique values
			if(mainList.size() == 0)
			{
				return 'x';
			}
		}

		// if there is only one unique value, use that
		if(mainList.size() == 1)
		{
			return mainList.toArray(new Character[0])[0];
		}

		return 'x';
	}

	// Checks given tile for a unique value in the square
	private char checkForUniqueSquareValue(int column, int row, SudokuBoard currentBoard)
	{
		Set<Character> mainList = new HashSet<Character>(currentBoard.getTileNotes(column, row));
		int xAdjust = (column/3)*3;
		int yAdjust = (row/3)*3;

		for(int x = xAdjust; x < xAdjust+3; x++)
		{
			for(int y = yAdjust; y < yAdjust+3; y++)
			{
				// Don't want to compare some square to itself or already set squares
				if((x != column || y != row) && currentBoard.getTileValue(x, y) == 'x')
				{
					for(char note : currentBoard.getTileNotes(x, y))
					{
						mainList.remove(note);
					}
				}

				// Break early if no unique values
				if(mainList.size() == 0)
				{
					return 'x';
				}
			}
		}

		// if there is only one unique value, use that
		if(mainList.size() == 1)
		{
			return mainList.toArray(new Character[0])[0];
		}

		return 'x';
	}
}
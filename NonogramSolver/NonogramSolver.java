import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NonogramSolver
{
	private boolean[][] grid;
	private int numberOfColumns;
	private int numberOfRows;
	private List<int[]> columns;
	private List<int[]> rows;

	public NonogramSolver(int numberOfColumns, int numberOfRows, List<int[]> columns, List<int[]> rows)
	{
		this.numberOfColumns = numberOfColumns;
		this.numberOfRows = numberOfRows;
		this.columns = columns;
		this.rows = rows;
		this.grid = new boolean[numberOfColumns][numberOfRows];

		// Initialize grid to be false
		for(int x = 0; x < numberOfColumns; x++)
		{
			for(int y = 0; y < numberOfRows; y++)
			{
				this.grid[x][y] = false;
			}
		}

		processInitialPositions();
	}

	private void processInitialPositions()
	{
		// Process columns
		for(int x = 0; x < this.numberOfColumns; x++)
		{
			boolean[] filled = getFilledLocations(this.numberOfRows, this.columns.get(x));

			for(int j = 0; j < this.numberOfRows; j++)
			{
				this.grid[x][j] = (this.grid[x][j] || filled[j]);
			}
		}

		// Process rows
		for(int y = 0; y < this.numberOfRows; y++)
		{
			boolean[] filled = getFilledLocations(this.numberOfColumns, this.rows.get(y));

			for(int j = 0; j < this.numberOfColumns; j++)
			{
				this.grid[j][y] = (this.grid[j][y] || filled[j]);
			}
		}
	}

	public void printOutState()
	{
		for(int y = 0; y < numberOfRows; y++)
		{
			for(int x = 0; x < numberOfColumns; x++)
			{
				if(grid[x][y])
				{
					System.out.print("[X]");
				}
				else
				{
					System.out.print("[ ]");
				}
			}

			System.out.print("\n");
		}
	}

	private int sum(int[] terms)
	{
		int total = 0;

		for(int term : terms)
		{
			total += term;
		}

		return total;
	}

	private boolean[] getFilledLocations(int rowlength, int[] row)
	{
		boolean[] filledLocations = new boolean[rowlength];
		int sectionUnfilled = rowlength - (sum(row) + (row.length - 1));
		int currentEndOfRow = 0;

		for(int term : row)
		{
			// update the current end of the row
			currentEndOfRow += term;

			int overlap = term - sectionUnfilled;

			// Print every position
			if(overlap > 0)
			{
				for(int i = overlap; i > 0; i--)
				{
					int postion = currentEndOfRow - (i -1);
					filledLocations[postion - 1] = true;
				}
			}

			// Add one for the space to the currentEndOfRow in case there is another term
			currentEndOfRow += 1;
		}

		return filledLocations;
	}

	public static void main(String[] args)
	{
		// Args should be a file formatted for processing
		List<String> input = new ArrayList();

		try(Stream<String> stream = Files.lines(Paths.get(args[0])))
		{
			input = stream.filter(line -> !line.startsWith("#")).collect(Collectors.toList());
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}

		NonogramSolver solver = processInput(input);

		solver.printOutState();
	}

	private static NonogramSolver processInput(List<String> input)
	{
		// First line should be a number indicating number of columns
		int numCol = Integer.parseInt(input.get(0));
		// Second line should be a number indicating number of rows
		int numRow = Integer.parseInt(input.get(1));

		List<int[]> colValues = new ArrayList();
		List<int[]> rowValues = new ArrayList();

		// The next numCol rows are CSV for the columns
		for(int i = 0; i < numCol; i++)
		{
			String[] rawRow = input.get(i + 2).split(",");
			int[] row = new int[rawRow.length];
			for(int j = 0; j < rawRow.length; j++)
			{
				row[j] = Integer.parseInt(rawRow[j]);
			}

			colValues.add(row);
		}

		// The next numRow rows are CSV for the rows
		for(int i = 0; i < numRow; i++)
		{
			String[] rawRow = input.get(i + numCol + 2).split(",");
			int[] row = new int[rawRow.length];
			for(int j = 0; j < rawRow.length; j++)
			{
				row[j] = Integer.parseInt(rawRow[j]);
			}

			rowValues.add(row);
		}

		return new NonogramSolver(numCol, numRow, colValues, rowValues);
	}
}
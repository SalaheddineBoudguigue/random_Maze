package map;
import java.util.*;

public class Labyrinthe {
	public static final int WALL = 1;
	public static final int PATH = 4;

    public static int[][] generateMaze(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Set all cells to walls initially
        for (int i = 1; i < rows-1; i++) {
            for (int j = 0; j < cols-1; j++) {
                matrix[i][j] = WALL;
            }
        }

        // Generate the maze
        generateMazeHelper(matrix, 0, 0);

        // Set the start and end points
        matrix[0][0] = PATH;
        matrix[rows - 1][cols - 1] = PATH;

        return matrix;
    }

    private static void generateMazeHelper(int[][] matrix, int row, int col) {
        matrix[row][col] = PATH;

        List<int[]> neighbors = getNeighbors(matrix, row, col);
        Collections.shuffle(neighbors);

        for (int[] neighbor : neighbors) {
            int newRow = neighbor[0];
            int newCol = neighbor[1];

            if (isValidCell(matrix, newRow, newCol)) {
                int wallRow = row + (newRow - row) / 2;
                int wallCol = col + (newCol - col) / 2;
                matrix[wallRow][wallCol] = PATH;
                generateMazeHelper(matrix, newRow, newCol);
            }
        }
    }

    private static List<int[]> getNeighbors(int[][] matrix, int row, int col) {
        int[][] directions = {{-2, 0}, {2, 0}, {0, -2}, {0, 2}};
        List<int[]> neighbors = new ArrayList<>();

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isValidCell(matrix, newRow, newCol)) {
                neighbors.add(new int[]{newRow, newCol});
            }
        }

        return neighbors;
    }

    private static boolean isValidCell(int[][] matrix, int row, int col) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        return row >= 0 && row < rows && col >= 0 && col < cols && matrix[row][col] == WALL;
    }    
}

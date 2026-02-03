package com.ga.sudoku.utils;

import com.ga.sudoku.models.SudokuCell;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SudokuSolver {

    /**
     * Solve a 9x9 sudoku puzzle.
     * @param board SudokuCell[][] Puzzle to solve.
     * @param row int
     * @param column int
     * @return SudokuCell[][] Solved puzzle board.
     */
    public SudokuCell[][] solveSudokuPuzzle(SudokuCell[][] board, int row, int column) {
        if (puzzleIsSolved(board, row, column)) {
            return board;
        } else {
            throw new IllegalArgumentException("Puzzle is not solvable");
        }
    }

    /**
     * Recursively go through the puzzle and solve it cell by cell until all cells are solved.
     * @param board SudokuCell[][] 9x9 Puzzle board to solve.
     * @param row int
     * @param column int
     * @return boolean true if solved, false if not.
     */
    private boolean puzzleIsSolved(SudokuCell[][] board, int row, int column) {
        // base case: Reached nth column of the last row
        System.out.println("CURRENT ROW: " + row + ", COLUMN: " + column);
        if (row == 8 && column == 9) return true;

        if (column == 9) { // If last column of the row go to the next row
            row++;
            column = 0;
        }

        // If cell is already occupied then move forward
        if (board[row][column].getCell() != 0) {
            System.out.println("ALREADY SOLVED CELL: "  + board[row][column].getCell());
            return puzzleIsSolved(board, row, column + 1);
        }

        for (int cell = 1; cell <= 9; cell++) { // valid numbers 1-9
            if (numberIsSafe(board, row, column, cell)) { // Add cell if it's safe at current position
                System.out.println("SOLVING CELL: " + cell);
                board[row][column].setCell(cell);

                if (puzzleIsSolved(board, row, column + 1)) return true; // solved path

                // Backtrack of recursion failed
                board[row][column].setCell(0);
            }
        }

        return false; // No safe number was found. Repeat iteration.
    }

    /**
     * Check if a provided number is safe to add to the puzzle or not. For solving.
     * @param board SudokuCell[][] The current puzzle board
     * @param row int The number's row
     * @param column int The number's column
     * @param cell int The number to check
     * @return boolean True if safe, false if not.
     */
    private boolean numberIsSafe(SudokuCell[][] board, int row, int column, int cell) {
        for (int i = 0; i < 9; i++) { // Number mustn't exist in row
            if (board[row][i].getCell() == cell) return false;
        }

        for (int i = 0; i < 9; i++) { // Number mustn't exist in column
            if (board[i][column].getCell() == cell) return false;
        }

        // Number mustn't exist in 3x3 sub-grid
        final int startRow = row - (row % 3);
        final int startColumn = column - (column % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startColumn].getCell() == cell) {
                    return false;
                }
            }
        }

        return true; // passed all checks
    }
}
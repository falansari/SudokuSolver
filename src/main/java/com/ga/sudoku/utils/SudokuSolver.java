package com.ga.sudoku.utils;

import com.ga.sudoku.models.SudokuCell;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SudokuSolver {

    public SudokuCell[][] solveSudokuPuzzle(SudokuCell[][] board, int row, int column) {
        // base case: Reached nth column of the last row
        if (row == 8 && column == 8) return board;

        if (column == 8) { // If last column of the row go to the next row
            row++;
            column = 0;
        }

        // If cell is already occupied then move forward
        if (board[row][column].getCell() != 0) {
            return solveSudokuPuzzle(board, row, column + 1);
        }

        for (int cell = 1; cell <= 9; cell++) { // valid numbers 1-9
            if (numberIsSafe(board, row, column, cell)) { // Add cell if it's safe at current position
                board[row][column].setCell(cell);
                return solveSudokuPuzzle(board, row, column + 1); // Move on to checking next cell
            } else {
                board[row][column].setCell(0); // Not safe so remains unsolved
            }
        }

        return board;
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
        System.out.println("CELL: " + cell);
        for (int i = 0; i < 9; i++) { // Number mustn't exist in row
            if (board[row][i].getCell() == cell) {
                System.out.println("BOARD CELL: " + board[row][column].getCell());
                return false;
            }
        }

        for (int i = 0; i < 9; i++) { // Number mustn't exist in column
            if (board[i][column].getCell() == cell) return false;
        }

        // Number mustn't exist in 3x3 sub-grid
        int startRow = row - (row % 3);
        int startColumn = column - (column % 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startColumn].getCell() == cell) {
                    System.out.println("BOARD CELL: " + board[i][j].getCell());
                    return false;
                }
            }
        }

        return true; // passed all checks
    }
}
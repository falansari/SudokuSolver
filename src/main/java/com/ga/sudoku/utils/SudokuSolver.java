package com.ga.sudoku.utils;

import com.ga.sudoku.models.SudokuCell;

public class SudokuSolver {

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


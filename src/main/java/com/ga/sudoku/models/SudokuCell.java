package com.ga.sudoku.models;

import lombok.Getter;
import lombok.Setter;

/**
 * Sudoku puzzles' model representing a single cell.
 */
@Getter
public class SudokuCell {
    private final int row;
    private final int column;
    @Setter
    private int cell;

    /**
     * Initialize a single sudoku puzzle's cell.
     * @param row int Row number [0-8]
     * @param column int Column number [0-8]
     * @param cell int Cell's value [0-9]
     */
    public SudokuCell(int row,  int column, int cell) {
        this.cell = cell;
        this.column = column;
        this.row = row;
    }

    @Override
    public String toString() {
        return String.valueOf(cell);
    }
}

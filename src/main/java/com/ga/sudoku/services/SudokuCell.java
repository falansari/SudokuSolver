package com.ga.sudoku.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
public class SudokuCell {
    private final int row;
    private final int column;
    @Setter
    private int cell;

    @Autowired
    SudokuCell(int row,  int column, int cell) {
        this.cell = cell;
        this.column = column;
        this.row = row;
    }
}

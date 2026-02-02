package com.ga.sudoku.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.FileNotFoundException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SudokuFileNotFoundException extends FileNotFoundException {
    public SudokuFileNotFoundException(String message) {
        super(message);
    }
}

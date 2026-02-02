package com.ga.sudoku.controllers;

import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import com.ga.sudoku.models.SudokuCell;
import com.ga.sudoku.services.Sudoku;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sudoku")
public class SudokuController {
    Sudoku sudoku;

    public SudokuController(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @PostMapping(value = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SudokuCell[][] loadPuzzle(@RequestParam("textFile") MultipartFile puzzle) throws SudokuFileNotFoundException {
        return sudoku.loadPuzzle(puzzle);
    }

    @PostMapping(value = "/print")
    public String printPuzzle(@RequestBody SudokuCell[][] board) {
        return sudoku.printPuzzle(board);
    }
}

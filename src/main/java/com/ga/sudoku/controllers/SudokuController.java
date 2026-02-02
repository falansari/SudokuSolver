package com.ga.sudoku.controllers;

import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import com.ga.sudoku.models.SudokuCell;
import com.ga.sudoku.services.SudokuService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("sudoku")
public class SudokuController {
    SudokuService sudokuService;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    @PostMapping(value = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SudokuCell[][] loadPuzzle(@RequestParam("textFile") MultipartFile puzzle) throws SudokuFileNotFoundException {
        return sudokuService.loadPuzzle(puzzle);
    }
}

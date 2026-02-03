package com.ga.sudoku.controllers;

import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import com.ga.sudoku.models.SudokuCell;
import com.ga.sudoku.services.Sudoku;
import com.ga.sudoku.utils.SudokuSolver;
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

    @PostMapping(value = "/pretty")
    public String prettifyPuzzle(@RequestBody SudokuCell[][] board) {
        return sudoku.prettifyPuzzle(board);
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean savePuzzle(@RequestPart("board") SudokuCell[][] board, @RequestPart("filepath") String filepath) {
        return sudoku.savePuzzle(board, filepath);
    }

    @PostMapping(value = "/print", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String printPuzzle(@RequestParam("textFile") MultipartFile puzzle) throws SudokuFileNotFoundException {
        return sudoku.printPuzzle(puzzle);
    }

    @GetMapping(value = "/solve")
    public SudokuCell[][] solvePuzzle(@RequestBody SudokuCell[][] board) {
        return sudoku.solvePuzzle(board);
    }
}

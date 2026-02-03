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

    /**
     * Take in a puzzle board from a text file and load it as a Sudoku board.
     * @param puzzle MultipartFile puzzle .txt file
     * @return SudokuCell[][] 9x9 Sudoku board
     * @throws SudokuFileNotFoundException Handle invalid file contents / file not found errors
     */
    @PostMapping(value = "/load", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SudokuCell[][] loadPuzzle(@RequestParam("textFile") MultipartFile puzzle) throws SudokuFileNotFoundException {
        return sudoku.loadPuzzle(puzzle);
    }

    /**
     * Take in a puzzle board and returns a prettified version of it for on screen display or file saving.
     * @param board SudokuCell[][] The puzzle board
     * @return String the prettified puzzle
     */
    @PostMapping(value = "/pretty")
    public String prettifyPuzzle(@RequestBody SudokuCell[][] board) {
        return sudoku.prettifyPuzzle(board);
    }

    /**
     * Save a Sudoku board to a .txt file in the specified file path.
     * @param board SudokuCell[][] Puzzle board
     * @param filepath String format: docs/puzzles/puzzle#.txt for unsolved puzzles, or docs/solved/puzzle#.solution.txt for solved puzzles.
     * @return boolean True if successfully saved, false if not.
     */
    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public boolean savePuzzle(@RequestPart("board") SudokuCell[][] board, @RequestPart("filepath") String filepath) {
        return sudoku.savePuzzle(board, filepath);
    }

    /**
     * Takes in a Sudoku 9x9 puzzle board in a text file and prints it out as a formatted string.
     * @param puzzle MultipartFile .txt
     * @return String Formatted puzzle
     * @throws SudokuFileNotFoundException Handle invalid file exceptions
     */
    @PostMapping(value = "/print", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String printPuzzle(@RequestParam("textFile") MultipartFile puzzle) throws SudokuFileNotFoundException {
        return sudoku.printPuzzle(puzzle);
    }

    /**
     * Accepts a Sudoku 9x9 puzzle board and returns the completed solution.
     * @param board SudokuCell[][] Unsolved/in-progress board
     * @return SudokuCell[][] Solved board
     */
    @PostMapping(value = "/solve")
    public SudokuCell[][] solvePuzzle(@RequestBody SudokuCell[][] board) {
        return sudoku.solvePuzzle(board);
    }
}

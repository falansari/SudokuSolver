package com.ga.sudoku.services;

import com.ga.sudoku.exceptions.InvalidCharacterException;
import com.ga.sudoku.exceptions.SudokuFileNotFoundException;
import com.ga.sudoku.models.SudokuCell;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Read, write and print out sudoku puzzles.
 */
@Getter @Setter
@Service
public class Sudoku {
    private SudokuCell[][] board;

    @Autowired
    public Sudoku(SudokuCell[][] board) {
        this.board = board;
    }

    /**
     * Load a sudoku puzzle from a text file.
     * @param textFile MultipartFile Must follow correct format as supplied in docs/puzzles.
     * @return SudokuCell[][] The puzzle's initialized grid.
     * @throws SudokuFileNotFoundException Catch file not found or wrong formatted file errors
     * @throws InvalidCharacterException Catch invalid characters errors
     */
    public SudokuCell[][] loadPuzzle(MultipartFile textFile) throws SudokuFileNotFoundException, InvalidCharacterException {
        SudokuCell[][] board = new SudokuCell[9][9];
        System.out.println("Loading puzzle from " + textFile.getOriginalFilename());

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(textFile.getInputStream()));
            String line;
            int row = 0;

            while ((line = bufferedReader.readLine()) != null && row < 9) { // Iterate through the file line by line
                line = line.trim();

                if (line.startsWith("-")) continue; // Skip separator ----- lines

                String[] rawValues = line.split("\\s+"); // Remove spaces in the rows

                // Filter out sub-grid separators | and the true values only
                List<String> values = new ArrayList<>();
                for (String value : rawValues) {
                    if (!value.equals("|")) values.add(value);
                }

                if (values.size() != 9) throw new InvalidCharacterException("Row " + row + " does not contain 9 values");

                for (int column = 0; column < 9; column++) { // Add valid cell to the board
                    int cell = getCell(values, column, row);

                    board[row][column] = new SudokuCell(row, column, cell);
                }

                row++;
            }

            if (row != 9) throw new InvalidCharacterException("""
                    Puzzle must have exactly 9 rows. It should have the following format:
                    0  0  0  |  1  5  0  |  0  7  0
                    1  0  6  |  0  0  0  |  8  2  0
                    3  0  0  |  8  6  0  |  0  4  0
                    --------------------------------
                    9  0  0  |  4  0  0  |  5  6  7
                    0  0  4  |  7  0  8  |  3  0  0
                    7  3  2  |  0  0  6  |  0  0  4
                    --------------------------------
                    0  4  0  |  0  8  1  |  0  0  9
                    0  1  7  |  0  0  0  |  2  0  8
                    0  5  0  |  0  3  7  |  0  0  0""");

            return board;

        } catch (IOException e) {
            throw new SudokuFileNotFoundException("Error reading puzzle file: " + textFile.getOriginalFilename());
        }
    }

    /**
     * Create a pretty version of a puzzle board for saving and display.
     * @param board SudokuCell[][] puzzle board to be printed.
     * @return String
     */
    public String prettifyPuzzle(SudokuCell[][] board) {
        StringBuilder sb = new StringBuilder();
        String separatorLine = "+-------+-------+-------+";

        sb.append(separatorLine).append("\n");

        for (int row = 0; row < 9; row++) {
            if (row != 0) sb.append("\n");

            if (row == 3 || row == 6) sb.append(separatorLine).append("\n");

            for (int column = 0; column < 9; column++) {
                if (column == 3 || column == 6) sb.append(" |");

                if (column != 0) sb.append(" ");

                if (column == 0) sb.append("| ");

                sb.append(board[row][column].toString());

                if (column == 8) sb.append(" |");
            }

            if (row == 8) sb.append("\n").append(separatorLine);
        }

        return sb.toString();
    }

    /**
     * Save a sudoku board to solved puzzles folder.
     * @param board SudokuCell[][] The Sudoku board to save
     * @param filepath String docs/solved/puzzle#.solution.txt for default solved puzzles folder in project.
     * @return boolean True if successfully saved.
     */
    public boolean savePuzzle(SudokuCell[][] board, String filepath) {
        String formattedPuzzle = prettifyPuzzle(board);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
            writer.write(formattedPuzzle);
            writer.close();

            return true;

        } catch (Exception e) {
            throw new InvalidCharacterException("Error writing file: " + filepath);
        }
    }

    /**
     * Get cell's value. Catches invalid values. 0 represents an unsolved cell.
     * @param values String[] values line from a puzzle text file.
     * @param column int Cell's column number [0-8]
     * @param row int Cell's row number [0-8]
     * @return int Cell's value [0-9]
     */
    private static int getCell(List<String> values, int column, int row) {
        int cell;

        try {
            cell = Integer.parseInt(values.get(column));

        } catch (NumberFormatException e) {
            throw new InvalidCharacterException("Invalid character at row " + row + ", col " + column);
        }

        if (cell < 0 || cell > 9)
            throw new InvalidCharacterException("Number out of range at row " + row + ", col " + column);

        return cell;
    }
}

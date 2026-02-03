# SudokuSolver
A small API that takes in 9x9 Sudoku puzzle boards and uses a backtracking algorithm to solve them.
Created as a solo algorithms project for General Assembly's JAVA bootcamp.

### API Endpoints

| ENDPOINT         | HTTP METHOD | INPUT                                                              | OUTPUT           | DESCRIPTION                                                                 |
|------------------|-------------|--------------------------------------------------------------------|------------------|-----------------------------------------------------------------------------|
| `/sudoku/load`   | POST        | `MultipartFile` (`textFile`) – `.txt` puzzle file                  | `SudokuCell[][]` | Loads a puzzle board from a text file and returns it as a 9x9 Sudoku board. |
| `/sudoku/pretty` | POST        | `SudokuCell[][]` (JSON body)                                       | `String`         | Returns a prettified version of the puzzle board for display or saving.     |
| `/sudoku/save`   | POST        | `SudokuCell[][]` (`board`), `String` (`filepath`) – multipart form | `boolean`        | Saves a Sudoku board to a `.txt` file at the specified path.                |
| `/sudoku/print`  | POST        | `MultipartFile` (`textFile`) – `.txt` puzzle file                  | `String`         | Prints a formatted string representation of the puzzle board.               |
| `/sudoku/solve`  | POST        | `SudokuCell[][]` (JSON body)                                       | `SudokuCell[][]` | Accepts a Sudoku board and returns the completed solution.                  |

### Default File Paths
- **Unsolved/In-progress puzzles:** docs/puzzles/puzzle#.txt ← you can add more puzzles in this folder.
- **Solved puzzles:** docs/solved/puzzle#.solution.txt

### Puzzle Board Accepted Format
For the puzzle board to be read correctly, it needs to:
- Have separator lines that start with +.
- Have a total of 13 lines in text file.
- The puzzle needs to be 9x9 grid only.
- Example:
```
+-------+-------+-------+
| 0 0 0 | 1 5 0 | 0 7 0 |
| 1 0 6 | 0 0 0 | 8 2 0 |
| 3 0 0 | 8 6 0 | 0 4 0 |
+-------+-------+-------+
| 9 0 0 | 4 0 0 | 5 6 7 |
| 0 0 4 | 7 0 8 | 3 0 0 |
| 7 3 2 | 0 0 6 | 0 0 4 |
+-------+-------+-------+
| 0 4 0 | 0 8 1 | 0 0 9 |
| 0 1 7 | 0 0 0 | 2 0 8 |
| 0 5 0 | 0 3 7 | 0 0 0 |
+-------+-------+-------+
```

### Technologies Used
- [JAVA 17 Spring Boot v4.0.2](https://start.spring.io/) application with dependencies:
  - Starter WebMVC
  - DevTools
- [Lombok](https://projectlombok.org/) for boilerplate reduction.
- [Maven builder](https://maven.apache.org/).
- Algorithm help from [geeksforgeeks.org](https://www.geeksforgeeks.org/dsa/sudoku-backtracking-7/)
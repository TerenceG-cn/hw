package com.tce.algebra.shudu.tdd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 作者：JOHANNES BRODWALL
 * http://johannesbrodwall.com/2010/04/06/why-tdd-makes-a-lot-of-sense-for-sudoko/
 */
public class SudokuSolver {
    private static final int SIZE = 9;
    private SudokuBoard board = new SudokuBoard();
    private int solve_count;

    /**
     * 构造函数
     *
     * @param puzzle 未解数独字符串，例如......52..8.4......3...9...5.1...6..2..7........3.....6...1..........7.4.......3.
     */
    public SudokuSolver(String puzzle,int cnt) {
        board.readBoard(puzzle);
        this.solve_count=cnt;
    }

    public SudokuSolver(SudokuBoard board) {
        this.board = board;
    }

    /**
     * 数独求唯一解
     *
     * @param board 9x9数板
     * @param index 0  1  2  3  4  5  6  7  8
     *              9 10 11 12 13 14 15 16 17
     *              18 19 20 21 22 23 24 25 26
     *              .. .. .. .. .. .. .. .. ..
     *              72 73 74 75 76 77 78 79 80
     * @return 有解true
     */
    private boolean findSolution(SudokuBoard board, int index) {
        int row = index / SIZE, column = index % SIZE;

        if (index == SIZE * SIZE) return true;
        if (board.isFilled(row, column)) return findSolution(board, index + 1);

        for (Integer value : board.getOptionsForCell(row, column)) {
            board.setCellValue(row, column, value);
            if (findSolution(board, index + 1)) return true;
        }
        board.clearCell(row, column);
        return false;
    }

    /**
     * 数独求全部解
     *
     * @param board
     * @param index
     */
    private void findAllSolution(SudokuBoard board, int index) {
        int row = index / SIZE, column = index % SIZE;
        if (index == SIZE * SIZE) {
            System.out.println(board.dumpBoard());
            return;
        }
        if (board.isFilled(row, column)) {
            findAllSolution(board, index + 1);
            return;
        }

        for (Integer value : board.getOptionsForCell(row, column)) {
            board.setCellValue(row, column, value);
            findAllSolution(board, index + 1);
            board.clearCell(row, column);
        }
    }

    public boolean solve() {
        return findSolution(board, 0);
    }

    public void solveAll() {
        findAllSolution(board, 0);
    }

    public String dumpBoard() {
        return board.dumpBoard();
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
//        String line;
//        while ((line=reader.readLine()) != null) {
//            SudokuSolver solver = new SudokuSolver(line,0);
//            System.out.println("Solving: \n" + solver.dumpBoard());
//            solver.solve();
//            System.out.println("Solved: \n" + solver.dumpBoard());
//        }
        String nullBoard = "123456789........................................................................";
        SudokuSolver solver = new SudokuSolver(nullBoard,0);
        System.out.println("Solving: \n" + solver.dumpBoard());
        solver.solveAll();
        System.out.println(solver.solve_count);
    }
}

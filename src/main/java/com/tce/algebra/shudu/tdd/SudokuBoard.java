package com.tce.algebra.shudu.tdd;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *作者：JOHANNES BRODWALL
 * http://johannesbrodwall.com/2010/04/06/why-tdd-makes-a-lot-of-sense-for-sudoko/
 */
public class SudokuBoard {
    private Integer[][] board = new Integer[SIZE][SIZE];//9x9数板
    private static final int SIZE = 9;

    public boolean isFilled(int row, int column) {//row:0~8,column:0~8
        return board[row][column] != null;
    }

    public List<Integer> getOptionsForCell(int row, int column) {//获得该cell剩余可选数字
        List<Integer> options = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        removeOptionsInRow(options, row);
        removeOptionsInColumn(options, column);
        removeOptionsInBox(options, row, column);
        return options;
    }

    private void removeOptionsInBox(List<Integer> options, int row, int column) {
        int boxRow = row - row%3, boxColumn = column - column%3;
        for (int rowOffset=0; rowOffset<3; rowOffset++) {
            for (int columnOffset=0; columnOffset<3; columnOffset++) {
                options.remove(board[boxRow+rowOffset][boxColumn+columnOffset]);
            }
        }
    }

    private void removeOptionsInColumn(List<Integer> options, int column) {
        for (int row=0; row<SIZE; row++) options.remove(board[row][column]);
    }


    private void removeOptionsInRow(List<Integer> options, int row) {
        for (int column=0; column<SIZE; column++) options.remove(board[row][column]);
    }

    public void setCellValue(int row, int column, int value) {
        board[row][column] = value;
    }

    public void clearCell(int row, int column) {
        board[row][column] = null;
    }

    public int getCellValue(int row, int column) {
        return board[row][column];
    }

    /**
     * 把数板转换成String
     */
    public String dumpBoard() {
        StringBuilder result = new StringBuilder();
        for (Integer[] row : board) {
            for (Integer values : row) {
                result.append(values != null ? values.toString() : '.');
                result.append(' ');result.append(' ');
            }
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * 数板读入未解数独
     */
    public void readBoard(String boardAsString) {
        int index = 0;
        for (Integer[] row : board) {
            for (int column = 0; column < row.length; column++) {
                row[column] = readValue(boardAsString.charAt(index++));
            }
        }
    }

    private Integer readValue(char c) {
        return c != '.' ? Integer.valueOf(c + "") : null;
    }
}
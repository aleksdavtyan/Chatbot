package com.aca.chatbot.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Board {
    private int size;
    String[][] board;
    private String turn = "X";

    Board(int size) {
        this.size = size;
        board = new String[this.size][this.size];
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                board[i][j] = String.valueOf(i) + String.valueOf(j);
            }
        }
    }

    void playerMove(Board brd) {
        int firstIndex, secondIndex;
        String position;
        String winner = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("X's will play first. Enter a cell number to place X in:");
        while (winner == null) {
            position = scanner.nextLine();
            try {
                firstIndex = Integer.parseInt("" + position.charAt(0));
                secondIndex = Integer.parseInt("" + position.charAt(1));
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid input number. Please re-enter a number.");
                continue;
            }
            try {
                if (!((firstIndex >= 0 && firstIndex < (brd.size)) && (secondIndex >= 0 && secondIndex < (brd.size)))) {
                    System.out.println("Invalid input number. Please re-enter a number from board cells:");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input number. Please re-enter a number from board cells:");
                continue;
            }
            if (brd.board[firstIndex][secondIndex].equals(position)) {
                brd.board[firstIndex][secondIndex] = turn;
                if (turn.equals("X")) {
                    turn = "O";
                } else {
                    turn = "X";
                }
                printBoard(brd);
                winner = checkWinner(brd);
            } else {
                System.out.println("Cell already taken. Re-enter another cell number:");
                continue;
            }
        }
        if (winner.equals("full")) {
            System.out.println("Board is full! Game over and nobody has won! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + " has won! Thanks for playing.");
        }
    }

    String checkWinner(Board brd) {
        String sizeTimeX = new String(new char[size]).replace("\0", "X");
        String sizeTimeO = new String(new char[size]).replace("\0", "O");
        String row, column;
        if (checkFullness(brd)) {
            return "full";
        }
        for (int i = 0; i < brd.size; i++) {
            row = "";
            for (int j = 0; j < brd.size; j++) {
                row += brd.board[i][j];
            }
            if (row.equals(sizeTimeX)) {
                return "X";
            } else if (row.equals(sizeTimeO)) {
                return "O";
            }
        }

        for (int j = 0; j < brd.size; j++) {
            column = "";
            for (int i = 0; i < brd.size; i++) {
                column += brd.board[i][j];
            }
            if (column.equals(sizeTimeX)) {
                return "X";
            } else if (column.equals(sizeTimeO)) {
                return "O";
            }
        }
        System.out.println(turn + "'s turn. Please enter a cell number to place " + turn + " in: ");
        return null;
    }


//    String checkRow(Board brd, int size) {
//
//
//        return null;
//    }

    boolean checkFullness(Board brd) {
        for (int i = 0; i < brd.size; i++) {
            for (int j = 0; j < brd.size; j++) {
                if (brd.board[i][j].equals(String.valueOf(i) + String.valueOf(j)))
                    return false;
            }
        }
        return true;
    }

    public void printBoard(Board brd) {
        System.out.print(new String(new char[6 * size]).replace("\0", "-"));
        for (int i = 0; i < size; i++) {
            System.out.println(" ");
            for (int j = 0; j < size; j++) {
                System.out.print("| " + brd.board[i][j] + " |");
            }
        }
        System.out.println(" ");
        System.out.println(new String(new char[6 * size]).replace("\0", "-"));
    }
}


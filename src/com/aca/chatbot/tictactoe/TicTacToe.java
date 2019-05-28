package com.aca.chatbot.tictactoe;

import com.aca.chatbot.ui.CommandLineUserInterface;

import java.util.Scanner;

public class TicTacToe {

    CommandLineUserInterface commandLineUserInterface = new CommandLineUserInterface();
    private static TicTacToe ticTacToeInstance = new TicTacToe();

    private TicTacToe() {}

    public static TicTacToe getTicTacToeInstance() {
        return ticTacToeInstance; }

        public void startTicTacToe() {
            Scanner scanner = new Scanner(System.in);
            int size;
            System.out.println("Welcome to X, O two player Terminal Game.");
            System.out.println("Please enter the Board size:");
            size = scanner.nextInt();
            Board brd = new Board(size);
            brd.printBoard(brd);
            brd.playerMove(brd);
        }
}

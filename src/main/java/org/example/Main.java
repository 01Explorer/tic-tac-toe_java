package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Starting the game");
        Engine game = new Engine().getInstance();
        Scanner scanner = new Scanner(System.in);
        game.drawGrid();
        while (!game.isFinished()){
            System.out.print("Enter the row number: ");
            int row = scanner.nextInt();
            System.out.print("Enter the column number: ");
            int column = scanner.nextInt();
            game.play(row, column);
            game.drawGrid();

        }
    }
}
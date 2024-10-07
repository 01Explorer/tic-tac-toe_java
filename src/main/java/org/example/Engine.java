package org.example;

import java.util.ArrayList;
import java.util.List;

public class Engine {
    private Engine instance;
    private final List<List<String>> grid;
    private Player currentPlayer;
    private boolean isFinished = false;

    enum Player{
        X,
        O;

        @Override
        public String toString() {
            return switch(this){
                case X:
                    yield "XXX";
                case O:
                    yield "OOO";
                default:
                    yield "";
            };
        }
    }

    public Engine(){
        instance = this;
        grid = new ArrayList<>();
        currentPlayer = Player.X;
    }

    public Engine getInstance(){
        if (instance == null){
            instance = new Engine();
        }
        return instance;
    }

    public void destroy(){
        instance = null;
    }

    public void drawGrid(){
        if (grid.isEmpty()){
            generateGrid();
        }

        for (List<String> row : grid){
            System.out.println("-------------");
            System.out.print("|");
            for (String cell : row){
                System.out.printf(" %s |", cell);
            }
            System.out.println();
        }
        System.out.println("-------------");

        if (!isFinished){
            printAvailableSpaces();
        }
        if (isFinished){
            System.out.printf("Game finished!! Congrats %s you won!", currentPlayer == Player.X ? "X" : "O");
        }
    }

    private void generateGrid(){
        ArrayList<String> zeroState = new ArrayList<>();
        for (int index = 0; index < 3; index++){
            zeroState.add(" ");
        }
        for (int index = 0; index < 3; index++){
            grid.add(new ArrayList<>(zeroState));
        }

    }

    private void printAvailableSpaces(){
        for (int index = 0; index < 3; index++){
            for (int cell = 0; cell < 3; cell++){
                if (grid.get(index).get(cell).trim().isEmpty()){
                    System.out.printf("(%d, %d)", index, cell);
                }
            }
        }
    }

    private boolean checkSpaceAvailable(int row, int col){
        return grid.get(row).get(col).trim().isEmpty();
    }

    public void play(int row, int col){
        if (!checkSpaceAvailable(row, col)){
            System.out.println("Invalid position, select one that is not already filled");
            return;
        }
        grid.get(row).set(col, currentPlayer == Player.X ? "X" : "O");
        checkGameFinished(row, col);
        currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
    }

    private void checkGameFinished(int row, int col) {
        if (grid.get(row).stream().reduce("", (result, element) -> result + element).equals(currentPlayer.toString())){
            isFinished = true;
        }
        StringBuilder auxResult = new StringBuilder();

        for (int index = 0; index < 3; index++){
            auxResult.append(grid.get(index).get(col).trim());
        }

        if (auxResult.toString().equals(currentPlayer.toString())){
            isFinished = true;
        }

        String horizontalResult = grid.get(0).get(0).trim() + grid.get(1).get(1).trim() + grid.get(2).get(2).trim();
        String secondHorizontalResult = grid.get(2).get(0).trim() + grid.get(1).get(1).trim() + grid.get(0).get(2).trim();

        if (horizontalResult.equals(currentPlayer.toString()) || secondHorizontalResult.equals(currentPlayer.toString())){
            isFinished = true;
        }
    }

    private boolean gameFinished(){
        return isFinished;
    }

    public boolean isFinished(){
        return isFinished;
    }
}

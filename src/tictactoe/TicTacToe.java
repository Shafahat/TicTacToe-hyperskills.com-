/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Scanner;

/**
 *
 * @author Shafahat
 */
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        char[][] board = new char[3][3];  
        createBoard(board);
        displayBoard(board);
        
        int turn = 1;  
        String gameState; 
        while(true){
            System.out.print("Enter the coordinates: ");
            String userInput = scan.nextLine();
            char cx = userInput.charAt(0);
            char cy = userInput.charAt(2); 
            if(checkUserInput(board, cx, cy, turn)){
                turn++;
                gameState = gameState(board);
                if(gameState.equals("Game not finished")){             
                    displayBoard(board);       
                } else{
                    displayBoard(board);
                    System.out.println(gameState);
                    break;
                }
            }  
        }

    }

    public static void createBoard(char[][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                board[i][j] = ' ';
            }            
        }
    }
    
    public static void displayBoard(char[][] board){
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                  if(j == 0){
                      System.out.print("| ");
                  }
                  System.out.print(board[i][j] + " ");
                  if(j == 2){
                      System.out.print("|");
                  }
            }            
            System.out.println();
        }
        System.out.println("---------");
    }
    
    public static boolean checkUserInput(char[][] board, char cx, char cy, int turn){
        boolean b = false;
        if(Character.isDigit(cy) && Character.isDigit(cx)){
            int x = Character.getNumericValue(cx), y = Character.getNumericValue(cy);
            if(y < 1  || y > 3  || x < 1 || x > 3 ){
                System.out.println("Coordinates should be from 1 to 3!");
            } else {
                if(board[3 - y][x - 1] == ' '){
                    b = true;
                    if(turn % 2 != 0){
                        board[3 - y][x - 1] = 'X';
                    } else{
                        board[3 - y][x - 1] = 'O';
                    }                   
                } else{
                    System.out.println("This cell is occupied! Choose another one!");
                }
            }
        } else {
            System.out.println("You should enter numbers!");
        }
        return b;
    }

    public static String gameState(char[][] board){
        String gameState = "";
        boolean win = false;
        char cell;
        int flag  = -1;
        int sumRow = 0, sumCol = 0, sumMainDiagonal = 0, sumSecondDiagonal = 0, countEmpty = 0;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                cell = board[i][j];                
                if(cell == ' '){
                    countEmpty++;
                }
                sumRow += checkCell(cell);
                sumCol += checkCell(board[j][i]);
                
                if(i == j){
                    sumMainDiagonal += checkCell(cell);
                }
                if(i + j == board.length - 1){
                    sumSecondDiagonal += checkCell(cell);
                }
            }    
            
            if (sumRow == 3 || sumCol == 3){
                flag = 1;
                break;
            }
            if (sumRow == -3 || sumCol == -3){
                flag = 0;
                break;
            }
            sumRow = 0;
            sumCol = 0;
        }
        
        if (sumMainDiagonal == 3 || sumSecondDiagonal == 3 || flag == 1){
            gameState = "X wins";
            win = true;
        }   
        if (sumMainDiagonal == -3 || sumSecondDiagonal == -3 || flag == 0){
            gameState = "O wins";
            win = true;
        }     
        
        if (countEmpty == 0 && win == false){
            gameState = "Draw";
        } 
        
        if (countEmpty != 0 && win == false){
            gameState = "Game not finished";
        }      
 
        
        return gameState;
    }
    
    public static int checkCell(char ch){
        int sum = 0;
        switch(ch){
            case 'X':
                sum++;
                break;
            case 'O':
                sum--;
                break;
            default:
                break;
        }
        return sum;
    }


}

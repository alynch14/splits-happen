/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingclient;

/**
 *
 * @author user
 */
public class BowlingGame {
    //Declaration of private data
    private int[] score;//Frame score
    private int total;//Total game score after the 10th frame
    private int size;//Number of rolls the player made overall
    private static final int MAX = 21;//Max amount of rolls the player could have made.  Used to initialize array.
    
    //General Constructor
    public BowlingGame(){
        score = new int[MAX];
        total = 0;
        size = 0;
    }
    
    public void initializeScore(){
        total = 0;
        size = 0;
    }
    
    //Method to initialize each frame's score. Converts characters of the string into int values.
    public void setScore(String gameScore){
        size = gameScore.length();
        for(int i = 0; i<size; i++){
            if(gameScore.charAt(i) == 'X' && i<size-2)
                score[i] = 10+ strike(gameScore,(i+1),1);
            
            else if(i == size-2 && gameScore.charAt(i-1) == 'X'){
                i=i+2;
                size = size-2;
            }
            
            else if(gameScore.charAt(i) == '/' && i!=size-1){
                score[i] = 10 + ((gameScore.charAt(i+1)!='-' && gameScore.charAt(i+1)!='X')?(int)(gameScore.charAt(i+1) - '0'):(gameScore.charAt(i+1)=='X')?10:0);
                score[i-1] = 0;
            }
            
            else if(gameScore.charAt(i) =='/' && i == size-1){
                size = size-2;
            }
            
            else{
                score[i] = (gameScore.charAt(i) =='-')?0:(int)(gameScore.charAt(i) - '0');
            }
        }
        score[size-1] = (gameScore.charAt(size-2) == '/')?0:score[size-1];
    }
    
    //Method for initialization of the total score from the game
    public void setTotal(){
        for(int i = 0; i<size; i++){
            total += score[i];
        }
    }
    
    //getter for the total score
    public int getTotal(){
        return total;
    }
    
    /*recursive method used to calculate strikes. Can only ever make 1 recursive call.
    * The strikeCounter variable is used to count how many strikes occurred in three frames
    * begun with the original strike.  If the frame after the original strike has one,
    * then the method checks to see if the next character in gameScore is also an 'X'.
    * if so, then the strikeCounter is advanced to 3 so that the compiler can check to find
    * that all it needs to do is return 10 to the previous call.
    */
    private int strike(String gameScore,int i, int strikeCounter){
        if(strikeCounter == 3){//1st base case
            return 10;
        }
        else if(strikeCounter == 2){//2nd base case
            return (gameScore.charAt(i) =='-')?0:(int)(gameScore.charAt(i) - '0');
        }
        else if(gameScore.charAt(i) =='X'){
            return 10 + ((gameScore.charAt(i + 1) == 'X')?strike(gameScore,(i+1),(strikeCounter+2)):strike(gameScore,(i+1),(strikeCounter+1)));//recursive call
        }
        else if(gameScore.charAt(i + 1) == '/'){
            return 10;
        }
        else{
            return ((gameScore.charAt(i) =='-')?0:(int)(gameScore.charAt(i) - '0')) + ((gameScore.charAt(i+1) =='-')?0:(int)(gameScore.charAt(i+1) - '0'));
        }
    }
    
    //toString method for easy printing in the client
    public String toString(){
        return String.format("Total = %d", total);
    }
}

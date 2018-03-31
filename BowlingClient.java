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
import java.util.Scanner;

public class BowlingClient {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        BowlingGame game = new BowlingGame();
        char anotherGame = 'y';
        
        while(anotherGame == 'y' || anotherGame == 'Y'){
            game.initializeScore();
            System.out.println("Please enter your bowling score: ");
            game.setScore(input.nextLine());
        
            System.out.println("Your score will now be calculated...");
            game.setTotal();
            System.out.println("Your Game " + game);
            System.out.println("Would you like to calculate another score? (Y/N)");
            anotherGame = input.nextLine().charAt(0);
        }
        
        System.out.println("Thank you!");
    }
}

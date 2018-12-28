//Alice Zhou
//February 23rd, 2018
//Monty Hall: this program gives the user options to choose from three doors with one car in only one of the doors.
//Revision: None

package com.company;

import java.awt.*;                      //import swing, imageio etc. for graphics and import Scanner for inputs.
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Scanner;

public class MontyHall extends JFrame {
    private JPanel panel;                        //initialize Jpanel and BufferedImage image1, image2 and image 3
    private BufferedImage image1 = null;
    private BufferedImage image2 = null;
    private BufferedImage image3 = null;

    public static void main(String[] args) {   //here's the main program:

        MontyHall frame = new MontyHall();      //create a frame
        frame.setSize(750, 500);  //set the size of the frame
        frame.setTitle("Monty Hall Game");      //set the title
        frame.createGUI();                      //call the createGUI() method to operate the rest of the program
    }

    public void createGUI() {         //here's the method for GUI display

        setDefaultCloseOperation(EXIT_ON_CLOSE);          //set the close operation
        panel = new panel1();                             //let panel be a new class from the class panel1(which actually has all the GUIs)
        panel.setSize(new Dimension(700, 400)); //set up the panel size
        add(panel);                                           //add panel to the frame

        //read in the images required for the game
        //If the images are not successfully read in, the system will print out the stack trace of the Exception.
        try {
            image1 = ImageIO.read(new File("C:\\Users\\14396\\IdeaProjects\\AliceZU1A1\\src\\com\\company\\arrow.png"));
            image2 = ImageIO.read(new File("C:\\Users\\14396\\IdeaProjects\\AliceZU1A1\\src\\com\\company\\car.png"));
            image3 = ImageIO.read(new File("C:\\Users\\14396\\IdeaProjects\\AliceZU1A1\\src\\com\\company\\arrow2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setVisible(true);                                    //display the panel
    }


    class panel1 extends JPanel {                           //this class is for the actual drawings of the game
        public void paint(Graphics display) {

            display.setColor(Color.yellow);                                 //set the color to yellow and draw three doors
            display.fillRect(150, 150, 100, 200);
            display.fillRect(300, 150, 100, 200);
            display.fillRect(450, 150, 100, 200);

            display.setColor(Color.GRAY);                                   //set the color to gray and draw the door handles
            display.fillOval(155, 250, 15, 15);
            display.fillOval(305, 250, 15, 15);
            display.fillOval(455, 250, 15, 15);

            display.setColor(Color.RED);                                    //set the font size and color and display the title
            Font f = new Font("Comic Sans MS", Font.PLAIN, 20);
            display.setFont(f);
            display.drawString("Monty Hall Game", 300, 65);


            Scanner input = new Scanner(System.in);            //create a new scanner
            int a;                                             //initialize integer variables a, b, c and x
            int b;
            int c = 100;
            int x;

            //create an integer array that contains all the position coordinates that need to be used later in the program
            int[] store = {120, 270, 420, 150, 300, 450, 180, 330, 480};

            //print out the introduction of the game
            System.out.println("Welcome to the Monty Hall Game. \nBelow are three doors, among which only one opens to a car. " +
                    "The other two rooms are empty. \n" +
                    "In this game you will use your wisdom to guess for the door that contains the car. " +
                    "You can have the car if you guess it right. \nGood luck!");

            a = (int) (Math.random() * 3 + 1);    //let a be a random integer between 1 and 3 (representing the car door)

            do {          //this loop repeats until the user inputs a correct number
                System.out.println("Please choose a door to open, 1 for the first one, " +
                        "2 for the second one and 3 for the third one. ");            //ask the user to choose a door to open
                String k = input.next();                  //let string k be the next input value

                try {
                    x = Integer.parseInt(k);              //convert string k to integer x
                    if (x == 1 || x == 2 || x == 3) {          //if x is 1, 2 or 3 (correct input):
                        display.drawImage(image1, store[x + 5], 100, 40, 40, null);  //draw a red arrow above the door
                        break;           //break the loop
                    } else
                        System.out.println("Invalid input! ");  //if x is not 1, 2 or 3, print "invalid input" (then proceed to the beginning of the loop)

                } catch (IllegalArgumentException e) {     //if the try statement cannot go through (k cannot be converted to an integer)
                    System.out.println("Invalid input! ");     //print "invalid input" (then proceed to the beginning of the loop)
                }
            } while (true);


            for (int i = 1; i < 4; i++) {   //this loop repeats 3 times going through each number 1, 2 and 3
                if (i != a && i != x) {  //if i is not equal to a nor x (which is the door that is neither the car door nor the one the user picked)
                    display.setColor(Color.BLACK);    //fill the door black
                    display.fillRect(store[i + 2], 150, 100, 200);
                    System.out.println("Door " + i + " is empty. Do you want to change your choice? yes = 0, no = 1: ");   //display this empty door and ask if the user wants to change his choice
                    c = i;                            //let c be the value of i
                    break;                            //break the loop
                }
            }

            do {               //this loop repeats until the user has the correct input
                String k = input.next();        //let string k be the next input value

                try {
                    b = Integer.parseInt(k);   //convert string k to integer b
                    if (b == 0 || b == 1)      //if b is 0 or 1:
                        break;                      //break the loop
                    else {             //else:
                        System.out.println("invalid input! ");       //print "invalid input!"
                        System.out.println("Do you want to change your choice? yes = 0 no = 1: ");   //ask the user to input again and go back to the beginning of the loop
                    }

                } catch (IllegalArgumentException e) {      //if the try statement does not work (k is not convertible)
                    System.out.println("invalid input! ");    //print "invalid input!"
                    System.out.println("Do you want to change your choice? yes = 0 no = 1: ");  //ask the user to input again and go back to the beginning of the loop
                }
            } while (true);


            if (b == 0) {     //if b is equal to 0 (the user wants to change door)

                for (int i = 1; i < 4; i++) {   //this loop goes through all the numbers 1, 2 and 3
                    if (i != x && i != c) {              //if i is neither x nor c (the door that the user can change to)
                        display.drawImage(image3, store[i + 5], 100, 40, 40, null);  //draw a new blue arrow above that door

                        if (i == a) {     //if i is equal to a (that door has a car inside)
                            display.drawImage(image2, store[a - 1], 200, 170, 130, null); //display the car
                            System.out.println("The car door is " + a + ". You Win!");        //show the win message on both the console and the panel
                            display.drawString("You Win!", 330, 100);

                        } else { //if the door is empty
                            display.drawImage(image2, store[a - 1], 200, 170, 130, null); //display the car
                            System.out.println("The car door is " + a + ". You Lose!");     //show the lose message on both the console and the panel
                            display.drawString("You Lose!", 330, 100);
                        }
                        break;    //exit the loop
                    }
                }

            } else {   //if b is equal to 1 (the user does not want to change door)
                if (x == a) {   //if x is equal to a (x is the car door)
                    display.drawImage(image2, store[a - 1], 200, 170, 130, null);   //display the car
                    System.out.println("The car door is " + a + ". You Win!");              //show the win message on both the console and the panel
                    display.drawString("You Win!", 330, 100);

                } else {          //if x is not equal to a (x is not the car door)
                    display.drawImage(image2, store[a - 1], 200, 170, 130, null);   //display the car
                    System.out.println("The car door is " + a + ". You Lose!");          //show the lose message on both the console and the panel
                    display.drawString("You Lose!", 330, 100);
                }
            }
        }
    }
}

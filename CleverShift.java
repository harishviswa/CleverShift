//CleverShift.java
//Harish Viswa
//10.28.20

import edu.fcps.karel2.Display;
import edu.fcps.karel2.Robot;
import javax.swing.JOptionPane;

public class CleverShift extends Athlete {
 // method for the athlete to put beepers in the current location.
 // numBeepers is the number of beepers the athlete needs to put in its current location.
 // temp is the athlete name
   public static void putBeepers(Athlete temp, int numBeepers) {
      for (int i = 0; i < numBeepers; i++) 
         temp.putBeeper();
   }

 // shiftBeepers is the method to perform the clever shift.
 //  It shifts a pile from the current x position to one more over
 // After shifting all the piles, the athlete will move back to the starting position.
   public static void shiftBeepers(Athlete temp) {
   // prevNumBeepers is the number of beepers the athlete picked up from the pile in the previous position.
   // 0 because there are no beepers in the previous position at first
      int prevNumBeepers = 0;
   
   // currentX represents the current x position
      for(int currentX = 1; currentX <= 8; currentX++) {
      // currNumBeepers is the number of beepers picked from the pile in the current position. 
      // differentiates between beepers need to be picked up and beepers picked up already
         int currNumBeepers = 0;
      
      // While temp is next to a beeper, pick all the beepers from the current position.
      // current number of beepers increases for every beeper that is picked up
         while (temp.nextToABeeper()) {
            temp.pickBeeper();
            currNumBeepers++;
         }
      
      // Put all the beepers from the previous position to the current location using the putBeepers method
      // The number of beepers we obtained from the previous pile is stored in prevNumBeepers.  
         putBeepers(temp, prevNumBeepers); 
      
      // currNumBeepers equals prevNumBeepers to keep track of the beepers that need to be shifted next
         prevNumBeepers = currNumBeepers;
      
      // Move forward
         temp.move();
      }
   
   // So athelete can return to the starting location
      temp.turnAround();
   
   // Moves the athlete back to the starting position based on how far robot has travelled
      for (int currentX = 8; currentX >= 1; currentX--)
         temp.move();
   }
   

//creates map and allows user to pick map
//athlete is instantiated 
//shiftBeepers class method is run
//checks if loop is infinite with print statement
   public static void main(String[] args) {     
      String filename = JOptionPane.showInputDialog("What robot world?\n pile1, pile2, pile3");
      Display.openWorld("maps/" + filename + ".map");
      Display.setSize(10, 10);
      Display.setSpeed(10);           
      Athlete bronny = new Athlete(1,1, Display.EAST, 0);
      shiftBeepers(bronny);
      System.out.println("Task completed");
   }
}


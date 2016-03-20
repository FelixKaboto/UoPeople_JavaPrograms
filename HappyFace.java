/*
 * UoPeople: CS 1102 Programming 1 - Unit 7 A Happy Face
 * Author: Pajama Programmer
 * Date 11-Mar-2016
 * 
 * Description: Unit 7 Program Assignment. 
 * The purpose of this assignment is to practice creating graphics in Java.
 * The assignment was to create a program that will display a happy face in its own window... 
 * 
 * Because I felt the assignment was too easy - the coded needed was in the learning videos...
 * I decided to add another button that controls an event Timer, which in turn controls the 
 * movement of the Happy Face. About half way through I realized that this was overly complicated, 
 * but there was no turning back... I did manage to implement the feature - though I still don't 
 * understand 100% how I did it! 
 */
package happyface;

//Various and assorted imports
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 *
 * @author PajamaProgrammer
 */
public class HappyFace extends JPanel implements ActionListener //ActionListener is used for responding to Timer events
{
    private int frameNum = 300; //Controls the animation
    
    //Constructor for Happy Face will create the JFrame (window) of the Happy Face
    HappyFace(JFrame window)
    {
        super(); 
        window.setContentPane(this);
        window.setBackground(Color.LIGHT_GRAY);
        window.setSize(300, 300);   //Size as required by assignment
        window.setResizable(false); //User cannot resize the window 
        window.setLocation(10, 10);    //Det default location just slightly away from the edge of computer screen
        window.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        JFrame window = new JFrame("My Happy Face"); //Create window frame and give it a title
        
        //Create HappyFace content to fit in the frame
        //I pass the window JFrame object and do all the work in the constructor, 
        //I can't say if this is better, but it does clean up the main routine
        HappyFace content = new HappyFace(window);    
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program on close
        
        //Create Button - this button controls the animation of the Happy Face (content).
        //The content object is passed to it so it can be registered as the Listener for Timer event
        HappyButton moveButton = new HappyButton(content);
        moveButton.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //exit program on close
    }
    
    
    private int y  = 0; //controls the y offest of the animation
    private boolean goUp = true; //flag to indicate if the animation is currently going up or down. 
    @Override
    public void paintComponent(Graphics g)
    {
        int x = frameNum%600 - 300; //controls x-offset of the animation
        
        if(goUp) //increment/decrement y offset based on goUp flag
            y++;
        else
            y--;
    
        super.paintComponent(g); //redraw content
 
        //Draw Happy Face
        g.setColor(Color.YELLOW);
        g.fillOval(x + 50, y+25, 200, 200);
        g.setColor(Color.BLACK);
        g.drawArc(x + 75, y + 35, 150, 150, -25, -125);
        g.fillOval(x + 100, y + 85, 25, 25);
        g.fillOval(x + 175, y + 85, 25, 25);
        
        //Draw my name!
        g.setColor(Color.BLUE);
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Hello my name is Becky",50, 255); 
    }
    
    //When a Timer event occurs, the frame number increments and then the animation is redrawn
    @Override
    public void actionPerformed(ActionEvent evt)  
    {
        frameNum++;  //increment frame number
        if(frameNum%35 == 0) //change up/down direction 
            goUp = !goUp;
        
        repaint();
    }

}
    

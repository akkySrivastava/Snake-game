/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;
import java.awt.*;
import javax.swing.*;

public class Snake extends JFrame{
    Snake()
    {
        add(new Board());
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Snake Game");
        setResizable(false);
    }
    
    public static void main(String[] args)
    {
        new Snake().setVisible(true);
    }
    
}
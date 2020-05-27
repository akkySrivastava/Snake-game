/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener{
    private Image apple;
    private Image dot;
    private Image head;
    private int dots;
    
    private final int DOT_SIZE=10;
    private final int ALL_DOTS=900;
    
    private final int RANDOM_POSITION=29;
    
    private final int x[]=new int[ALL_DOTS];
    private final  int  y[]=new int[ALL_DOTS];
    
    private int appleX;
    private int appleY;
    
    private  Timer timer;
    
    private boolean leftDirection=false;
    private boolean rightDirection=true;
    private boolean upDirection=false;
    private boolean downDirection=false;
    
    private boolean endGame=true;
    Board()
    {
        addKeyListener(new TAdapter());
        setPreferredSize(new Dimension(300,300));
        setBackground(new Color(0,0,0));
        initGames();
        loadImage();
        
        
        
        setFocusable(true);
    }
    public void loadImage()
    {
        ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("icons/apple.png"));
        apple=i.getImage();
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/dot.png"));
        dot=i1.getImage();
        ImageIcon i2=new ImageIcon(ClassLoader.getSystemResource("icons/head.png"));
        head=i2.getImage();
        
    }
    public void initGames()
    {
        dots=3;
        for(int i=0;i<dots;i++)
        {
            x[i]=50-i*DOT_SIZE;
            y[i]=50;
        }
        locateApple();
        
        timer=new Timer(180,this);
        timer.start();
    }
    public void locateApple()
    {
        int z=(int)(Math.random() * RANDOM_POSITION);
        appleX=(z*DOT_SIZE);
        
        z=(int)(Math.random() * RANDOM_POSITION);
        appleY=(z*DOT_SIZE);
        
    }
    public void checkApple()
    {
        if(x[0]==appleX && y[0]==appleY)
        {
            dots++;
            locateApple();
        }
        
    }
    public void checkCollision()
    {
        for(int u=dots;u>0;u--)
        {
            if((u>4) && (x[0] == x[u]) && (y[0] == y[u]))
            {
                endGame=false;
            }
        }
        if(y[0]>=300||x[0]>=300)
        {
            endGame=false;
        }
        if(x[0]<0||y[0]<0)
        {
            endGame=false;
        }
        if(!endGame)
        {
            timer.stop();
        }
    }
    public void move()
    {
        for(int z=dots;z>0;z--)
        {
            x[z]=x[z-1];
            y[z]=y[z-1];
        }
        
        if(leftDirection)
        {
            x[0]-=DOT_SIZE;
        }
        if(rightDirection)
        {
            x[0]+=DOT_SIZE;
        }
        if(upDirection)
        {
            y[0]-=DOT_SIZE;
        }
        if(downDirection)
        {
            y[0]+=DOT_SIZE;
        }
    }
    public void paintComponent(Graphics g)
    {
            super.paintComponent(g);
            draw(g);
        }
    public void draw(Graphics g)
    {
        if(endGame)
        {
            g.drawImage(apple,appleX,appleY,this);
            
            for(int z=0;z<dots;z++)
            {
                if(z==0)
                {
                    g.drawImage(head,x[z],y[z],this);
                }
                else
                {
                    g.drawImage(dot,x[z],y[z],this);
                }
            }
            Toolkit.getDefaultToolkit().sync();
        }
        else
        {
            gameOver(g);
        }
    }
    public void gameOver(Graphics g)
    {
        String msg="GAME OVER";
        Font font=new Font("serif",Font.BOLD,20);
        FontMetrics metrics=getFontMetrics(font);
        g.setColor(Color.white);
        g.setFont(font);
        g.drawString(msg,(300-metrics.stringWidth(msg))/2,300/2);
               
    }
    @Override
    
    public void actionPerformed(ActionEvent ae)
    {
        if(endGame)
        {
            checkApple();
            checkCollision();
            move();
        }
        repaint();
    }
    private class TAdapter extends KeyAdapter
    {
        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            
            if(key==KeyEvent.VK_LEFT && (!rightDirection))
            {
                leftDirection=true;
                upDirection=false;
                downDirection=false;
            }
            
            if(key==KeyEvent.VK_RIGHT && (!leftDirection))
            {
                rightDirection=true;
                upDirection=false;
                downDirection=false;
            }
            
            if(key==KeyEvent.VK_UP && (!downDirection))
            {
                leftDirection=false;
                upDirection=true;
                rightDirection=false;
            }
            
            if(key==KeyEvent.VK_DOWN && (!upDirection))
            {
                leftDirection=false;
                rightDirection=false;
                downDirection=true;
            }
            
        }
    }
}
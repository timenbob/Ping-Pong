import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.util.Random;



public class PingPong {
	//fokus
	public static boolean panelFocus = false;
	//igra
	public static boolean play = false;
	//angles
	public static boolean angle=false;
    // length of the slider
    public static final int SHORT = 30;
    public static final int AVERAGE = 50;
    public static final int LONG = 100; 
    public static final int BALL= 20;
    
    public static final int SLOW = 5;
    public static final int MEDIUM = 7;
    public static final int FAST = 10;
    public static int i = 5;
    
    public static int lenght_s=100;
    // width of the sliders
    public static final int WIDTH = 10;
    

    //sliders
    public static Slider right = new Slider(10, 10, false);
    public static Slider left = new Slider(10, 10, true);
    //sliders points
    public static int left_p = 0;
    public static int right_p = 0;
    
    public static int fr_width = 0;
    public static int fr_height = 0;
    
    //sleep
    public static final int SLEEP = 50;
    
    public static boolean W;
    public static boolean S;
    public static boolean UP;
    public static boolean DN;
    
    
    public static int random_kot = 0;
    
    //ball
    public static Ball ball=new Ball(100, 100,50,5);
    

    
    public static void main(String[] args) {
    	
    	
    	String levi = JOptionPane.showInputDialog(null, "Enter the name of the first player:", "Input Dialog", JOptionPane.PLAIN_MESSAGE);
    	String desni = JOptionPane.showInputDialog(null, "Enter the name of the second player:", "Input Dialog", JOptionPane.PLAIN_MESSAGE);
//    	System.out.println(Player1 + " " + Player2);
//  
        JFrame frame = new JFrame("PingPong");
        frame.setSize(new Dimension(800, 600));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(true);

        JPanel panel = new Panel();
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        frame.setLayout(new BorderLayout());
        
        JPanel north = new JPanel();
        frame.add(north, BorderLayout.NORTH);
        north.setBackground(Color.CYAN);
        
        //fokus
     // Inside the main method
        panel.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                PingPong.panelFocus = true;
            }

            @Override
            public void focusLost(FocusEvent e) {
                PingPong.panelFocus = false;
            }
        });

        ///////////////////////////////////////////////

        //tezavnost
        
        JPanel	console	= new JPanel();
        north.add(console,	BorderLayout.NORTH);
        console.add(new JLabel("Difficulty :"));
        JComboBox<String>	tezavnosti	= new JComboBox<String>(new String[] { "Easy", "Medium", "Hard"});
        console.add(tezavnosti);
        
        tezavnosti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String selectedOption = (String) tezavnosti.getSelectedItem();
               System.out.println("Selected option: " + selectedOption);
       
               if (selectedOption.equals("Hard")) {
            	   lenght_s=SHORT;
            	   ball.setR(10);
            	   i = FAST; 
            	   random_kot = 10;
				    }
               else if(selectedOption.equals("Medium")) {
				   	lenght_s=AVERAGE;
				   	ball.setR(7);
				   	i = MEDIUM;
				   	random_kot = 15;
				    }
               else if(selectedOption.equals("Easy")) {
				   	lenght_s=LONG;
				   	ball.setR(3);
				   	i = SLOW;
				   	random_kot = 20;
				    }
               left.setLength(lenght_s);
               right.setLength(lenght_s);
			}});
        /////////////////////////////////////////////////////////////////////////
//        gumb play na novo pozene igro in prestavi zogo na sredino
        JButton button = new JButton("PLAY");
        button.setPreferredSize(new Dimension(96, 40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ball.setX(panel.getWidth()/2 - BALL/2);
                ball.setY(panel.getHeight()/2 + BALL/2);
                ball.setFi(ball.getFi()+180);
                play=true;
                

                if (play) {
                    panel.setFocusable(true);
                    panel.requestFocusInWindow();
                } else {
                    panel.setFocusable(false);
                    
                }
            }
        });
        north.add(button);
     
        /////////////////////////////////////////////////////////
        //gumb koti
        
        JPanel	anglee	= new JPanel();
        north.add(anglee,	BorderLayout.NORTH);
        anglee.add(new JLabel("Random angles :"));
        JComboBox<String>	angles	= new JComboBox<String>(new String[] { "NO", "YES"});
        anglee.add(angles);
        
        tezavnosti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               String selectedOption = (String) angles.getSelectedItem();
               System.out.println("Selected option: " + selectedOption);
       
               if (selectedOption.equals("NO")) {
            	   angle=false;
				    }
               else if(selectedOption.equals("YES")) {
				   angle=true;
				    }

			}});
        ////////////////////////////////////////
        JPanel east = new JPanel();
        east.setBackground(Color.CYAN);
        east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
        frame.add(east, BorderLayout.EAST);
        
        // Create labels for displaying points
        JLabel text1 = new JLabel(" Levi igralec : ");
        JLabel leftPointsLabel = new JLabel(" " + levi + " = " + left_p + "  ");
        JLabel text2 = new JLabel(" Desni igralec :  ");
        JLabel rightPointsLabel = new JLabel(" " + desni + " = " + right_p + " ");

        // Add labels to the east panel
        east.add(text1);
        east.add(leftPointsLabel);
        east.add(text2);
        east.add(rightPointsLabel);
        
        JPanel west = new JPanel();
        frame.add(west, BorderLayout.WEST);
        west.setBackground(Color.CYAN);
        
        JPanel south = new JPanel();
        frame.add(south, BorderLayout.SOUTH);
        south.setBackground(Color.CYAN);
        
        frame.add(panel, BorderLayout.CENTER);
        
     

        
        
        left.setLength(lenght_s);
        left.setY((int) (frame.getHeight() / 2 - lenght_s));
        
        right.setLength(lenght_s);
        right.setY((int) (frame.getHeight() / 2 - lenght_s));

        frame.getRootPane().putClientProperty("apple.awt.brushMetalLook", true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        ///////////////////////////////////////////////////////////////////////////////
        // gumbi za gor in dol
        
        panel.addKeyListener(new KeyListener() {
        	
            
            @Override
            public void keyPressed(KeyEvent e) {
            	 if (PingPong.panelFocus && PingPong.play) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    W = true;
                } 
                else if (e.getKeyCode() == KeyEvent.VK_S) {
                    S = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	UP = true;
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                	DN = true;
                }

                if (W && UP) {
                	if (left.getY() >= 0)
                		left.setY(left.getY() - i);
                	if (right.getY() >= 0)
                		right.setY(right.getY() - i);
                	
                    System.out.println("Both keys are pressed! WUP");
                }
                else if (W) {
                	System.out.println("W");
                	if (left.getY() >= 0)
                		left.setY(left.getY() - i);
                }
                else if (UP) {
                	if (right.getY() >= 0)
                		right.setY(right.getY() - i);
                	System.out.println("UP");
                }
                
                if (W && DN) {
                	if (right.getY() <= fr_height - left.getLength())
                		right.setY(right.getY() + i);
                	if (left.getY() >= 0)
                		left.setY(left.getY() - i);
                	System.out.println("DN & W");	
                }
                if (S && UP) {
                	if (left.getY() <= fr_height - left.getLength())
                		left.setY(left.getY() + i);
                	if (right.getY() >= 0)
                		right.setY(right.getY() - i);
                	System.out.println("UP & S");
                	
                }
                if (S && DN) {
                	if (left.getY() <= fr_height - left.getLength())
                		left.setY(left.getY() + i);
                	if (right.getY() <= fr_height - left.getLength())
                		right.setY(right.getY() + i);
                    System.out.println("Both keys are pressed! SDN");
                }
                else if (S) {
                	if (left.getY() <= fr_height - left.getLength())
                		left.setY(left.getY() + i);
                	System.out.println("S");
                }
                else if (DN) {
                	if (right.getY() <= fr_height - left.getLength())
                		right.setY(right.getY() + i);
                	System.out.println("DN");
                }
               
            }}

            @Override
            public void keyReleased(KeyEvent e) {
            	 if (PingPong.panelFocus && PingPong.play) {
            	if (e.getKeyCode() == KeyEvent.VK_W) {
                    W = false;
                } 
            	else if (e.getKeyCode() == KeyEvent.VK_S) {
                    S = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_UP) {
                	UP = false;
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                	DN = false;
                }
            	

            }}
            
            

            @Override
            public void keyTyped(KeyEvent e) {
            }
        });
        

        
        
        fr_width = panel.getWidth();
        fr_height = panel.getHeight();
        
        ball.setX((int)fr_width/2);
        ball.setY((int)fr_height/2);
        Random random = new Random();
     
        
        ///////////////////////////////////////////////////////////////////////
        // premikanje
        
        while (true) {
        	while(play) {
        	fr_width = panel.getWidth();
        	fr_height = panel.getHeight();
        	
        	double r = random.nextDouble();        	
        	
        	//zabijanje zoge
        	if(ball.getY() <=0) {ball.setFi( - ball.getFi());}
        	if(ball.getY()+PingPong.BALL >=panel.getHeight()) {ball.setFi(- ball.getFi());}
        	//naredi x osi fikse
//        	if(ball.getX() <=0) {ball.setFi(ball.getFi()+100);}
//        	if(ball.getX()+PingPong.BALL >=panel.getWidth()) {ball.setFi(ball.getFi()+100);}
        	//odboj drsnik 
        	//
        	
        	
        	if(ball.getX()+PingPong.BALL >= panel.getWidth()-PingPong.WIDTH) {
        	if(ball.getY()+ PingPong.BALL >= right.getY() && right.getY()+PingPong.lenght_s >= ball.getY()) {
        		if(0<ball.getFi()& ball.getFi()<90) {
        			if (ball.getFi() + 90 + random_kot < 180 && ball.getFi() + 90 + random_kot > 90 && angle==true)
        				ball.setFi(ball.getFi() + 90 +  (int)(r*random_kot));
        			else
        				ball.setFi(ball.getFi() + 90);
        			}
        		else{
        			if (ball.getFi()-90 + random_kot < 270 && ball.getFi()-90 + random_kot > 180 && angle==true)
        				ball.setFi(ball.getFi()-90 + (int)(r*random_kot));
        			else
        				ball.setFi(ball.getFi()-90);    			
        		}   
        			}
        	else {left_p ++ ; leftPointsLabel.setText(" " + levi + " = " + left_p + "  ");play=false; }
        		}
        	
        	
        	if(ball.getX() < 0+PingPong.WIDTH) {
        	if(ball.getY()+ PingPong.BALL >= left.getY() && left.getY()+PingPong.lenght_s >= ball.getY()) {
        		ball.setX(PingPong.WIDTH);
        		if(180<ball.getFi()& ball.getFi()<270) {
        			if (ball.getFi()+90 +  random_kot < 360 && ball.getFi()+90 +  random_kot > 270 && angle==true)
        				ball.setFi(ball.getFi() + 90 +  (int)(r*random_kot));
        			else
        				ball.setFi(ball.getFi() + 90);
        			}
        		else{
        			if (ball.getFi()-90 -  random_kot > 0 && ball.getFi()-90 -  random_kot < 90 && angle==true)
        				ball.setFi(ball.getFi()-90 -  (int)(r*random_kot));
        			else
        				ball.setFi(ball.getFi()-90 );
        		     		
        			}
        		}
        	else {right_p ++; rightPointsLabel.setText(" " + desni + " = " + right_p + " ");play=false;}
        	
        		}
        	
        	
        	ball.setXY();
        	frame.repaint();
        	
            
            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                frame.repaint();
            }
        }
        	try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
                frame.repaint();
            }
		}}		
	}


/////////////////////////////////////////////////////////////////
//risanje
@SuppressWarnings("serial")
class Panel extends JPanel {

	public Panel() {
		super();
		setBackground(Color.PINK);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g); 
		Graphics2D graphics = (Graphics2D)g; 
		
	graphics.setStroke(new BasicStroke(2));
	graphics.drawRoundRect(0, (int)(PingPong.left.getY()), PingPong.WIDTH, (int)PingPong.left.getLength(), 3, 3);
	graphics.drawRoundRect((int)(PingPong.fr_width- PingPong.WIDTH), (int)(PingPong.right.getY()), PingPong.WIDTH, (int)PingPong.right.getLength(), 3, 3);
	graphics.setColor(Color.BLUE);
	graphics.fillRoundRect(0, (int)(PingPong.left.getY()), PingPong.WIDTH, (int)PingPong.left.getLength(), 3, 3);
	graphics.fillRoundRect((int)(PingPong.fr_width- PingPong.WIDTH), (int)(PingPong.right.getY()), PingPong.WIDTH, (int)PingPong.right.getLength(), 3, 3);

	graphics.setStroke(new BasicStroke(3));
	graphics.setColor(Color.BLACK);
	graphics.drawOval(PingPong.ball.getX(), PingPong.ball.getY(), PingPong.BALL, PingPong.BALL);	
	graphics.setColor(Color.YELLOW);
	graphics.fillOval(PingPong.ball.getX(), PingPong.ball.getY(), PingPong.BALL, PingPong.BALL);
	
		
	}
}


	



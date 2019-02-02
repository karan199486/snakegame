package snakegame;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class DriverClass extends Frame 
{

	Snake s;
	Boolean result; // result is for checking whether snake has bitten itself?
	Food food;
	
	Panel playGround;
	Panel scoreBoard;
	Label score;
	
	
	
	DriverClass(int width, int height)
	{
		
		playGround = new Panel() {
			public void paint(Graphics g)
			{
				result = s.drawSnake(g);

			}
		};
		scoreBoard = new Panel() {
			
			public void paint(Graphics g)
			{
				score.setText("Your Score : "+s.getScore());
				
			}
		};
		score = new Label("Your Score : 0");
		scoreBoard.add(score);
		
		playGround.setBounds(0, 0, 400, 400);
		scoreBoard.setBounds(0,400,400,100);
		
		super.setSize(width, height);
		food = new Food(playGround.getWidth(), playGround.getHeight());
		s = new Snake(1,food,playGround.getWidth(), playGround.getHeight() );
		result = true;
		
		
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}
		});
		
		this.addKeyListener(new KeyAdapter()
				{
					public int prevkey = KeyEvent.VK_RIGHT;  // for restricting the reverse movement
						
					public void keyPressed(KeyEvent ke)
					{
						int kc = ke.getKeyCode();
						if(KeyEvent.getKeyText(kc).equals("Up") && !KeyEvent.getKeyText(prevkey).equals("Down") && prevkey != kc)
						{
							s.map.put(new Point(s.blocks.lastElement().x, s.blocks.lastElement().y) , 
									new Point(0,-1));
						}
						else if(KeyEvent.getKeyText(kc).equals("Down") && !KeyEvent.getKeyText(prevkey).equals("Up") && prevkey != kc)
						{
							s.map.put(new Point(s.blocks.lastElement().x, s.blocks.lastElement().y) , 
									new Point(0,1));
						}
						else if(KeyEvent.getKeyText(kc).equals("Left") && !KeyEvent.getKeyText(prevkey).equals("Right") && prevkey != kc)
						{
							s.map.put(new Point(s.blocks.lastElement().x, s.blocks.lastElement().y) , 
									new Point(-1,0));
						}
						else if(KeyEvent.getKeyText(kc).equals("Right") && !KeyEvent.getKeyText(prevkey).equals("Left") && prevkey != kc)
						{
							s.map.put(new Point(s.blocks.lastElement().x, s.blocks.lastElement().y) , 
									new Point(1,0));
						}
						
						prevkey = kc;
					}
				});
		
		this.add(scoreBoard,BorderLayout.SOUTH);
		this.add(playGround, BorderLayout.CENTER);
		
	}
	
	public void moveSnake() {
		
		
		while(result)
		{
			playGround.repaint();
			scoreBoard.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		this.setTitle("GAME OVER");
		
	}
	
	public static void main(String...args)
	{
		
		DriverClass f = new DriverClass(400,400 + 50);
		f.setVisible(true);
		f.moveSnake();
		
	}
	
	public void paint(Graphics g)
	{
		//Graphics g1 = playGround.getGraphics();
		/*result = s.drawSnake(g);*/
	    
	    
	}

}

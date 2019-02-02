package snakegame;
import java.awt.*;

public class Food {
	int x;
	int y;
	int framewidth;
	int frameheight;
	
	Food(int framewidth, int frameheight)
	{
		this.framewidth = framewidth;
		this.frameheight = frameheight;
		
		randomizexy();
	}
	
	public void drawFood(Graphics g)
	{
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, 20, 20);
		g.setColor(Color.black);
		g.drawRect(x, y, 20, 20);
	}
	
	public void randomizexy()
	{
		
		
		x = (int)((framewidth - 40) * Math.random());
		y = (int)((frameheight - 40) * Math.random());
		x = 20 * (x/20) + 40;
		y = 20 * (y/20) + 40;
		
		if(x <= (framewidth - 40) && x >= 40 && y <= (frameheight - 40) && y >= 40)
		{
			//System.out.println("x = "+x+" y = "+y);
			return;
		}
			
		else 
			randomizexy();
		
		
		
		
	}

}

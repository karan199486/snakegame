package snakegame;
import java.awt.*;

public class Block {
	int x,y;
	int changex, changey;
	int blocksize;
	int areawidth, areaheight;
	//int modheight, modwidth;
	
	Block(int x, int y, int changex, int changey, int blocksize, int areawidth, int areaheight)
	{
		this.areaheight = areaheight;
		this.areawidth = areawidth;
		//this.modwidth = (areawidth - blocksize+1);
		//this.modheight = (areaheight - blocksize+1);
		this.x = x; // % modwidth;
		this.y = y; // % modheight;
		this.changex = changex;
		this.changey = changey;
		this.blocksize = blocksize;
	}
	
	public int getNextX()
	{
		/*int nextx;
	    if((nextx = (x + blocksize*changex)%modwidth)>0)
	    	return nextx;
	    else 
	    	return areawidth + nextx;*/
		return x + blocksize*changex;
	}
	
	public int getNextY()
	{
		/*int nexty;
	    if((nexty = (y + blocksize*changey)%modheight)>0)
	    	return nexty;
	    else 
	    	return areawidth + nexty;*/
		
		return y + blocksize*changey;
	}
	
	public void drawBlock(Graphics g, Color color)
	{
		//System.out.println(getNextX());
		g.setColor(color);
		g.fillRect(getNextX(), getNextY(), blocksize, blocksize);
		g.setColor(Color.BLACK);
		g.drawRect(getNextX(), getNextY(), blocksize, blocksize);
	}
	
	
}

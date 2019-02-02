package snakegame;
import java.util.*;
import java.awt.*;

public class Snake {
	Vector<Block> blocks;
	int length;
	Food food;
	int score;  // it rep. no. of times snake had eaten the food
	int areawidth;
	int areaheight;
	
	Map<Point,Point> map;    // this map is used to track at which point block has to change it's direction
	
	Snake(int len, Food food, int width, int height)
	{
		length = len;
		score = 0;
		blocks = new Vector<Block>();
		map = new HashMap<Point,Point>();
		this.food = food;
		areawidth = width;
		areaheight = height;
		
		int x = 0;
		int y = 40;
		int blocksize = 20;
							// default direction for all the blocks is from left to right
		int changex = 1;
		int changey = 0;
		
		// iniiallizing blocks
		for(int i = 0; i< length; i++)
		{	
			Block b = new Block(x, y, changex, changey ,blocksize, areawidth, areaheight); 
			blocks.add(b);
			x += blocksize*changex ;
			y += blocksize*changey ;
		}
	}
	
	public int getScore()
	{
		return score;
	}
	
	public boolean hasEatedFood()
	{
		if(blocks.get(length-1).x == food.x && blocks.get(length-1).y == food.y)
			return true;
		
		return false;
	}
	
	public boolean drawSnake(Graphics g)  
	{
	   food.drawFood(g);
		
		if(hasEatedFood())
		{
			this.length++;  // incrementing length
			score++; // incrementing score
			
			// adding new block at 0th index (tail) of snake
			Block tail = blocks.firstElement();
			blocks.add(0,new Block( tail.x - 20*tail.changex, tail.y - 20*tail.changey , tail.changex,
					tail.changey, tail.blocksize, areawidth, areaheight));
			
			food.randomizexy();
		}
		
		boolean result = true;
		Color color = Color.GREEN;
		
		//checking for collision
		int headx = blocks.get(length-1).x; 
		int heady = blocks.get(length-1).y; 
		int blocksize = blocks.get(length-1).blocksize;
		
		if(headx == areawidth - blocksize || heady == areaheight - blocksize || headx == 0  || heady == 0  )
		{
			result = false;
			color = Color.red;
		}
		else
		for(int i = 0; i< length-1; i++)
		{
			
			
			int currblockx = blocks.get(i).x; 
			int currblocky = blocks.get(i).y; 
			if(headx == currblockx && heady == currblocky)
			{
				result = false;
				color = Color.red;
				
				break;
			}
		}
			
		
		for(int i = 0; i< blocks.size(); i++)
		{
			int x = blocks.get(i).x;
			int y = blocks.get(i).y;
			
			if(map.containsKey(new Point(x,y)))
			{
				blocks.get(i).changex = map.get(new Point(x,y)).x;
				blocks.get(i).changey = map.get(new Point(x,y)).y;
				
				if(i == 0)
				{
					// when last block crossed the block of turn, we will delete the point of reference for turn
					map.remove(new Point(x,y));
				}
			}
			
			blocks.get(i).drawBlock(g,color);
			
			blocks.get(i).x = (blocks.get(i).x + blocks.get(i).blocksize * blocks.get(i).changex);  //%(areawidth - blocks.get(i).blocksize);
			blocks.get(i).y = (blocks.get(i).y + blocks.get(i).blocksize * blocks.get(i).changey);  //%(areaheight - blocks.get(i).blocksize);
			
			
		}
		
		return result;
				
		
	}
	
	
}

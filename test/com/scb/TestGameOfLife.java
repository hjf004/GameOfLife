package com.scb;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test; 

/**
 * 测试类，包含一些测试用例
 * @author Group 5
 *
 */
public class TestGameOfLife {
	private Game game;
	
	@Before
	public void setUp() throws Exception {
		game=new Game(); 
	}

	@After
	public void tearDown() throws Exception {
		game=null;
	}

	/**
	 * @author 胡季风
	 * 测试Game类的calculateLifeAround方法
	 */
	@Test
	public void calculateLifeAroundTest() {
		int[][] grid={ {0,0,0,0,0},
				   	   {0,0,1,1,0},
				       {0,1,0,1,0},
				       {0,0,1,1,0},
				       {0,0,0,0,0}};
		game.setGrid(grid);
		int result=game.calculateLifeAround(2,3);
		assertEquals(4, result);
		result=game.calculateLifeAround(4,0);
		assertEquals(-1, result);
	}
	
	/**
	 * @author 杨海峰
	 * 测试Game类的checkIfTooLonely方法
	 */
	@Test
	public void checkIfTooLonelyTest() {
		int[][] grid={ {0,0,0,0,0},
				   	   {0,0,1,1,0},
				       {0,1,0,1,0},
				       {0,0,1,1,0},
				       {0,0,0,0,0}};
		game.setGrid(grid);
		boolean result=game.ifTooLonely(2,3);
		assertFalse(result);
		result=game.ifTooLonely(2,1);
		assertFalse(result);
	}
	
	
	/**
	 * @author 郭杨杨
	 * 测试Game类的isTooCrowd()方法
	 */
	@Test
	public void isTooCrowdTest() { 
		int[][] grid={ {0,0,0,0,0},
				   	   {0,0,1,1,0},
				       {0,1,0,1,0},
				       {0,0,1,1,0},
				       {0,0,0,0,0}};
		game.setGrid(grid); 
		boolean result=game.ifTooCrowd(2,3);
		assertTrue(result); 
	}



	/**
	 * @author 钟明阳
	 * 测试Game类的ifCanRevive()方法
	 */
	@Test
	public void ifCanReviveTest() {
		int[][] grid={ {0,0,0,0,0},
					   {0,0,0,1,0},
					   {0,1,0,0,0},
					   {0,0,1,0,0},
					   {0,0,0,0,0}};
		game.setGrid(grid);
		boolean result;
		result = game.ifCanRevive(2,2);
		assertTrue(result);
		result=game.ifCanRevive(1,1);
		assertFalse(result);
	}

	/**
	 * @author 钱海龙
	 * 测试Game类的ifCanLiving()方法
	 */
	@Test
	public void ifCanLivingTest(){
		int[][] grid={ {0,0,0,0,0},
					   {0,1,0,1,0},
					   {0,1,0,1,0},
					   {0,0,1,0,0},
					   {0,0,0,0,0}};
		game.setGrid(grid);
		boolean result;
		result = game.ifCanLiving(3,2);
		assertTrue(result);
		result=game.ifCanLiving(1,3);
		assertFalse(result);
	}

	/**
	 * @author 邓袁
	 * 测试Game类的nextGeneration()方法
	 */
	@Test
	public void nextGenerationTest(){
		int[][] grid={ {0,0,0,0,0},
			   	       {0,1,0,1,0},
			           {0,1,0,1,0},
			           {0,0,1,0,0},
			           {0,0,0,0,0}};
		int[][] gridChildRight={   {0,0,0,0,0},
		   	       			 	   {0,0,0,0,0},
		   	       			 	   {0,1,0,1,0},
		   	       			 	   {0,0,1,0,0},
		   	       			 	   {0,0,0,0,0}};
		int[][] gridChildWrong={   {0,0,0,0,0},
		   	       			 	   {0,1,0,1,0},
		   	       			 	   {0,1,0,1,0},
		   	       			 	   {0,0,1,0,0},
		   	       			 	   {0,0,0,0,0}};
			
		game.setGrid(grid);
		boolean result=true;
		int[][] grid_child = game.nextGeneration();
		for(int i=1;i<grid_child.length-1;++i)
			for(int j=1;j<grid_child[0].length-1;++j) {
				result&=(grid_child[i][j]==gridChildRight[i][j]);
			}
		assertTrue(result);
		
		result=true;
		for(int i=1;i<grid_child.length-1;++i)
			for(int j=1;j<grid_child[0].length-1;++j) {
				result&=(grid_child[i][j]==gridChildWrong[i][j]);
			}
		
		assertFalse(result);	
	}
}

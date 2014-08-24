package com.scb;

/**
 * 游戏逻辑类
 * @author Group 5
 */
public class Game {

	private int[][] grid; // 二维数组存储当代的生命
	private int[][] nextGrid; // 二维数组存储下一代生命，用作缓存

	/**
	 * 设置grid值
	 * @param grid
	 */
	public void setGrid(int[][] grid) {
		this.grid = grid;
		if (grid != null)
			nextGrid = new int[grid.length][grid[0].length];
	}

	/**
	 * @author 杨海峰 
	 * 计算某个cell周围存活体的个数
	 * @param r 行数
	 * @param c 列数
	 * @return 返回存活体的个数,-1代表出现错误
	 */
	public int calculateLifeAround(int r, int c) {
		if (grid == null || r <= 0 || r >= (grid.length - 1) || c <= 0
				|| c >= (grid[0].length - 1))
			return -1;
		int count = 0;
		for (int i = r - 1; i <= r + 1; i++)
			for (int j = c - 1; j <= c + 1; j++)
				count += grid[i][j];
		count -= grid[r][c];
		return count;
	}

	/**
	 * @author 郭杨杨 
	 * 判断某个live cell是否太寂寞(has fewer than two live neighbors)
	 * @param r 行数
	 * @param c 列数
	 * @return true live neighbors 少于两个，false live neighbors在两个或两个以上
	 */
	public boolean ifTooLonely(int r, int c) {
		if (calculateLifeAround(r, c) < 2)
			return true;
		return false;
	}

	/**
	 * @author 钟明阳 
	 * 判断某个live cell是否太拥挤(has more than three live neighbors)
	 * @param r 行数
	 * @param c 列数
	 * @return true live neighbors超过三个，false live neighbors没有超过三个
	 */
	public boolean ifTooCrowd(int r, int c) {
		if (calculateLifeAround(r, c) > 3) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @author 钱海龙 
	 * 判断某个dead cell是否能够复活(has exactly three live neighbors)
	 * @param r 行数
	 * @param c 列数
	 * @return true live neighbors恰好三个，false live neighbors不是三个
	 */
	public boolean ifCanRevive(int r, int c) {
		if (3 == calculateLifeAround(r, c))
			return true;
		else
			return false;
	}

	/**
	 * @author 邓袁
	 * 判断某个 live cell是否能够继续活着（has two or three live neighbors)
	 * @param r 行数
	 * @param c 列数
	 * @return true live neighbors = 2 or 3; 否则, return false
	 */
	public boolean ifCanLiving(int r, int c) {
		if (this.ifTooLonely(r, c) || this.ifTooCrowd(r, c))
			return false;
		else
			return true;

	}

	/**
	 * @author 胡季风 
	 * 根据规则产生下一代
	 * @return 下一代二维数组
	 */
	public int[][] nextGeneration() {
		for (int i = 1; i < grid.length - 1; ++i)
			for (int j = 1; j < grid[0].length - 1; ++j) {
				nextGrid[i][j] = grid[i][j];
				if (1 == grid[i][j] && (!ifCanLiving(i, j))) {
					nextGrid[i][j] = 0;
				} else if (0 == grid[i][j] && ifCanRevive(i, j)) {
					nextGrid[i][j] = 1;
				}
			}
		// 交换当代的grid与下一代的grid
		int[][] tmp = grid;
		grid = nextGrid;
		nextGrid = tmp;

		return grid;
	}

	/**
	 * 打印指定的Grid
	 */
	private void printGrid(int[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * 打印Game的Grid
	 */
	public void printGrid() {
		printGrid(grid);
	}
}

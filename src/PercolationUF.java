
public class PercolationUF implements IPercolate {

	IUnionFind myFinder;
	int myOpenCount;
	private final int VTOP;
	private final int VBOTTOM;
	boolean[][] myGrid;

	public PercolationUF(int size, IUnionFind finder) {

		myGrid = new boolean[size][size];
		// myGrid[size][size] = false;

		myOpenCount = 0;
		myFinder = new QuickUWPC(size * size + 2);
		VTOP = size * size;
		VBOTTOM = size * size + 1;

	}
	
	boolean inBounds(int row, int col) {
		// TODO Auto-generated method stub
		if (row < 0 || row >= myGrid.length)
			return false;
		if (col < 0 || col >= myGrid.length)
			return false;
		return true;

	}


	@Override
	public void open(int row, int col) {
		// TODO Auto-generated method stub
		if (!inBounds(row, col)) {
			throw new IndexOutOfBoundsException();
		}
	
		if (!isOpen(row, col)) {
			myGrid[row][col] = true;
			myOpenCount++;            
	
			if (row == 0) {
				myFinder.union((row * myGrid.length) + col, VTOP);

			}

			if (row == myGrid.length - 1) {
				myFinder.union((row * myGrid.length) + col, VBOTTOM);
			}
			if (inBounds(row, col + 1) && isOpen(row, col + 1)) {
				myFinder.union((row * myGrid.length + (col + 1)), (row * myGrid.length )+ col);

			}
			if (inBounds(row, col - 1) && isOpen(row, col - 1)) {
				myFinder.union((row * myGrid.length + (col - 1)), (row * myGrid.length )+ col);

			}
			if (inBounds(row + 1, col) && isOpen(row + 1, col)) {
				myFinder.union((row * myGrid.length) + col, ((row + 1) * myGrid.length) + col);

			}
			if (inBounds(row - 1, col) && isOpen(row - 1, col)) {
				myFinder.union((row * myGrid.length) + col, ((row - 1) * myGrid.length) + col);

			}
		}
	}

	@Override
	public boolean isOpen(int row, int col) {
		// TODO Auto-generated method stub
		if (!inBounds(row, col))
			throw new IndexOutOfBoundsException();
		if (myGrid[row][col] == true)
			return true;
		return false;
	}

	@Override
	public boolean isFull(int row, int col) {
		// TODO Auto-generated method stub
		if (!inBounds(row, col))
			throw new IndexOutOfBoundsException();
		if (myFinder.connected(((row * myGrid.length) + col), VTOP)) {
			return true;
		}
		return false;
	

	}

	@Override
	public boolean percolates() {
		// TODO Auto-generated method stub
		if (myFinder.connected(VTOP, VBOTTOM)) 
			return true;
		return false;

	}

	@Override
	public int numberOfOpenSites() {
		// TODO Auto-generated method stub
		int myOpenCount =0;
		for (int row=0; row<myGrid.length; row++) {
			for (int col =0; col<myGrid.length;col++) {
				if (isOpen(row, col) == true) {
					myOpenCount++;
				}
			}
		}
		
		return myOpenCount;
		
	
	}

	

}

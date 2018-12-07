import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {

	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void dfs(int row, int col) {
		// out of bounds?
		// int size = 0;
		// myGrid[row][col] = row * myGrid.length + col;

		Queue<Integer> qp = new LinkedList<Integer>();
		myGrid[row][col] = FULL;
		// size++; // count pixel
		qp.add((row * myGrid.length) + col);

		while (qp.size() != 0) {
			int p = qp.remove();
			int deqRow = p / myGrid.length;
			int deqCol = p % myGrid.length;

			// for(int k=0; k < rowDelta.length; k++){

			if (inBounds(deqRow - 1, deqCol)) {
				if (isOpen(deqRow - 1, deqCol) && !isFull(deqRow - 1, deqCol)) {
					myGrid[deqRow - 1][deqCol] = FULL;
					qp.add((deqRow - 1) * myGrid.length + deqCol);
				}

			}

			if (inBounds(deqRow + 1, deqCol)) {
				if (isOpen(deqRow + 1, deqCol) && !isFull(deqRow + 1, deqCol)) {
					myGrid[(deqRow) - 1][deqCol] = FULL;
					qp.add((deqRow + 1) * myGrid.length + deqCol);
				}
			}

			if (inBounds(deqRow, deqCol + 1)) {
				if (isOpen(deqRow, deqCol + 1) && !isFull(deqRow, deqCol + 1)) {
					myGrid[deqRow][deqCol + 1] = FULL;
					// size++;
					qp.add(deqRow * myGrid.length + deqCol + 1);
				}
			}

			if (inBounds(deqRow, deqCol - 1)) {
				if (isOpen(deqRow, deqCol - 1) && !isFull(deqRow, deqCol - 1)) {
					myGrid[deqRow][deqCol - 1] = FULL;
					// size++;
					qp.add(deqRow * myGrid.length + deqCol - 1);
				}
			}

		}

	}
}

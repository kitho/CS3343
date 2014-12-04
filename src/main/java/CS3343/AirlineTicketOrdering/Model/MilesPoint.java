package CS3343.AirlineTicketOrdering.Model;

/**
 * The Class MilesPoint.
 */
public class MilesPoint {
	
	/** The minimun. */
	private int minimun;
	
	/** The maximun. */
	private int maximun;
	
	/** The basepoint. */
	private int basepoint;
	
	/**
	 * Instantiates a new miles point.
	 *
	 * @param mini the mini
	 * @param max the max
	 * @param base the base
	 */
	public MilesPoint(int mini, int max, int base){
		minimun = mini;
		maximun = max;
		basepoint = base;
	}

	/**
	 * Gets the minimun.
	 *
	 * @return the minimun
	 */
	public int getMinimun() {
		return minimun;
	}

	/**
	 * Sets the minimun.
	 *
	 * @param minimun the new minimun
	 */
	public void setMinimun(int minimun) {
		this.minimun = minimun;
	}

	/**
	 * Gets the maximun.
	 *
	 * @return the maximun
	 */
	public int getMaximun() {
		return maximun;
	}

	/**
	 * Sets the maximun.
	 *
	 * @param maximun the new maximun
	 */
	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}

	/**
	 * Gets the basepoint.
	 *
	 * @return the basepoint
	 */
	public int getBasepoint() {
		return basepoint;
	}

	/**
	 * Sets the basepoint.
	 *
	 * @param basepoint the new basepoint
	 */
	public void setBasepoint(int basepoint) {
		this.basepoint = basepoint;
	}
	
	
}

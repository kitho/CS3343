package CS3343.AirlineTicketOrdering.Model;

public class MilesPoint {
	private int minimun;
	private int maximun;
	private int basepoint;
	
	public MilesPoint(int mini, int max, int base){
		minimun = mini;
		maximun = max;
		basepoint = base;
	}

	public int getMinimun() {
		return minimun;
	}

	public void setMinimun(int minimun) {
		this.minimun = minimun;
	}

	public int getMaximun() {
		return maximun;
	}

	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}

	public int getBasepoint() {
		return basepoint;
	}

	public void setBasepoint(int basepoint) {
		this.basepoint = basepoint;
	}
	
	
}

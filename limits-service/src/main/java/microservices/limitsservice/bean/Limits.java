package microservices.limitsservice.bean;

public class Limits {
	
	private int minimumLimit;
	private int maximumLimit;
	public Limits() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Limits(int minimumLimit, int maximumLimit) {
		super();
		this.minimumLimit = minimumLimit;
		this.maximumLimit = maximumLimit;
	}
	public int getMinimumLimit() {
		return minimumLimit;
	}
	public void setMinimumLimit(int minimumLimit) {
		this.minimumLimit = minimumLimit;
	}
	public int getMaximumLimit() {
		return maximumLimit;
	}
	public void setMaximumLimit(int maximumLimit) {
		this.maximumLimit = maximumLimit;
	}
	
	

}

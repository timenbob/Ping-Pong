
public class Slider {
	
	private int length;
	
	public int y;
	
	private boolean left;

	public Slider(int length, int y, boolean left) {
	    
	    this.length = length;
	    this.y = y;
	    this.left = left;
	  }
	
	public int getLength() {
		return length;
	}
	
	public void setLength(int length) {
		this.length = length;
	}


	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	

	public boolean isLeft() {
		return left;
	}
	
	


}




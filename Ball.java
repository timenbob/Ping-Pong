
public class Ball {


	
	public int x;
	
	public int y;
	
	public long fi;
	
	public int r;
	
	public Ball(int x, int y, long fi,int r) {
	    
	    this.x = x;
	    this.y = y;
	    this.fi = fi;
	    this.r = r;
	  }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public long getFi() {
		return fi;
	}

	public void setXY() {
	    double radians = Math.toRadians(this.fi); // Convert degrees to radians
	    setX((int)(this.x + Math.cos(radians) * this.r));
	    setY((int)(this.y - Math.sin(radians) * this.r));
	}
	
	
	public void setFi(long fi) {
		if (fi >= 0)
			this.fi = fi % 360;
		else {
			this.fi =(fi + 360) %360;
		}
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}
}


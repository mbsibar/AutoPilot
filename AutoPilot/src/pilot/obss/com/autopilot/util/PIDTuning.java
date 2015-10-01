package pilot.obss.com.autopilot.util;

public class PIDTuning {
	private float p;
	private float i;
	private float d;
	private float iMax;

	public float getP() {
		return p;
	}

	public void setP(float p) {
		this.p = p;
	}

	public float getI() {
		return i;
	}

	public void setI(float i) {
		this.i = i;
	}

	public float getD() {
		return d;
	}

	public void setD(float d) {
		this.d = d;
	}

	public float getiMax() {
		return iMax;
	}

	public void setiMax(float iMax) {
		this.iMax = iMax;
	}
}

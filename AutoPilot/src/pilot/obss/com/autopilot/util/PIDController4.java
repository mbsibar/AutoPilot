package pilot.obss.com.autopilot.util;

public class PIDController4 {
	private float integral, derivative;
	private float dHist[] = new float[32];
	private float kp;
	private float ki;
	private float kd;
	private float ilim;
	private float lim;
	private int dFilLen;
	private int dFilK;
	private float prevError;
	private Double altDerivativeSource;
	private long lastNs = System.nanoTime();

	public double compute(float error, float dtx) {
		long dt = System.nanoTime() - lastNs;
		integral += error * dt;

		if (altDerivativeSource == null) {
			// Derivative low pass filter Store current derivative value into
			// history table
			dHist[dFilK] = (error - prevError) / dt;
			dFilK++;
			if (dFilK == dFilLen) {
				dFilK = 0;
			}
			// Average history table
			derivative = 0;
			for (int k = 0; k < dFilLen; k++) {
				derivative += dHist[k];
			}
			derivative /= dFilLen;
		} else {
			derivative = -altDerivativeSource.floatValue();
		}

		// Anti-windup
		integral = constrain_(integral, ilim);

		float output = error * kp + integral * ki + derivative * kd;

		// Anti-saturation
		output = constrain_(output, lim);
		prevError = error;
		lastNs = System.nanoTime(); 
		return output;
	}

	public void reset_I() {
	}

	public void SetTunings(float Kp, float Ki, float Kd) {
		this.kp = Kp;
		this.ki = Ki;
		this.kd = Kd;
		ilim = 5;
		lim = 1500;
		dFilLen = 1;
		altDerivativeSource = null;
	}

	private float constrain_(float value, float range) {
		if (value > range)
			value = range;
		else if (value < -range)
			value = -range;
		return value;
	}
}

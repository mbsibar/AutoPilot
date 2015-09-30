package pilot.obss.com.autopilot.util;

import pilot.obss.com.autopilot.util.constants.Constants;

public class PIDController {

	private float kp;
	private float ki;
	private float kd;
	private float imax;
	private float filt_hz; // PID Input filter frequency in Hz

	private float dt; // timestep in seconds
	private float integrator; // integrator value
	private float input; // last input for derivative
	private float derivative; // last derivative for low-pass filter

	private boolean reset_filter = true; // true when input filter should be
											// reset
	// during next call to set_input
	private float desired;

	private float p;
	private float i;
	private float d;

	public PIDController(float initial_p, float initial_i, float initial_d, float initial_imax, float initial_filt_hz, float dt) {
		this.kp = initial_p;
		this.ki = initial_i;
		this.kd = initial_d;
		this.imax = Math.abs(initial_imax);
		filt_hz(initial_filt_hz);
		this.dt = dt;
		this.reset_filter = true;
	}

	public void set_dt(float dt) {
		this.dt = dt;
	}

	public void set_input_filter_all(float input) {
		// reset input filter to value received
		if (reset_filter) {
			reset_filter = false;
			this.input = input;
			derivative = 0.0f;
		}

		// update filter and calculate derivative
		float input_filt_change = get_filt_alpha() * (input - this.input);
		this.input = this.input + input_filt_change;
		if (dt > 0.0f) {
			derivative = input_filt_change / dt;
		}
	}

	public void set_input_filter_d(float input) {
		if (reset_filter) {
			reset_filter = false;
			derivative = 0.0f;
		}

		// update filter and calculate derivative
		if (dt > 0.0f) {
			float derivative = (input - this.input) / dt;
			this.derivative = this.derivative + get_filt_alpha() * (derivative - this.derivative);
		}

		this.input = input;
	}

	// get_pid - get results from pid controller
	public float get_pid() {
		return get_p() + get_i() + get_d();
	}

	public float get_pi() {
		return get_p() + get_i();
	}

	public float get_p() {
		p = (input * kp);
		return p;
	}

	public float get_i() {
		if (ki != 0 && dt != 0) {
			integrator += ((float) input * ki) * dt;
			if (integrator < -imax) {
				integrator = -imax;
			} else if (integrator > imax) {
				integrator = imax;
			}
			i = integrator;
			return integrator;
		}
		return 0;
	}

	public float get_d() {
		d = (kd * derivative);
		return d;
	}

	// reset_I - reset the integrator
	public void reset_I() {
		integrator = 0;
	}

	public float kP() {
		return kp;
	}

	public float kI() {
		return ki;
	}

	public float kD() {
		return kd;
	}

	public float imax() {
		return imax;
	}

	public float filt_hz() {
		return filt_hz;
	}

	public void kP(float v) {
		kp = v;
	}

	public void kI(float v) {
		ki = v;
	}

	public void kD(float v) {
		kd = v;
	}

	public void imax(float v) {
		imax = Math.abs(v);
	}

	public void filt_hz(float hz) {
		this.filt_hz = Math.abs(hz);
	}

	public float get_integrator() {
		return integrator;
	}

	public void set_integrator(float i) {
		integrator = i;
	}

	// set the designed rate (for logging purposes)
	public void set_desired_rate(float desired) {
		this.desired = desired;
	}

	public float get_filt_alpha() {
		if (filt_hz == 0) {
			return 1.0f;
		}

		// calculate alpha
		float rc = 1 / (Constants.M_2PI_F * filt_hz);
		return dt / (dt + rc);
	}
}

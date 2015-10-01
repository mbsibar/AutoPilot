package pilot.obss.com.autopilot.util;

public class PIDController5 {
	private float integral, derivative;
	private float dHist[] = new float[32];
	private float kp;
	private float ki;
	private float kd;
	 float m_err;
	  float m_last_err;
	  float m_sum_err;
	  float m_ddt_err;
	  float m_lastInput;
	  float m_outmax;
	  float m_outmin;
	  float m_output;
	private long lastNs = System.nanoTime();

	public double compute(float m_err, float input) {
		long dt = (System.nanoTime() - lastNs) /1000000;
		// Integrating errors
		m_sum_err += m_err * ki * dt;

		// calculating error derivative
		// Input derivative is used to avoid derivative kick
		m_ddt_err = -kd / dt * (input - m_lastInput);

		// Calculation of the output
		// winds up boundaries
		double m_output = kp * m_err + m_sum_err + m_ddt_err;
		if (m_output > m_outmax) {
			// winds up boundaries
			m_sum_err = 0.0f;
			m_output = m_outmax;
		} else if (m_output < m_outmin) {
			// winds up boundaries
			m_sum_err = 0.0f;
			m_output = m_outmin;
		}

		m_lastInput = input;

		lastNs = System.nanoTime();
		return m_output;
	}

	public void reset_I() {
	}

	public void SetTunings(float Kp, float Ki, float Kd) {
		this.kp = Kp;
		this.ki = Ki;
		this.kd = Kd;
		 m_err = 0;
		  m_last_err=0;
		  m_sum_err = 0;
		  m_ddt_err = 0;
		  m_lastInput= 0;
		  m_outmax =  350;
		  m_outmin = -350;
	}

	private float constrain_(float value, float range) {
		if (value > range)
			value = range;
		else if (value < -range)
			value = -range;
		return value;
	}
}

package pilot.obss.com.autopilot.util;

import java.util.Date;

public class PIDController {
	private long _last_t = 0;
	private float _last_derivative = 0;
	private float _last_error = 0;
	private float _integrator = 0;
	private float _kp = 0;
	private float _kd = 0;
	private float _ki = 0;
	private float _fCut = 20;
	private float _imax = 50;

	public double compute(float error, float scaler) {
		long tnow = new Date().getTime();
		long dt = tnow - _last_t;
		float output = 0;
		float delta_time;

		if (_last_t == 0 || dt > 1000) {
			dt = 0;
			// if this PID hasn't been used for a full second then zero
			// the intergator term. This prevents I buildup from a
			// previous fight mode from causing a massive return before
			// the integrator gets a chance to correct itself
//			reset_I();
		}
		_last_t = tnow;

		delta_time = (float) dt / 1000.0f; 
		// Compute proportional component
		
		output += error * _kp;
		// Compute derivative component if time has elapsed
		if ((dt > 2)) {
			float derivative;

			// if (_last_derivative == 0) {
			// // we've just done a reset, suppress the first derivative
			// // term as we don't want a sudden change in input to cause
			// // a large D output change
			// derivative = 0;
			// _last_derivative = 0;
			// } else {
			derivative = (error - _last_error) / delta_time;
			// }

			// discrete low pass filter, cuts out the
			// high frequency noise that can drive the controller crazy
			float RC = 1f / (2f * new Double(Math.PI).floatValue() * _fCut);
			
			derivative = _last_derivative + ((delta_time / (RC + delta_time)) * (derivative - _last_derivative));
			// update state
			_last_error = error;
			_last_derivative = derivative;

			// add in derivative component
			output += _kd * derivative;
		}
		

		// scale the P and D components
		output *= scaler;

		// Compute integral component if time has elapsed
		if ((Math.abs(_ki) > 0) && (dt > 0)) {
			_integrator += (error * _ki)* scaler * delta_time ;  
			if (_integrator < -_imax) {
				_integrator = -_imax;
			} else if (_integrator > _imax) {
				_integrator = _imax;
			}
			output += _integrator;
		}

		return output;
	}

	public void reset_I() {
		_integrator = 0;
		// we use NAN (Not A Number) to indicate that the last derivative value
		// is not valid
	}

	public void SetTunings(float Kp, float Ki, float Kd) {
		_kp = Kp;
		_ki = Ki;
		_kd = Kd;
	}
}

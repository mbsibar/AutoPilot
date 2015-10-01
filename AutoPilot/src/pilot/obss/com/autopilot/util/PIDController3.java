package pilot.obss.com.autopilot.util;


public class PIDController3 {
	private long _last_t = 0;
	private float _last_derivative = 0;
	private float _last_error = 0;
	private float pid_i_mem_roll = 0;
	private float _kp = 0f;
	private float _kd = 0f;
	private float _ki = 0f;
	private float _imax = 300;
	
	long lastNanoTime = 0;

	public double compute(float error, float dt) {
		dt = (System.nanoTime() - lastNanoTime)/1000000.0f / 2f;
		lastNanoTime = System.nanoTime();
		float output = 0;

		float pid_error_temp = error;
		pid_i_mem_roll += _ki * pid_error_temp; 
		
		if (pid_i_mem_roll > _imax) {
			pid_i_mem_roll = _imax;
		} else if (pid_i_mem_roll < -_imax) {
			pid_i_mem_roll = -_imax;
		}
		//pid_i_mem_roll = pid_i_mem_roll * dt;
		
		output = _kp * pid_error_temp + pid_i_mem_roll + (_kd * (pid_error_temp - _last_derivative));// /dt
		
		if (output > _imax) {
			output = _imax;
		} else if (output < -_imax) {
			output = -_imax;
		}
		_last_derivative = pid_error_temp;
		
		return output;
	}

	public void reset_I() {
		pid_i_mem_roll = 0;
		_last_derivative = 0;
	}

	public void SetTunings(float Kp, float Ki, float Kd, float imax) {
		_kp = Kp;
		_ki = Ki;
		_kd = Kd;
		this._imax = imax;
	}
}

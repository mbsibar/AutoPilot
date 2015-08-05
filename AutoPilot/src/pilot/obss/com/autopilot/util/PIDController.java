package pilot.obss.com.autopilot.util;

import java.util.Date;

public class PIDController {
	double myOutput = 0;
	double mySetPoint = 0;
	double sampleTime = 100;
	double lastTime = 0;
	double lastInput = 0;

	double outMax = 0;
	double outMin = 0;

	int iSize = 10000;
	double[] ITerm = new double[iSize];;
	int it = 0;

	double kp = 0;
	double ki = 0;
	double kd = 0;

	public PIDController(double kP, double kI, double kD) {
		this.kp = kP;
		this.ki = kI;
		this.kd = kD;
		lastTime = new Date().getTime() - sampleTime;
	}

	public void addToITerm(double error){
		ITerm[it] = error;
		it++;
		if(it>=iSize){
			it = 0;
		}
	}
	
	public double getISum(){
		double sum = 0;
		for(int i = 0; i<iSize; i++){
			sum += ITerm[i];
		}
		return sum;
	}
	
	public double compute(double myInput) {

		double now = new Date().getTime();
		double timeChange = (now - lastTime);

		if (timeChange >= sampleTime) {
			double input = myInput;
			double error = mySetPoint - input;
			addToITerm((ki * error));
			
			double ITermSum = getISum();
			if (ITermSum > outMax)
				ITermSum = outMax;
			else if (ITermSum < outMin)
				ITermSum = outMin;
			double dInput = (input - lastInput);

			/* Compute PID Output */
			double output = kp * error + ITermSum - kd * dInput;

			if (output > outMax)
				output = outMax;
			else if (output < outMin)
				output = outMin;
			myOutput = output;

			/* Remember some variables for next time */
			lastInput = input;
			lastTime = now;
		}
		return myOutput;
	}

	public void SetTunings(double Kp, double Ki, double Kd) {
		if (Kp < 0 || Ki < 0 || Kd < 0)
			return;

		double SampleTimeInSec = ((double) sampleTime) / 1000;
		kp = Kp;
		ki = Ki * SampleTimeInSec;
		kd = Kd / SampleTimeInSec;

	}
	
	

	public void setSampleTime(int newSampleTime) {
		if (newSampleTime > 0) {
			double ratio = (double) newSampleTime / (double) sampleTime;
			ki *= ratio;
			kd /= ratio;
			sampleTime = (long) newSampleTime;
		}
	}

	public void setOutputLimits(double min, double max) {
		if (min >= max)
			return;
		outMin = min;
		outMax = max;

		if (myOutput > outMax)
			myOutput = outMax;
		else if (myOutput < outMin)
			myOutput = outMin;

//		if (ITerm > outMax)
//			ITerm = outMax;
//		else if (ITerm < outMin)
//			ITerm = outMin;
	}

	public void initialize() {
//		ITerm = myOutput;
		lastInput = 0;
//		if (ITerm > outMax)
//			ITerm = outMax;
//		else if (ITerm < outMin)
//			ITerm = outMin;
	}

	public double GetKp() {
		return kp;
	}

	public double getKi() {
		return ki;
	}

	public double getKd() {
		return kd;
	}

	public void setSetPoint(double setpoint) {
		this.mySetPoint = setpoint;
	}

}

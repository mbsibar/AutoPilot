package pilot.obss.com.autopilot.util;//----------------------------------------------------------------------------

import java.util.Date;
// Copyright (c) FIRST 2008. All Rights Reserved.
// Open Source Software - may be modified and shared by FRC teams. The code
// must be accompanied by the FIRST BSD license file in the root directory of
// the project.
//
// File: FL_PIDController.java
//
// Description: This PIDCOntroller class is a port from the FIRST PID Controller
//              task. This port removes the additional thread overhead but it
//              must run within a periodic task (not a continous task) to work
//              correctly.
//
// Lead: Mark
// ----------------------------------------------------------------------------

public class PIDController {

	private int MANUAL = 0;
	private int AUTOMATIC = 1;
	private long lastTime;
	private double Input, Output, Setpoint;
	private double ITerm, lastInput;
	private double kp, ki, kd;
	private int SampleTime = 1; // 1 ms
	private double outMin, outMax;
	private boolean inAuto = false;

	public PIDController(double Kp, double Ki, double Kd) {
		double SampleTimeInSec = ((double) SampleTime) / 1000;
		kp = Kp;
		ki = Ki * SampleTimeInSec;
		kd = Kd / SampleTimeInSec;
		Setpoint = 0;
	}

	public double Compute(double input) {
		this.Input = input;
		if (!inAuto)
			return 0;
		long now = new Date().getTime();
		long timeChange = (now - lastTime);
		if (timeChange >= SampleTime) {
			/* Compute all the working error variables */
			double error = Setpoint - Input;
			ITerm += (ki * error);
			if (ITerm > outMax)
				ITerm = outMax;
			else if (ITerm < outMin)
				ITerm = outMin;
			double dInput = (Input - lastInput);

			/* Compute PID Output */
			Output = kp * error + ITerm - kd * dInput;
			if (Output > outMax)
				Output = outMax;
			else if (Output < outMin)
				Output = outMin;

			/* Remember some variables for next time */
			lastInput = Input;
			lastTime = now;
		}
		return Output;
	}

	public void SetTunings(double Kp, double Ki, double Kd) {
		double SampleTimeInSec = ((double) SampleTime) / 1000;
		kp = Kp;
		ki = Ki * SampleTimeInSec;
		kd = Kd / SampleTimeInSec;
	}

	public void SetSampleTime(int NewSampleTime) {
		if (NewSampleTime > 0) {
			double ratio = (double) NewSampleTime / (double) SampleTime;
			ki *= ratio;
			kd /= ratio;
			SampleTime = NewSampleTime;
		}
	}

	public void SetOutputLimits(double Min, double Max) {
		if (Min > Max)
			return;
		outMin = Min;
		outMax = Max;

		if (Output > outMax)
			Output = outMax;
		else if (Output < outMin)
			Output = outMin;

		if (ITerm > outMax)
			ITerm = outMax;
		else if (ITerm < outMin)
			ITerm = outMin;
	}

	public void SetMode(int Mode) {
		boolean newAuto = (Mode == AUTOMATIC);
		if (newAuto && !inAuto) { /* we just went from manual to auto */
			Initialize();
		}
		inAuto = newAuto;
	}

	public void Initialize() {
		lastInput = Input;
		ITerm = Output;
		if (ITerm > outMax)
			ITerm = outMax;
		else if (ITerm < outMin)
			ITerm = outMin;
	}
	
	public void setSetPoint(double setPoint){
		this.Setpoint = setPoint;
	}

}
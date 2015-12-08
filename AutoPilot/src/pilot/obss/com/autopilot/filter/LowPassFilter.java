package pilot.obss.com.autopilot.filter;

public class LowPassFilter {

	private float cutoff_freq;
	private float sample_freq;
	private double b0;
	private float b1;
	private float b2;
	private double a1;
	private double a2;
	private float _delay_element_2;
	private float _delay_element_1;

	public LowPassFilter(float sample_freq, float cutoff_freq) {
		set_cutoff_frequency(sample_freq, cutoff_freq);
	}

	// change parameters
	public void set_cutoff_frequency(float sample_freq, float cutoff_freq) {
		this.cutoff_freq = cutoff_freq;
		this.sample_freq = sample_freq;

		Float fr = sample_freq / cutoff_freq;
		Double ohm = Math.atan(Math.PI / fr);
		float c = (float) (1.0f + 2.0f * Math.cos(Math.PI / 4.0f) * ohm + ohm * ohm);

		this.b0 = ohm * ohm / c;
		this.b1 = (float) (2.0f * this.b0);
		this.b2 = (float) this.b0;
		this.a1 = 2.0f * (ohm * ohm - 1.0f) / c;
		this.a2 = (1.0f - 2.0f * Math.cos(Math.PI / 4.0f) * ohm + ohm * ohm) / c;
	}

	public double apply(double sample) {
		if (cutoff_freq == 0 || sample_freq == 0) {
			return sample;
		}

		float delay_element_0 = (float) (sample - _delay_element_1 * a1 - _delay_element_2 * a2);
		float output = (float) (delay_element_0 * b0 + _delay_element_1 * b1 + _delay_element_2 * b2);

		_delay_element_2 = _delay_element_1;
		_delay_element_1 = delay_element_0;

		return output;
	}

}

package pilot.obss.com.autopilot.util.types;

/**
 * Created by burak on 19.3.2015.
 */
public abstract class AlgorithmObject {

    public abstract void addIntegral(float increment);

    public abstract void setDerivative(float derivative);

    public abstract float getDerivative();

    public abstract float getIntegralSum();

    public abstract void setPreError(float error);

    public abstract float getPreError();

    public abstract float getTotal(float error);
}

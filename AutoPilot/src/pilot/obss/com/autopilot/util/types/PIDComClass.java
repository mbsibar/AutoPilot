package pilot.obss.com.autopilot.util.types;

/**
 * Created by burak on 24.4.2015.
 */
public class PIDComClass {
    float p = 0.3f;
    float i = 0.3f;
    float d = 0.1f;

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
}

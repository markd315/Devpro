
public interface Drivable {
    void gas();
    void gas(double x);
    void brake();
    void brake(double x);
    double getOdometer();
    boolean checkForCrash();
    void handleCrash(String x);
}

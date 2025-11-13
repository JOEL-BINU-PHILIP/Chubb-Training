// A car model to store the details
// for a specific car model, including its price and engine size.

public class CarModel {
    
    private double exShowroomPrice;
    private int engineCC;

    public CarModel(double exShowroomPrice, int engineCC) {
        this.exShowroomPrice = exShowroomPrice;
        this.engineCC = engineCC;
    }

    public double getExShowroomPrice() {
        return exShowroomPrice;
    }

    public int getEngineCC() {
        return engineCC;
    }
}
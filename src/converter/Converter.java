package converter;

public class Converter {

    private String unitName;

    public Converter() {
        this.unitName = "Length Converter";
    }

    public Converter(String unitName) {
        this.unitName = unitName;
    }

    public double toMeters(int cm) {
        return cm / 100.0;
    }

    public double toMeters(double mm) {
        return mm / 1000.0;
    }

    public double toMeters(int km, int m) {
        return km * 1000.0 + m;
    }

    double convertWithFactor(double value, double factor) {
        return value * factor;
    }

    protected double validateResult(double result) {
        return result;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    @Override
    public String toString() {
        return "Converter: " + unitName;
    }

    public String description() {
        return "Base Converter - " + unitName;
    }
}
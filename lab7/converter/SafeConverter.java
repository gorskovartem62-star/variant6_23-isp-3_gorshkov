package converter;

public class SafeConverter extends Converter {

    public SafeConverter() {
        super("Safe Length Converter");
    }

    public SafeConverter(String unitName) {
        super(unitName);
    }

    @Override
    public double toMeters(double mm) {
        double result = super.toMeters(mm);
        return clampResult(result);
    }

    private double clampResult(double value) {
        if (value < 0) return 0;
        if (value > 1000) return 1000;
        return value;
    }

    @Override
    protected double validateResult(double result) {
        System.out.println("Validating result in SafeConverter...");
        return clampResult(result);
    }

    @Override
    public String toString() {
        return "SafeConverter: " + getUnitName() + " (with result clamping 0-1000)";
    }

    @Override
    public String description() {
        return "SafeConverter (child class) - " + getUnitName();
    }
}
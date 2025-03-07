package domain;

public abstract class Equipment {
    protected int serial_number;
    protected boolean regular_maintenance;
    Equipment(int serial_number, boolean regular_maintenance) {
        if(serial_number < 0)
            throw new IllegalArgumentException("Serial number cannot be negative");
        this.serial_number = serial_number;
        this.regular_maintenance = regular_maintenance;
    }
    public int getSerial_number() {
        return serial_number;
    }
    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }
    public boolean isregular_maintenance() {
        return regular_maintenance;
    }
    public void setregular_maintenance(boolean regular_maintenance) {
        this.regular_maintenance = regular_maintenance;
    }
    public abstract double computePrice();

    @Override
    public String toString() {
        return "serial_number=" + serial_number +
                ", regular_maintenance=" + regular_maintenance +
                ", price=" + computePrice() +
                '}';
    }
}

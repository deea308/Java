package domain;

public class CardioMachine extends Equipment {
    private int resistance;

    public CardioMachine(int serial_number, boolean regular_maintenance, int resistance) {
        super(serial_number, regular_maintenance);
        this.resistance=resistance;
    }
    public int getResistance() {
        return resistance;
    }
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    @Override
    public double computePrice() {
        double price = 0;
        if(resistance<10)
            price=500;
        if(resistance>10)
            price=200;
        return price;
    }

    @Override
    public String toString() {
        return "CardioMachine{" +
                "resistance=" + resistance +
                super.toString() +
                '}';
    }
}

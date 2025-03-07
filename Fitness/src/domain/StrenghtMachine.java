package domain;

public class StrenghtMachine extends Equipment {
    private int motion_lvl;
    private String muscle_group;

    public StrenghtMachine(int serial_number, boolean regular_maintenance, int motion_lvl, String muscle_group) {
        super(serial_number, regular_maintenance);
        if(muscle_group=="legs" || muscle_group=="arms" || muscle_group=="core")
            this.muscle_group = muscle_group;
        else throw new IllegalArgumentException("Invalid muscle group");
        this.motion_lvl = motion_lvl;

    }
    public int getMotion_lvl() {
        return motion_lvl;
    }
    public String getMuscle_group() {
        return muscle_group;
    }
    public void setMotion_lvl(int motion_lvl) {
        this.motion_lvl = motion_lvl;
    }
    public void setMuscle_group(String muscle_group) {
        this.muscle_group = muscle_group;
    }

    @Override
    public double computePrice() {
        double price=0;
        if(muscle_group=="legs" || muscle_group=="arms")
            price=600;
        else price=800;
        return price;
    }

    @Override
    public String toString() {
        return "StrenghtMachine{" +
                "motion_lvl= " + motion_lvl +
                ", muscle_group= " + muscle_group +" "+
                super.toString()+ '}';
    }
}

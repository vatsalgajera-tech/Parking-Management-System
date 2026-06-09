public class FourWheeler extends Vehicle {

    public FourWheeler(String number, String owner, String slot) {
        super(number, owner, slot);
    }

    @Override
    public double calculateFee(long exitTime) {
        long hours = (exitTime - entryTime) / (1000 * 60 * 60);
        if (hours == 0) hours = 1;
        return hours * 20;
    }

    @Override
    public String getType() {
        return "4-Wheeler";
    }
}
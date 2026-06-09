public class TwoWheeler extends Vehicle {

    public TwoWheeler(String number, String owner, String slot) {
        super(number, owner, slot);
    }

    @Override
    public double calculateFee(long exitTime) {
        long hours = (exitTime - entryTime) / (1000 * 60 * 60);
        if (hours == 0) hours = 1;
        return hours * 10;
    }

    @Override
    public String getType() {
        return "2-Wheeler";
    }
}
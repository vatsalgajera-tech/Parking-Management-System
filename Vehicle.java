import java.text.SimpleDateFormat;
import java.util.Date;

public class Vehicle {
    protected String vehicleNumber;
    protected String ownerName;
    protected long entryTime;
    protected String slotNumber;

    public Vehicle(String vehicleNumber, String ownerName, String slotNumber) {
        this.vehicleNumber = vehicleNumber;
        this.ownerName = ownerName;
        this.slotNumber = slotNumber;
        this.entryTime = System.currentTimeMillis();
    }

    public double calculateFee(long exitTime) {
        return 0;
    }

    public String getType() {
        return "Unknown";
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public long getEntryTimeRaw() {
        return entryTime;
    }

    public String getEntryTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        return sdf.format(new Date(entryTime));
    }
}
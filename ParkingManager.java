
import java.io.*;
import java.util.*;

public class ParkingManager {

    private final HashMap<String, Vehicle> parkingMap = new HashMap<>();
    private Queue<String> slots2W = new LinkedList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
    private Queue<String> slots4W = new LinkedList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));

    private int current2W = 0;
    private int current4W = 0;

    private double totalEarnings = 0;
    private int totalVehiclesServed = 0;

    public void parkVehicle(int type, String number, String owner) throws Exception {

        if (number.trim().isEmpty() || owner.trim().isEmpty()) {
            throw new Exception("Vehicle number or owner name cannot be empty.");
        }

        if (parkingMap.containsKey(number)) {
            throw new Exception("Vehicle already parked!");
        }

        switch (type) {
            case 1 -> {
                if (slots2W.isEmpty()) {
                    throw new Exception("No space available for Two-Wheelers!");
                }

                String slot = slots2W.poll();
                parkingMap.put(number, new TwoWheeler(number, owner, slot));
                current2W++;
            }
            case 2 -> {
                if (slots4W.isEmpty()) {
                    throw new Exception("No space available for Four-Wheelers!");
                }

                String slot = slots4W.poll();
                parkingMap.put(number, new FourWheeler(number, owner, slot));
                current4W++;
            }
            default ->
                throw new Exception("Invalid vehicle type! Please enter 1 or 2.");
        }
    }

    public void removeVehicle(String number) throws Exception {

        if (!parkingMap.containsKey(number)) {
            throw new Exception("Vehicle not found!");
        }

        Vehicle v = parkingMap.get(number);
        long exitTime = System.currentTimeMillis();

        long duration = (exitTime - v.getEntryTimeRaw()) / (1000 * 60 * 60);
        if (duration == 0) {
            duration = 1;
        }

        double fee = v.calculateFee(exitTime);
        totalEarnings += fee;
        totalVehiclesServed++;

        if (v instanceof TwoWheeler) {
            slots2W.add(v.getSlotNumber());
            current2W--;
        } else {
            slots4W.add(v.getSlotNumber());
            current4W--;
        }

        parkingMap.remove(number);

        String exitTimeStr = new java.text.SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                .format(new java.util.Date(exitTime));

        System.out.println("\n========== PARKING RECEIPT ==========");
        System.out.println("Vehicle Number : " + v.getVehicleNumber());
        System.out.println("Owner Name     : " + v.getOwnerName());
        System.out.println("Vehicle Type   : " + v.getType());
        System.out.println("Slot Number    : " + v.getSlotNumber());
        System.out.println("------------------------------------");
        System.out.println("Entry Time     : " + v.getEntryTime());
        System.out.println("Exit Time      : " + exitTimeStr);
        System.out.println("Duration       : " + duration + " Hours");
        System.out.println("------------------------------------");
        System.out.println("Total Fee      : Rs" + fee);
        System.out.println("====================================\n");

        try (FileWriter fw = new FileWriter("receipt_" + number + ".txt")) {
            fw.write("========== PARKING RECEIPT ==========\n");
            fw.write("Vehicle Number : " + number + "\n");
            fw.write("Owner Name     : " + v.getOwnerName() + "\n");
            fw.write("Vehicle Type   : " + v.getType() + "\n");
            fw.write("Slot Number    : " + v.getSlotNumber() + "\n\n");

            fw.write("Entry Time     : " + v.getEntryTime() + "\n");
            fw.write("Exit Time      : " + exitTimeStr + "\n");
            fw.write("Duration       : " + duration + " Hours\n\n");

            fw.write("Total Fee      : Rs" + fee + "\n");
            fw.write("====================================\n");

            System.out.println("Receipt saved as: receipt_" + number + ".txt");

        } catch (IOException e) {
            System.out.println("Error saving receipt file.");
        }

        try (FileWriter report = new FileWriter("daily_report.txt", true)) {
            report.write(number + " | " + v.getType() + " | Rs" + fee + " | " + duration + " Hours\n");
        } catch (IOException e) {
            System.out.println("Error writing to daily report.");
        }
    }

    public void searchVehicle(String number) {

        Vehicle v = parkingMap.get(number);

        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        long now = System.currentTimeMillis();
        long hours = (now - v.getEntryTimeRaw()) / (1000 * 60 * 60);
        if (hours == 0) {
            hours = 1;
        }

        System.out.println("\nVehicle Found!");
        System.out.println("Vehicle No : " + v.getVehicleNumber());
        System.out.println("Owner      : " + v.getOwnerName());
        System.out.println("Type       : " + v.getType());
        System.out.println("Slot       : " + v.getSlotNumber());
        System.out.println("Entry Time : " + v.getEntryTime());
        System.out.println("Duration   : " + hours + " Hours");
    }

    public void showCount() {
        System.out.println("\nTotal Vehicles Parked : " + parkingMap.size());
        System.out.println("Two-Wheelers          : " + current2W);
        System.out.println("Four-Wheelers         : " + current4W);
    }

    public void showSlots() {
        System.out.println("\nAvailable 2-Wheeler Slots: " + slots2W);
        System.out.println("Available 4-Wheeler Slots: " + slots4W);
    }

    public void showSummary() {
        System.out.println("\n========== PARKING SUMMARY ==========");
        System.out.println("Total Vehicles Served : " + totalVehiclesServed);
        System.out.println("Total Earnings        : Rs" + totalEarnings);
        System.out.println("====================================");
    }

    public void viewVehicles() {

        if (parkingMap.isEmpty()) {
            System.out.println("No vehicles currently parked.");
            return;
        }

        System.out.println("\n--------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-12s %-8s %-20s\n",
                "Vehicle No", "Owner", "Type", "Slot", "Entry Time");
        System.out.println("--------------------------------------------------------------------------");

        for (Vehicle v : parkingMap.values()) {
            System.out.printf("%-15s %-15s %-12s %-8s %-20s\n",
                    v.getVehicleNumber(),
                    v.getOwnerName(),
                    v.getType(),
                    v.getSlotNumber(),
                    v.getEntryTime());
        }

        System.out.println("--------------------------------------------------------------------------");
    }

    public void removeAll() {

        parkingMap.clear();

        slots2W = new LinkedList<>(Arrays.asList("A1", "A2", "A3", "A4", "A5"));
        slots4W = new LinkedList<>(Arrays.asList("B1", "B2", "B3", "B4", "B5"));

        current2W = 0;
        current4W = 0;

        System.out.println("All vehicles removed and slots reset.");
    }
}

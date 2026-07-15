# 🚘Parking Management System

A console-based Parking Management System developed using Java. The application allows users to manage vehicle parking records efficiently through a menu-driven interface.

## Features

- Park a vehicle
- Remove a parked vehicle
- View all parked vehicles
- Search vehicle by number
- Show total parked vehicles
- View available parking slots
- Display parking summary
- Remove all vehicles
- Generate parking receipts on vehicle exit
- Append daily report entries for each removed vehicle
- Input validation and exception handling

## Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Collections Framework
- Exception Handling

## Project Structure

- `Main.java` — menu-driven user interface
- `ParkingManager.java` — parking operations and business logic
- `Vehicle.java` — base vehicle class with entry time and slot details
- `TwoWheeler.java` — two-wheeler parking fee calculation
- `FourWheeler.java` — four-wheeler parking fee calculation

## How It Works

The application manages parking in two categories:

- Two-Wheelers: slots `A1` to `A5`
- Four-Wheelers: slots `B1` to `B5`

When a vehicle is parked, it is stored in memory with its entry time and assigned slot.
When a vehicle is removed, the system calculates parking duration, fees, and saves a receipt.

## Exit and Input Handling

The menu accepts a choice between `1` and `9`.
Choosing `9` exits the program cleanly and closes the scanner resource.

## Receipt Details

When a vehicle is removed, the application generates a receipt file named:

- `receipt_<vehicleNumber>.txt`

Example for vehicle number `GJ-03-CP-0301`:

```text
========== PARKING RECEIPT ==========
Vehicle Number : GJ-03-CP-0301
Owner Name     : John Doe
Vehicle Type   : 4-Wheeler
Slot Number    : B2

Entry Time     : 09-06-2026 14:25:12
Exit Time      : 09-06-2026 16:30:05
Duration       : 2 Hours

Total Fee      : Rs40
====================================
```

The receipt includes:

- Vehicle number
- Owner name
- Vehicle type
- Assigned slot
- Entry time
- Exit time
- Duration in hours
- Total fee

## Daily Report

Each removed vehicle also writes an entry to `daily_report.txt` in append mode.
One line is added for every removal, for example:

```text
GJ-03-CP-0301 | 4-Wheeler | Rs40 | 2 Hours
```

This file accumulates the day's transaction summary for review.

## How to Run

Compile:

```bash
javac *.java
```

Run:

```bash
java Main
```

## Learning Outcomes

This project demonstrates:

- Classes and Objects
- Inheritance
- Polymorphism
- Collections
- Exception Handling
- Menu-Driven Applications

## Contributor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/vatsalgajera-tech">
        <img src="https://github.com/vatsalgajera-tech.png" width="80" style="border-radius:50%"/><br/>
        <b>Vatsal Gajera</b>
      </a><br/>
      <sub>JAVA · Console App</sub>
    </td>
  </tr>
</table>

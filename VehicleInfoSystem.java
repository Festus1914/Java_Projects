/**
 * Vehicle Information System
 *
 * This program demonstrates the use of interfaces to enforce consistency among different vehicle types.
 * It includes classes for Car, Motorcycle, and Truck, which implement interfaces to provide functionality
 * for retrieving and storing vehicle information. The main program allows user interaction to create
 * and display vehicle details.
 */

 import java.util.Scanner;

 // Interface for general vehicle attributes
 interface Vehicle {
     String getMake();
     String getModel();
     int getYearOfManufacture();
 }
 
 // Interface for additional car-specific attributes
 interface CarVehicle {
     void setNumberOfDoors(int doors);
     int getNumberOfDoors();
     void setFuelType(String fuelType);
     String getFuelType();
 }
 
 // Interface for additional motorcycle-specific attributes
 interface MotorVehicle {
     void setNumberOfWheels(int wheels);
     int getNumberOfWheels();
     void setMotorcycleType(String type);
     String getMotorcycleType();
 }
 
 // Interface for additional truck-specific attributes
 interface TruckVehicle {
     void setCargoCapacity(double tons);
     double getCargoCapacity();
     void setTransmissionType(String type);
     String getTransmissionType();
 }
 
 // Implementation of a car
 class Car implements Vehicle, CarVehicle {
     private String make;
     private String model;
     private int year;
     private int numberOfDoors;
     private String fuelType;
 
     public Car(String make, String model, int year) {
         this.make = make;
         this.model = model;
         this.year = year;
     }
 
     @Override
     public String getMake() {
         return make;
     }
 
     @Override
     public String getModel() {
         return model;
     }
 
     @Override
     public int getYearOfManufacture() {
         return year;
     }
 
     @Override
     public void setNumberOfDoors(int doors) {
         this.numberOfDoors = doors;
     }
 
     @Override
     public int getNumberOfDoors() {
         return numberOfDoors;
     }
 
     @Override
     public void setFuelType(String fuelType) {
         this.fuelType = fuelType;
     }
 
     @Override
     public String getFuelType() {
         return fuelType;
     }
 }
 
 // Implementation of a motorcycle
 class Motorcycle implements Vehicle, MotorVehicle {
     private String make;
     private String model;
     private int year;
     private int numberOfWheels;
     private String motorcycleType;
 
     public Motorcycle(String make, String model, int year) {
         this.make = make;
         this.model = model;
         this.year = year;
     }
 
     @Override
     public String getMake() {
         return make;
     }
 
     @Override
     public String getModel() {
         return model;
     }
 
     @Override
     public int getYearOfManufacture() {
         return year;
     }
 
     @Override
     public void setNumberOfWheels(int wheels) {
         this.numberOfWheels = wheels;
     }
 
     @Override
     public int getNumberOfWheels() {
         return numberOfWheels;
     }
 
     @Override
     public void setMotorcycleType(String type) {
         this.motorcycleType = type;
     }
 
     @Override
     public String getMotorcycleType() {
         return motorcycleType;
     }
 }
 
 // Implementation of a truck
 class Truck implements Vehicle, TruckVehicle {
     private String make;
     private String model;
     private int year;
     private double cargoCapacity;
     private String transmissionType;
 
     public Truck(String make, String model, int year) {
         this.make = make;
         this.model = model;
         this.year = year;
     }
 
     @Override
     public String getMake() {
         return make;
     }
 
     @Override
     public String getModel() {
         return model;
     }
 
     @Override
     public int getYearOfManufacture() {
         return year;
     }
 
     @Override
     public void setCargoCapacity(double tons) {
         this.cargoCapacity = tons;
     }
 
     @Override
     public double getCargoCapacity() {
         return cargoCapacity;
     }
 
     @Override
     public void setTransmissionType(String type) {
         this.transmissionType = type;
     }
 
     @Override
     public String getTransmissionType() {
         return transmissionType;
     }
 }
 
 // Main program for user interaction
 public class VehicleInfoSystem {
     public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
 
         System.out.println("Select Vehicle Type:");
         System.out.println("1. Car\n2. Motorcycle\n3. Truck");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline
 
         switch (choice) {
             case 1:
                 System.out.println("Enter Make:");
                 String carMake = scanner.nextLine();
                 System.out.println("Enter Model:");
                 String carModel = scanner.nextLine();
                 System.out.println("Enter Year of Manufacture:");
                 int carYear = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
 
                 Car car = new Car(carMake, carModel, carYear);
 
                 System.out.println("Enter Number of Doors:");
                 car.setNumberOfDoors(scanner.nextInt());
                 scanner.nextLine(); // Consume newline
                 System.out.println("Enter Fuel Type (Petrol/Diesel/Electric):");
                 car.setFuelType(scanner.nextLine());
 
                 System.out.println("\nVehicle Details:");
                 System.out.println("Type: Car");
                 System.out.println("Make: " + car.getMake());
                 System.out.println("Model: " + car.getModel());
                 System.out.println("Year: " + car.getYearOfManufacture());
                 System.out.println("Number of Doors: " + car.getNumberOfDoors());
                 System.out.println("Fuel Type: " + car.getFuelType());
                 break;
 
             case 2:
                 System.out.println("Enter Make:");
                 String motoMake = scanner.nextLine();
                 System.out.println("Enter Model:");
                 String motoModel = scanner.nextLine();
                 System.out.println("Enter Year of Manufacture:");
                 int motoYear = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
 
                 Motorcycle motorcycle = new Motorcycle(motoMake, motoModel, motoYear);
 
                 System.out.println("Enter Number of Wheels:");
                 motorcycle.setNumberOfWheels(scanner.nextInt());
                 scanner.nextLine(); // Consume newline
                 System.out.println("Enter Motorcycle Type (Sport/Cruiser/Off-road):");
                 motorcycle.setMotorcycleType(scanner.nextLine());
 
                 System.out.println("\nVehicle Details:");
                 System.out.println("Type: Motorcycle");
                 System.out.println("Make: " + motorcycle.getMake());
                 System.out.println("Model: " + motorcycle.getModel());
                 System.out.println("Year: " + motorcycle.getYearOfManufacture());
                 System.out.println("Number of Wheels: " + motorcycle.getNumberOfWheels());
                 System.out.println("Motorcycle Type: " + motorcycle.getMotorcycleType());
                 break;
 
             case 3:
                 System.out.println("Enter Make:");
                 String truckMake = scanner.nextLine();
                 System.out.println("Enter Model:");
                 String truckModel = scanner.nextLine();
                 System.out.println("Enter Year of Manufacture:");
                 int truckYear = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
 
                 Truck truck = new Truck(truckMake, truckModel, truckYear);
 
                 System.out.println("Enter Cargo Capacity (in tons):");
                 truck.setCargoCapacity(scanner.nextDouble());
                 scanner.nextLine(); // Consume newline
                 System.out.println("Enter Transmission Type (Manual/Automatic):");
                 truck.setTransmissionType(scanner.nextLine());
 
                 System.out.println("\nVehicle Details:");
                 System.out.println("Type: Truck");
                 System.out.println("Make: " + truck.getMake());
                 System.out.println("Model: " + truck.getModel());
                 System.out.println("Year: " + truck.getYearOfManufacture());
                 System.out.println("Cargo Capacity: " + truck.getCargoCapacity() + " tons");
                 System.out.println("Transmission Type: " + truck.getTransmissionType());
                 break;
 
             default:
                 System.out.println("Invalid choice. Exiting.");
         }
 
         scanner.close();
     }
 }
 
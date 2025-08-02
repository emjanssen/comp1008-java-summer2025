package assignment3;

public class Vehicle {

    /* Private Variables */

    private String color;
    private int[] numberOfDoors;
    private boolean isGasPowered;

    /* Getters and Setters */

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int[] getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int[] numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public boolean isGasPowered() {
        return isGasPowered;
    }

    public void setGasPowered(boolean gasPowered) {
        isGasPowered = gasPowered;
    }

    /* Constructors */

    public Vehicle(String color, int[] numberOfDoors, boolean isGasPowered) {
        this.color = color;
        this.numberOfDoors = numberOfDoors;
        this.isGasPowered = isGasPowered;
    }






}

package assignment3;

public class Vehicle { // - Done

    // 1) Create 3 instance variables with unique data types and unique accessibility levels to represent the following attributes of a vehicle
    // - - - Private Variables - - - //

    private String color;
    private int numberOfDoors;
    private boolean isGasPowered;

    // 3) Create the necessary getters and setters for this class
    // - - - Getters and Setters - - - //

    public String getColor() {
        return color;
    }

    public void setColor(String color) {

        // validation login //
        this.color = color;
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {

        // validation login //
        this.numberOfDoors = numberOfDoors;
    }

    public boolean getIsGasPowered() {
        return isGasPowered;
    }

    public void setIsGasPowered(boolean isGasPowered) {

        // validation login //
        this.isGasPowered = isGasPowered;
    }

    // 2) Create 4 constructors with parameters + the default constructor
    // - - - Constructors - - - //

    // default values for Vehicle object fields
    // making them static and final and using caps to indicate constant value
    public static final String DEFAULT_COLOUR = "black";
    public static final int DEFAULT_NUMBER_OF_DOORS = 4;
    public static final boolean DEFAULT_IS_GAS_POWERED = true;

    // no parameters
    public Vehicle() {

        this.color = DEFAULT_COLOUR;
        this.numberOfDoors = DEFAULT_NUMBER_OF_DOORS;
        this.isGasPowered = DEFAULT_IS_GAS_POWERED;
    }

    // colour parameter
    public Vehicle(String color) {
        this.color = color;
        this.numberOfDoors = DEFAULT_NUMBER_OF_DOORS;
        this.isGasPowered = DEFAULT_IS_GAS_POWERED;
    }

    // numberOfDoors parameter
    public Vehicle(int numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
        this.color = DEFAULT_COLOUR;
        this.isGasPowered = DEFAULT_IS_GAS_POWERED;
    }

    // isGasPowered parameter
    public Vehicle(boolean isGasPowered) {
        this.isGasPowered = isGasPowered;
        this.color = DEFAULT_COLOUR;
        this.numberOfDoors = DEFAULT_NUMBER_OF_DOORS;
    }

    // all parameters
    public Vehicle(String color, int numberOfDoors, boolean isGasPowered) {
        this.color = color;
        this.numberOfDoors = numberOfDoors;
        this.isGasPowered = isGasPowered;
    }

    // - - - Methods - - - //

    //6) Create a method named isEcoFriendly. It has no parameters. It determines whether the Vehicle two-doored and is not gas powered.
    // can call this on a Vehicle object and see if vehicle is eco-friendly as per method declaration
    public boolean isEcoFriendly() {

        if ((getNumberOfDoors() != 2) || (getIsGasPowered())) {
            return false;
        }
        else {
            return true;
        }
    }

    // - - -  Overrides - - - //

    // 4) Override (not Overload) the equals() method so it evaluates two Vehicle objects and returns true if and only if (iff) the two Vehicle objects are equal is value
    // overriding the equals() function so we can compare specific Object types
    @Override
    public boolean equals(Object object) {
        if (this == object)
        // if the objects are the same (i.e. both references point to the same object in memory), return true and exit function
        {
            return true;
        }

        // if the object is null, return false and exit function
        if (object == null) {
            return false;
        }

        // if the object we're comparing isn't a Vehicle object
        // return false to prevent ClassCastException exception
        // can pass in any kind of object to equals(); we need to make sure we're actually comparing two Vehicle objects
        if (getClass() != object.getClass()) {
            return false;
        }

        // if we haven't exited already due to true and false returns above
        // equals(Object object) passes in any type of object; code doesn't know we want a Vehicle object
        // we need to cast the object being passed in to Vehicle so code can compare Vehicle objects properly
        Vehicle otherVehicle = (Vehicle) object;

        // return a comparison for all fields in the constructor
        // we know they're not the exact same object, but they can still have all the same field attributes
        return this.color.equals(otherVehicle.color) && this.numberOfDoors == otherVehicle.numberOfDoors && this.isGasPowered == otherVehicle.isGasPowered;
    }

    // 5) Override the toString method to summarize all instance variables of the class
    // override toString() to print out a summary of the Vehicle instance
    @Override
    public String toString() {
        return "Vehicle color: " + color + "\nNumber of doors: " + numberOfDoors + " \nWhether the vehicle is gas-powered: " + isGasPowered;
    }
}
package assignment3;

public class Truck extends Vehicle { // - Done

    // 1) Create two additional instance variables with unique non-private accessibility levels and data types to represent the following attributes of a truck
    // - - - Public Variables - - - //

    public int numberOfSeats;
    public double trunkSpaceInFeet;

    // 2) Create 8 constructors
    // - - - Constructors - - - //

    // default values for truck class variables //
    // make them static and final (i.e. constants) so they cannot be modified
    // using caps syntax to indicate that they are constants
    public static final int DEFAULT_NUMBER_OF_SEATS = 5;
    public static final double DEFAULT_TRUNK_SPACE_IN_FEET = 0;

    // a) The default constructor
    public Truck() {
        super();
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // b) 4 constructors that mirror the constructors of the Vehicle class
    // color parameter
    public Truck(String color) {
        super(color);
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // numberOfDoors parameter
    public Truck(int numberOfDoors) {
        super(numberOfDoors);
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // isGasPowered parameter
    public Truck(boolean isGasPowered) {
        super(isGasPowered);
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // parameters: color, numberOfDoors, isGasPowered
    public Truck(String color, int numberOfDoors, boolean isGasPowered) {
        super(color, numberOfDoors, isGasPowered);
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // c) A constructor with 3 instance variables of Vehicle & seats instance variable of Truck
    public Truck(String color, int numberOfDoors, boolean isGasPowered, int numberOfSeats) {
        super(color, numberOfDoors, isGasPowered);
        this.numberOfSeats = numberOfSeats;
        this.trunkSpaceInFeet = DEFAULT_TRUNK_SPACE_IN_FEET;
    }

    // d) A constructor with 3 instance variables of Vehicle & trunk space instance variable of Truck
    public Truck(String color, int numberOfDoors, boolean isGasPowered, double trunkSpaceInFeet) {
        super(color, numberOfDoors, isGasPowered);
        this.numberOfSeats = DEFAULT_NUMBER_OF_SEATS;
        this.trunkSpaceInFeet = trunkSpaceInFeet;
    }

    // e) A constructor with 3 instance variables of Vehicle & both instance variables of Truck
    public Truck(String color, int numberOfDoors, boolean isGasPowered, int numberOfSeats, double trunkSpaceInFeet) {
        super(color, numberOfDoors, isGasPowered);
        this.numberOfSeats = numberOfSeats;
        this.trunkSpaceInFeet = trunkSpaceInFeet;
    }

    // - - - Overrides - - - //

    // 3) Override (not Overload) the equals() method so it evaluates two Truck objects and returns true if and only if (iff) the two Truck objects are equal is value
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if ((object == null) || (getClass() != object.getClass())) {
            return false;
        }
        Truck otherTruck = (Truck) object;
        return super.equals(object)                         // Compare Vehicle fields (color, doors, etc.)
                && this.numberOfSeats == otherTruck.numberOfSeats
                && this.trunkSpaceInFeet == otherTruck.trunkSpaceInFeet;
    }

    // 4) Override the toString method to summarize all instance variables of the class
    @Override
    public String toString() {
        return super.toString() + "\nNumber of seats: " + numberOfSeats + "\nTrunk space in feet: " + trunkSpaceInFeet;
    }

    //5) Override the isEcoFriendly method. In addition to its original behavior, it also determines if the Truck has at most two seats and has no trunk space.
    @Override
    public boolean isEcoFriendly() {
        // if isEcoFriendly returns true from Vehicle class, we check whether the truck has 2 or fewer seats, and check if the truck space is 0
        if (super.isEcoFriendly()) {
            // if conditions are true, overridden isEcoFriendly() function returns true
            if ((numberOfSeats <= 2) && (trunkSpaceInFeet == 0)) {
                return true;
            } else
            // if conditions are false, overridden isEcoFriendly() function returns false
            {
                return false;
            }
            // if isEcoFriendly returns false from Vehicle class, we already know Truck is not going to be eco-friendly; no need to check for seat count or truck size
        } else {
            return false;
        }
    }
}
package assignment3;

// 6) Create an abstract class named Keyboard (1 mark)
public abstract class Keyboard {

    // - - - Public Variables - - - //

    // a) instance variables (2 marks)
    // variable start as public; not using getters and setters
    public String keyboardManufacturer;
    public double keyboardLengthInInches;

    // also going to have default constant values for default constructor
    public static final String DEFAULT_MANUFACTURER = "Logitech";
    public static final double DEFAULT_LENGTH_INCHES = 17.0;

    // - - - Methods - - - //

    // b) concrete methods (2 marks)
    // methods can be called on Keyboard object once it has been instantiated; methods will read the values the object was instantiated with

    public void printKeyboardDetails() {
        System.out.println("Keyboard is manufactured by " + this.keyboardManufacturer + ".");
        System.out.println("Keyboard is " + this.keyboardLengthInInches + " inches long.");
    }

    public boolean isLargeKeyboard() {
        // choosing an arbitrary length to consider large
        if (keyboardLengthInInches >= 10.0) {
            return true;
        }
        return false;
    }

    // c) abstract methods (2 marks)
    // method details will be defined in Computer class
    // two booleans representing physical trait options for keyboards
    public abstract boolean isWireless();
    public abstract boolean hasRGBLighting();

    // d) constructors (2 marks)
    // - - - Constructors - - - //

    // no parameters, assigning default values
    public Keyboard() {
        this.keyboardManufacturer = DEFAULT_MANUFACTURER;
        this.keyboardLengthInInches = DEFAULT_LENGTH_INCHES;
    }

    // both parameters, no default values assigned
    public Keyboard(String keyboardManufacturer, double keyboardLengthInInches) {
        this.keyboardManufacturer = keyboardManufacturer;
        this.keyboardLengthInInches = keyboardLengthInInches;
    }
}

package assignment3;

// 6) Create an abstract class named Keyboard (1 mark)
public abstract class Keyboard {

    // - - - Public Variables - - - //

    // a) instance variables (2 marks)
    // variable start as public; not using getters and setters
    public String keyboardManufacturer;
    public double keyboardLengthInInches;

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
    // method details will be defined in Computer class and also make use of the Button interface
    public abstract int listenForButtonPress(int keypress);
    public abstract double countWordsPerMinute(double wordsPerMinute);

    // d) constructors (2 marks)
    // - - - Constructors - - - //

    // no parameters, assigning default values
    public Keyboard() {

        String defaultKeyboardManufacturer = "Logitech";
        double defaultKeyboardLengthInInches = 17.0;

        this.keyboardManufacturer = defaultKeyboardManufacturer;
        this.keyboardLengthInInches = defaultKeyboardLengthInInches;
    }

    // both parameters, no default values assigned
    public Keyboard(String keyboardManufacturer, double keyboardLengthInInches) {
        this.keyboardManufacturer = keyboardManufacturer;
        this.keyboardLengthInInches = keyboardLengthInInches;
    }
}

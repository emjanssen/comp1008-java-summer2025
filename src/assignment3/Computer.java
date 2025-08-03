package assignment3;

// import scanner class
import java.util.Scanner;

/*
1) Create concrete class named Computer
2) Base this class on the abstract Keyboard class (1 mark)
3) This class is to implement the Button interface (1 mark)
 */
// so we can use both Keyboard and Button functions
public class Computer extends Keyboard implements Button {

    // - - - Private Variables - - - //

    private Scanner scanner = new Scanner(System.in);
    private boolean isWireless;
    private boolean hasRGBLighting;

    // - - - Constructor - - - //

    public Computer(String keyboardManufacturer, double keyboardLengthInInches, boolean isWireless, boolean hasRGBLighting) {
        // call keyboard constructor first
        super(keyboardManufacturer, keyboardLengthInInches);
        this.isWireless = isWireless;
        this.hasRGBLighting = hasRGBLighting;
    }

    // a) Complete the interface method bodies (2 x 2 marks)

    // - - - Abstract Methods from Button Interface - - - //
    @Override
    public boolean listenForButtonPress() {
        int currentKeypress = 9;
        // simulate a keypress value; for instance, let's say keypress value is 9
        // right now, the function always return false, as we have a hardcoded currentKeypress
        // in real code, this function would take keypress via user input on the keyboard
        if (currentKeypress == DEFAULT_KEYPRESS) {
            return true;
        }
        return false;
    }

    @Override
    public int countWordsPerMinute() {
        System.out.print("Please enter your word per minute rate.");
        // this presupposes user input is actually an int; real code would have better validation/error handling
        int userInputWPM = scanner.nextInt();
        return userInputWPM;
    }

    // - - - Abstract Methods from Keyboard Class - - - //

    @Override
    // return whether computer object (i.e. keyboard) is wireless
    public boolean isWireless() {
        return this.isWireless;
    }

    @Override
    // return whether computer object (i.e. keyboard) has RGB lighting
    public boolean hasRGBLighting() {
        return this.hasRGBLighting;
    }
}

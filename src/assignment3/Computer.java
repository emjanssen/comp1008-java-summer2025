package assignment3;

/*
1) Create concrete class named Computer
2) Base this class on the abstract Keyboard class (1 mark)
3) This class is to implement the Button interface (1 mark)
 */
public class Computer extends Keyboard implements Button {

    // a) Complete the interface method bodies (2 x 2 marks)

    // - - - Abstract Methods from Button Interface - - - //

    @Override
    public boolean listenForButtonPress() {
        return false;
    }

    @Override
    public int countWordsPerMinute() {
        return 0;
    }

    // - - - Abstract Methods from Keyboard Class - - - //

    @Override
    public boolean isWireless() {
        return false;
    }

    @Override
    public boolean hasRGBLighting() {
        return false;
    }
}

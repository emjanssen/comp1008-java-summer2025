package assignment3;

// 7) Create an interface named Button (1 mark)
public interface Button {

    // - - - Public Variables - - - //

    // 8) Code two constant values (2 marks)
    // enter key as the default button press
    // ASCII/Unicode 10 = enter key
    public static final int DEFAULT_KEYPRESS = 10;
    // 0 as the default words per minute value
    public static final int DEFAULT_WORDS_PER_MINUTE = 0;

    // - - - Methods - - - //

    // 9) Code two methods (2 marks)
    // methods will be implemented in Computer class
    public boolean listenForButtonPress();
    public int countWordsPerMinute();
}

package assignment2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {

    // - - - Variables - - - //

    private int accountNumber;
    private String accountName;
    private double accountBalance;

    // - - - Constructors - - - //

    // no arguments
    public Account() {
        // default values
        String accountNameDefault = "DEF-";
        int accountNumberDefault = 11111;
        double accountBalanceDefault = 11.11;

        // set to default when we have no arguments
        this.accountNumber = accountNumberDefault;
        this.accountName = accountNameDefault;
        this.accountBalance = accountBalanceDefault;
    }

    // three arguments
    public Account(String accountName, int accountNumber, double accountBalance) {
        // default values
        String accountNameDefault = "DEF-";
        int accountNumberDefault = 11111;
        double accountBalanceDefault = 11.11;

        // if functions return true, assign new values from arguments
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;

        // if functions return false, assign default values
        if (accountName == null || !setAccountName(accountName)) {
            this.accountName = accountNameDefault;
        }

        if (!setAccountNumber(accountNumber)) {
            this.accountNumber = accountNumberDefault;
        }

        if (!setAccountBalance(accountBalance)) {
            this.accountBalance = accountBalanceDefault;
        }
    }

    // -- Helper Methods -- //

    // function purpose: validate accountName input
    // return false is any input is invalid; return true is all input is valid
    public static boolean validateAccountName(String accountName) {
        // permitted characters via regex pattern: a-z, a-Z, hyphen, whitespace; zero or more times
        String accountNameRegex = "^[a-zA-Z\\- ']*$";
        // compile into permitted pattern
        Pattern permitted = Pattern.compile(accountNameRegex);
        // additional constrains on whitespace and single quote characters, as each can only appear up to once
        // will use count values to count char instances
        int whitespaceCount = 0;
        int singleQuoteCount = 0;

        // is the string is less than four characters
        if (accountName.length() < 4) {
            System.out.println("The account name must be at least four characters.");
            System.out.println("Returning false...");
            return false;
        }

        // if the chars don't equal the permitted pattern
        if (!permitted.matcher(accountName).matches()) {
            System.out.println("Your account name contains invalid characters.");
            System.out.println("Returning false...");
            return false;
        }

        // conspiile pattern to search for whitespace chars; match that against accountName
        Matcher whitespaceMatcher = Pattern.compile("\\s").matcher(accountName);
        // while we have whitespace chars to find, run the while loop, and increment count on each loop
        while (whitespaceMatcher.find())
            whitespaceCount++;
        // if the whitespaceCount ends up being more than one, input is invalid
        if (whitespaceCount > 1) {
            System.out.println("You have included too many whitespace chars.");
            System.out.println("Returning false...");
            return false;
        }

        // same process for single quotes as with white space characters
        Matcher quoteMatcher = Pattern.compile("'").matcher(accountName);
        while (quoteMatcher.find())
            singleQuoteCount++;
        if (singleQuoteCount > 1) {
            System.out.println("You have included too many single quote mark chars.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        return true;
    }

    // function purpose: validate account number
    // return false is any input is invalid; return true is all input is valid
    public static boolean validateAccountNumber(int accountNumber) {

        // cast int to string for purpose of checking against a regex pattern
        String accountNumberAsString = Integer.toString(accountNumber);

        // required pattern: a number between 1 to 9 (so we have no leading zeroes), and then between 4-8 of numbers from 0-9; gives us 5-9 chars total
        if (!accountNumberAsString.matches("^[1-9][0-9]{4,8}$")) {
            System.out.println("Input has too many or too few digits, or has leading zeros.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is a negative
        if (accountNumber < 1) {
            System.out.println("Input is not a positive number.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        return true;
    }

    // validate accountBalance input
    // return false is any input is invalid; return true is all input is valid
    public static boolean validateAccountBalance(double accountBalance) {
        // cast accountBalance to string for purpose of comparing against regex pattern
        String valueAsString = Double.toString(accountBalance);

        /* regex pattern:
        ^               : start of string
        -?              : optional minus sign
            (               : start of group
            \\d+            : one or more digits
            (\.\d{1,2})?    : optional - decimal followed by up to two digits
            |               : OR
            \\.\d{1,2}      : decimal followed by up to two digits (no leading digits)
            )               : end of group
        $               : end of string

         matches either:
         1. whole number, with optional decimal
         2. number starting with decimal

         */
        // if balance doesn't match pattern
        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        return true;
    }

    // - - - Methods: Account Name - - - //

    public String getAccountName() {
        return this.accountName;
    }

    // function purpose: validate account name, and either update value, or return false
    public boolean setAccountName(String accountName) {

        if (validateAccountName(accountName)) {
            this.accountName = accountName;
            System.out.println("Value updated: " + this.accountName);
            return true;
        }
        return false;
    }

    // - - - Methods: Account Number - - - //

    public int getAccountNumber() {
        return this.accountNumber;
    }

    // function purpose: validate account number, and either update value, or return false
    public boolean setAccountNumber(int accountNumber) {

        if (validateAccountNumber(accountNumber)) {
            this.accountNumber = accountNumber;
            System.out.println("Value updated: " + this.accountNumber);
            return true;
        }
        return false;
    }

    // - - - Methods: Account Balance - - - //

    public double getAccountBalance() {
        return this.accountBalance;
    }

    // function purpose: validate account balance, and either update value, or return false
    public boolean setAccountBalance(double accountBalance) {

        if (validateAccountBalance(accountBalance)) {
            this.accountBalance = accountBalance;
            System.out.println("Value updated: " + this.accountBalance);
            return true;
        }
        return false;
    }

    // - - - Overrides - - - //

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        // if the objects are the same, return true and exit function
        {
            return true;
        }

        // if the object is null, return false and exit function
        if (obj == null) {
            return false;
        }

        // if the object we're comparing isn't an Account object
        // return false to prevent ClassCastException exception
        // can pass in any kind of object to equals(); need to make sure we're actually comparing two Account objects
        if (!(obj instanceof Account)) {
            return false;
        }

        // if we haven't exited already due to true and false returns above
        // equals(Object obj) passes in any type of object; code doesn't know we want an Account object
        // cast the object to Account so code can compare Account objects properly
        Account otherAccount = (Account) obj;
        if (this.accountNumber != otherAccount.accountNumber)
            return false;
        if (Double.compare(this.accountBalance, otherAccount.accountBalance) != 0)
            return false;
        if (!this.accountName.equals(otherAccount.accountName))
            return false;
        return true;

    }

    // override toString() to print out a summary of the object instance
    @Override
    public String toString() {
        return "Account Name: " + accountName + "\nAccount Number: " + accountNumber + " \nBalance: $" + String.format("%.2f", accountBalance);
    }

}

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
    }

    // three arguments
    public Account(String accountName, int accountNumber, double accountBalance) {

        // if functions return true, assign new values
        // if functions return false, assign default value

        String accountNameDefault = "DEF-";
        int accountNumberDefault = 11111;
        double accountBalanceDefault = 11.11;

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

    public static boolean validateAccountName(String accountName) {
        String accountNameRegex = "^[a-zA-Z\\- ']*$";
        Pattern permitted = Pattern.compile(accountNameRegex);
        int whitespaceCount = 0;
        int singleQuoteCount = 0;

        if (accountName.length() < 4) {
            System.out.println("The account name must be at least four characters.");
            System.out.println("Returning false...");
            return false;
        }

        if (!permitted.matcher(accountName).matches()) {
            System.out.println("Your account name contains invalid characters.");
            System.out.println("Returning false...");
            return false;
        }

        Matcher whitespaceMatcher = Pattern.compile("\\s").matcher(accountName);
        while (whitespaceMatcher.find())
            whitespaceCount++;
        if (whitespaceCount > 1) {
            System.out.println("You have included too many whitespace chars.");
            System.out.println("Returning false...");
            return false;
        }

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

    public static boolean validateAccountNumber(int accountNumber) {

        String accountNumberAsString = Integer.toString(accountNumber);

        if (!accountNumberAsString.matches("^[1-9][0-9]{4,8}$")) {
            System.out.println("Input has too many or too few digits, or has leading zeros.");
            System.out.println("Returning false...");
            return false;
        }

        if (accountNumber < 1) {
            System.out.println("Input is not a positive number.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        return true;
    }

    public static boolean validateAccountBalance(double accountBalance) {
        String valueAsString = Double.toString(accountBalance);

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
        if (!(obj instanceof Account)) {
            return false;
        }

        // if we haven't exited already due to true and false returns above
        // equals(Object obj) passes in any type of object; code doesn't know we want an Account object
        // cast the object to Account so code can compare Account objects properly
        Account otherAccount = (Account) obj;
        // compare the account numbers; return true if they match; return false if they don't match
        return this.accountNumber == otherAccount.accountNumber;
    }

    @Override
    public String toString() {
        return "Account Name: " + accountName + "\nAccount Number: " + accountNumber + " \nBalance: $" + String.format("%.2f", accountBalance);
    }

}

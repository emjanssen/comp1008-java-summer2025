package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static assignment2.Account.*;

public class Bank {

    // - - - Variables - - - //

    // BranchLocations objects with list of branch locations
    public static enum BranchLocations {
        NORTHVANCOUVER, VANCOUVER, BURNABY, RICHMOND, NEWWESTMINSTER, SURREY, DEFAULT
    }

    private String bankName;
    private BranchLocations branchLocation;
    // array list to track accounts
    private List<Account> accounts = new ArrayList<>();

    // - - - Constructors - - - //

    // no arguments
    public Bank() {
        // default values
        String defaultBankName = "BANK&DEF123";
        BranchLocations defaultBranch = BranchLocations.DEFAULT;

        // set to default when we have no arguments
        this.bankName = defaultBankName;
        this.branchLocation = defaultBranch;
    }

    // two arguments: bankName, branchLocation String object
    public Bank(String bankName, String branchLocation) {

        // default values
        String defaultBankName = "BANK&DEF123";
        BranchLocations defaultBranch = BranchLocations.DEFAULT;

        // if functions return false, assign default values
        if (bankName == null || !setBankName(bankName)) {
            this.bankName = defaultBankName;
        }

        if (!setBranchLocation(branchLocation)) {
            this.branchLocation = defaultBranch;
        }
    }

    //two arguments: bankName, branchLocation BranchLocations object
    public Bank(String bankName, BranchLocations branchLocation) {

        // default values
        String defaultBankName = "BANK&DEF123";
        BranchLocations defaultBranch = BranchLocations.DEFAULT;

        // if functions return false, assign default values
        if (!setBankName(bankName)) {
            this.bankName = defaultBankName;
        }
        if (!setBranchLocation(branchLocation)) {
            this.branchLocation = defaultBranch;
        }
    }

    // - - - Helper Methods - - - //

    // function purpose: validation bankName input, return false if any input is invalid, return true if all inputs are valid
    public static boolean validateBankName(String bankName) {
        // permitted characters via regex pattern: a-z, A-Z, &, 0-9, whitespace; zero or more times
        String bankNameRegex = "^[a-zA-Z&0-9 ]*$";
        // compile into permitted pattern
        Pattern permittedBankNamePattern = Pattern.compile(bankNameRegex);
        // additional constrains on whitespace and digits characters
        // will use count values to count char instances
        int whitespaceCount = 0;
        int digitCount = 0;
        // compile matcher patterns to search for (whitespace, digit)
        Matcher whitespaceMatcher = Pattern.compile(" ").matcher(bankName);
        Matcher digitMatcher = Pattern.compile("\\d").matcher(bankName);

        // if bank name doesn't have enough chars
        if (bankName.length() < 8) {
            System.out.println("The bank name must be at least eight characters.");
            System.out.println("Returning false...");
            return false;
        }

        // if bank name doesn't have the chars permitted via our regex pattern
        if (!permittedBankNamePattern.matcher(bankName).matches()) {
            System.out.println("The bank name contains invalid characters.");
            System.out.println("Returning false...");
            return false;
        }

        // while we have whitespace chars to find, run the while loop, and increment count on each loop
        while (whitespaceMatcher.find()) whitespaceCount++;
        // if the whitespaceCount ends up being more than one, input is invalid
        if (whitespaceCount > 1) {
            System.out.println("You have included too many whitespace characters.");
            System.out.println("Returning false...");
            return false;
        }

        // same process for digits (except digit count needs to be no more than 3)
        while (digitMatcher.find()) digitCount++;
        if (digitCount > 3) {
            System.out.println("You have included too many digits.");
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        return true;
    }

    // - - - Methods: Bank Name - - - //

    public String getBankName() {
        return bankName;
    }

    // function purpose: validate bank name; return false is any input is invalid; return true is all input is valid
    public boolean setBankName(String bankName) {

        if (validateBankName(bankName)) {
            this.bankName = bankName;
            System.out.println("Value updated: " + this.bankName);
            return true;
        }
        return false;
    }

    // - - - Methods: Branch Location - - - //

    // function purpose: validate branch location input, see if it matches list of enums
    public boolean setBranchLocation(String branchLocation) {

        if (branchLocation == null || branchLocation.trim().isEmpty()) {
            System.out.println("Branch location is null or empty. Not updating branch location. Returning false...");
            return false;
        }

        try {
            // convert branchLocation input to upper case
            // call valueOf(branchLocation) to convert branchLocation input tro string for comparison purposes
            // call that input against BranchLocations enum values
            // and see if there is a match; if there is, create Branchlocations object called location
            BranchLocations location = BranchLocations.valueOf(branchLocation.toUpperCase());
            // if that is successful, assign location object to the branchLocation value for this instance
            this.branchLocation = location;
            // print confirmation message with details
            System.out.println("Branch location set to: " + location);
            // return true
            return true;
            // if we are unable to create the location object
            // catch ensuing error and print error message with branchLocation input details
        } catch (IllegalArgumentException exception) {
            System.out.println("Invalid branch location string: " + branchLocation);
            // return false
            return false;
        }
    }

    public String getBranchLocation() {
        return this.branchLocation.toString();
    }

    // function purpose: validate whether BranchLocations object is null; update if it isn't
    public boolean setBranchLocation(BranchLocations branchLocation) {
        if (branchLocation == null) {
            System.out.println("Branch location is null. Not updating branch location. Returning false...");
            return false;
        }

        this.branchLocation = branchLocation;
        System.out.println("Branch location set to: " + branchLocation);
        return true;
    }

    // - - -  Methods: Accounts - - - //

    // validate accountNumber input, iterate over accounts list, return match if it's found, create new object if match isn't found
    public Account getAccountByNumber(int accountNumber) {

        // once input has been validated via helper function, iterate over accounts list
        if (validateAccountNumber(accountNumber)) {
            // use length (i.e. size() ) of accounts list to know how many loops to iterate
            for (int i = 0; i < accounts.size(); i++) {
                // if current iteration value of object's getAccountNumber() outcome matches the input passed into this function
                if (accounts.get(i).getAccountNumber() == accountNumber) {
                    // then we have found a matching account; we return that account
                    System.out.println("We have found your account.");
                    return accounts.get(i);
                }
            }
        }

        // if no match found, we return a new object using the default (no arguments) constructor
        System.out.println("Account not found. Returning default constructor");
        return new Account();
    }

    // same process as getAccountByNumber(), just with entire object passed in, instead of just the account number
    public boolean addAccount(Account account) {

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == account.getAccountNumber()) {
                System.out.println("An account with this number already exists: " + account.getAccountNumber());
                System.out.println("Returning false...");
                return false;
            }
        }

        System.out.println("Account number is unique. Adding to accounts list and returning true...");
        accounts.add(account);
        return true;
    }

    // validate inputs, iterate over accounts list, return match if it's found, create new object if match isn't found
    public boolean addAccount(String accountName, int accountNumber, double accountBalance) {

        if (validateAccountName(accountName) && validateAccountNumber(accountNumber) && validateAccountBalance(accountBalance)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    System.out.println("Account with this number already exists: " + accountNumber);
                    return false;
                }
            }
        }

        Account newAccount = new Account(accountName, accountNumber, accountBalance);
        accounts.add(newAccount);
        return true;
    }

    // validate input, iterate over accounts list, return match if it's found, create new object if match isn't found
    public Account viewAccount(int accountNumber) {

        if (validateAccountNumber(accountNumber)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    System.out.println("Existing account found: " + accountNumber + ". Returning existing account...");
                    return existingAccount;
                }
            }
        }
        System.out.println("Account not found. Returning default constructor...");
        return new Account();
    }

    // find account using index value
    public Account viewAccount(byte index) {
        // confirm that index argument is not less than 0, and isn't greater than our array length
        if (index < 0 || index >= accounts.size()) {
            // if index is out of range, create a new object
            System.out.println("Invalid index: " + index + ". Returning default constructor...");
            return new Account();
        }

        // if index is valid, return account at that index
        return accounts.get(index);
    }

    // validate inputs, validate the index is valid, iterate over accounts to search for a match, then update relevant arguments using setters
    public boolean modifyAccount(int accountNumber, String accountName) {

        if (validateAccountName(accountName) && validateAccountNumber(accountNumber)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    existingAccount.setAccountName(accountName);
                    existingAccount.setAccountNumber(accountNumber);
                    System.out.println("Account name updated to: " + accountName);
                    System.out.println("Account number updated to: " + accountNumber);
                    return true;
                }
            }
        }
        // return false if account not found
        System.out.println("Account not found. Returning false...");
        return false;
    }

    public boolean modifyAccount(int accountNumber, double accountBalance) {

        // validate inputs, validate the index is valid, iterate over accounts to search for a match,then update relevant arguments using setters
        if (validateAccountBalance(accountBalance) && validateAccountBalance(accountBalance)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    existingAccount.setAccountBalance(accountBalance);
                    existingAccount.setAccountNumber(accountNumber);
                    System.out.println("Account balance updated to: " + accountBalance);
                    System.out.println("Account number updated to: " + accountNumber);
                    return true;
                }
            }
        }
        // return false if account not found
        System.out.println("Account not found. Returning false...");
        return false;
    }

    // validate inputs, validate the index is valid, iterate over accounts to search for a match,then update relevant arguments using setters
    public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {

        if (validateAccountName(accountName) && validateAccountNumber(accountNumber) && validateAccountBalance(accountBalance)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    existingAccount.setAccountName(accountName);
                    existingAccount.setAccountNumber(accountNumber);
                    existingAccount.setAccountBalance(accountBalance);
                    System.out.println("Account name updated to: " + accountName);
                    System.out.println("Account number updated to: " + accountNumber);
                    System.out.println("Account balance updated to: " + accountBalance);
                    return true;
                }
            }
        }
        // return false if account not found
        System.out.println("Account not found. Returning false...");
        return false;
    }

    // - - - Methods: index - - - //

    // validate input, validate index, then either return false or update the relevant object values
    public boolean modifyAccount(byte index, String accountName) {

        if (validateAccountName(accountName)) {
            // if index is out of range, return false
            if (index < 0 || index >= accounts.size()) {
                System.out.println("Invalid index: " + index + ". Returning false...");
                return false;

            }
        }

        // if all inputs are valid, get the account at the index passed in, use setters to update relevant values
        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountName(accountName);
        System.out.println("Account name updated to: " + accountName);
        return true;
    }

    // validate input, validate index, then either return false or update the relevant object values
    public boolean modifyAccount(byte index, double accountBalance) {

        if (validateAccountBalance(accountBalance)) {
            // if index is out of range, return false
            if (index < 0 || index >= accounts.size()) {
                System.out.println("Invalid index: " + index + ". Returning false...");
                return false;
            }
        }

        // if all inputs are valid, get the account at the index passed in, use setters to update relevant value
        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountBalance(accountBalance);
        System.out.println("Account balance updated to: " + accountBalance);
        return true;
    }

    // validate input, validate index, then either return false or update the relevant object values
    public boolean modifyAccount(byte index, String accountName, double accountBalance) {

        if (validateAccountName(accountName) && validateAccountBalance(accountBalance)) {
            // if index is out of range, return false
            if (index < 0 || index >= accounts.size()) {
                System.out.println("Invalid index: " + index + ". Returning false...");
                return false;
            }
        }

        // if all inputs are valid, get the account at the index passed in, use setters to update relevant values
        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountName(accountName);
        selectedAccount.setAccountBalance(accountBalance);
        System.out.println("Account name updated to: " + accountName);
        System.out.println("Account balance updated to: " + accountBalance);
        return true;
    }

    public boolean deleteAccount(int accountNumber) {

        // validate inputs then search for account object; if match found, remove it from accounts list
        if (validateAccountNumber(accountNumber)) {
            for (int i = 0; i < accounts.size(); i++) {
                Account existingAccount = accounts.get(i);
                if (existingAccount.getAccountNumber() == accountNumber) {
                    accounts.remove(i);
                    System.out.println("Existing account found: " + accountNumber + " and deleted. Returning true...");
                    return true;
                }
            }
        }

        // return false if not match found
        System.out.println("Account not found. Returning false...");
        return false;
    }

    // validate index; if valid, search for account object; if match found, remove object from accounts list
    public boolean deleteAccount(byte index) {
        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index: " + index + ". Returning false...");
            return false;
        }

        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + " and deleted. Returning true...");
        accounts.remove(index);
        return true;
    }
}

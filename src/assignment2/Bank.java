package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Bank {

    // - - - Variables - - - //

    public static enum BranchLocations {
        TORONTO, VANCOUVER, MONTREAL, CALGARY, HALIFAX, DEFAULT
    }

    private String bankName;
    private BranchLocations branchLocation;
    private List<Account> accounts = new ArrayList<>();

    // - - - Constructors - - - //

    // no arguments
    public Bank() {
    }

    // two arguments: bankName,
    public Bank(String bankName, String branchLocation) {

        String defaultBankName = "BANK&DEF123";
        BranchLocations defaultBranch = BranchLocations.DEFAULT;

        if (!setBankName(bankName)) {
            this.bankName = defaultBankName;
        }

        if (!setBranchLocation(branchLocation)) {
            this.branchLocation = defaultBranch;
        }
    }

    //two arguments: bankName,
    public Bank(String bankName, BranchLocations branchLocation) {
        if (!setBankName(bankName)) {
            this.bankName = "BANK&DEF123";
        }
        if (!setBranchLocation(branchLocation)) {
            this.branchLocation = BranchLocations.DEFAULT;
        }
    }

    // - - - Methods - - - //

    public String getBankName() {
        return bankName;
    }

    public boolean setBankName(String bankName) {

        String bankNameRegex = "^[a-zA-Z&0-9 ]*$";
        Pattern permittedBankNamePattern = Pattern.compile(bankNameRegex);
        int whitespaceCount = 0;
        int digitCount = 0;
        Matcher whitespaceMatcher = Pattern.compile(" ").matcher(bankName);
        Matcher digitMatcher = Pattern.compile("\\d").matcher(bankName);

        if (bankName.length() < 8) {
            System.out.println("The bank name must be at least eight characters.");
            System.out.println("Current value remains: " + this.bankName);
            System.out.println("Returning false...");
            return false;
        }

        if (!permittedBankNamePattern.matcher(bankName).matches()) {
            System.out.println("The bank name contains invalid characters.");
            System.out.println("Current value remains: " + this.bankName);
            System.out.println("Returning false...");
            return false;
        }

        while (whitespaceMatcher.find()) whitespaceCount++;
        if (whitespaceCount > 1) {
            System.out.println("You have included too many whitespace characters.");
            System.out.println("Current value remains: " + this.bankName);
            System.out.println("Returning false...");
            return false;
        }

        while (digitMatcher.find()) digitCount++;
        if (digitCount > 3) {
            System.out.println("You have included too many digits.");
            System.out.println("Current value remains: " + this.bankName);
            System.out.println("Returning false...");
            return false;
        }

        // if input is not returned as false, return as true
        this.bankName = bankName;
        System.out.println("Value updated: " + this.bankName);
        return true;
    }

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

    public boolean setBranchLocation(BranchLocations branchLocation) {
        if (branchLocation == null) {
            System.out.println("Branch location is null. Not updating branch location. Returning false...");
            return false;
        }

        this.branchLocation = branchLocation;
        System.out.println("Branch location set to: " + branchLocation);
        return true;
    }

    public Account getAccountByNumber(int accountNumber) {

        String accountNumberAsString = Integer.toString(accountNumber);

        if (!accountNumberAsString.matches("^[1-9][0-9]{4,8}$")) {
            System.out.println("Your number has too many or too few digits, or has leading zeros.");
            System.out.println("Returning default constructor...");
            return new Account();
        }

        if (accountNumber < 1) {
            System.out.println("Input is not a positive number.");
            System.out.println("Returning default constructor...");
            return new Account();
        }

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber() == accountNumber) {
                System.out.println("We have found your account.");
                return accounts.get(i);
            }
        }
        System.out.println("Account not found. Returning default constructor");
        return new Account();
    }

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

    public boolean addAccount(String accountName, int accountNumber, double accountBalance) {

        // validate accountName //

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

        // validate accountNumber //

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

        // validate accountBalance //

        String valueAsString = Double.toString(accountBalance);

        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        for (int i = 0; i < accounts.size(); i++) {
            Account existingAccount = accounts.get(i);
            if (existingAccount.getAccountNumber() == accountNumber) {
                System.out.println("Account with this number already exists: " + accountNumber);
                return false;
            }
        }

        Account newAccount = new Account(accountName, accountNumber, accountBalance);
        accounts.add(newAccount);
        return true;

    }

    public Account viewAccount(int accountNumber) {

        String accountNumberAsString = Integer.toString(accountNumber);

        if (!accountNumberAsString.matches("^[1-9][0-9]{4,8}$")) {
            System.out.println("Input has too many or too few digits, or has leading zeros.");
            System.out.println("Returning default constructor...");
            return new Account();
        }

        if (accountNumber < 1) {
            System.out.println("Input is not a positive number.");
            System.out.println("Returning default constructor...");
            return new Account();
        }

        for (int i = 0; i < accounts.size(); i++) {
            Account existingAccount = accounts.get(i);
            if (existingAccount.getAccountNumber() == accountNumber) {
                System.out.println("Existing account found: " + accountNumber + ". Returning existing account...");
                return existingAccount;
            }
        }

        System.out.println("Account not found. Returning default constructor...");
        return new Account();
    }

    public Account viewAccount(byte index) {
        return new Account();
    }

    public boolean modifyAccount(int accountNumber, String accountName) {

        // validate accountNumber //

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

        // validate accountNumber //

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

        // if no inputs are returned as false, see if account exists

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
        System.out.println("Account not found. Returning false...");
        return false;
    }

    public boolean modifyAccount(int accountNumber, double accountBalance) {

        // validate accountNumber //

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

        // validate accountBalance //

        String valueAsString = Double.toString(accountBalance);

        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        // if no inputs are returned as false, see if account exists
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
        System.out.println("Account not found. Returning false...");
        return false;
    }

    public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {

        // validate accountName //

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

        // validate accountNumber //

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

        // validate accountBalance //

        String valueAsString = Double.toString(accountBalance);

        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        // if no inputs are returned as false, see if account exists
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
        System.out.println("Account not found. Returning false...");
        return false;
    }

    // - - - Methods: index - - - //

    public boolean modifyAccount(byte index, String accountName) {

        // validate accountName //

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

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index: " + index + ". Returning false...");
            return false;
        }

        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountName(accountName);
        System.out.println("Account name updated to: " + accountName);
        return true;
    }

    public boolean modifyAccount(byte index, double accountBalance) {

        // validate accountBalance //

        String valueAsString = Double.toString(accountBalance);

        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index: " + index + ". Returning false...");
            return false;
        }

        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountBalance(accountBalance);
        System.out.println("Account balance updated to: " + accountBalance);
        return true;
    }

    public boolean modifyAccount(byte index, String accountName, double accountBalance) {

        // validate accountName //

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

        // validate accountBalance //

        String valueAsString = Double.toString(accountBalance);

        if (!valueAsString.matches("^-?(\\d+(\\.\\d{1,2})?|\\.\\d{1,2})$")) {
            System.out.println("Input is invalid.");
            System.out.println("Returning false...");
            return false;
        }

        if (index < 0 || index >= accounts.size()) {
            System.out.println("Invalid index: " + index + ". Returning false...");
            return false;
        }

        Account selectedAccount = accounts.get(index);
        System.out.println("Account " + selectedAccount.getAccountNumber() + " found at index " + index + ".");
        selectedAccount.setAccountName(accountName);
        selectedAccount.setAccountBalance(accountBalance);
        System.out.println("Account name updated to: " + accountName);
        System.out.println("Account balance updated to: " + accountBalance);
        return true;
    }

    public boolean deleteAccount(int accountNumber) {
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

        for (int i = 0; i < accounts.size(); i++) {
            Account existingAccount = accounts.get(i);
            if (existingAccount.getAccountNumber() == accountNumber) {
                accounts.remove(i);
                System.out.println("Existing account found: " + accountNumber + " and deleted. Returning true...");
                return true;
            }
        }
        System.out.println("Account not found. Returning false...");
        return false;
    }

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

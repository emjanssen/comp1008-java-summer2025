package assignment2;

public class Bank {
	public static enum BranchLocations {};
	
	public Bank() {}
	public Bank(String bankName, String branchLocation) {}
	public Bank(String bankName, BranchLocations branchLocation) {}
	public String getBankName() {
		return "";
	}
	public boolean setBankName(String bankName) {
		return false;
	}
	public boolean setBranchLocation(String branchLocation) {
		return false;
	}
	public String getBranchLocation() {
		return "";
	}
			
	public boolean setBranchLocation(BranchLocations branchLocation) {
		return false;
	}
	public Account getAccountByNumber(int accountNumber) {
		return new Account();
	}
	
	public boolean addAccount(Account account) {
		return false;
	}
	public boolean addAccount(String accountName, int accountNumber, double accountBalance) {
		return false;
	}
	public Account viewAccount(int accountNumber) {
		return new Account();
	}
	public Account viewAccount(byte index) {
		return new Account();
	}
	public boolean modifyAccount(int accountNumber, String accountName) {
		return false;
	}
	public boolean modifyAccount(int accountNumber, double accountBalance) {
		return false;
	}
	public boolean modifyAccount(int accountNumber, String accountName, double accountBalance) {
		return false;
	}

	public boolean modifyAccount(byte index, String accountName) {
		return false;
	}
	public boolean modifyAccount(byte index, double accountBalance) {
		return false;
	}
	public boolean modifyAccount(byte index, String accountName, double accountBalance) {
		return false;
	}
	public boolean deleteAccount(int accountNumber) {
		return false;
	}
	public boolean deleteAccount(byte index) {
		return false;
	}
	
	
}

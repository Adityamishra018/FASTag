package mypckg;

class Account {
	static int accToll = 154212;
	int accNo;
	String bankName;
	String ownerName;
	float balance;
	
	Account(String name,String bname){
		ownerName = name;
		bankName = bname;
		balance = 500;
		accNo = accToll;
		accToll += 1;
	}
	
	void credit(float amt) {
		balance += amt;
	}
	
	void debit(float amt) {
		balance -= amt;
	}
	
	String getBankName() {
		return bankName;
	}
	
	String getOwnerName() {
		return ownerName;
	}
	
	int getAccountNo() {
		return accNo;
	}
	
	float getBalance() {
		return balance;
	}
	
	public String toString() {
		return bankName + " : " + Integer.toString(accNo);
	}
	
}

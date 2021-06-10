package mypckg;

import java.util.ArrayList;

class Owner {
	static int ownerToll = 98245661;
	String name;
	String uidai;
	String address;
	ArrayList<Vehicle> vList;
	ArrayList<Account> aList;

	Owner(String n,String addr){
		name = n;
		address = addr;
		uidai =  Integer.toString(ownerToll);
		ownerToll += 1;
		vList = new ArrayList<Vehicle>();
		aList = new ArrayList<Account>();
	}
	
	String getUidai() {
		return uidai;
	}
	
	String getName() {
		return name;
	}
	
	void addVehicle(Vehicle v) {
		vList.add(v);
	}
	
	void addAccount(Account a) {
		aList.add(a);
	}
	
	public String toString() {
		return name + "  :" + uidai;
	}
	
	 public String getKey()
	 {
	        return uidai;
	 }

	 public String getValue()
	 {
	        return name;
	 }
}

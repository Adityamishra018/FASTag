package mypckg;

class Vehicle {
	static int vehicleToll = 3658;
	String vehicleNo;
	String model;
	float price;
	String type;
	Fastag fTag;
	
	Vehicle(String name,String t,float p){
		model = name;
		price = p;
		fTag = null;
		vehicleNo = "KNT"+ Integer.toString(vehicleToll);
		vehicleToll += 1;
		type = t;
	}
	
	boolean hasTag() {
		if(fTag == null)
			return false;
		else
			return true;
	}
	
	void setTag(Fastag f) {
		fTag = f;
	}
	
	Fastag getTag() {
		return fTag;
	}
	
	String getType() {
		return type;
	}
	
	String getModel() {
		return model;
	}
	
	String getVehicleNo() {
		return vehicleNo;
	}
	
	float getPrice() {
		return price;
	}
	
	public String toString() {
		return model + " : " + vehicleNo;
	}
	
}

package mypckg;
import java.io.FileWriter;
import java.util.Date;
import java.io.IOException;

class TollPlaza {
	String name;
	static int plazacntr = 3560;
	int plazaCode;
	float amount;
	FileWriter fp;
	
	TollPlaza(String n){
		name = n;
		plazaCode = plazacntr;
		plazacntr +=1;
		try {
			fp = new FileWriter("tollrecords/" + name + ".txt");
		}
		catch(IOException i) {
			System.out.println(i);
		}
		try {
			fp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		amount=0;
	}
	
	void PassVehicle(Vehicle v,float p) {
		Date d = new Date ();
		
		try {
			fp = new FileWriter("tollrecords/" + name + ".txt",true);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			fp.write(v.getVehicleNo() + " " + v.getModel() + "  " +  name + " " + d.toString() + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fp.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String getName() {
		return name;
	}
	
	
	public String toString() {
		return name + " : " + Integer.toString(plazaCode);
	}

}

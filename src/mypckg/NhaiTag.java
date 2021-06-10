package mypckg;

import java.util.Date;
import java.util.Calendar;

class NhaiTag implements Fastag{
	
	int tagId;
	float balance;
	String issuer;
	Date issueDate;
	Date expDate;
	String flag;
	
	NhaiTag(NetcMapper m,int i,String st){
		if(activate(m,i) == true)
			tagId = i;
		else
			System.out.println("Duplicacy error !");
		balance = 500;
		issuer = "PAYTM";
		issueDate = new Date();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(issueDate);
		cal.add(Calendar.YEAR, 4);
		expDate = cal.getTime();
		
		flag = st;
	}
	
	public boolean activate(NetcMapper m,int id) {
		if(m.addTag(id) == true)
			return true;
		else
			return false;
	}
	
	public boolean renew(NetcMapper m,int id) {
		if(m.lookUp(id) == true)
		{
			issueDate = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(issueDate);
			cal.add(Calendar.YEAR, 4);
			expDate = cal.getTime();
	        return true;
		}
		return false;
		
	}
	
	public int getId() {
		return tagId;
	}
	
	void addBalance(float p) {
		balance += p;
	}
	
	String getFlag() {
		return flag;
	}
	float getBalance() {
		return balance;
	}
	
	public String details() {
		return "Blabla";
	}

}

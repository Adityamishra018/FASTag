package mypckg;

import java.util.Calendar;
import java.util.Date;

class BankSpecificTag implements Fastag {
	
	int tagId;
    Account acc;
	Date issueDate;
	Date expDate;
	String flag;
	
	BankSpecificTag(Account a,NetcMapper m,int i,String st){
		if(activate(m,i) == true)
			tagId = i;
		else
			System.out.println("Duplicacy error !");
		acc = a;
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
		if(m.lookUp(id) == true) {
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
	
	String getFlag() {
		return flag;
	}
	
	String getDate(){
		return Integer.toString(issueDate.getMonth()) + Integer.toString(issueDate.getYear()) +
				"/" + Integer.toString(expDate.getMonth()) + Integer.toString(expDate.getYear());
	}
	
	public String details() {
		return "Blabla";
	}
	
	Account getAccount() {
		return acc;
	}

}

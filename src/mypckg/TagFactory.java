package mypckg;

class TagFactory {
	static int tagToll = 1358;
    NetcMapper mapper;
    
    TagFactory(){
    	mapper = NetcMapper.getInstance();
    }
    
	Fastag getTag(Account acc,String st) {
		BankSpecificTag t =  new BankSpecificTag(acc,mapper,tagToll,st);
		tagToll += 1;
		return t;
	}
	
	Fastag getTag(String st) {
		
		NhaiTag t = new NhaiTag(mapper,tagToll,st);
		tagToll += 1;
		return t;
	}
}

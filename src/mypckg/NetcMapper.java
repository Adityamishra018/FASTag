package mypckg;

import java.util.ArrayList;

class NetcMapper {
	private static NetcMapper mapper = new NetcMapper();
	ArrayList<Integer> tags;
	
	private NetcMapper(){
		tags = new ArrayList<Integer>();
	}
	
	static NetcMapper getInstance() {
		return mapper;
	}
	
	
	boolean addTag(int x) {
		if(tags.contains(x) == true)
			return false;
		else
			tags.add(x);
		return true;
	}
	
	boolean removeTag(int x) {
		if(tags.contains(x) == true) {
			tags.remove(x);
			return true;
		}
		return false;
	}
	
	boolean lookUp(int x) {
		if(tags.contains(x)) {
			return true;
		}
		return false;
	}
}

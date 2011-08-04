package icircles.abstractDescription;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

class AbstractSpider{
	
	public AbstractSpider(TreeSet<AbstractBasicRegion> feet, String name){
		m_feet = feet; 
		m_name = name;
	}

	private TreeSet<AbstractBasicRegion> m_feet;
	private String m_name;

	public SortedSet<AbstractBasicRegion> get_feet() {
		return Collections.unmodifiableSortedSet(m_feet);
	}
	
	public String get_name(){
		return m_name;
	}
	
	

}


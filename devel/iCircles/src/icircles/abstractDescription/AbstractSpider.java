package icircles.abstractDescription;

import java.util.Collections;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class AbstractSpider implements Comparable<AbstractSpider>{
	
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
        
        public void setName(String name) {
            this.m_name = name;
        }

	public int compareTo(AbstractSpider other) {
		
        if (other.m_feet.size() < m_feet.size()) {
            return 1;
        } else if (other.m_feet.size() > m_feet.size()) {
            return -1;
        }

        // same sized m_feet
        Iterator<AbstractBasicRegion> this_it = m_feet.iterator();
        Iterator<AbstractBasicRegion> other_it = other.m_feet.iterator();

        while (this_it.hasNext()) {
        	AbstractBasicRegion this_c = this_it.next();
        	AbstractBasicRegion other_c = other_it.next();
            int comp = this_c.compareTo(other_c);
            if (comp != 0) {
                return comp;
            }
        }
        return 0;
	}

	public String journalString() {
        StringBuilder b = new StringBuilder();
        for (AbstractBasicRegion z : m_feet) {
            b.append(z.journalString());
            b.append(" ");
        }
        if(m_name != null)
        {
        	b.append("'");
        	b.append(m_name);
        }
        return b.toString();
	}
}


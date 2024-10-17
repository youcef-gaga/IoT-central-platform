
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Thing {
	private String userId;
	private String macAddress;
	private HashMap<String,String> mapData;
	private ArrayList<Service> arrServices;
	
	public Thing(String mac ,String id) {
		this.macAddress =mac ;
		this.userId =id ;
		this.arrServices = new ArrayList<Service>() ;
	    this.mapData = new HashMap<String, String>() ;
	}
	public String getMacaddress() {
		return this.macAddress; 
	}
	public String getUserId() {
		return this.userId;
	}
	public void putData(String key,String dat) {
		this.mapData.put(key, dat) ;
	}
	public String getData(String key) {
		return this.mapData.get(key);
	}
	public void setFormDatagram(String datagram) {
		String[] tabExplode = datagram.split(";") ;
		for(int  i=0 ;i<tabExplode.length ;i++) {
		String key = tabExplode[i].substring(0, 3) ;
		String dat = tabExplode[i].substring(4) ;
		putData(key,dat) ;}
	}
	public boolean existData(String key) {
		return this.mapData.containsKey(key);
	}
	public void resetData() {
		this.mapData.clear();
	}
	public void subscribe(Service service) {
		this.arrServices.add(service) ;
	}

	public String toString() {
		String str=" "+macAddress +" ; "+ userId;
		Iterator<Map.Entry<String, String>> it = this.mapData.entrySet().iterator();
		while (it.hasNext()) {
		Map.Entry<String, String> couple = it.next() ;
		String dat = (String)couple.getValue() ;
		str=str +" ; "+ dat;
		}
	   
		return str ;
	}
	
	public void update() {
		
		Iterator<Service> it = this.arrServices.iterator();
		while (it.hasNext()) {
		Service service = it.next() ;
		service.writeData(this);
		}
	}
	
	

}

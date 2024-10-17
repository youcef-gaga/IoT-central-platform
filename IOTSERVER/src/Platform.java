import java.io.IOException;  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Platform {
private Map<String , Thing> MapThings ;
private ArrayList <Service> arrServices;

public Platform() {
	this.arrServices = new ArrayList<Service>() ;
    this.MapThings = new HashMap<String, Thing>() ;
}
public void  addThing(Thing thing) {
	this.MapThings.put(thing.getMacaddress(), thing);
}
public void addService(Service service) {
	this.arrServices.add(service);
}
public void run(DataReceiver datareceiver ) throws IOException {
	String datagram ;

    while (datareceiver.ready()) {
        datagram = datareceiver.readDatagram() ;
        if (datagram != null && !datagram.isEmpty()) {
            String mac = datagram.substring(0, 17) ;
            Thing theThing = this.MapThings.get(mac) ;
            if (theThing == null) {
                System.out.println("Mac address unknown: "+mac);
            }
            else {
                theThing.setFormDatagram(datagram.substring(18)) ;
                theThing.update();
                theThing.resetData();
            }
        }
    }
		
}

public  void loadFromDatabase() {
	HashMap<String, Service> mapIds = new HashMap<String, Service>() ;
	try
    {
      String myDriver = "org.gjt.mm.mysql.Driver";
      String myUrl = "jdbc:mysql://localhost/platform_iot?autoReconnect=true&useSSL=false";
      Class.forName(myDriver);
      Connection conn = DriverManager.getConnection(myUrl, "root", "");
      Statement st = conn.createStatement();
      Statement st2 = conn.createStatement();
      ResultSet rs,rs2 ;
      String query = "SELECT * FROM table_service";
       rs = st.executeQuery(query);
      while (rs.next())
      {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String type = rs.getString("type");
        if(type.equals("smarthome")) {
        	mapIds.put(id,new SmartHome(name)) ;
        }else if(type.equals("quantifiedself")) {
        	mapIds.put(id,new QuantifiedSelf(name)) ;
        } else {
        	mapIds.put(id,new Service(name)) ;
        }}
      //-----------------------------------
       query = "SELECT * FROM table_thing";
       rs = st.executeQuery(query);
      while (rs.next())
      {
    	  String id = rs.getString("id_user");
          String mac = rs.getString("mac");
          String type = rs.getString("type");
          String param = rs.getString("param");
        if(type.equals("thingtempo")) {
        	this.addThing(new ThingTempo(mac,id,Long.parseLong(param))) ;
        }else 
        	this.addThing(new Thing(mac,id)) ;
        } 
     
      
      query = "SELECT * FROM table_thing";
      rs = st.executeQuery(query);
     while (rs.next())
     {
   	  String id = rs.getString("id_user");
         String mac = rs.getString("mac");
         String type = rs.getString("type");
         String param = rs.getString("param");
         
       if(type.equals("thingtempo")) {
    	   ThingTempo t =new ThingTempo(mac,id,Long.parseLong(param)) ;
       	
        query = "SELECT id_service FROM table_subscribe WHERE id_user IN (SELECT id_user FROM table_thing WHERE mac='"+mac+"') ;";
        rs2 = st2.executeQuery(query);
        while (rs2.next()){
        	String ids = rs2.getString("id_service");	
        t.subscribe(mapIds.get(ids));	
        }
        this.addThing(t) ;
        t.update();
       }else {
    	   Thing t1 = new Thing(mac,id) ;

       query = "SELECT id_service FROM table_subscribe WHERE id_user IN (SELECT id_user FROM table_thing WHERE mac='"+mac+"') ;";
       rs2 = st2.executeQuery(query);
       while (rs2.next()){
       	String ids = rs2.getString("id_service");	
       t1.subscribe(mapIds.get(ids));	
       }
       this.addThing(t1) ; 
       t1.update();}
       }
      
      
      st2.close();
      st.close();
    }
    catch (Exception e)
    {
      System.err.println("Got an exception! ");
      System.err.println(e.getMessage());
    }
  	
}


public void close() {
	 for (int i=0 ; i < this.arrServices.size() ; i++) {
	        Service service = this.arrServices.get(i) ;
	        service.close();}
}
}



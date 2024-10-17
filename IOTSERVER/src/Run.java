
import java.io.IOException;



public class Run {

	public static void main(String[] args) throws IOException {
	
		    {
		     System.out.println("Welcome on IoT central platform.");
		     System.out.println("");
		     
		     //partie 4 import from database
		     System.out.println("import from data base");		
		     System.out.println("");

		         Platform p = new Platform();
	             p.loadFromDatabase() ;
		    	 p.close() ;		
		    	 
		    	 System.out.println("");
			     System.out.println("THE IMPORT IS DONE SUCCESFULY");
			     System.out.println("");
			     
             // partie 5
		     Thing t1 = new Thing("f0:de:f1:39:7f:17","1");
		     Thing t2 = new Thing("f0:de:f1:39:7f:18","2");
		     Service s1=new Service("mon_service");
		     Service s2=new Service("mon_service2"); 
		   /*  Service s3=new Service("mon_service3");
		     Service s4=new Service("mon_service4"); 
		     Service s5=new Service("mon_service5");
		     SmartHome sh=new SmartHome("myKWHome");
		     QuantifiedSelf qs=new QuantifiedSelf("RUNstats");*/
		     
		     SocketServer s=new SocketServer(51291);
		
		     p.addService(s1);p.addService(s2); /*p.addService(s3);p.addService(s4);p.addService(s5);p.addService(sh);p.addService(qs);*/
		     p.addThing(t1); p.addThing(t2);
		     t1.subscribe(s1);/*t1.subscribe(s2);t1.subscribe(s3);t1.subscribe(s4);t1.subscribe(s5);t1.subscribe(qs);*/
		   /*  t2.subscribe(s1);*/ t2.subscribe(s2);/*t2.subscribe(s3);t2.subscribe(s4);t2.subscribe(s5);t2.subscribe(sh);*/
		     
             s.open();
             p.run(s);
		     p.close();
		     s.close();
		     
		
		    
            
		     
		    }  
 
		} 

	}


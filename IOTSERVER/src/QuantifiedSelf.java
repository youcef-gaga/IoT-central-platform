import java.io.IOException;
import java.util.Date;

public class QuantifiedSelf  extends Service {

	public QuantifiedSelf(String name) throws IOException {
		super(name);
		
	}

public void writedata(Thing thing) {
	
		Date now = new Date () ;
		   long time = now.getTime() ;
	    
		pw.println(time+"ms ; "+thing.toString()); 
		 pw.flush(); 

	
	
}
}

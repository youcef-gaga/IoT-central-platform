
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Service {

  protected PrintWriter  pw ;

  Service(String name) throws IOException{
		FileWriter fw = new FileWriter ("log_"+name+".txt", true); 
		BufferedWriter bw = new BufferedWriter (fw);
		this.pw = new PrintWriter (bw);
	}
	
	public void writeData(Thing thing) {
		Date now = new Date () ;
	    SimpleDateFormat formater = new SimpleDateFormat ("yyyy-MM-dd H:m:s");
	    String d = formater.format (now);
	    pw.println(d+" ; "+thing.toString());
	    pw.flush();
	    
	}
	public void close() {
		pw.flush(); 
		pw.close();
	}
	
}

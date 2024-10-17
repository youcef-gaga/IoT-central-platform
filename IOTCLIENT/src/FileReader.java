
import java.io.BufferedReader; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader implements DataSender {

	String filename;
	BufferedReader br;
	boolean ok;

	
	FileReader (String filename){
		this.filename=filename;
	}
	
	public boolean ready() {
		return ok;
	}

	public void open() {
		InputStream ips = null;
		try {
			ips = new FileInputStream(this.filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    InputStreamReader ipsr = new InputStreamReader(ips);
	    br = new BufferedReader(ipsr);
        ok=true;

	}
	
	
	
	public String readDatagram() throws IOException {
		String line=br.readLine();
		if(line!=null  ) { 
			return  line; } 
		else{ok=false; return null;}
		
		
		}
	
	
	public void close() {
		try {
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ok=false;

	}


	public void writeDatagram(String datagram) {
		
		
	}

}

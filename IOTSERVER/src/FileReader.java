import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileReader implements DataReceiver {

	private String filename;
	private BufferedReader br;
	private  boolean ok;
	
	
	public FileReader(String filename) {
		this.filename=filename;
	}
	
	public void open() throws FileNotFoundException {
		
		InputStream ips = new FileInputStream(this.filename);
		InputStreamReader ipsr = new InputStreamReader(ips);
		br = new BufferedReader(ipsr);
		ok=true;
	}
	
	
	public String readdatagram() throws IOException {
		String read = null;
		 read= br.readLine();
		
		
		if (read != null && !read.isEmpty()) {
			
			return read;
				
			}
			else {
				ok=false;
				return null;
			}
	}
	
	
	
    public boolean ready() {
		
		return ok;
	}
	
    public void close() throws IOException {
    	br.close();
    	ok=false;
    }

	@Override
	public String readDatagram() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
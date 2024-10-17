import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyboardInput implements DataReceiver {
    private boolean ok ;

	public KeyboardInput() {
	this.ok = false ;
	}

	@Override
	public void open() {
		this.ok = true ;
		
	}

	public String readDatagram() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String adressemac = null,donne = null ;
		 try {
			 System.out.print("MAC ADRESS :");
			 adressemac =br.readLine() ;
			System.out.print("DATAGRAM :");
			 donne =br.readLine() ;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 if(adressemac.equals("quit")) {
			 this.ok =false ;
			 return null; 
		 }
		return adressemac+";"+donne;
	}

	@Override
	public boolean ready() {
		return this.ok ;
	}

	@Override
	public void close() {
		this.ok = false ;
	}

}

import java.io.IOException;

public class GatewaySimulator {

	public static void main(String[] args) throws IOException {
		
		
		FileReader f=new FileReader("simu.txt");
		
		SocketClient s=new SocketClient("127.0.0.1",51291);
		
		f.open();
		s.open();
		
while(f.ready()) {
			String read = f.readDatagram();
			if(read!=null) {
		s.writeDatagram(read); 
}}
		 f.close();
		 s.close();
		
	}

}

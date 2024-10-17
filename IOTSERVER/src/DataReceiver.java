
import java.io.IOException;

public interface DataReceiver {
	public void open() throws IOException ;
	public String readDatagram() throws IOException ;
	public boolean ready () ;
	public void close() throws IOException ;

}

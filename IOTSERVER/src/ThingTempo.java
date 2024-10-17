import java.util.Date;

public class ThingTempo extends Thing{
	long delay;
    long lastUpdate=0;
public ThingTempo(String mac, String id,long delay) {
		super(mac, id);
		this.delay=delay;
		
	}
public void update() {
	
	Date now = new Date () ;
	long nowtime = now.getTime();
	if(nowtime > lastUpdate + (this.delay*1000)) {
		super.update();
		lastUpdate=nowtime;
	}
}
}

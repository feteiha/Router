import java.util.ArrayList;

public class Router {
	private Semaphore s;
	int maxConnections;
	ArrayList<Integer> connections;
	public Router(int maxConnections, int maxDevices) {
		this.maxConnections = maxConnections;
		connections = new ArrayList<Integer>();
		s = new Semaphore(maxConnections);
		for (int i=0; i< maxConnections; i++) {
			connections.add(0);
		}
	}
	
	public int requestConnection(Device d) {
		s.P(d.name + "("+d.type+")");
		int connectionNumber = 0; 
		for (int i=0; i<maxConnections; i++) {
			if (connections.get(i) == 0) {
				connections.set(i,1);
				connectionNumber = i+1;
				break;
			}
		}
		return connectionNumber;
		
	}
	public void endConnection(int connectionNumber) {
		connections.set(connectionNumber-1,0);
		s.V();
	}

}

import java.util.ArrayList;
import java.util.Scanner;

public class Network {
	public static void main(String[] args) {
		int maxConnections;
		int maxDevices;
		ArrayList<Device> devices = new ArrayList<Device>();
		Scanner in = new Scanner(System.in);
		System.out.println("What is number of WI-FI Connections?");
		maxConnections = in.nextInt();
		System.out.println("What is number of devices Clients want to connect?");
		maxDevices = in.nextInt();
		
		for (int i=0 ; i<maxDevices ; i++) {
			String name = in.next();
			String type = in.next();
			devices.add(new Device(name,type));
		}
		
		GUI g = new GUI(maxConnections,maxDevices,devices);
		Router r = new Router(maxConnections, maxDevices);
		
		//Starts Connections
		for (int i=0; i<maxDevices ; i++) {
			devices.get(i).r = r;
			devices.get(i).start();
		}
		in.close();			
	}
}

public class Device extends Thread{
	public String name,type;
	public Router r;
	public int connectionNumber;
	public Device (String name,String type) {
		this.name = name;
		this.type = type;
	}
	public Device () {
		name = type = "";
	}
	public void run() {
		connect();
		doActivity();
		disConnect();
	}
	
	
	private void connect() {
		GUI.arrived(this);
		connectionNumber = r.requestConnection(this);
	}
	
	private void doActivity() {
		GUI.occupied(this);
		System.out.println("Connection " + connectionNumber + ": " + name + " occupied");
		System.out.println("Connection " + connectionNumber + ": " + name+ " performs online activity");
		try {
			Thread.sleep((long)(Math.random() * 20000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	private void disConnect() {
		GUI.finished(this);
		System.out.println("Connection " + connectionNumber + ": " + name+ " Logged out");
		r.endConnection(connectionNumber);
	}
}

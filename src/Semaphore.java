
public class Semaphore {
	
	protected int value = 0 ;

	protected Semaphore() { value = 0 ; }

	protected Semaphore(int initial) { value = initial ; }
	
	// wait
	public synchronized void P(String entity) {
		  
		value-- ;
		if (value < 0)
			try {
				System.out.println(entity+" arrived and waiting");
				wait() ; 
			} 
		catch(  InterruptedException e ) { }
		else
			System.out.println(entity + "arrived");
	}
	
	// Signal
	public synchronized void V() { 
	  value++ ; if (value <= 0) notify() ;
	}

}

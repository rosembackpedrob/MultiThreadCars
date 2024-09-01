import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class RacePodium {
	
	private ArrayList<String> racersPodium = new ArrayList<>();
	
	private Semaphore semaphore;
	
	public RacePodium() {
		semaphore = new Semaphore(1); 
		//Semáforo com 1 int permit: apenas 1 thread pode adquirir permissão
		//Por default o boolean fair é true, o que significa que as threads
		//acessarão usando a política de First In First Out (FIFO). 
	}
	

	//Methods
	public void addRacer(CarThreadProcessor car) {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();	
		}
		
		racersPodium.add( car.getCarName() );
		
		semaphore.release();
	}
		
	public void printPodium() {
		System.out.println("\n*Pódio*:\n");
		
		for(int car = 0; car < (racersPodium.size()); car++) {
			System.out.println((car + 1) + "º Lugar:" + racersPodium.get(car) );
		}
		
	}

}

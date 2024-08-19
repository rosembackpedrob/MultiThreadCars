
public class MultiThreadMain {
	
	public static void main(String[] args) {
		
		//propriedades dos carros:
		int numCars = 3;
		
		float initSpeed = 0f; // vel. inicial de todos = 0
		float minAccel = 5f;
		float maxAccel = 15f;
		float position = 0f; //largada no 0
		
		float destiny = 100f;// fim da corrida
		
		//Criando as Threads
		CarThreadProcessor[] carThreads = new CarThreadProcessor[numCars];
		
		//Inicializando os carros e colocando IDs
		for (int i = 0; i < numCars; i++) {
			carThreads[i] = new CarThreadProcessor(i + 1, initSpeed, minAccel, maxAccel, position, destiny);
		}
		
		// Inicia as threads dos carros
        System.out.println("Início da corrida:");
        for (int i = 0; i < numCars; i++) {
            carThreads[i].start();
        }
        
        // Aguarda todas as threads terminarem
        for (int i = 0; i < numCars; i++) {
            try {
                carThreads[i].join();		//join irá parar esta thread do Main até todas as dos carros terminem de executar.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
		
		//Impressão da Corrida
		
	}
}

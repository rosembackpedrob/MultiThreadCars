
public class SingleThreadMain {
	
	static float initSpeed = 0f;
	static float accel = 10f;
	
	static float position = 0f; //posição inicial
	
	static float traveledDistance; //deslocamento final
	
	static float destiny = 250f; // linha de chegada

	public static void main(String[] args) {
		
		//Impressão da Corrida
		carRun("Carlos", initSpeed, accel, position, destiny);
		carRun("Marco", initSpeed, accel, position, destiny);
		carRun("Pedro", initSpeed, accel, position, destiny);
	}
	
	public static void carRun(String _name, float currentSpeed, float _accel, float _initPosition, float _destiny) {
		//executa a corrida de um carro
		//Single-Thread
		float speed;
		long startTime;
		long elapsedTime; 
		
		float carPosition;
		float traveledDistance;
		
		carPosition = _initPosition;
		startTime = System.currentTimeMillis(); //tempo inicial
		
		
		do {
			//aplicar aceleração no carro:
			try {
				float seconds = 1f;
				long miliseconds = (long)seconds * 1000;
				Thread.sleep(miliseconds); //isso está aqui para simular o tempo do movimento já que é uma aplicação de terminal
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			carUpdatePosition(carPosition, _accel);
			
			//tempo final
			elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
			
			speed = calculateSpeed(currentSpeed, _accel, (float)elapsedTime);
			//debugPrints(currentSpeed, _accel, elapsedTime, speed);
			
			traveledDistance = calculateTravel(carPosition, speed, (float)elapsedTime);
			
			System.out.println("O Carro_" + _name + " andou " + (speed / elapsedTime) + "m" + "	e já percorreu " + traveledDistance + "m...");
			
		} while (traveledDistance <= _destiny);
		
		//Carro está na linha de chegada
		System.out.println("***	O Carro_" + _name + " alcançou a linha de chegada em " + elapsedTime + " segundos.");
	}
	
	
	public static float calculateSpeed(float initSpeed, float acceleration, float time) {
		float speedResult;
		
		speedResult = initSpeed + (acceleration * time);
		return speedResult;
	}
	
	public static float calculateTravel(float initPosition, float speed, float time) {
		float travelResult;
		
		travelResult = initPosition + (speed * time);
		return travelResult;
	}
	
	public static void carUpdatePosition(float _carPosition, float _acceleration) {
		_carPosition += _acceleration;
		SingleThreadMain.position = _carPosition;
	}
	
	public static void debugPrints(float _initSpeed, float _accel, float elapsedTime, float speed) {
		System.out.println("initSpeed: " + _initSpeed);
		System.out.println("accel: " + _accel);
		System.out.println("time: " + elapsedTime);
		System.out.println("speed: " + speed);
	}

}

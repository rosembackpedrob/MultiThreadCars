import java.util.Random;

public class CarThreadProcessor extends Thread {

	int carID;
	
	float initSpeed;
	float minAccel;
	float maxAccel;
	float position;
	float traveledDistance;
	
	float destiny;
	
	Random random = new Random();
	
	
	
	public CarThreadProcessor(int _carID, float _initSpeed, float _minAccel, float _maxAccel, float _position, float _destiny) {
		this.carID = _carID;
		
		this.initSpeed = _initSpeed;
		this.minAccel = _minAccel;
		this.maxAccel = _maxAccel;
		this.position = _position;
		this.destiny = _destiny;
	}
	
	
	//Sobrescreve o metodo execução da classe Thread
	@Override
	public void run() {
		
		//o que a thread vai fazer/calcular
		carRun(carID, initSpeed, position, destiny);
	}
	
	public void carRun(int _carID, float currentSpeed, float _initPosition, float _destiny) {
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
			float randomAccel = minAccel + (maxAccel - minAccel) * random.nextFloat(); //accel aleatória
			carUpdatePosition(carPosition, randomAccel);
			
			//tempo final
			elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
			
			speed = calculateSpeed(currentSpeed, randomAccel, (float)elapsedTime);
			//debugPrints(currentSpeed, _accel, elapsedTime, speed);
			
			traveledDistance = calculateTravel(carPosition, speed, (float)elapsedTime);
			
			System.out.println("O Carro_" + _carID + " andou " + (speed / elapsedTime) + "m" + "	e já percorreu " + traveledDistance + "m...");
			
		} while (traveledDistance <= _destiny);
		
		//Carro está na linha de chegada
		System.out.println("***	O Carro_" + _carID + " alcançou a linha de chegada em " + elapsedTime + " segundos.");
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
	
	public void carUpdatePosition(float _carPosition, float _acceleration) {
		_carPosition += _acceleration;
		this.position = _carPosition;
	}
	
	public static void debugPrints(float _initSpeed, float _accel, float elapsedTime, float speed) {
		System.out.println("initSpeed: " + _initSpeed);
		System.out.println("accel: " + _accel);
		System.out.println("time: " + elapsedTime);
		System.out.println("speed: " + speed);
	}
	
}

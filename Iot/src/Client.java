public class Client{
	protected double tempsArrivee; //c'est un instant
	protected double dureeService; 
	//c'est une duré
	public Client(double arrivalTime, double serviceTime) {
		this.tempsArrivee = arrivalTime;this.dureeService = serviceTime;
	}
	// si les deux attributs sont declares protected ou private, 
	// il faut des méthods publiques d’accès à la valeur de ces attributs 
	public double getArrivalTime() {
		return tempsArrivee;
		}
	
	public double getServiceTime() {
		return dureeService;
		}
}
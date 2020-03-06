import java.util.Vector;

public class Queue{
	/* Liste des clients dans la queue */
	Vector lesClients;
	//Construire une queue vide
	public Queue() {
	lesClients = new Vector();
	}
	
	public void addClient(Client c) {
		lesClients.add(c);
	}
	
	public void remove() {
		lesClients.remove(0);
	}
	
	public Client get() {
		return (Client) lesClients.get(0);
	}
	
	public int size() {
		return lesClients.size();
	}
	
	public double tempsTotal() {
		double temps=0;
		for (int i = 0; i < lesClients.size(); i++) {
			temps +=  ((Client) lesClients.get(i)).getServiceTime();
		}
		return temps;
	}
	
	public double tempsMoyen() {
		double temps=0;
		for (int i = 0; i < lesClients.size(); i++) {
			temps +=  ((Client) lesClients.get(i)).getServiceTime();
		}
		return temps/lesClients.size();
	}
}
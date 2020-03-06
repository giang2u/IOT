import java.util.ArrayList;
import java.util.Vector;
import javafx.scene.chart.LineChar;

public class MM1Simulation {
	double lambda;
	double mu;
	ListeEvents liste;
	Queue q;
	double t; 

	Vector listeTemps = new Vector();
	Vector listeNombre = new Vector();
	Vector tempsMoyen = new Vector();
	Surface s;

	public MM1Simulation (double lambda, double mu) {
		this.lambda = lambda;
		this.mu = mu;
		q = new Queue();
		liste= new ListeEvents();
		s = new Surface();
	}

	public Surface getSurface() {
		return s;
	}

	public double expo(double taux){
		return -Math.log(Math.random())/taux;
	}

	public void addTempsMoyen(Client c, double t) {
		tempsMoyen.add(t - c.getArrivalTime());
	}

	public double tempsMoyen() {
		double temps =0;
		int taille = tempsMoyen.size();
		for (int i = 0; i < tempsMoyen.size(); i++) {
			temps += (double) tempsMoyen.get(i);
		}
		return temps/taille;
	}

	public void simulate (double simLength) {
		//à remplir avec l’algorithme suivant
		t = 0;
		liste.addEvent(new Event(0, t));
		int compteur= 0;

		while (t < simLength) {

			Event e = liste.getEventFirst();

			t = e.getTime();

			if (e.getType() == 0) {
				liste.addEvent(new Event(0, t+ expo(lambda) ));
				compteur++;
				double dureeService = expo(mu);
				q.addClient(new Client(t, dureeService));

				// stocke nb client temps
				s.addTemps(t);
				s.addNbEvent(compteur);
				compteur = 0;

				if (liste.size() == 1) {
					liste.addEvent( new Event(1,   (t + dureeService) ) );
					compteur++;
				}

			} else if (e.getType() == 1){
				addTempsMoyen(q.get(), t);
				q.remove();
				
				// stocke nb client temps
				s.addTemps(t);
				s.addNbEvent(compteur);
				compteur = 0;
				
				
				if (q.size() != 0) {
					double dureeService = expo(mu);
					liste.addEvent( new Event(1,   (t + dureeService) ) );
					compteur++;
				}
			}

		}


	}

	public static void main(String[] arg) {

		MM1Simulation s = new MM1Simulation(0.5, 1.0);
		s.simulate(1000000);
		System.out.println("SIMULE ");
		System.out.println(" temps moyen d'attente "  +s.tempsMoyen() );
		System.out.println(" surface nb moyen " +  s.getSurface().surface(1000000) );

		
		System.out.println("THEORIQUE  :");
		double p = 0.5/1.0;
		
		System.out.println(" nb moyen  client " + p/(1-p));
		
		System.out.println(" temps  moyen attente " + ( (p/(1-p)) / 0.5 )  );
		
		
		

	}
}
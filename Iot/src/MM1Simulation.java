import java.util.ArrayList;
import java.util.Vector;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

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
	
	public double ecartType() {
		double mean = tempsMoyen();
		double ecart =0;
		int taille = tempsMoyen.size();
		for (int i = 0; i < tempsMoyen.size(); i++) {
			ecart += Math.pow( (double) tempsMoyen.get(i) - mean , 2);
		}
		return Math.sqrt(ecart/taille);
	}
	
	
	// https://fr.wikihow.com/calculer-un-intervalle-de-confiance thk u
	public Vector intervConf95Temps() {
		Vector interv = new Vector(); 
		double mean = tempsMoyen();
		double ecart = ecartType();
		int tailleEchantillon = tempsMoyen.size();
		
		// calcul marge erreur
		double valeurCrit = 1.96;
		double erreurType = ecart/ Math.sqrt(tailleEchantillon);
		double margeErreur = valeurCrit * erreurType;
		
		// interv conf
		interv.add(mean - margeErreur);
		interv.add(mean + margeErreur);
		interv.add(margeErreur);
		
		return interv;
	}
	
	public Vector intervConf95Nb(int t) {
		Vector interv = new Vector(); 
		double mean = s.surface(t);
		double ecart = s.ecartType(t);
		int tailleEchantillon = s.size();
		// calcul marge erreur
		double valeurCrit = 1.96;
		double erreurType = ecart/ Math.sqrt(tailleEchantillon);
		double margeErreur = valeurCrit * erreurType;
		// interv conf
		interv.add(mean - margeErreur);
		interv.add(mean + margeErreur);
		interv.add(margeErreur);
		
		return interv;
	}

	public void simulate (double simLength, String type) {
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
				if (type.equals("MD1") ) {
					dureeService = 1/mu;
				}
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
					if (type.equals("MD1") ) {
						dureeService = 1/mu;
					}
					liste.addEvent( new Event(1,   (t + dureeService) ) );
					compteur++;
				}
			}

		}
	}

	public static void main(String[] arg) {
		
		double lambda = 0.5;
		double mu = 1.0;
		double p = 0.5/1.0;

		MM1Simulation s = new MM1Simulation(0.5, 1.0);
		s.simulate(1000000,"MM1");
		System.out.println("MM1 ");
		System.out.println("\tSIMULE :");
		System.out.println("\t\ttemps moyen d'attente "  +s.tempsMoyen() );
		System.out.println("\t\tsurface nb moyen " +  s.getSurface().surface(1000000) );

		
		System.out.println("\tTHEORIQUE :");
		
		
		System.out.println("\t\tnb moyen  client " + p/(1-p));	
		System.out.println("\t\ttemps  moyen attente " + ( (p/(1-p)) / 0.5 )  );
		
		System.out.println("\tINTERVALLE DE CONFIANCE 0.95 POUR TEMPS MOYEN DATTENTE " + s.intervConf95Temps() );
		System.out.println("\tINTERVALLE DE CONFIANCE 0.95 POUR NB MOYEN " + s.intervConf95Nb(1000000) );
		
		
		MM1Simulation ss = new MM1Simulation(0.5, 1.0);
		ss.simulate(1000000,"MD1");
		System.out.println("MD1"); 
		
		System.out.println("\tSIMULE :");
		
		System.out.println("\t\ttemps moyen d'attente "  +ss.tempsMoyen() );
		System.out.println("\t\tsurface nb moyen " +  ss.getSurface().surface(1000000) );

		
		System.out.println("\tTHEORIQUE :");
	
		System.out.println("\t\tnb moyen  client " + (2*p-Math.pow(p, 2) ) / (2 *(1-p) ) ) ;
		System.out.println("\t\ttemps  moyen attente " +  (mu/(1-p) - mu*p/(2*(1-p)) ) );
		
		/*
		SwingUtilities.invokeLater(() -> {
		      XYLineChartExample example = new XYLineChartExample("XY Chart Example | BORAJI.COM", s.getSurface());
		      example.setSize(800, 400);
		      example.setLocationRelativeTo(null);
		      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		      example.setVisible(true);
		    });
		*/
	}
}
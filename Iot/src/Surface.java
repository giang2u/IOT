import java.util.Vector;

public class Surface {

	public Vector temps;
	public Vector nombreEvent;
	public Vector nbclients;
	public double compte;
	

	public Surface() {
		temps = new Vector();
		nombreEvent = new Vector();
		nbclients = new Vector();
		compte = 0.0;
	}

	public void addTemps(double t) {
		temps.add(t);
	}

	public void addNbEvent(double entier) {
		nombreEvent.add(entier);
	}
	
	public void setClients(double i) {
		compte += i;
		nbclients.add(compte);
	}


	public double surface(int t) {
		double rect = 0.0;
		for (int i = 0; i < nbclients.size(); i++) {
			/*
			if (i > 0) {
				rect += ( (double)temps.get(i) - (double)temps.get(i-1) )  * (double)nbclients.get(i);
				//System.out.println(" SURFACE i " + i + " temps   " +  (double)temps.get(i) + "  nb   " +    (int)nombreEvent.get(i)  + "  surface :  "  + ( (double)temps.get(i) - (double)temps.get(i-1) )  * (int)nombreEvent.get(i) );
			} else {
				rect += ( (double)temps.get(i) - 0 )  * (double)nbclients.get(i);
			}
			*/
			rect += (double)nombreEvent.get(i);
		}
		return rect/t;
	}
	
	public double ecartType(int t) {
		double mean = surface(t);
		double ecart =0;
		int taille = temps.size();
		for (int i = 0; i < taille; i++) {
			if (i > 0) {
				ecart +=  Math.pow( ( (double)temps.get(i) - (double)temps.get(i-1) )  * (double)nombreEvent.get(i) - mean, 2);
				//System.out.println(" SURFACE i " + i + " temps   " +  (double)temps.get(i) + "  nb   " +    (int)nombreEvent.get(i)  + "  surface :  "  + ( (double)temps.get(i) - (double)temps.get(i-1) )  * (int)nombreEvent.get(i) );
			} else {
				ecart += Math.pow( ( (double)temps.get(i) - 0 )  * (double)nombreEvent.get(i) - mean, 2);
			}
		}
		return Math.sqrt(ecart/taille);
	}
	
	public int size() {
		return temps.size();
	}
	
	public Vector getTime() {
		return temps;
	}

	public Vector getEvents() {
		return nombreEvent;
	}
	
	
}

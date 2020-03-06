import java.util.Vector;

public class Surface {

	public Vector temps;
	public Vector nombreEvent;
	public int pointeur;

	public Surface() {
		temps = new Vector();
		nombreEvent = new Vector();
		pointeur = -1;
	}

	public void addTemps(double t) {
		temps.add(t);
		pointeur++;
	}

	public void addNbEvent(int entier) {
		if ( pointeur > (nombreEvent.size() - 1) ) {
			nombreEvent.add(entier);
		} else {
			int i = (int) nombreEvent.get(pointeur);
			nombreEvent.set(pointeur, (i+ entier) );
		}
	}


	public double surface(int t) {
		double rect = 0;
		for (int i =0; i < temps.size(); i++) {
			if (i > 0) {
				rect += ( (double)temps.get(i) - (double)temps.get(i-1) )  * (int)nombreEvent.get(i);
				//System.out.println(" SURFACE i " + i + " temps   " +  (double)temps.get(i) + "  nb   " +    (int)nombreEvent.get(i)  + "  surface :  "  + ( (double)temps.get(i) - (double)temps.get(i-1) )  * (int)nombreEvent.get(i) );
			} else {
				rect += ( (double)temps.get(i) - 0 )  * (int)nombreEvent.get(i);
			}
		}
		return rect/t;
	}


}

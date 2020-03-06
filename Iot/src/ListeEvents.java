import java.util.Vector;
public class ListeEvents{
	// cette classe modélise la liste des événements futurs
	Vector events;
	public ListeEvents () {
		events = new Vector();
	}
	public void addEvent(Event newEvent) {
		int insertIndex = 0; 
		//sert à repérer l’endroit où l’événement newEvent doit être insérer
		//on compare l’instant de newEvent avec l’instant des autres événements déjà dans la liste
		while (insertIndex < events.size()) {
			Event  e  =  (Event)  events.elementAt(insertIndex);  //on  extrait  le insertIndex ème événement
			if  (e.getTime()  >  newEvent.getTime())  break;  //si  l’instant  de newEvent est le plus près, 

			//on sort de la boucle
			insertIndex++; //sinon on incrémente insertIndex, et on reboucle
		}
		events.insertElementAt(newEvent,  insertIndex);  //on  insert  l’événement newEvent au bon endroit
	}
	
	public Event getEventFirst() {
		Event e = (Event) events.firstElement();
		events.remove(0);
		return (Event) e;
	}
	
	public double getTime() {
		double t = 0;
		if (events.size() > 0) {
			 t =  ((Event)events.get(events.size() - 1)).getTime();
		}
		return t;
	}

	public int size() {
		return events.size();
	}

}
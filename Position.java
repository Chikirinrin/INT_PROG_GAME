
/**
 * Write a description of class Position here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Position
{
    // instance variables - replace the example below with your own
    private City from;
    private City to;
    private int distance;
    private int total;


    public Position(City from, City to, int distance) {
        this.from = from; // Byen vi kommer fra
        this.to = to; // Byen vi er på vej til
        this.distance = distance; // Afstand der er tilbage
        this.total = distance; // Totale afstand
    }

    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getDistance() {
        return distance;
    }

    public int getTotal() {
        return total;
    }

    public boolean hasArrived(){
        if(distance == 0){
            return true;
        }
        return false;
    }

    // Tæller distance 1 ned hvis distance > 0.
    // Returværdien angiver om distance ændres.
    public boolean move(){
        if(distance > 0){
            distance--;
            return true;
        }
        return false;
    }

    public void turnAround(){
        distance = total-distance;
        City tempTo = to;
        to = from;
        from = tempTo;
    }
}

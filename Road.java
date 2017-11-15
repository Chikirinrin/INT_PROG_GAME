
/**
 * Write a description of class Road here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Road implements Comparable<Road>
{
    // instance variables - replace the example below with your own
    private City from;
    private City to;
    private int length;

    /**
     * Constructor for objects of class Road
     */
    public Road(City from, City to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public City getFrom() {
        return from;
    }

    public City getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }

    public int compareTo(Road road){
        if(this.from.equals(road.getFrom())){
            return (this.to.compareTo(road.getTo()));
        }
        return (this.from.compareTo(road.getFrom()));
    }

}

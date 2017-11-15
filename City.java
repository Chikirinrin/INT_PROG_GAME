
/**
 * Write a description of class City here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class City implements Comparable<City>
{
    // instance variables - replace the example below with your own
    private String name;
    private int value;
    private int initialValue;
    private Country country;

    public City(String name, int value, Country country) {
        this.name=name;
        this.value = value;
        this.initialValue = value;
        this.country = country;
    }

    public int getInitialValue() {
        return initialValue;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public Country getCountry(){
        return country;
    }

    public void changeValue(int amount){
        this.value = getValue()+amount;
    }

    public void reset(){
        this.value = getInitialValue();
    }

    public int compareTo(City city) {
        return name.compareTo(city.getName());
    }

    // Fratrækker den udbetalte bonus fra byens værdi.
    public int arrive(){
        int b = country.bonus(value);
        value -= b;
        return b;
    }
}

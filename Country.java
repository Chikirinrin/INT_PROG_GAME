import java.util.List;
import java.util.Map;
import java.util.*;

/**
 * Write a description of class Country here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Country
{
    private Map<City,List<Road>> network;
    private String name;
    private Country country;
    private Game game;

    public Country(String name, Map<City, List<Road>> network) {
        this.network = network;
        this.name = name;
    }

    public Game getGame(){
        return game;
    }

    public void setGame(Game game){
        this.game = game;
    }

    public Map<City, List<Road>> getNetwork() {
        return network;
    }

    public Country getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public List<Road> getRoads(City city){
        if(network.containsKey(city)){
            return network.get(city);
        }
        return Collections.emptyList();
    }

    public List<City> getCities(){
        List<City> cities  = new ArrayList<>(network.keySet());
        Collections.sort(cities);
        return cities;
    }

    public City getCity(String name){
        for (City city : getCities() ){
            if(city.getName().equals(name)){
                return city;
            }
        }
        return null;
    }

    public void reset(){
        for(City city: getCities()){
            city.reset();
        }
    }

    // Beregner størrelsen af den bonus, som spilleren modtager.
    public int bonus(int value){
        if(value>0){
            return game.getRandom().nextInt(value+1);
        }
        return 0;
    }

    //Tilføjer veje mellem a og b.
    public void addRoads(City a, City b, int length){
        if(network.containsKey(a)) {
            Road roadab = new Road(a, b, length);
            network.get(a).add(roadab);
        }

        if(network.containsKey(b)){
            Road roadba = new Road(b,a,length);
            network.get(b).add(roadba);
        }

    }

    // returnerer byens position.
    public Position position(City city){
        return new Position(city, city, 0);
    }

    // Gør klar til at påbegynde rejse.
    public Position readyToTravel(City from, City to){
        if (from.equals(to)){ 
            return position(from);
        }
        
        for (Road road : getRoads(from)) {
            if (road.getTo().equals(to)) {
                return new Position(from, to, road.getLength());
            }
        }
        return position(from);
    }
}


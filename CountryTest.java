import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class CountryTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD, cityE, cityF, cityG;
    private Map<City,List<Road>> network1, network2;
    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp() {
        game = new Game(0);
        game.getRandom().setSeed(0);
        network1 = new HashMap<>();
        network2 = new HashMap<>();

        // Create countries
        country1 = new Country("Country 1", network1);
        country2 = new Country("Country 2", network2);
        country1.setGame(game);
        country2.setGame(game);

        // Create Cities
        cityA = new City("City A", 80, country1);
        cityB = new City("City B", 60, country1);
        cityC = new City("City C", 40, country1);
        cityD = new City("City D", 100, country1);
        cityE = new City("City E", 50, country2);
        cityF = new City("City F", 90, country2);
        cityG = new City("City G", 70, country2);

        // Create road lists
        List<Road> roadsA = new ArrayList<Road>(),
                roadsB = new ArrayList<>(),
                roadsC = new ArrayList<>(),
                roadsD = new ArrayList<>(),
                roadsE = new ArrayList<>(),
                roadsF = new ArrayList<>(),
                roadsG = new ArrayList<>();

        network1.put(cityA, roadsA);
        network1.put(cityB, roadsB);
        network1.put(cityC, roadsC);
        network1.put(cityD, roadsD);
        network2.put(cityE, roadsE);
        network2.put(cityF, roadsF);
        network2.put(cityG, roadsG);

        // Create roads

        country1.addRoads(cityA, cityD, 5);
        country1.addRoads(cityB, cityD, 2);
        country1.addRoads(cityC, cityD, 2);
        country1.addRoads(cityC, cityE, 4);
        country1.addRoads(cityD, cityF, 3);
        country2.addRoads(cityE, cityC, 4);
        country2.addRoads(cityE, cityF, 2);
        country2.addRoads(cityE, cityG, 5);

    }

    @Test
    public void Country(){
        Country country1 = new Country("country1", network1);
        assertEquals(country1.getName(),"country1");
        assertEquals(country1.getNetwork(), network1);
    }

    @Test
    public void reset(){
        cityA.arrive();
        cityA.arrive();
        cityA.arrive();

        cityE.arrive();
        cityE.arrive();
        cityE.arrive();
        int valueE = cityE.getValue();  // remember value of cityE.
        country1.reset();
        assertEquals(cityA.getValue(),80); // cityA is reset.
        assertEquals(cityE.getValue(), valueE); // cityB is reset.
    }

    @Test
    public void bonus() throws Exception {
        for(int seed = 0; seed < 1000; seed++){ //Try 1000 different seeds
            game.getRandom().setSeed(seed);
            int sum = 0;
            int sum1 = 0;
            Set<Integer> values = new HashSet<>();
            Set<Integer> values1 = new HashSet<>();
                    for(int i = 0; i<10000; i++){ // Call method 10000 times
                        int bonus = country1.bonus(80);
                        assertTrue( 0<= bonus && bonus <= 80 );
                        sum += bonus;
                        values.add(bonus);
                        assertEquals(country1.bonus(0),0);
                    }
                    for(int i = 0; i<10000; i++){
                        int bonus1 = country2.bonus(1);
                        assertTrue(0<=bonus1 && bonus1 <= 1);
                        sum1 += bonus1;
                        values1.add(bonus1);
                    }
            assertTrue(375000 < sum && sum < 425000);
            assertTrue(4000 < sum1 && sum1 < 6000);
            assertEquals(values.size(), 81);
            assertEquals(values1.size(),2);
        }
    }

    @Test
    public void addRoads() throws Exception {
        List<Road> roads = network1.get(cityA);

        //tester når begge byer ligger i samme land.
        roads.add(new Road(cityA, cityB, 4));
        roads.add(new Road(cityB, cityA,4));
        country1.addRoads(cityA, cityB, 4);
        assertEquals(network1.get(cityA), roads);

        //tester når en by ligger i det ene land og den anden by i
        //et andet land.
        roads.add(new Road(cityA, cityF,3));
        country1.addRoads(cityA, cityF, 3);
        assertEquals(network1.get(cityA), roads);

        //tester når begge byer ikke er i landet.
        country1.addRoads(cityE,cityF,2);
        assertEquals(network1.get(cityA), roads);
    }

    @Test
    public void position() throws Exception {
        assertEquals(country1.position(cityA).getFrom(),cityA);
        assertEquals(country1.position(cityA).getTo(),cityA);
        assertEquals(country1.position(cityA).getDistance(),0);
    }

    @Test
    public void readyToTravel() throws Exception {
        assertEquals(country1.readyToTravel(cityA,cityD).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityA,cityD).getTo(), cityD);
        assertEquals(country1.readyToTravel(cityA,cityA).getFrom(), cityA);
        assertEquals(country1.readyToTravel(cityC,cityA).getTo(),cityC);

    }

}
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class CityTest {
    private Game game;
    private Country country1, country2;
    private City cityA, cityB, cityC, cityD;

    @Before
    public void setUp() throws Exception {
        game = new Game(0);
        game.getRandom().setSeed(0);
        Map<City, List<Road>> network1 = new HashMap<>();
        Map<City, List<Road>> network2 = new HashMap<>();

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
    }

    @Test
    public void City(){
        assertEquals(cityD.getValue(),100);
        assertEquals(cityD.getCountry(), country1);
        assertEquals(cityD.getName(), "City D");
        assertEquals(cityD.getInitialValue(),100);
    }

    @Test
    public void changeValue(){
        assertEquals(cityA.getInitialValue(), 80);
        cityA.changeValue(50);
        assertEquals(cityA.getValue(), 130);
        assertEquals(cityA.getInitialValue(),80);
        assertEquals(cityB.getInitialValue(),60);
        cityB.changeValue(-80);
        assertEquals(cityB.getValue(),-20);
    }

    @Test
    public void reset() throws Exception {
        cityA.changeValue(80);
        assertEquals(cityA.getValue(), cityA.getInitialValue()+80);
        cityA.reset();
        assertEquals(cityA.getValue(),cityA.getInitialValue());

        cityA.changeValue(-90);
        assertEquals(cityA.getValue(), cityA.getInitialValue()-90);
        cityA.reset();
        assertEquals(cityA.getValue(),cityA.getInitialValue());
    }

    @Test
    public void compareTo() throws Exception {
        assertTrue(cityA.compareTo(cityB) < 0);
        assertTrue(cityA.compareTo(cityC) < 0);
        assertTrue(cityB.compareTo(cityC) < 0);
        assertTrue(cityC.compareTo(cityA) > 0);
        assertTrue(cityB.compareTo(cityA) > 0);
        assertTrue(cityC.compareTo(cityB) > 0);
        assertEquals(cityA.compareTo(cityA), 0);

    }

    @Test
    public void arrive() throws Exception {
        for(int i = 0; i<1000 ; i++){       // Try different seeds
            game.getRandom().setSeed(i);    // Set seed
            int bonus = country1.bonus(80); // Remember bonus
            game.getRandom().setSeed(i);    // Reset seed
            int arrive = cityA.arrive();    // Same bonus
            assertEquals(arrive, bonus);
            assertEquals(cityA.getValue(), 80-bonus);
            cityA.reset();
        }

    }

}
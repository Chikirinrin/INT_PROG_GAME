import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CityTest {
    private Country country1;
    private City cityA, cityB, cityC;
    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
        country1 = new Country("country1", null);
        cityA = new City("cityA", 80, country1);
        cityB = new City("cityB", 70, country1);
        cityC = new City("cityC", 10, country1);
    }

    @Test
    public void reset() throws Exception {
        cityA.changeValue(80);
        assertEquals(cityA.getValue(),160);
        cityA.reset();
        assertEquals(cityA.getValue(),cityA.getInitialValue());

        cityA.changeValue(-80);
        assertEquals(cityA.getValue(),0);
        cityA.reset();
        assertEquals(cityA.getValue(),cityA.getInitialValue());

        cityA.changeValue(-90);
        assertEquals(cityA.getValue(),-10);
        cityA.reset();
        assertEquals(cityA.getValue(),cityA.getInitialValue());
    }

    @Test
    public void compareTo() throws Exception {
        assertTrue(cityA.compareTo(cityB) < 0);
        assertTrue(cityA.compareTo(cityC) < 0);
        assertTrue(cityB.compareTo(cityC) < 0);
        assertTrue(cityC.compareTo(cityA) > 0);
        assertTrue(cityA.compareTo(cityA) == 0);
    }

    @Test
    public void arrive() throws Exception {
        for(int i = 0; i<1000 ; i++){       // Try different seeds
            game.getRandom().setSeed(i);    // Set seed
            int bonus = country1.bonus(70); // Remember bonus
            game.getRandom().setSeed(i);    // Reset seed
            int arrive = cityA.arrive();    // Same bonus
            assertEquals(arrive, bonus);
            assertEquals(cityA.getValue(), cityA.getInitialValue()-bonus);
            cityA.reset();
        }

    }

}
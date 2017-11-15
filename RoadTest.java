import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.*;

public class RoadTest {
    private Country country1, country2;
    private City cityA, cityB, cityC;

    @Before
    public void setUp() {
        country1 = new Country("country 1",null);
        cityA = new City("CityA",80, country1);
        cityB = new City("CityB",70, country1);
        cityC = new City("CityC", 10, country1);
    }



    @Test
    public void Road(){
        Road road = new Road(cityA, cityB,4);
        assertEquals(road.getFrom(), cityA);
        assertEquals(road.getTo(), cityB);
    }

    @Test
    public void compareTo(){
        Road road1 = new Road(cityA, cityB, 0);
        Road road2 = new Road(cityA, cityC, 0);
        Road road3 = new Road(cityB, cityA, 0);
        assertTrue(road1.compareTo(road2) < 0);
        assertTrue(road1.compareTo(road3) < 0);
        assertTrue(road1.compareTo(road2) < 0);
        assertTrue(road3.compareTo(road1) > 0);
        assertTrue(road2.compareTo(road1) > 0);
        assertEquals(road1.compareTo(road1), 0);
    }


}
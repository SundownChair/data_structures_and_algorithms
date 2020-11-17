package com.pedrofonseca.dsalgo.design_patterns;

import com.pedrofonseca.dsalgo.design_patterns.factory_method.Planet;
import com.pedrofonseca.dsalgo.design_patterns.factory_method.PlanetFactory;
import com.pedrofonseca.dsalgo.design_patterns.factory_method.Star;
import com.pedrofonseca.dsalgo.design_patterns.factory_method.StarFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoryMethodTest {
    @Test
    public void testFactoryMethod() {
        StarFactory sFactory = new StarFactory();
        Star star = (Star) sFactory.createCelestialBody();
        System.out.println(star.getBodyType());
        assertEquals("Star with mass of 0 that will not go supernova", star.getBodyType());

        PlanetFactory pFactory = new PlanetFactory();
        Planet planet = (Planet) pFactory.createCelestialBody();
        System.out.println(planet.getBodyType());
        assertEquals("Planet with static orbit and mass of 0", planet.getBodyType());
    }
}

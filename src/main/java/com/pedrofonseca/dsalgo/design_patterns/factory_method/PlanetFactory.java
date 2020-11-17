package com.pedrofonseca.dsalgo.design_patterns.factory_method;

public class PlanetFactory implements CelestialBodyFactory {

    @Override
    public CelestialBody createCelestialBody() {
        return new Planet();
    }
}

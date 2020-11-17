package com.pedrofonseca.dsalgo.design_patterns.factory_method;

public class StarFactory implements CelestialBodyFactory {

    @Override
    public CelestialBody createCelestialBody() {
        return new Star();
    }
}

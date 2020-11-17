package com.pedrofonseca.dsalgo.design_patterns.factory_method;

public class Planet implements CelestialBody {

    private String orbit;

    private int mass;

    public Planet() {
        this("static", 0);
    }

    public Planet(final String pOrbit, final int pMass) {
        orbit = pOrbit;
        mass = pMass;
    }

    @Override
    public String getBodyType() {
        return "Planet with " + orbit + " orbit and mass of " + mass;
    }

    public String getOrbit() {
        return orbit;
    }

    public void setOrbit(String pOrbit) {
        orbit = pOrbit;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int pMass) {
        mass = pMass;
    }
}

package com.pedrofonseca.dsalgo.design_patterns.factory_method;

public class Star implements CelestialBody {

    private boolean willSupernova;

    private int mass;

    public Star() {
        this(false, 0);
    }

    public Star(final boolean pWillSupernova, final int pMass) {
        willSupernova = pWillSupernova;
        mass = pMass;
    }

    @Override
    public String getBodyType() {
        return "Star with mass of " + mass + (willSupernova ? " that will go supernova" : " that will not go supernova");
    }

    public boolean isWillSupernova() {
        return willSupernova;
    }

    public void setWillSupernova(boolean pWillSupernova) {
        willSupernova = pWillSupernova;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int pMass) {
        mass = pMass;
    }
}

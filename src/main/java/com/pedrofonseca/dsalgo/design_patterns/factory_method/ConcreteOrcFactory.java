package com.pedrofonseca.dsalgo.design_patterns.factory_method;

import com.pedrofonseca.dsalgo.design_patterns.objects.*;

public class ConcreteOrcFactory implements OrcFactory {

    @Override
    public Orc createOrc(OrcEnum orcType) {
        if(orcType == null)
            return null;

        switch (orcType) {
            case PEON:
                return new PeonOrc();
            case WIZARD:
                return new WizardOrc();
            case WARRIOR:
                return new WarriorOrc();
            default:
                return null;
        }
    }
}

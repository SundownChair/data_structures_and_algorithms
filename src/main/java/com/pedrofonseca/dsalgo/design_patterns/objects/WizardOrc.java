package com.pedrofonseca.dsalgo.design_patterns.objects;

import java.sql.SQLOutput;

public class WizardOrc implements Orc {
    @Override
    public String work() {
        return "Wizards don't work, they study.";
    }

    @Override
    public String fight() {
        return "Pew pew!!";
    }
}

package com.pedrofonseca.dsalgo.design_patterns.objects;

public class PeonOrc  implements Orc{
    @Override
    public String work() {
        return "Work, work.";
    }

    @Override
    public String fight() {
        return "Zug-zug.";
    }
}

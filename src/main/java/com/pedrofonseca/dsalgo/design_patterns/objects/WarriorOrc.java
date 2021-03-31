package com.pedrofonseca.dsalgo.design_patterns.objects;

public class WarriorOrc implements Orc {
    @Override
    public String work() {
        return "Lok'tar.";
    }

    @Override
    public String fight() {
        return "Lok'tar Ogar!";
    }
}

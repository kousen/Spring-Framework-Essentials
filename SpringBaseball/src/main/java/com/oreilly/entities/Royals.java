package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public class Royals implements Team {
    @Override
    public String getName() {
        return "Kansas City Royals";
    }

    public String toString() {
        return getName();
    }
}

package com.oreilly.entities;

import org.springframework.stereotype.Component;

@Component
public class Cubs implements Team {
    @Override
    public String getName() {
        return "Chicago Cubs";
    }

    public String toString() {
        return getName();
    }
}

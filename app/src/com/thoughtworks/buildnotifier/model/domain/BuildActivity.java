package com.thoughtworks.buildnotifier.model.domain;

public enum BuildActivity {
    SLEEPING, BUILDING;

    public static BuildActivity parse(String activity) {
        for (BuildActivity buildActivity : BuildActivity.values())
            if (buildActivity.matches(activity))
                return buildActivity;
        throw new IllegalArgumentException("Invalid Activity " + activity);
    }

    private boolean matches(String s) {
        return s.equalsIgnoreCase(toString());
    }
}

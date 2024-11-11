package com.ada.holiday_party_planning.enums;

public enum CategoryFun {
    minion(true),
    yoda(false),
    hodor(false);

    private final boolean repeat;

    CategoryFun(boolean repeat) {
        this.repeat = repeat;
    }

    public boolean isRepeat() {
        return repeat;
    }
}
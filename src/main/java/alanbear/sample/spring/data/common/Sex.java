package alanbear.sample.spring.data.common;

import lombok.Getter;

public enum Sex {
    male("1"),
    female("0"),
    other("3");

    @Getter
    private String id;

    Sex(String id) {
        this.id = id;
    }
}

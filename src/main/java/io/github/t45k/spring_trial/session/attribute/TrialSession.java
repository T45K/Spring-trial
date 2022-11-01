package io.github.t45k.spring_trial.session.attribute;

import lombok.Data;

import java.io.Serializable;

@Data
public class TrialSession implements Serializable {
    private static final String SESSION_KEY = "trial";

    private int id;
    private String name;
}

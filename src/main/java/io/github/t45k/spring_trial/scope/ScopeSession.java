package io.github.t45k.spring_trial.scope;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serial;
import java.io.Serializable;

@Component
@SessionScope
@Data
public class ScopeSession implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    private int count;
}

package io.github.t45k.spring_trial.scope;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScopeController {
    private final ScopeSession scopeSession;

    @GetMapping("scope")
    public int id() {
        scopeSession.setCount(scopeSession.getCount() + 1);
        return scopeSession.getCount();
    }
}

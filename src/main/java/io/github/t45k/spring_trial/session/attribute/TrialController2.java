package io.github.t45k.spring_trial.session.attribute;

import io.github.t45k.spring_trial.session.model.Request;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

// これでもいける？
@RestController
@RequestMapping("2")
public class TrialController2 {

    @GetMapping
    public TrialSession index(@SessionAttribute("trial2") final TrialSession trialSession,
                              final HttpSession session) {
        System.out.println(trialSession);
        System.out.println(session.getAttribute("trial2"));
        return trialSession;
    }

    @PostMapping
    public TrialSession post(@ModelAttribute final Request request,
                             @SessionAttribute("trial2") final TrialSession trialSession) {
        trialSession.setId(request.id());
        trialSession.setName(request.name());

        return trialSession;
    }

    @ModelAttribute("trial2") // SessionAttributeを使うなら、ここで初期化はできないらしい。
    public TrialSession init() { // なので、SessionAttributeはバインドする時だけ
        return new TrialSession();
    }
}

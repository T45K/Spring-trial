package io.github.t45k.spring_trial.session.attributes;

import io.github.t45k.spring_trial.session.model.Request;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * SessionAttributesを使ったセッション管理
 */
@RestController
@SessionAttributes(types = TrialSession.class) // メソッド抜けたタイミングでこの型のオブジェクトがセッションに保存されるっぽい
@Slf4j
@RequestMapping("1")
public class TrialController {

    @GetMapping
    public Map<String, String> index(@ModelAttribute("trial") final TrialSession trialSession,
                                     final HttpSession session) {
        System.out.println(trialSession); // 2. 最初にこのエンドポイントが呼ばれた時、1. で作られたセッションを見る
        System.out.println(session.getAttribute("trial")); // 3. 最初に呼ばれた時はまだtrialセッションは保存されていない
        // 5: 2回目以降は普通に見れる
        return Map.of("id", "1", "name", "Taro");
    } // 4. メソッドを抜けたタイミングでセッションにキーが"trial"でオブジェクトが保存される

    @PostMapping
    public Map<String, String> post(@ModelAttribute final Request request,
                                    @ModelAttribute("trial") final TrialSession trialSession) {
        System.out.println(request);
        trialSession.setId(request.id());
        trialSession.setName(request.name());

        return Map.of("status", "ok");
    }

    @ModelAttribute("trial") // "trial"がセッションのキーになる
    public TrialSession init() {
        return new TrialSession(); // 1. セッションが存在しない時、このメソッドが呼ばれる
    }
}

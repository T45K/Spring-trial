package io.github.t45k.spring_trial.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * MockMvcでCookieを扱うためのテスト
 */
@RestController
public class TestController {

    @GetMapping("test")
    public int test(final HttpSession session, final HttpServletRequest request) {
        final int count = Optional.ofNullable(session.getAttribute("count"))
            .map(it -> (int) it)
            .orElse(0);
        session.setAttribute("count", count + 1);
        return count;
    }
}

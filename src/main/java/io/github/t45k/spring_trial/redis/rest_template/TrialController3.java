package io.github.t45k.spring_trial.redis.rest_template;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("3")
@RequiredArgsConstructor
public class TrialController3 {
    private final StringRedisTemplate redisTemplate;

    @GetMapping("{key}")
    public Long increment(@PathVariable("key") final String key) {
        return redisTemplate.execute(new SessionCallback<List<Long>>() {
            @Override
            public List<Long> execute(final RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForValue().increment(key);
                // ここに処理いっぱい書く
                return operations.exec();
            }
        }).get(0);
    }
}

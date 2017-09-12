package com.rakesh.ratelimiter.repository;


import com.rakesh.ratelimiter.conf.pojo.Client;
import com.rakesh.ratelimiter.conf.pojo.Rate;
import com.rakesh.ratelimiter.pojo.RateLimitContext;
import com.rakesh.ratelimiter.util.RateGeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Map;

/**
 * Abstract Implementation of RateLimiter
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public abstract class AbstractRateLimiter implements RateLimiter {

    static Logger LOGGER = LoggerFactory.getLogger(AbstractRateLimiter.class);

    @Override
    public Map<String, Rate> accept(Client client, RateLimitContext context) {
        Map<String, Rate> keyToRates = RateGeneratorUtils.getKeyToRate(client, context);
        LOGGER.info("configured keys : {} for client-id : {}  ", keyToRates , context.getClientId());
        keyToRates.forEach((k, v) -> {
            Rate rate = createIfAbsent(v);
            this.updateRate(rate);
            this.saveRate(rate);
            LOGGER.info("updated key : {} and request remaining : {} ", k, v.getLimit());

        });
        return keyToRates;
    }


    private Rate createIfAbsent(Rate rate) {
        Rate oldRate = this.getRate(rate.getKey());
        if (isExpired(oldRate)) {
            return rate;
        }
        rate.setLimit(oldRate.getLimit());
        rate.setExpiry(oldRate.getExpiry());
        return rate;
    }

    private void updateRate(final Rate rate) {
        rate.setLimit(Math.max(rate.getLimit() - 1,-1));
    }

    private boolean isExpired(Rate rate) {
        return rate == null || (rate.getExpiry().getTime() < System.currentTimeMillis());
    }

    protected abstract Rate getRate(String key);

    protected abstract void saveRate(Rate rate);
}

package com.rakesh.ratelimiter.util;


import com.rakesh.ratelimiter.conf.pojo.*;
import com.rakesh.ratelimiter.conf.pojo.Client;
import com.rakesh.ratelimiter.conf.pojo.Rate;
import com.rakesh.ratelimiter.conf.pojo.Time;
import com.rakesh.ratelimiter.pojo.RateLimitContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.rakesh.ratelimiter.util.RateLimiterUtils.get;
import static java.util.concurrent.TimeUnit.*;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Rates Generator Util.
 *
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

public class RateGeneratorUtils {

    static Logger LOGGER = LoggerFactory.getLogger(RateGeneratorUtils.class);

    public static Map<String, Long> milliSeconds = new HashMap<String, Long>() {
        {
            put("sec", SECONDS.toMillis(1));
            put("min", MINUTES.toMillis(1));
            put("hour", HOURS.toMillis(1));
            put("week", DAYS.toMillis(1 * 7));
            put("month", DAYS.toMillis(1 * 30));
        }
    };


    public static Map<String, Rate> getKeyToRate(Client client, RateLimitContext context) {

        Map<String, Rate> keyToRate = new ConcurrentHashMap<String, Rate>();

        if(client==null){
            LOGGER.info("no configuration found for client-id : {} ", context.getClientId());
        }

        putAll(keyToRate, getRates(Time.class, (get(() -> client.getLimit(), null)),
                new StringJoiner(":").add(context.getClientId()).toString()));

        if ("get".equals(context.getMethod())) {
            putAll(keyToRate, getRates(Time.class, (get(() -> client.getSpecialization().getMethod().getGet().getLimit(), null)),
                    new StringJoiner(":").add(context.getClientId()).add("get").toString()));
        }

        if ("post".equals(context.getMethod())) {
            putAll(keyToRate, getRates(Time.class, (get(() -> client.getSpecialization().getMethod().getPost().getLimit(), null)),
                    new StringJoiner(":").add(context.getClientId()).add("post").toString()));
        }
        if ((get(() -> client.getSpecialization().getApi().get(context.getApi()), null) != null)) {
            putAll(keyToRate, getRates(Time.class, (get(() -> client.getSpecialization().getApi().get(context.getApi()).getLimit(), null)),
                    new StringJoiner(":").add(context.getClientId()).add(context.getApi()).toString()));
        }

        return keyToRate;

    }


    private static void putAll(Map<String, Rate> rateMap, List<Rate> rates) {
        for (Rate rate : rates) {
            Optional<Integer> optionalValue = Optional.ofNullable(rate.getLimit());
            if (optionalValue.isPresent()) {
                rateMap.put(rate.getKey(), rate);
            }
        }
    }

    private static <S, T> List<Rate> getRates(Class<S> clazz, T instance, String keyPrefix) {
        List<Rate> rates = new ArrayList<Rate>();
        if (instance != null) {
            try {
                for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                    if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                        rates.add(new Rate(
                                keyPrefix + ":" + pd.getName(),
                                (Integer) pd.getReadMethod().invoke(instance),
                                new Date(System.currentTimeMillis() + milliSeconds.get(pd.getName()))));

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rates;
    }

}

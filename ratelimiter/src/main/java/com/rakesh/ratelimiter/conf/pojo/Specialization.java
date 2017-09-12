package com.rakesh.ratelimiter.conf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Specialization {
    public Map<String, Limit> api = new HashMap<String, Limit>();
    private Method method;
}


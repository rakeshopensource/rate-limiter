package com.rakesh.ratelimiter.conf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rate {
    private String key;
    private Integer limit;
    private Date expiry;
}

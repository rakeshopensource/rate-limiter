package com.rakesh.ratelimiter.conf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rakesh Rathi
 * @since 2017-09-11
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    private Integer min;
    private Integer sec;
    private Integer hour;
    private Integer week;
    private Integer month;
}
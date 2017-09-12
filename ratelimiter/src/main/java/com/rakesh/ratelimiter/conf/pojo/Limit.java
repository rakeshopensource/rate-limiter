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
public class Limit {
    private Time limit;

}

package com.phonepe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Dumb Recommendation controller
 *
 * @author Rakesh Rathi
 * @since  2017-09-11
 */

@RestController
public class RecommendationController {

    @RequestMapping(value = "/recommendation", method = RequestMethod.GET)
    public String recommendation() {
        return "iPhone X" ;
    }
}

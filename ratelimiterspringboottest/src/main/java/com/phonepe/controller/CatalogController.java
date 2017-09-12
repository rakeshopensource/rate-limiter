package com.phonepe.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Dumb Catalog controller
 *
 * @author Rakesh Rathi
 * @since  2017-09-11
 */

@RestController
public class CatalogController {

    @RequestMapping(value = "/catalog", method = RequestMethod.GET)
    public String paymentService() {
        return "Hello from catalog service";
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public String paymentService(@RequestBody Name name) {
        String str ="{\"greeting\":\"Hello from payment service\"}";
        return str;
    }

    static class Name {
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String name;
    }



}

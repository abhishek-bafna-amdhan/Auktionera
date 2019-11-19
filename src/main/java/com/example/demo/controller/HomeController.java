package com.example.demo.controller;

import com.example.demo.model.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HomeController {


    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "Auction Site";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signIn() {
        //ToDo
        return null;
    }

    @RequestMapping(value = "/signout", method = RequestMethod.GET)
    public void signOutUser() {
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
    public Account createUser(@RequestBody Account account) {
    return account;
    }
}

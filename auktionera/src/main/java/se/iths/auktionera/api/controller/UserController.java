package se.iths.auktionera.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.iths.auktionera.business.model.Auction;
import se.iths.auktionera.business.model.User;
import se.iths.auktionera.business.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/users")
    public List<User> getUsers(@RequestParam Map<String, String> filter, @RequestParam Map<String, String> sort, HttpServletRequest request) {
        return userService.getUsers(filter, sort);
    }

    @GetMapping("/api/users/{id}")
    public User getOneUser(HttpServletRequest request) {
        return userService.getUserById((String)  request.getAttribute("authId"));
    }

    @GetMapping("/api/users/{id}/auctions")
    public List<Auction> getAuctionsByUser(@RequestParam Map<String, String> filter, @RequestParam Map<String, String> sort, HttpServletRequest request) {
        return null;
    }
}

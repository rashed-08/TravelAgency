package com.web.travelagency.controller;

import com.web.travelagency.model.Location;
import com.web.travelagency.model.Posts;
import com.web.travelagency.model.User;
import com.web.travelagency.service.impl.LocationServiceImpl;
import com.web.travelagency.service.impl.PostServiceImpl;
import com.web.travelagency.service.impl.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final UserServiceImpl userService;
    private final PostServiceImpl postService;
    private final LocationServiceImpl locationService;

    @Autowired
    public HomeController(UserServiceImpl userService, PostServiceImpl postService, LocationServiceImpl locationService) {
        this.userService = userService;
        this.postService = postService;
        this.locationService = locationService;
    }


    @RequestMapping(path = {"/", "/index", "/home"})
    public String gotoHome(Model model) {
        try {
            List<Posts> showPublicPost = postService.getAllPublicPost(true);
            model.addAttribute("posts", showPublicPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @RequestMapping(path = "/register", method = RequestMethod.GET)
    public String userRegister(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute final User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        userService.createUser(user);
        return "redirect:/login";
    }

    @RequestMapping(path = "/login")
    public String showLogin() {
        return "login";
    }

    @RequestMapping(path = "/profile")
    public String showProfile(Model model) {
        List<Posts> getAllPost = postService.getAllPosts();
        model.addAttribute("posts", getAllPost);
        return "profile";
    }

    @RequestMapping(path = "/post")
    public String showPost(Model model) {
        List<Location> getAllLocation = locationService.getAllLocation();
        model.addAttribute("post", new Posts());
        model.addAttribute("locations", getAllLocation);
        return "post";
    }

    @RequestMapping(path = "/post", method = RequestMethod.POST)
    public String createPost(@ModelAttribute final Posts posts, BindingResult result) {
        if (result.hasErrors()) {
            return "redirect:/post";
        }
        postService.createPost(posts);
        return "redirect:/profile";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String updatePost(@RequestParam("id") Integer id, Model model) {
        Optional<Posts> post = postService.updatePost(id);
        model.addAttribute("post", post);
        return "redirect:/profile";
    }
}

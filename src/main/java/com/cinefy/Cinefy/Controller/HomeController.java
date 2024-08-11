package com.cinefy.Cinefy.Controller;


import com.cinefy.Cinefy.dao.UserRepository;
import com.cinefy.Cinefy.dto.MovieDTO;
import com.cinefy.Cinefy.model.Movie;
import com.cinefy.Cinefy.model.User;
import com.cinefy.Cinefy.service.CustomUserDetails;
import com.cinefy.Cinefy.service.ToWatchMovieService;
import com.cinefy.Cinefy.service.WatchedMovieService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ToWatchMovieService toWatchMovieService;

    @Autowired
    private WatchedMovieService watchedMovieService;

    @GetMapping("/signup")
    public String signup(Model model, HttpSession session){
        model.addAttribute("title", "Register");
        model.addAttribute("user", new User());
        return "signup" ;
    }

    @GetMapping("/login")
    public String customLogin(Model model){
        model.addAttribute("title", "Login Page");
        model.addAttribute("user", new User());

        return "login";
    }

    @PostMapping("/do-register")
    public String registerUser(@ModelAttribute User user){
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        System.out.println(user);
        userRepository.save(user);
        System.out.println(user);
        return "redirect:/signup";
    }

    @PostMapping("/login")
    public String verifyLogin(@ModelAttribute("user") User user, Model model){
        System.out.println("You are inside verifyLogin method.");
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }
    @GetMapping("/dashboard")
    public String findHome(Model model){
        model.addAttribute("movieDTO", new MovieDTO());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();
        List<Movie> toWatchMovies = toWatchMovieService.getToWatchMoviesForUser(user);
        List<Movie> watchedMovies = watchedMovieService.getMoviesByUser(user);
        model.addAttribute("watchedMovies", watchedMovies);
        model.addAttribute("toWatchMovies", toWatchMovies);
        return "/dashboard";
    }
}

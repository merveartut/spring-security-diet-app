package com.fitkal.controller;

import com.fitkal.models.Diyetisyen;
import com.fitkal.models.Role;
import com.fitkal.models.User;
import com.fitkal.payload.*;
import com.fitkal.repository.DiyetsyenRepository;
import com.fitkal.repository.RoleRepository;
import com.fitkal.repository.UserRepository;
import com.fitkal.security.services.JwtUtils;
import com.fitkal.security.services.MyUserDetailService;
import com.fitkal.security.services.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiyetsyenRepository diyetsyenRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private MyUserDetailService userDetailsService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),

                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signin/diyetisyen")
    public ResponseEntity<?> authenticateDiy(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),

                userDetails.getEmail(),
                roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getName(),signUpRequest.getSurname(),signUpRequest.getPhone(),signUpRequest.getDate(),signUpRequest.getWeight(),signUpRequest.getHeight(),signUpRequest.getHealthProblem(),signUpRequest.getAllergy());

        String strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/signup/diyetisyen")
    public ResponseEntity<?> registerDiyet(@Valid @RequestBody SignupRequestDiy signUpRequest) {
        if (diyetsyenRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already taken!"));
        }


        // Create new user's account
        Diyetisyen diyetisyen = new Diyetisyen(
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),signUpRequest.getName(),signUpRequest.getSurname(),signUpRequest.getPhone(),signUpRequest.getDate());

        String strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        Role userRole = roleRepository.findByName("ROLE_DIYETISYEN")
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(userRole);

        diyetisyen.setRoles(roles);
        diyetsyenRepository.save(diyetisyen);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
@GetMapping("/profile")
public String currentUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        UserDetail userDetails = (UserDetail) authentication.getPrincipal();
        return userDetails.getUsername();

}
}



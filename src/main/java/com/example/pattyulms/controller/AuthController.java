package com.example.pattyulms.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pattyulms.models.EUserRole;
import com.example.pattyulms.models.UserModel;
import com.example.pattyulms.models.UserRole;
import com.example.pattyulms.payload.request.LoginRequest;
import com.example.pattyulms.payload.request.SignupRequest;
import com.example.pattyulms.payload.response.JwtResponse;
import com.example.pattyulms.payload.response.MessageResponse;
import com.example.pattyulms.repository.RoleRepo;
import com.example.pattyulms.repository.UserRepo;
import com.example.pattyulms.repository.UserSequenceGenService;
import com.example.pattyulms.security.jwt.JwtUtils;
import com.example.pattyulms.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        UserRepo userRepo;

        @Autowired
        PasswordEncoder encoder;

        @Autowired
        JwtUtils jwtUtils;

        @Autowired
        RoleRepo roleRepo;

        @Autowired
        private UserSequenceGenService userSequenceGenService;

        @PostMapping("/signin")
        public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

                Authentication authentication = authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                                loginRequest.getPassword()));

                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtUtils.generateJwtToken(authentication);

                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
                List<String> roles = userDetails.getAuthorities().stream()
                                .map(item -> item.getAuthority())
                                .collect(Collectors.toList());

                return ResponseEntity.ok(new JwtResponse(jwt,
                                userDetails.getUserID(),
                                userDetails.getEmail(),
                                userDetails.getUsername(),
                                userDetails.getFirstname(),
                                userDetails.getLastname(),
                                roles));
        }

        @PostMapping("/signup")
        public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
                if (userRepo.existsByUsername(signUpRequest.getUsername())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new MessageResponse("Error: Username is already taken!"));
                }

                if (userRepo.existsByEmail(signUpRequest.getEmail())) {
                        return ResponseEntity
                                        .badRequest()
                                        .body(new MessageResponse("Error: Email is already in use!"));
                }

                try {
                        // Create new user's account
                        UserModel user = new UserModel(null,
                                        signUpRequest.getEmail(), signUpRequest.getUsername(),
                                        encoder.encode(signUpRequest.getPassword()), signUpRequest.getFirstname(),
                                        signUpRequest.getLastname());

                        Long userID = userSequenceGenService.generateSequence(UserModel.SEQUENCE_NAME);
                        user.setUserID(userID);

                        Set<String> strRoles = signUpRequest.getRoles();
                        Set<UserRole> roles = new HashSet<>();

                        if (strRoles == null) {
                                UserRole userRole = roleRepo.findByName(EUserRole.USER)
                                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(userRole);
                        } else {
                                strRoles.forEach(role -> {
                                        switch (role) {
                                                case "pattyulms":
                                                        UserRole adminRole = roleRepo.findByName(EUserRole.OMNI)
                                                                        .orElseThrow(() -> new RuntimeException(
                                                                                        "Error: Role is not found."));
                                                        roles.add(adminRole);

                                                        break;
                                                case "admin":
                                                        UserRole modRole = roleRepo.findByName(EUserRole.ADMIN)
                                                                        .orElseThrow(() -> new RuntimeException(
                                                                                        "Error: Role is not found."));
                                                        roles.add(modRole);

                                                        break;
                                                default:
                                                        UserRole userRole = roleRepo.findByName(EUserRole.USER)
                                                                        .orElseThrow(() -> new RuntimeException(
                                                                                        "Error: Role is not found."));
                                                        roles.add(userRole);
                                        }
                                });
                        }

                        user.setRoles(roles);
                        userRepo.insert(user);

                        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
                } catch (Exception e) {
                        // If there is an exception, we will print the exception and return an error
                        // Nothing will be posted if their is an error
                        System.out.println(e);
                        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

                }
        }

}

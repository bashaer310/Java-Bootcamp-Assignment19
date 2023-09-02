package com.example.assignment17.Controller;


import com.example.assignment17.Model.User;
import com.example.assignment17.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("user registered");
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout");
    }


}

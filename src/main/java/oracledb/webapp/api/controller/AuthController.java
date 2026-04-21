package oracledb.webapp.api.controller;

import lombok.AllArgsConstructor;
import oracledb.webapp.api.dto.SignUpRequest;
import oracledb.webapp.api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        Map<String, Object> response = new HashMap<>();

        if ("admin".equals(username) && "admin123".equals(password)) {
            response.put("message", "Login successful");
            response.put("token", "dummy-jwt-token");
            return ResponseEntity.ok(response);
        }

        response.put("message", "Invalid credentials");
        return ResponseEntity.status(401).body(response);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> dashboard() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalEmployees", 150);
        data.put("activeDepartments", 12);
        data.put("pendingTasks", 8);
        data.put("monthlyRevenue", "₹12,50,000");
        return ResponseEntity.ok(data);
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.createUser(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signUp(request));
    }
}

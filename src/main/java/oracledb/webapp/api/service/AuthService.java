package oracledb.webapp.api.service;

import lombok.AllArgsConstructor;
import oracledb.webapp.api.dto.SignUpRequest;
import oracledb.webapp.api.entity.User;
import oracledb.webapp.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthService {

    @Autowired
    private final UserRepository userRepository;

    public Map<String, Object> login(String username, String password) {
        Map<String, Object> response = new HashMap<>();

        if ("admin".equals(username) && "admin123".equals(password)) {
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("token", "dummy-jwt-token");
            response.put("role", "ADMIN");
        } else {
            response.put("success", false);
            response.put("message", "Invalid credentials");
        }

        return response;
    }

    public Map<String, Object> getDashboardData() {
        Map<String, Object> data = new HashMap<>();
        data.put("totalEmployees", 150);
        data.put("activeDepartments", 12);
        data.put("pendingTasks", 8);
        data.put("monthlyRevenue", "₹12,50,000");
        data.put("newJoinees", 5);
        return data;
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> response = new HashMap<>();


        if (userRepository.existsByUsername(user.getUsername())) {
            response.put("success", false);
            response.put("message", "Username already exists");
            return response;
        }

        userRepository.save(user);

        response.put("success", true);
        response.put("message", "User registered successfully");
        return response;
    }

    public Map<String, Object> createUser(SignUpRequest request) {

        Map<String, Object> response = new HashMap<>();

        // check duplicate username
        if (userRepository.existsByUsername(request.getUsername())) {
            response.put("success", false);
            response.put("message", "Username already exists");
            return response;
        }

        // create entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setRole(request.getRole());
        user.setStatus("ACTIVE");

        // INSERT INTO ORACLE TABLE happens here
        userRepository.save(user);

        response.put("success", true);
        response.put("message", "User inserted successfully");
        response.put("username", user.getUsername());

        return response;
    }
    // ✅ SIGN UP METHOD
    public Map<String, Object> signUp(SignUpRequest request) {

        Map<String, Object> response = new HashMap<>();

        // 1. Check duplicate username
        if (userRepository.existsByUsername(request.getUsername())) {
            response.put("success", false);
            response.put("message", "Username already exists");
            return response;
        }

        // 2. Create new user entity
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // later replace with BCrypt
        user.setRole(request.getRole() != null ? request.getRole() : "USER");
        user.setStatus("ACTIVE");

        // 3. SAVE TO ORACLE DB (INSERT happens here)
        userRepository.save(user);

        // 4. Response
        response.put("success", true);
        response.put("message", "User registered successfully");
        response.put("username", user.getUsername());

        return response;
    }

}

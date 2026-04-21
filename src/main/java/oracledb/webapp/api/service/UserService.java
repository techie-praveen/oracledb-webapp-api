package oracledb.webapp.api.service;

import lombok.RequiredArgsConstructor;
import oracledb.webapp.api.entity.User;
import oracledb.webapp.api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // FETCH ALL USERS
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
package com.quyen.test.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.quyen.test.entity.Role;
import com.quyen.test.entity.User;
import com.quyen.test.model.request.UserRequest;
import com.quyen.test.model.response.UserResponse;
import com.quyen.test.repository.RoleRepository;
import com.quyen.test.repository.UserRepository;
import com.quyen.test.statics.Roles;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    private final RoleRepository roleRepository;

    public List<UserResponse> getAll() {
        List<User> users = userRepository.findAll();
        if (!CollectionUtils.isEmpty(users)) {
            return users.stream().map(u -> objectMapper.convertValue(u, UserResponse.class)).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public UserResponse getDetail(Long id) throws ClassNotFoundException {
        return userRepository.findById(id).map(u -> objectMapper.convertValue(u, UserResponse.class)).orElseThrow(ClassNotFoundException::new);
    }

    public UserResponse create(UserRequest request) {
        Optional<Role> roleOptional = roleRepository.findByName(Roles.USER.name());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt the password
        Set<Role> roles = new HashSet<>();
        roles.add(roleOptional.get());
        user.setRoles(roles);
        userRepository.save(user);

        return objectMapper.convertValue(user, UserResponse.class);
    }

}

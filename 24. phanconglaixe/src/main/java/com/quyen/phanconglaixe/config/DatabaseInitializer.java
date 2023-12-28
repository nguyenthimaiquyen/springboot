package com.quyen.phanconglaixe.config;

import com.quyen.phanconglaixe.entity.Role;
import com.quyen.phanconglaixe.entity.User;
import com.quyen.phanconglaixe.repository.RoleRepository;
import com.quyen.phanconglaixe.repository.UserRepository;
import com.quyen.phanconglaixe.statics.Roles;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class DatabaseInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = null;
        Role userRole = null;
        Optional<Role> userRoleOptional = roleRepository.findByName(Roles.USER.toString());
        if (userRoleOptional.isEmpty()) {
            userRole = Role.builder().name(Roles.USER.toString()).build();
            roleRepository.save(userRole);
        }
        Optional<Role> adminRoleOptional = roleRepository.findByName(Roles.ADMIN.toString());
        if (adminRoleOptional.isEmpty()) {
            adminRole = Role.builder().name(Roles.ADMIN.toString()).build();
            roleRepository.save(adminRole);
        }
        User admin = userRepository.findByUsername("admin");
        if (ObjectUtils.isEmpty(admin)) {
            User user1 = new User();
            user1.setUsername("admin");
            user1.setPassword(passwordEncoder.encode("admin123")); // Encrypt the password
            Set<Role> roles1 = new HashSet<>();
            roles1.add(adminRole);
            user1.setRoles(roles1);
            userRepository.save(user1);
        }
        User quyen = userRepository.findByUsername("quyen");
        if (ObjectUtils.isEmpty(quyen)) {
            User user2 = new User();
            user2.setUsername("quyen");
            user2.setPassword(passwordEncoder.encode("quyen")); // Encrypt the password
            Set<Role> roles2 = new HashSet<>();
            roles2.add(userRole);
            user2.setRoles(roles2);
            userRepository.save(user2);
        }
    }



}

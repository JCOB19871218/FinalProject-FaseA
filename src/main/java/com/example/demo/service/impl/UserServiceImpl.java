package com.example.demo.service.impl;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
//    private final ElasticSearchRepository searchRepository;
    private final PasswordEncoder encoder;

    @Override
    public UserResponseDto registerUser(UserRegisterDto dto) {

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(dto.getRole());
        user.setStatus(Status.PENDING);

        userRepository.save(user);


        return new UserResponseDto(user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole(),
                user.getStatus());
    }
}

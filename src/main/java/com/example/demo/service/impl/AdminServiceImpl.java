package com.example.demo.service.impl;


import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.Role;
import com.example.demo.entity.Status;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AdminService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
//    private final ElasticSearchRepository searchRepository;


    @Override
    public UserResponseDto updateUser(Long id, UserResponseDto dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());

        userRepository.save(user);
        return UserMapper.toDto(user);
    }

    @Override
    public UserResponseDto updateUserStatus(Long id, String status) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (status.equalsIgnoreCase("APPROVED")) user.setStatus(Status.APPROVED);
        else if (status.equalsIgnoreCase("REJECT")) user.setStatus(Status.REJECT);
        else throw new IllegalArgumentException("Invalid status: " + status);

        userRepository.save(user);
        return UserMapper.toDto(user);
    }


    @Override
    public List<UserResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.toDtoList(users);
    }

    @Override
    public void changeRole(Long id, String role) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User not found"));
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("user not found"));
        return UserMapper.toDto(user);
    }
}

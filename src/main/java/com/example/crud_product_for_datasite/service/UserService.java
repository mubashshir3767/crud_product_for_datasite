package com.example.crud_product_for_datasite.service;

import com.example.crud_product_for_datasite.ApiResponse;
import com.example.crud_product_for_datasite.dto.request.UserRequestDTO;
import com.example.crud_product_for_datasite.entity.UserEntity;
import com.example.crud_product_for_datasite.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ApiResponse addUser(UserRequestDTO userRequestDto) {

        Optional<UserEntity> currUser = userRepository.findByUsername(userRequestDto.getUsername());
        if (currUser.isPresent()) {
            return new ApiResponse(400,"user already exist");
        }

        UserEntity userEntity = userRepository.save(getEntity(userRequestDto));

        return new ApiResponse(
                200,
                "successfully added",
                userEntity
        );
    }

    private UserEntity getEntity(UserRequestDTO userRequestDto) {
        UserEntity build = UserEntity
                .builder()
                .name(userRequestDto.getName())
                .username(userRequestDto.getUsername())
                .userRole(userRequestDto.getUserRole())
                .build();
        return build;
    }

}

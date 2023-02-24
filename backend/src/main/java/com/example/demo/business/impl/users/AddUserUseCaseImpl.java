package com.example.demo.business.impl.users;

import com.example.demo.business.cases.users.AddUserUseCase;
import com.example.demo.domain.User;
import com.example.demo.persistence.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddUserUseCaseImpl implements AddUserUseCase {
    private final UserRepository userRepository;

    @Override
    public User AddUser(User user) {
        return userRepository.AddUser(user);
    }
}
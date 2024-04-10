package olen.olen;

import org.springframework.stereotype.Service;
import olen.olen.users;
import olen.olen.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(users user) {
        userRepository.save(user);
    }
}
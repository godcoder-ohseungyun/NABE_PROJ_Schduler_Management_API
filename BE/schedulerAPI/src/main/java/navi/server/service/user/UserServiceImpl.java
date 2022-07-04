package navi.server.service.user;

import navi.server.domain.user.User;
import navi.server.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User join(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUniqueId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(String LoginId, User updateParam) {
        return userRepository.update(LoginId,updateParam);
    }

    @Override
    public void out(Long id) {
        userRepository.delete(id);
    }
}

package navi.server.service.user;

import navi.server.domain.user.User;

public interface UserService {
    User join(User user);
    User findUserByUniqueId(Long id);
    User updateUser(String LoginId, User updateParam);
    void out(Long id);
}

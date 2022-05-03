package navi.server.repository.user;

import navi.server.domain.user.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);

    User findById(Long id);

    User update(String LoginId, User updateParam);

    void delete(Long id);

    void clearStore();
}

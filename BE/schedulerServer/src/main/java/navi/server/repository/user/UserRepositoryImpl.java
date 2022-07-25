package navi.server.repository.user;

import navi.server.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class UserRepositoryImpl implements UserRepository{

    private Map<Long,User> store = new ConcurrentHashMap<>();
    private Long uniqueId = 0L; //임시

    @Override
    public User save(User user) {
        user.setId(uniqueId++);//임시: 별도 unique id 생성 로직 짜야함
        store.put(user.getId(),user);
        return user;
    }

    @Override
    public User findById(Long id) {
        return store.get(id);
    }

    @Override
    public User update(String LoginId, User updateParam) {
        /**
         * 추가 예정
         */
        return null;
    }

    @Override
    public void delete(Long id) {
        store.remove(id);
    }

    @Override
    public void clearStore() {
        store.clear();
    }
}

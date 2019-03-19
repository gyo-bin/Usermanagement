package kr.hs.dgsw.web0319;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> users;

    public UserServiceImpl() {
        users = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            User user = new User("user" + (i + 1) , "User" + (i + 1), "user" + (i + 1) + "@email.com");
            users.add(user);
        }
    }

    @Override
    public List<User> list() {
        return users;
    }

    @Override
    public User view(String id) {
        return users.stream()
                .filter(user1 -> user1.getId().equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public boolean add(User user) {
        if (view(user.getId()) == null)
            return users.add(user);
        else
            return false;
    }

    @Override
    public User update(User user) {
        User found = view(user.getId());
        if (found != null) {
            found.setName(user.getName());
            found.setEmail(user.getEmail());
        }
        return found;
    }

    @Override
    public boolean delete(String id) {
        User found = view(id);
        return users.remove(found);
    }
}
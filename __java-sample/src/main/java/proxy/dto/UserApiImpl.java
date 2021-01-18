package proxy.dto;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Hazer
 * @since 2021/1/18 上午11:23
 */
public class UserApiImpl implements UserApi {
    @Override
    public User save(User user) {
        System.out.println("---UserApiImpl.save(User)---, User:" + user);
        return user;
    }
}

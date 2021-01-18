package nio.reactor;

import java.io.IOException;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-30 11:47
 */
@SuppressWarnings("all")
public class TestReactor {
    public static void main(String[] args) throws IOException {
        new Reactor(8080).run();
    }
}

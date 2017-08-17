package demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @author Eric
 * Date 2017/4/28 9:53
 * Desc
 */
class Zk extends Base {

    Zk() {
    }

    static ZooKeeper createZk(String connectionString) {
        try {
            return new ZooKeeper(connectionString, 1000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("WatchedEvent: " + GSON.toJson(watchedEvent));
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

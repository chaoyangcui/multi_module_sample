package demo;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author Eric
 * @Date 2017/4/28 9:53
 * @Desc
 */
public class Zk extends Base {
    private static ZooKeeper zooKeeper;

    public Zk() {
    }

    public static ZooKeeper createZk(String connectionString) {
        try {
            zooKeeper = new ZooKeeper(connectionString, 1000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {
                    System.out.println("WatchedEvent: " + GSON.toJson(watchedEvent));
                }
            });
            return zooKeeper;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.apache.zookeeper.ZooDefs.Ids;

/**
 * Created by cuiguiyang on 2017/4/26 20:21.
 * Desc
 */
public class ZookeeperDemo {
    private static final int CLIENT_PORT = 2181;

    static final String connectionString = "localhost:" + CLIENT_PORT;
    static ZooKeeper zk;

    static {
        try {
            zk = new ZooKeeper(connectionString,
                    ClientBase.CONNECTION_TIMEOUT,
                    (event) -> {
                        // System.out.println(event.getPath());
                        System.out.println("已经触发了" + event.getType() + "事件！");
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {

            /*// 创建一个目录节点
            zk.create("/testRootPath", "testRootData".getBytes(), Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
            // 创建一个子目录节点
            zk.create("/testRootPath/testChildPathOne", "testChildDataOne".getBytes(),
                    Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(new String(zk.getData("/testRootPath", false, null)));
            // 取出子目录节点列表
            System.out.println(zk.getChildren("/testRootPath", true));
            // 修改子目录节点数据
            zk.setData("/testRootPath/testChildPathOne", "modifyChildDataOne".getBytes(), -1);
            System.out.println("目录节点状态：[" + zk.exists("/testRootPath", true) + "]");
            // 创建另外一个子目录节点
            zk.create("/testRootPath/testChildPathTwo", "testChildDataTwo".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(new String(zk.getData("/testRootPath/testChildPathTwo", true, null)));
            // 删除子目录节点
            zk.delete("/testRootPath/testChildPathTwo", -1);
            zk.delete("/testRootPath/testChildPathOne", -1);
            // 删除父目录节点
            zk.delete("/testRootPath", -1);*/

            System.out.println(zk.getChildren("/_node_root", true));

            // 关闭连接
            zk.close();
            // } catch (IOException e) {
            //     e.printStackTrace();

            // byte[] localhost = InetAddress.getLocalHost().getAddress();
            // System.out.println(new String(localhost));
            System.out.println(InetAddress.getLocalHost().getHostAddress());

            System.out.println(System.getProperty("build.test.dir", "build"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}

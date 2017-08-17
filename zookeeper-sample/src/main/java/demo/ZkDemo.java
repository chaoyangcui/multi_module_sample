package demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.lang.reflect.InvocationTargetException;

/**
 * @Author Eric
 * @Date 2017/4/28 9:52
 * @Desc
 */
public class ZkDemo extends Zk {

    static final String zkConn = "localhost:2181";

    public static void main(String[] args) {
        ZooKeeper zooKeeper = createZk(zkConn);
        try {
            String zkNodee = zooKeeper.create("/_root", "data".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(zkNodee);
            /*Stat stat = new Stat();
            byte[] datas = zooKeeper.getData("/_root", false, stat);
            System.out.println(datas);
            System.out.println(GSON.toJson(stat));*/
            System.out.println(OBJECT_MAPPER.writeValueAsString(new ZkDemo()));


            BeanCopier beanCopier = BeanCopier.create(Zk.class, ZkDemo.class, true);
            Zk zk = new Zk();
            ZkDemo zkDemo = new ZkDemo();
            beanCopier.copy(zk, zkDemo, new Converter() {
                public Object convert(Object value, Class target, Object context) {
                    System.out.println(value);
                    System.out.println(target);
                    System.out.println(context);
                    return null;
                }
            });

            BeanUtils.copyProperty(zk, "name", zkDemo);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    public void zk() {
    }

}

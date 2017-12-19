package base;

import com.sssarm.model.RoomUserInfo;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiguiyang on 2017/3/5 21:23.
 * Desc
 */
public class OOMTest {

    public static void main(String[] args) {
        List<RoomUserInfo> list = new ArrayList<>();
        for (;;) {
            RoomUserInfo roomUserInfo = new RoomUserInfo();
            WeakReference<RoomUserInfo> weakReference = new WeakReference<>(roomUserInfo);
            list.add(weakReference.get());
        }
    }

    public static class OOM {
        public static void main(String[] args) {
            List<String> list = new ArrayList<>();
            int i = 0;
            while (true) {
                list.add(String.valueOf(i++).intern());
            }
        }
    }

}

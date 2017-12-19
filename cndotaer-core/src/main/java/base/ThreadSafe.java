package base;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Created by cuiguiyang on 2017/2/20 23:30.
 * Desc
 */
public class ThreadSafe {

    static final CopyOnWriteArrayList<String> COPY_ON_WRITE_ARRAY_LIST = new CopyOnWriteArrayList<>();

    static final CopyOnWriteArraySet<String> COPY_ON_WRITE_ARRAY_SET = new CopyOnWriteArraySet<>();

}

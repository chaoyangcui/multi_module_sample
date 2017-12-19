package zookeeper;

import org.junit.Assert;

import java.io.File;
import java.io.IOException;

/**
 * Created by cuiguiyang on 2017/4/26 20:29.
 * Desc
 */
public abstract class ClientBase {

    public static int CONNECTION_TIMEOUT = 30000;
    static final File BASETEST =
            new File(System.getProperty("build.test.dir", "build"));

    public ClientBase() {
        super();
    }

    public static File createTmpDir() throws IOException {
        return createTmpDir(BASETEST);
    }

    static File createTmpDir(File parentDir) throws IOException {
        File tmpFile = File.createTempFile("test", ".junit", parentDir);
        // don't delete tmpFile - this ensures we don't attempt to create
        // a tmpDir with a duplicate name
        File tmpDir = new File(tmpFile + ".dir");
        Assert.assertFalse(tmpDir.exists()); // never true if tmpfile does it's job
        Assert.assertTrue(tmpDir.mkdirs());

        return tmpDir;
    }

}

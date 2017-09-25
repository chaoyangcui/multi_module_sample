package xulingbo.zookeeper;

import org.apache.zookeeper.common.IOUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/25 16:29
 * Desc    Setting | Editor | File and Code Templates
 */
public class ClientBase {
    protected static final Logger LOG = LoggerFactory.getLogger(ClientBase.class);

    static final File BASETEST =
            new File(System.getProperty("build.test.dir", "build"));

    public ClientBase() {
        super();
    }

    /**
     * In general don't use this. Only use in the special case that you
     * want to ignore results (for whatever reason) in your test. Don't
     * use empty watchers in real code!
     *
     */

    public static class HostPort {
        String host;
        int port;
        public HostPort(String host, int port) {
            this.host = host;
            this.port = port;
        }
    }
    public static List<HostPort> parseHostPortList(String hplist) {
        ArrayList<HostPort> alist = new ArrayList<HostPort>();
        for (String hp: hplist.split(",")) {
            int idx = hp.lastIndexOf(':');
            String host = hp.substring(0, idx);
            int port;
            try {
                port = Integer.parseInt(hp.substring(idx + 1));
            } catch(RuntimeException e) {
                throw new RuntimeException("Problem parsing " + hp + e.toString());
            }
            alist.add(new HostPort(host,port));
        }
        return alist;
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

    private static int getPort(String hostPort) {
        String[] split = hostPort.split(":");
        String portstr = split[split.length-1];
        String[] pc = portstr.split("/");
        if (pc.length > 1) {
            portstr = pc[0];
        }
        return Integer.parseInt(portstr);
    }

    public static String readFile(File file) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
        try {
            IOUtils.copyBytes(is, os, 1024, true);
        } finally {
            is.close();
        }
        return os.toString();
    }

    public static String join(String separator, Object[] parts) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Object part : parts) {
            if (!first) {
                sb.append(separator);
                first = false;
            }
            sb.append(part);
        }
        return sb.toString();
    }
}

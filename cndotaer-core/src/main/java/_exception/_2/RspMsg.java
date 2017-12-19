package _exception._2;

/**
 * Created by cuiguiyang on 2017/4/25 21:09.
 * Desc
 */
public class RspMsg {
    public RspMsg(int code, String msg, Object record) {
        String recordStr = record == null ? "" : record.toString();
        System.out.println(String.format("{\"code\":%d, \"msg\":\"%s\", \"record\":\"%s\"}", code, msg, recordStr));
    }

    public static void main(String[] args) {
        new RspMsg(0, "msg", null);
    }
}

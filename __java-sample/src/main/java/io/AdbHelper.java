package io;
//                       _oo0oo_
//                      o8888888o
//                      88" . "88
//                      (| -_- |)
//                      0\  =  /0
//                    ___/`---'\___
//                  .' \\|     |// '.
//                 / \\|||  :  |||// \
//                / _||||| -:- |||||- \
//               |   | \\\  -  /// |   |
//               | \_|  ''\---/''  |_/ |
//               \  .-\__  '-'  ___/-. /
//             ___'. .'  /--.--\  `. .'___
//          ."" '<  `.___\_<|>_/___.' >' "".
//         | | :  `- \`.;`\ _ /`;.`/ - ` : | |
//         \  \ `_.   \_ __\ /__ _/   .-` /  /
//     =====`-.____`.___ \_____/___.-`___.-'=====
//                       `=---='
//
//
//     ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//               佛祖保佑         永无BUG


//         ┌─┐       ┌─┐
//      ┌──┘ ┴───────┘ ┴──┐
//      │                 │
//      │       ───       │
//      │  ─┬┘       └┬─  │
//      │                 │
//      │       ─┴─       │
//      │                 │
//      └───┐         ┌───┘
//          │         │
//          │         │
//          │         │
//          │         └──────────────┐
//          │                        │
//          │                        ├─┐
//          │                        ┌─┘
//          │                        │
//          └─┐  ┐  ┌───────┬──┐  ┌──┘
//            │ ─┤ ─┤       │ ─┤ ─┤
//            └──┴──┘       └──┴──┘
//                神兽保佑
//                代码无BUG!


//
//  ██████▒█    ██  ▄████▄   ██ ▄█▀       ██████╗ ██╗   ██╗ ██████╗
// ▓██   ▒ ██  ▓██▒▒██▀ ▀█   ██▄█▒        ██╔══██╗██║   ██║██╔════╝
// ▒████ ░▓██  ▒██░▒▓█    ▄ ▓███▄░        ██████╔╝██║   ██║██║  ███╗
// ░▓█▒  ░▓▓█  ░██░▒▓▓▄ ▄██▒▓██ █▄        ██╔══██╗██║   ██║██║   ██║
// ░▒█░   ▒▒█████▓ ▒ ▓███▀ ░▒██▒ █▄       ██████╔╝╚██████╔╝╚██████╔╝
//  ▒ ░   ░▒▓▒ ▒ ▒ ░ ░▒ ▒  ░▒ ▒▒ ▓▒       ╚═════╝  ╚═════╝  ╚═════╝
//  ░     ░░▒░ ░ ░   ░  ▒   ░ ░▒ ▒░
//  ░ ░    ░░░ ░ ░ ░        ░ ░░ ░
//           ░     ░ ░      ░  ░
//                 ░

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/3 15:57
 * Description
 */
public class AdbHelper {

    private static final String PATH = new ImageTest().getCurrFilePath();
    private static final String ADB_PATH = "D:\\__sssarm\\platform-tools\\adb";

    public static void main(String[] args) {
        // CmdUtils.runProcess(true, String.format("%s shell input tap %d %d", ADB_PATH, 540, 1000));
        tap(540, 1000);
    }

    public static void screen() {
        CmdUtils.runProcess(true, String.format("%s shell screencap -p /sdcard/finddiff.png", ADB_PATH));
    }

    public static void pull() {
        CmdUtils.runProcess(true, String.format("%s pull /sdcard/finddiff.png " + PATH, ADB_PATH));
    }

    public static void tap(int x, int y) {
        CmdUtils.runProcess(true, String.format("%s shell input tap %d %d", ADB_PATH, x, y));
    }

}
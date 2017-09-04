package com.decorator.window;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:32
 * Desc    Setting | Editor | File and Code Templates
 */
public class Main {
    // for print descriptions of the window subclasses
    private static void printInfo(Window w) {
        System.out.println("description:" + w.getDescription());
    }

    public static void main(String[] args) {
        // original SimpleWindow
        SimpleWindow sw = new SimpleWindow();
        printInfo(sw);
        // HorizontalScrollBar  mixed Window
        HorizontalScrollBar hbw = new HorizontalScrollBar(sw);
        printInfo(hbw);
        // VerticalScrollBar mixed Window
        VerticalScrollBar vbw = new VerticalScrollBar(hbw);
        printInfo(vbw);
    }
}

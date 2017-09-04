package com.decorator.window;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:25
 * Desc    Setting | Editor | File and Code Templates
 */
public class VerticalScrollBar extends WindowDecorator {
    public VerticalScrollBar(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public void draw() {
        super.draw();
        drawVerticalScrollBar();
    }

    private void drawVerticalScrollBar() {
        // Draw the vertical scrollbar
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including vertical scrollbars";
    }
}

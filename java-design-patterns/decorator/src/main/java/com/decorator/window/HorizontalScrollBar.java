package com.decorator.window;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:30
 * Desc    Setting | Editor | File and Code Templates
 */
public class HorizontalScrollBar extends WindowDecorator {
    public HorizontalScrollBar(Window windowToBeDecorated) {
        super(windowToBeDecorated);
    }

    @Override
    public void draw() {
        super.draw();
        drawHorizontalScrollBar();
    }

    private void drawHorizontalScrollBar() {
        // Draw the horizontal scrollbar
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", including horizontal scrollbars";
    }
}

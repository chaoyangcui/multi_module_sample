package com.decorator.window;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/1 17:25
 * Desc    Setting | Editor | File and Code Templates
 */
public abstract class WindowDecorator implements Window {
    protected Window decoratedWindow; // the Window being decorated

    public WindowDecorator (Window decoratedWindow) {
        this.decoratedWindow = decoratedWindow;
    }

    @Override
    public void draw() {
        decoratedWindow.draw();
    }

    @Override
    public String getDescription() {
        return decoratedWindow.getDescription();
    }
}

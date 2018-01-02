package io;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Intellij IDEA.
 * Date  : 2018/1/3 00:27
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class FindDiffHelper {

    private static final int top = 116;
    private static final int left = 148;
    private static final int height = 573;
    private static final int width = 573;
    private static final int gap = 25;

    public static void main(String[] args) throws IOException {
        String path = new ImageTest().getCurrFilePath();
        String fileName = "finddiff.png";
        File file = new File(path + fileName);
        if (!file.exists()) {
            System.out.println("file not exist");
        }
        File geFile = new File(path + "diff.png");
        if (!geFile.exists()) {
            geFile.createNewFile();
        }

        BufferedImage bufImg = ImageIO.read(file);

        System.out.println(bufImg.getRGB(left, top));
        System.out.println(bufImg.getRGB(left, top + height + gap));

        bufImg.setRGB(left + 20, top, ImageTest.RBG_BLACK);
        bufImg.setRGB(left + 20, top + height + gap, ImageTest.RBG_BLACK);

        int oriRgb, diffRgb;
        for (int x = left; x < left + width; x++) {
            for (int y = top; y < top + height; y++) {
                oriRgb = bufImg.getRGB(x, y);
                diffRgb = bufImg.getRGB(x, y + height + gap);
                if (oriRgb != diffRgb) {
                    bufImg.setRGB(x, y + height + gap, Color.BLACK.getRGB());
                }
            }
        }

        ImageIO.write(bufImg, "png", geFile);

        bufImg.flush();
    }


    public void findDiff() {

    }


}

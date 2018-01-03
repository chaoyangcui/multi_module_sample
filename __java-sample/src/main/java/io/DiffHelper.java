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
public class DiffHelper {

    static class IP6 {
        public static final int TOP = 116;
        public static final int LEFT = 148;
        public static final int HEIGHT = 573;
        public static final int WIDTH = 573;
        public static final int GAP = 25;
    }
    static class MX2 {
        public static final int TOP = 161;
        public static final int LEFT = 183;
        public static final int HEIGHT = 550;
        public static final int WIDTH = 550;
        public static final int GAP = 24;
    }

    public static final int TOP = MX2.TOP;
    public static final int LEFT = MX2.LEFT;
    public static final int HEIGHT = MX2.HEIGHT;
    public static final int WIDTH = MX2.WIDTH;
    public static final int GAP = MX2.GAP;

    public static int IMG_HEIGHT = MX2.HEIGHT;
    public static int IMG_WIDTH = MX2.WIDTH;

    static final String path = Utils.getCurrFilePath();

    public static void main(String[] args) throws IOException {

        String fileName = "finddiff.png";
        // fileName = "finddiff_room.png";
        File file = new File(path + fileName);
        if (!file.exists()) {
            System.out.println("file not exist");
        }
        File oridDiffFile = new File(path + "_ori_diff.png");
        if (!oridDiffFile.exists()) {
            oridDiffFile.createNewFile();
        }
        File diffFile = new File(path + "_diff.png");
        if (!diffFile.exists()) {
            diffFile.createNewFile();
        }
        /*File topImg = new File(path + "_top.png");
        if (!topImg.exists()) {
            topImg.createNewFile();
        }
        File bottomImg = new File(path + "_bottom.png");
        if (!bottomImg.exists()) {
            bottomImg.createNewFile();
        }*/

        BufferedImage bufImg = ImageIO.read(file);
        int height = bufImg.getHeight();

        BufferedImage diffBufImg = new BufferedImage(WIDTH, HEIGHT - 5, BufferedImage.TYPE_INT_RGB);
        // BufferedImage topBufImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        // BufferedImage bottomBufImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

        int topRgb, bottomRgb;
        for (int x = LEFT; x < LEFT + WIDTH; x++) {
            for (int y = TOP; y < TOP + HEIGHT; y++) {
                topRgb = bufImg.getRGB(x, y);
                // 适配MX2
                if (y + HEIGHT + GAP >= height) {
                    break;
                }
                bottomRgb = bufImg.getRGB(x, y + HEIGHT + GAP);

                // topBufImg.setRGB(x - LEFT, y - TOP, topRgb);
                // bottomBufImg.setRGB(x - LEFT, y - TOP, bottomRgb);

                if (topRgb != bottomRgb
                        && !colorSimilar(topRgb, bottomRgb)) {
                    bufImg.setRGB(x, y + HEIGHT + GAP, Color.BLACK.getRGB());
                    diffBufImg.setRGB(x - LEFT, y - TOP, Color.BLACK.getRGB());
                } else {
                    bufImg.setRGB(x, y + HEIGHT + GAP, Color.WHITE.getRGB());
                    diffBufImg.setRGB(x - LEFT, y - TOP, Color.WHITE.getRGB());
                }
            }
        }

        ImageIO.write(bufImg, "png", oridDiffFile);
        ImageIO.write(diffBufImg, "png", diffFile);
        // ImageIO.write(topBufImg, "png", topImg);
        // ImageIO.write(bottomBufImg, "png", bottomImg);

        bufImg.flush();
    }

    public static void findDiff() {
        String diffFileName = fileName();
        FindDiffWindow.fileName = diffFileName;
        try {
            String fileName = "finddiff.png";
            File file = new File(path + fileName);
            if (!file.exists()) {
                System.out.println("file not exist");
            }
            File diffFile = new File(path + diffFileName);
            if (!diffFile.exists()) {
                diffFile.createNewFile();
            }
            File oriDiffFile = new File(path + diffFileName);
            if (!oriDiffFile.exists()) {
                oriDiffFile.createNewFile();
            }

            BufferedImage bufImg = ImageIO.read(file);
            BufferedImage diffBufImg = new BufferedImage(WIDTH, HEIGHT - 5, BufferedImage.TYPE_INT_RGB);

            int height = bufImg.getHeight();
            int topRgb, bottomRgb;
            for (int x = LEFT; x < LEFT + WIDTH; x++) {
                for (int y = TOP; y < TOP + HEIGHT; y++) {
                    topRgb = bufImg.getRGB(x, y);
                    // 适配MX2
                    if (y + HEIGHT + GAP >= height) {
                        continue;
                    }
                    bottomRgb = bufImg.getRGB(x, y + HEIGHT + GAP);

                    if (topRgb != bottomRgb
                            && !colorSimilar(topRgb, bottomRgb)) {
                        // bufImg.setRGB(x, y + HEIGHT + GAP, Color.BLACK.getRGB());
                        diffBufImg.setRGB(x - LEFT, y - TOP, Color.BLACK.getRGB());
                    } else {
                        // bufImg.setRGB(x, y + HEIGHT + GAP, Color.WHITE.getRGB());
                        diffBufImg.setRGB(x - LEFT, y - TOP, Color.WHITE.getRGB());
                    }
                }
            }

            ImageIO.write(diffBufImg, "png", diffFile);
            // ImageIO.write(bufImg, "png", oriDiffFile);
            diffBufImg.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 颜色是否相近
     * @param oriRgb  color RGB值
     * @param diffRgb color RGB值
     * @return 相近:true,否则false
     */
    public static boolean colorSimilar(int oriRgb, int diffRgb) {
        Color oriColor = Color.getColor("", oriRgb);
        Color diffColor = Color.getColor("", diffRgb);

        if (Math.abs(oriColor.getBlue() - diffColor.getBlue()) < 45
                && Math.abs(oriColor.getGreen() - diffColor.getGreen()) < 45
                && Math.abs(oriColor.getRed() - diffColor.getRed()) < 45) {
            return true;
        }

        return false;
    }

    public static void delOldImage(final String fileName) {
        File file = new File(path + fileName);
        file.deleteOnExit();
    }

    public static String fileName() {
        return String.valueOf(System.currentTimeMillis()) + ".png";
    }
}

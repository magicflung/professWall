package com.profess.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class ImageUtil {


    /**
     * 仅仅通过ImageIO.write(img, "jpg", file);不足以保证转换出来的jpg文件显示正常。这段转换代码，可以确保转换后jpg的图片显示正常，而不会出现暗红色( 有一定几率出现)。
     *
     * @param f
     * @return 确保图片文件的二进制格式是jpg
     */
    public static BufferedImage change2jpg(File f) {
        try {
            // 图片副本
            Image i = Toolkit.getDefaultToolkit().createImage(f.getAbsolutePath());
            // 创建一个PixelGrabber对象
            PixelGrabber pg = new PixelGrabber(i, 0, 0, -1, -1, true);
            // 把图片转为像素图
            pg.grabPixels();
            int width = pg.getWidth();
            int height = pg.getHeight();
            final int[] RGB_MASKS = {0xFF0000, 0xFF00, 0xFF};
            // bits：表示每个像素所占的位数
            final ColorModel RGB_OPAQUE = new DirectColorModel(32, RGB_MASKS[0], RGB_MASKS[1], RGB_MASKS[2]);
            DataBuffer buffer = new DataBufferInt((int[]) pg.getPixels(), pg.getWidth() * pg.getHeight());
            WritableRaster raster = Raster.createPackedRaster(buffer, width, height, width, RGB_MASKS, null);
            BufferedImage img = new BufferedImage(RGB_OPAQUE, raster, false, null);
            return img;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 两种resizeImage用于改变图片大小
    public static void resizeImage(File srcFile, int width, int height, File destFile) {
        try {
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            Image i = ImageIO.read(srcFile);
            i = resizeImage(i, width, height);
            ImageIO.write((RenderedImage) i, "jpg", destFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image resizeImage(Image srcImage, int width, int height) {
        try {
            BufferedImage buffImage = null;
            buffImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            buffImage.getGraphics().drawImage(srcImage.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            return buffImage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /*
     * 获取图片并显示在页面
     * @return
     * @throws SQLException
     */
    public static void queryPic(String filePath, FileInputStream fileInputStream, HttpServletRequest request, HttpServletResponse response) throws IOException  {

        if (filePath != null){
            FileInputStream is = fileInputStream;

            if (is != null){
                int i = is.available(); // 得到文件大小
                byte data[] = new byte[i];
                is.read(data); // 读数据
                is.close();
                OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
                toClient.write(data); // 输出数据
                toClient.close();
            }
        }
    }



    /**
     * 上传文件
     * @param file
     * @param request
     * @return
     * @throws Exception
     */
    public static String saveOrUpdateImageFile(MultipartFile file, HttpServletRequest request, String systemFilePath) throws IOException{
        //如果文件不为空，写入上传路径
        if (!file.isEmpty()){
            // 路径
            // File path = new File(request.getServletContext().getRealPath("/static/images/"));
            File path = new File(systemFilePath);
            // 创建随机文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String suffix = ".jpg";
            String fileName = uuid + suffix;
            File filePath = new File(path, fileName);
            //判断路径是否存在，如果不存在就创建一个
            if (!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }
            //将上传文件保存到一个目标文件当中
            file.transferTo(filePath);
            // png，jpg都转为jpg
            BufferedImage img = ImageUtil.change2jpg(filePath);
            // ImageIo对图片进行io操作
            ImageIO.write(img, "jpg", filePath);
            return uuid;
        } else {
            return null;
        }
    }

    /**
     * 检查文件类型
     * @param fileName
     * @return
     */
    public static boolean checkFile(String fileName) {
        //设置允许上传文件类型
        String suffixList = "jpg,png";
        // 获取文件后缀
        String suffix = fileName.substring(fileName.lastIndexOf(".")
                + 1, fileName.length());
        if(suffixList.contains(suffix.trim().toLowerCase())) {
            return true;
        }
        return false;
    }
}

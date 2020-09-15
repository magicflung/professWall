package com.profess.controller;

import com.profess.util.Data;
import com.profess.util.ImageUtil;
import com.profess.util.JSONResult;
import com.profess.util.Meta;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
@RestController
public class FileController {
    @Value("${linux.upload-path}")
    private String linuxFilePath;
    /**
     * 上传文件
     * @param file
     * @param request
     * @return 返回文件名
     * @throws Exception
     */
    @PostMapping(value = "/upload", consumes="multipart/form-data")
    public JSONResult<Object> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        // 判断类型
        if(!ImageUtil.checkFile(file.getOriginalFilename())) {
            return new JSONResult<>(new Meta(400, "文件类型只能为jpg或png"));
        }
        // 获取图片的路径
        // String  path = request.getServletContext().getRealPath("/static/images/");
        // 上传文件
        String fileName = ImageUtil.saveOrUpdateImageFile(file, request, linuxFilePath);
        Data<Object> data = null;
        Meta meta = new Meta(200, "请求成功");
        if(fileName == null) {
            data = new Data<>("", 0);
            return new JSONResult<>(data, meta);
        } else {
            data = new Data<>(fileName, 0);
            return new JSONResult<>(data, meta);
        }
    }


    /**
     * 获取图片
     * @param expImgid
     * @param request
     * @param response
     * @return 读取图片
     * @throws Exception
     */
    @GetMapping("/getImg")
    public JSONResult<Object> getImg(@RequestParam("expImgid") String expImgid, HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 获取图片的路径
        // File  path = new File(request.getServletContext().getRealPath("/static/images/"));
        // linux 绝对路径
        String filePath = linuxFilePath + expImgid + ".jpg";
        FileInputStream is = null;
        File filePic = new File(filePath);
        try {
            is = new FileInputStream(filePic);
            ImageUtil.queryPic(filePath, is, request, response);
            return new JSONResult<>(new Meta(200, "请求成功"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new JSONResult<>(Meta.NOT_OUND);
        }
    }
}

package com.sg.controller;

import com.sg.result.Result;
import com.sg.result.impl.ErrorResult;
import com.sg.result.impl.SuccessResult;
import com.sg.utils.OssUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-05 18:36
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private OssUtils ossUtils;

    @PostMapping("/upload")
    public Result fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        int userId = (int) request.getAttribute("userId");
        InputStream inputStream;
        try {
            inputStream = file.getInputStream();
            System.out.println(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return new ErrorResult();
        }
        String name = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
        ossUtils.saveFaceImg(userId,name,inputStream);
        return new SuccessResult(userId+"/"+name);
    }
}

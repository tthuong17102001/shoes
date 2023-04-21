package com.shop.shoes.controller;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class LoadImageController {
    @Value("${upload.path}")
    private String pathUploadImage;
    @GetMapping(value = "loadImage")
    @ResponseBody // giá trị trả về sẽ điền vào phần thân của Http response
    public byte[] index(@RequestParam(value = "imageName")String imageName, HttpServletResponse response) throws IOException {
        response.setContentType("image/jpeg");
        File file = new File(pathUploadImage + File.separatorChar + imageName);
        InputStream inputStream = null;
        if(file.exists()){
            try{
                inputStream = new FileInputStream(file);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            if(inputStream!=null){
                return IOUtils.toByteArray(inputStream);
            }
        }
        return null;
    }
}

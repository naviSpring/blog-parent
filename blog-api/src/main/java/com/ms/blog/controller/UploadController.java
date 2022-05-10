package com.ms.blog.controller;

import com.ms.blog.common.AjaxResult;
import com.ms.blog.common.ServerResponse;
import com.ms.blog.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @PackageName com.ms.blog.controller
 * @className UploadController
 * @Author :Wud
 * @CreateDate 2022/5/9 9:18
 * @Desc
 */
@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("uploadImg")
    public ServerResponse upload(@RequestParam("image") MultipartFile file) {
        //原始文件名称 比如说aa.png
        String originalFilename = file.getOriginalFilename();
        //唯一的文件名称
        String fileName = UUID.randomUUID().toString() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //降低我们自身应用服务器的带宽消耗
//        boolean upload = qiniuUtils.upload(file, fileName);
        if (false) {
//            return Result.success(QiniuUtils.url+fileName);
        }
        return ServerResponse.Error(ErrorCode.FILE_UPLOAD_ERROR.getMsg());
    }

    @PostMapping("uploadFile")
    public AjaxResult uploadFile(
            @RequestParam("userName") String userName,
            @RequestPart("headerImg") MultipartFile headerImg,
            @RequestPart("photos") MultipartFile[] photos) throws IOException {
        try {
            log.info("userName：{}",userName);
            if(!headerImg.isEmpty()){
                String originalFilename = headerImg.getOriginalFilename();
                String rq = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy" + "/" + "MM" + "/" + "dd" + "/"));
                String path = "D:/workSpace/fileCache/"+rq+"/"+originalFilename;
                File file = new File(path);
                File parentFile = new File(file.getParent());
                if(!parentFile.exists()){
                    parentFile.mkdirs();
                }
                    headerImg.transferTo(file);
            }

            if(photos.length > 0){
                for (MultipartFile photo : photos) {
                    String originalFilename = photo.getOriginalFilename();
                    String rq = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy" + "/" + "MM" + "/" + "dd" + "/"));
                    String path = "D:/workSpace/fileCache/"+rq+"/"+originalFilename;
                    File file = new File(path);
                    File parentFile = new File(file.getParent());
                    if(!parentFile.exists()){
                        parentFile.mkdirs();
                    }
                        photo.transferTo(file);
                }
            }

            return AjaxResult.success();
        }catch (Exception e){
            e.printStackTrace();
        }
        return AjaxResult.error();
    }







}

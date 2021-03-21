package com.aliwo.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassTask;
import com.aliwo.service.UploadOrDownLoadService;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/3/21 15:28
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

@RestController
public class UploadOrDownLoadController {

    private static final org.slf4j.Logger Log = LoggerFactory.getLogger(UploadOrDownLoadController.class);

    @Autowired
    private UploadOrDownLoadService uploadOrDownLoadService;

    /**
     * 上传课程计划Excel文件
     *
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ServerResponse uploadClassTaskFile(MultipartFile file) {
        Log.info("文件上传被调用。。。");
        return uploadOrDownLoadService.uploadExcel(file);
    }

    /**
     * 下载系统提供的Excel导入模板
     *
     * @return
     */
    @RequestMapping(value = "/download", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
    //@GetMapping(value = "/download", consumes = MediaType.ALL_VALUE)
    public void downloadTemplate(final HttpServletResponse response) throws IOException {
        Long startTime = System.currentTimeMillis();
        Log.info("文件下载被开始调用耗时.....:" + startTime + "ms");
        // 获取文件
        File file = new File("doc/课程任务导入模板.xls");
        if (!file.exists()) {
            // 没有该模板文件就调用创建模板文件方法
            Log.info("创建模板文件");
            // 创建模板多级目录
            file = this.createTemplate();
        }
        // 获取文件名字
        String fileName = file.getName();
        response.reset();
        // 设置ContentType，响应内容为二进制数据流，编码为utf-8，此处设定的编码是文件内容的编码
        response.setContentType("application/octet-stream;charset=utf-8");
        // 以（Content-Disposition: attachment; filename="filename.jpg"）格式设定默认文件名，设定utf编码，此处的编码是文件名的编码，使能正确显示中文文件名
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName + ";filename*=utf-8''" +
                    URLEncoder.encode(fileName, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // 实现文件下载
        byte[] buffer = new byte[1024];
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            // 获取字节流
            OutputStream os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
            Long endTime = System.currentTimeMillis();
            Log.info("文件下载被调用结束耗时.....:" + endTime + "ms");
            Log.info("文件下载成功.........................");
            Log.info("文件下载被调用总耗时.....:" + (endTime - startTime) + "ms");
        } catch (Exception e) {
            e.printStackTrace();
            Log.info("文件下载失败: " + e.getMessage());
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 如果没有模板文件就创建模板文件
     */
    private File createTemplate() throws IOException {
        ExportParams params = new ExportParams();
        params.setTitle("课程任务导入模板(请严格对照数据库信息填写)");
        params.setSheetName("课程任务模板");
        List<ClassTask> list = new ArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(params, ClassTask.class, list);
        File file = null;
        file = new File("D:/arrange/excel/课程任务导入模板.xls");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        file.createNewFile();
        FileOutputStream fos = null;
        try {
            // 输出模板到本地
            fos = new FileOutputStream(file);
            workbook.write(fos);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //一定要判断fos是否为Null,只有不为null时，才可以关闭资源
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    throw new RuntimeException("");
                }
            }

        }
        return file;
    }

}

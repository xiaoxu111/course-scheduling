package com.aliwo.util;/**
 * package_name:com.aliwo.util
 *
 * @author:徐亚远 Date:2021/4/4 18:54
 * 项目名:course-scheduling
 * Description:阿里云OSS工具类
 **/

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author xuyayuan
 * @data 2021年04月04日
 */
@Component
public class AliyunUtil {
    private static final Logger LOG = LoggerFactory.getLogger(AliyunUtil.class);

    @Value("${aliyun.oss.file.endpoint}")
    private static String endpoint = "";
    private static String accessKeyId = "";
    private static String accessKeySecret = "";
    private static String bucketName = "";

    /**
     * 文件上传成功返回路径
     *
     * @param file
     * @param directory 选择需要上传到的目录下面，暂时不用，原本想将用户头像、其他文件上传到不同的目录下面的
     * @return
     */
    public static Map<String, Object> upload(MultipartFile file, String directory) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String url = "";
        try {
            // 获取输入流
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();
            //1 在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            // 随机id
            String newFileName = uuid + fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
            url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            LOG.info("url========" + url);
            Map<String, Object> map = new HashMap<>();
            map.put("url", url);
            map.put("name", fileName);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 文件下载到本地
     */
    public static String download(String fileName) {
        LOG.info("1" + fileName);
        LOG.info("阿里云开始下载文件到本地");
        String path = "D:\\arrange\\tempfile\\" + fileName;
        LOG.info("阿里云开始下载文件到本地的路径为：" + path);
        File file = new File(path);
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.getObject(new GetObjectRequest(bucketName, fileName), file);
        ossClient.shutdown();
        return path;
    }


}

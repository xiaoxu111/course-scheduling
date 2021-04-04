package com.aliwo.controller;/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/4/4 18:51
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.OnlineVideo;
import com.aliwo.entity.request.UserInfoVO;
import com.aliwo.service.OnlineVideoService;
import com.aliwo.util.AliyunUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuyayuan
 * @data 2021年04月04日
 */
@RestController
@RequestMapping("/onlinevideo")
public class OnlineVideoController {
    private static final Logger LOG = LoggerFactory.getLogger(OnlineVideoController.class);
    @Autowired
    private OnlineVideoService onlineVideoService;

    /**
     * 上传视频并返回url和文件名到前端
     *
     * @param file
     * @return com.aliwo.common.ServerResponse
     * @author xuyayuan
     * @date 2021/4/4 18:53
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ServerResponse upload(MultipartFile file) {
        Map<String, Object> map = AliyunUtil.upload(file, null);
        if (null != map) {
            return ServerResponse.ofSuccess(map);
        }
        return ServerResponse.ofError("视频上传失败");
    }


    /**
     * 上传新视频
     *
     * @param file
     * @param u
     * @return com.aliwo.common.ServerResponse
     * @author xuyayuan
     * @date 2021/4/4 20:05
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse addVideo(MultipartFile file, @RequestBody UserInfoVO u) {
        // 检查上传视频的参数
        ServerResponse ofError = this.checkUploadVideo(u);
        if (ofError != null) return ofError;
        Map<String, Object> map = new HashMap();
        OnlineVideo onlineVideo = new OnlineVideo();
        // 所属课程的id
        onlineVideo.setOnlineCourseId(u.getCourseId());
        onlineVideo.setVideoName(u.getVideoName());
        onlineVideo.setVideoUrl(u.getVideoUrl());
        onlineVideo.setCover(u.getCover());
        onlineVideo.setVideoNo(u.getVideoNo()); // 视频编号
        onlineVideo.setFromUserType(u.getUserType());
        onlineVideo.setFromUserId(u.getId());
        onlineVideo.setFromUserName(u.getRealname());
        return onlineVideoService.save(onlineVideo) ? ServerResponse.ofSuccess("添加视频成功") : ServerResponse.ofError(
                "添加视频失败");
    }

    /**
     * 检查上传视频的参数
     * @author xuyayuan
     * @date 2021/4/4 21:35
     * @param u 
     * @return com.aliwo.common.ServerResponse
     */
    private ServerResponse checkUploadVideo(UserInfoVO u) {
        if (StringUtils.isEmpty(u.getCover())) {
            return ServerResponse.ofError("请您上传封面！！！");
        }
        if (StringUtils.isEmpty(u.getVideoUrl())) {
            return ServerResponse.ofError("请您上传视频！！！");
        }
        if (StringUtils.isEmpty(u.getVideoName())) {
            return ServerResponse.ofError("请您上传视频！！！");
        }
        if (null == u.getCourseId()) {
            return ServerResponse.ofError("请您选择课程！！！");
        }
        if (null == u.getVideoNo()) {
            return ServerResponse.ofError("请您输入课程编号！！！");
        }
        return null;
    }

    /**
     * 根据课程id获取课程包含的视频
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ServerResponse getAllVideo(@PathVariable("id") Integer id) {
        return ServerResponse.ofSuccess(onlineVideoService.list( new QueryWrapper<OnlineVideo>().eq("online_course_id", id)));
    }

    /**
     * 根据id删除视频
     * @author xuyayuan
     * @date 2021/4/4 21:10
     * @param id 
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse deleteVideo(@PathVariable("id") Integer id) {
        return onlineVideoService.removeById(id) ? ServerResponse.ofSuccess("删除视频成功") : ServerResponse.ofError("删除视频失败");
    }

    /**
     * 根据id修改视频
     * @author xuyayuan
     * @date 2021/4/4 21:10
     * @param id
     * @return com.aliwo.common.ServerResponse
     */
    @RequestMapping(value = "/modifyById/{id}", method = RequestMethod.POST)
    public ServerResponse modifyVideoById(@PathVariable("id") Integer id) {

        //return onlineVideoService.removeById(id) ? ServerResponse.ofSuccess("删除视频成功") : ServerResponse.ofError("删除视频失败");
        return ServerResponse.ofSuccess();
    }
}

package com.wzx.nirvana.controller;


import com.wzx.nirvana.annotation.UserLoginToken;
import com.wzx.nirvana.model.Video;
import com.wzx.nirvana.repository.VideoRepository;
import com.wzx.nirvana.utils.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/video")
public class VideoController {


    private static final Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    VideoRepository videoRepository;

    @UserLoginToken
    @RequestMapping("add")
    @ResponseBody
    public CommonResult<Video> addVideo(@RequestBody Video video) {
        //logger.info(page.toString());
        video = videoRepository.addVideo(video);
        if (video != null) {
            return CommonResult.successReturn(video, "Add success");
        }
        return CommonResult.errorReturn(400, "Add failed");
    }

    @UserLoginToken
    @RequestMapping("update")
    @ResponseBody
    public CommonResult<String> updateVideo(@RequestBody Video video) {
        if (videoRepository.updateVideo(video) == 1) {
            return CommonResult.successReturn("0", "Update success");
        }
        return CommonResult.errorReturn(400, "Update failed");
    }

    @UserLoginToken
    @RequestMapping("getVideos")
    @ResponseBody
    public CommonResult<List<Video>> getVideos() {
        return CommonResult.successReturn(videoRepository.getVideos());
    }

    @UserLoginToken
    @RequestMapping("delete/{id}")
    @ResponseBody
    public CommonResult<Long> deleteVideo(@PathVariable String id) {
        long result = videoRepository.deleteVideo(id);
        return result == 1 ? CommonResult.successReturn(result) : CommonResult.errorReturn(400, "Wrong id");
    }

}

package com.aliwo.common;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * package_name:com.aliwo.common
 *
 * @author:徐亚远 Date:2021/1/24 15:57
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Component
public class TimerTask {
    /**
     * 每3小时执行一次
     */
    @Scheduled(cron = "0 0 0-3 * * ? ")
    public void task() {

    }
}

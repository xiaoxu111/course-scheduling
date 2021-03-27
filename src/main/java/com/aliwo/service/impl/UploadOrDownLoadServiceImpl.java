package com.aliwo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.aliwo.common.ServerResponse;
import com.aliwo.dao.ClassTaskDao;
import com.aliwo.entity.ClassTask;
import com.aliwo.service.ClassTaskService;
import com.aliwo.service.UploadOrDownLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:xuyy Date:2021/3/21 15:32
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class UploadOrDownLoadServiceImpl implements UploadOrDownLoadService {

    private static final Logger LOG = LoggerFactory.getLogger(UploadOrDownLoadServiceImpl.class);
    @Autowired
    private ClassTaskService classTaskService;
    @Autowired
    private ClassTaskDao classTaskDao;


    /**
     * 文件上传实现并解析Excel存入数据库
     *
     * @param file
     * @return
     */
    @Override
    public ServerResponse uploadExcel(MultipartFile file) {
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        List<ClassTask> list = null;
        try {
            // list就是Excel文件中每一行的记录
            list = ExcelImportUtil.importExcel(
                    file.getInputStream(),
                    ClassTask.class, params);
            LOG.info("总共需要插入多少条记录：" + list.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 调用课程任务存入数据库方法
        boolean b = save(list);
        if (b) {
            return ServerResponse.ofSuccess("导入课程任务成功");
        }
        return ServerResponse.ofError("导入课程任务失败");
    }
    /**
     * 将文件中的数据插入数据库
     * @param list
     * @return
     */
    private boolean save(List<ClassTask> list) {
        // 清空旧任务
        this.clearClassTaskOld();
        LOG.info("清空旧数据成功：");
        int i = 0;
        // 遍历课程任务实际插入的数据
        Integer liseSize = list.size();
        // 记录不合规数据 默认 0
        Integer noComplianceLisze = 0;
        for (ClassTask classTask : list) {
            if (StringUtils.isEmpty(classTask.getGradeNo())) {
                ++noComplianceLisze;
                liseSize--;
                continue;
            }
            ClassTask c = new ClassTask();
            c.setSemester(classTask.getSemester());
            c.setGradeNo(classTask.getGradeNo());
            c.setClassNo(classTask.getClassNo());
            c.setCourseNo(classTask.getCourseNo());
            c.setCourseName(classTask.getCourseName());
            c.setTeacherNo(classTask.getTeacherNo());
            c.setRealname(classTask.getRealname());
            c.setCourseAttr(classTask.getCourseAttr());
            c.setStudentNum(classTask.getStudentNum());
            c.setWeeksSum(classTask.getWeeksSum());
            c.setWeeksNumber(classTask.getWeeksNumber());
            c.setIsFix(classTask.getIsFix());
            c.setClassTime(classTask.getClassTime());
            boolean b = classTaskService.save(c);
            if (b) {
                i = i + 1;
                LOG.info("插入到第" +"【" + i + "】" + "条数据" + c.toString());
            }
        }
        LOG.info("记录到excel" + "【" + list.size() + "】" +"条数据");
        LOG.info("记录到" + "【" + noComplianceLisze + "】" +"条不合规数据");
        LOG.info("实际导入了" + "【" + liseSize + "】" +"条数据");
        if (i == liseSize) {
            return true;
        }
        return false;
    }

    /**
     * 清空旧的课程任务
     */
    private void clearClassTaskOld() {
        classTaskDao.clearClassTaskOld();
    }

}

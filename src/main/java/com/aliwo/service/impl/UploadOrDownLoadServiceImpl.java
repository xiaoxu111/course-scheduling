package com.aliwo.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.aliwo.common.ServerResponse;
import com.aliwo.dao.ClassTaskDao;
import com.aliwo.entity.ClassTask;
import com.aliwo.service.ClassTaskService;
import com.aliwo.service.UploadOrDownLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * package_name:com.aliwo.service.impl
 *
 * @author:徐亚远 Date:2021/3/21 15:32
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@Service
public class UploadOrDownLoadServiceImpl implements UploadOrDownLoadService {

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
        int i = 0;
        // 遍历课程任务插入数据库
        for (ClassTask classTask : list) {
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
            }
        }
        if (i == list.size()) {
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

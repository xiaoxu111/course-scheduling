package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.ClassInfo;
import com.aliwo.entity.Teacher;
import com.aliwo.entity.request.PasswordVO;
import com.aliwo.entity.request.TeacherAddRequest;
import com.aliwo.entity.request.UserLoginRequest;
import com.aliwo.service.ClassInfoService;
import com.aliwo.service.TeacherService;
import com.aliwo.service.impl.TokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/2 12:53
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private static final Logger LOG = LoggerFactory.getLogger(TeacherController.class);
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClassInfoService classInfoService;

    /**
     * @param userLoginRequest
     * @return ServerResponse
     * 讲师登录controller
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse TeacherLogin(@RequestBody UserLoginRequest userLoginRequest) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_no", userLoginRequest.getUsername());
        // 先查询是否有该账号
        Teacher teacher2 = teacherService.getOne(wrapper);
        if (null == teacher2) {
            return ServerResponse.ofError("账号不存在");
        } else if (0 != teacher2.getStatus()) {
            return ServerResponse.ofError("账号状态异常，请联系管理员");
        }
        // 登录,使用编号登录或者用户名或者真实姓名
        Teacher teacher = teacherService.teacherLogin(userLoginRequest.getUsername(), userLoginRequest.getPassword());
        String token = tokenService.getToken(teacher);
        if (null != teacher && Strings.isNotEmpty(token)) {
            // 允许登录
            map.put("teacher", teacher);
            map.put("token", token);
            return ServerResponse.ofSuccess(map);
        }
        // 否则一律视为密码错误
        return ServerResponse.ofError("密码错误");
    }

    /**
     * 修改讲师信息 个人中心使用
     *
     * @param teacher
     * @return ServerResponse
     * token 验证现在不能用后续完善
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    //@UserLoginToken
    public ServerResponse modifyTeacher(@RequestBody Teacher teacher) {
        if (StringUtils.isEmpty(teacher.getTelephone())){
            return ServerResponse.ofError("手机号必填!");
        }
        if (StringUtils.isEmpty(teacher.getEmail())){
            return ServerResponse.ofError("邮箱必填");
        }
        // 修改操作
        return teacherService.updateById(teacher) ? ServerResponse.ofSuccess("修改成功") : ServerResponse.ofError("修改失败");
    }

    /**
     * 讲师修改密码
     *
     * @param passwordVO
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ServerResponse updateTeacherPass(@RequestBody PasswordVO passwordVO) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper();
        wrapper.eq("id", passwordVO.getId());
        wrapper.eq("password", passwordVO.getOldPass());
        Teacher teacher = teacherService.getOne(wrapper);
        if (null == teacher) {
            return ServerResponse.ofError("旧密码错误");
        }
        // 否则进入修改密码流程
        teacher.setPassword(passwordVO.getNewPass());
        boolean b = teacherService.updateById(teacher);
        if (b) {
            return ServerResponse.ofSuccess("密码修改成功");
        }
        return ServerResponse.ofError("密码更新失败");
    }

    /**
     * 分页查询讲师
     * @param page
     * @param limit
     * @return ServerResponse
     *  讲师管理，查询所有讲师分页
     */
    @RequestMapping(value = "/query/{page}",method = RequestMethod.GET)
    public ServerResponse queryTeacher(@PathVariable(value = "page") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Page<Teacher> pages = new Page<>(page, limit);
        // 根据讲师编号降序排列
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>().orderByDesc("teacher_no");
        IPage<Teacher> iPage = teacherService.page(pages, wrapper);
        if (iPage == null || 0 == iPage.getTotal()) {
            return ServerResponse.ofError("没有查询讲师信息！！！");
        }
        return ServerResponse.ofSuccess(iPage);
    }

    /**
     * 查询所有讲师
     *
     * @return
     */
    @GetMapping("/all")
    public ServerResponse getAllTeacher() {
        return ServerResponse.ofSuccess(teacherService.list());
    }

    /**
     * @param t
     * @return ServerResponse
     * 管理员添加讲师信息
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ServerResponse teacherAdd(@RequestBody TeacherAddRequest t) {
        Teacher teacher = new Teacher();
        // 查询出最后一个讲师编号 讲师编号自动生成默认按照编号降序取第一个+1
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("teacher_no");
        Page<Teacher> page = new Page<>(0,1);
        IPage<Teacher> iPage = teacherService.page(page,wrapper );
        List<Teacher> teacherList =  iPage.getRecords();
        if (teacherList == null || teacherList.size()>1){
            return ServerResponse.ofError("查询最后一个讲师错误");
        }
        if (Strings.isEmpty(teacherList.get(0).getTeacherNo())) {
            return ServerResponse.ofError("讲师编号非空!!!");
        }
        teacher.setTeacherNo(String.valueOf(Integer.parseInt(teacherList.get(0).getTeacherNo())+1));
        if (Strings.isEmpty(t.getUsername())){
            return ServerResponse.ofError("用户名非空！！！");
        }
        teacher.setUsername(t.getUsername());
        if (Strings.isEmpty(t.getEmail())){
            return ServerResponse.ofError("用户名非空！！！");
        }
        teacher.setEmail(t.getEmail());
        // 每一个新增的讲师密码默认是123456
        teacher.setPassword("123456");
        if (Strings.isEmpty(t.getRealname())){
            return ServerResponse.ofError("真实姓名非空！！！");
        }
        teacher.setRealname(t.getRealname());
        if (Strings.isEmpty(t.getJobTitle())){
            return ServerResponse.ofError("职称非空！！！");
        }
        teacher.setJobTitle(t.getJobTitle());
        if (Strings.isEmpty(t.getTeachSubject())){
            return ServerResponse.ofError("教授科目非空！！！");
        }
        teacher.setTeachSubject(t.getTeachSubject());
        if (Strings.isEmpty("手机号非空"));
        teacher.setTelephone(t.getTelephone());
        if (Strings.isEmpty(t.getAddress())){
            return ServerResponse.ofError("地址非空！！！");
        }
        teacher.setAddress(t.getAddress());
        if (t.getAge()<0 || t.getAge()>200){
            return ServerResponse.ofError("请填写年龄在0-200之间");
        }
        teacher.setAge(t.getAge());
        Boolean flag = teacherService.save(teacher);
        if (!flag){
            return ServerResponse.ofError("添加讲师服务失败，请检查！！！");
        }
        return ServerResponse.ofSuccess("添加讲师成功！！！");
    }


    /**
     * @param id
     * @return ServerResponse
     * 管理员删除讲师信息
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public ServerResponse teacherDelete(@PathVariable(value = "id") Integer id) {
        // 传来讲师id
        if (null == id){
            return ServerResponse.ofError("删除讲师服务失败 id错误！！！");
        }
        // 根据讲师id查询讲师信息
        Teacher teacher = teacherService.getById(id);
        if (null == teacher) {
            return ServerResponse.ofError("没有查询到该讲师信息，删除服务失败！！！");
        }
        Integer teacherId = teacher.getId();
        Map<String,Object> map = new HashMap<>();
        map.put("teacher_id",teacherId );
        // 查询该讲师所教的班级信息,如果该班级已经分配学生则删除讲师信息失败,一个讲师可以教多个班级
        List<ClassInfo> classInfos = (List<ClassInfo>) classInfoService.listByMap(map);
        if (null != classInfos && classInfos.size() != 0) {
            for (ClassInfo classInfo : classInfos) {
                if (0 != classInfo.getNum()) {
                    return ServerResponse.ofError("该讲师" + "【" + teacher.getRealname() + "】" + "已经分配了学生删除失败！！！");
                }
            }
        }
        QueryWrapper<Teacher> wrapper1 = new QueryWrapper<>();
        Boolean flag = teacherService.removeById(id);
        if (!flag) {
            return ServerResponse.ofError("删除失败！！！");
        }
        return ServerResponse.ofSuccess("删除成功");
    }

}

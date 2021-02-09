package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.common.UserLoginToken;
import com.aliwo.entity.Student;
import com.aliwo.entity.request.PasswordVO;
import com.aliwo.entity.request.StudentLoginRequest;
import com.aliwo.entity.request.StudentRegisterRequest;
import com.aliwo.service.StudentService;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.*;


/**
 * package_name:com.aliwo.controller
 *
 * @author:xuyy19 Date:2021/2/2 14:26
 * 项目名:course-scheduling
 * Description:TODO
 * Version: 1.0
 **/
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private static final Logger LOG = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private TokenService tokenService;

    /**
     * @param studentLoginRequest
     * @return ServerResponse
     * 学生登录controller
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ServerResponse studentLogin(@RequestBody StudentLoginRequest studentLoginRequest, HttpServletResponse
            response, HttpServletRequest request, HttpSession session)
            throws UnsupportedEncodingException {
        Map<String, Object> map = new HashMap<>();
        // 先判断是否有该学号，该学生
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>().eq("student_no", studentLoginRequest.getUsername());
        // 查询是否有该学生
        Student student2 = studentService.getOne(wrapper);

        if (null == student2) {
            return ServerResponse.ofError("学生账号不存在!");

        } else if (0 != student2.getStatus()) {
            // 否则进行下一步验证账号的的状态
            return ServerResponse.ofError("该学生账号异常，请联系管理员");
        }
        // 调用登录
        Student student = studentService.studentLogin(studentLoginRequest.getUsername(), studentLoginRequest.getPassword());
        String token = tokenService.getToken(student);
        if (null != student && StringUtils.isNotEmpty(token)) {
            //允许登录,返回token
            //student.setPassword("");
            map.put("student", student);
            map.put("token", token);
            response.setHeader("token",token );
            return ServerResponse.ofSuccess(map);
        }
        return ServerResponse.ofSuccess("密码错误！");
    }

    /**
     * @param studentRegisterRequest
     * @return ServerResponse
     * 学生注册controller
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    public ServerResponse studentRegist(@RequestBody StudentRegisterRequest studentRegisterRequest) {
        Student student = new Student();
        // 填充学号 非空
        if (StringUtils.isEmpty(studentRegisterRequest.getStudentNo())) {
            return ServerResponse.ofError("学号非空，请填写!");
        }
        student.setStudentNo(studentRegisterRequest.getStudentNo());
        // 填充用户名
        student.setUsername(studentRegisterRequest.getUsername());
        if (StringUtils.isEmpty(studentRegisterRequest.getPassword())) {
            return ServerResponse.ofError("密码非空，请填写!");
        }
        // 填充密码   非空
        student.setPassword(studentRegisterRequest.getPassword());
        if (StringUtils.isEmpty(studentRegisterRequest.getRealname())) {
            return ServerResponse.ofError("真实姓名非空，请填写!");
        }
        // 填充真实姓名 非空
        student.setRealname(studentRegisterRequest.getRealname());
        // 填充年级
        student.setGrade(studentRegisterRequest.getGrade());
        // 填充地址
        student.setAddress(studentRegisterRequest.getAddress());
        // 填充联系电话
        student.setTelephone(studentRegisterRequest.getTelephone());
        // 填充email
        student.setEmail(studentRegisterRequest.getEmail());
        boolean b = studentService.save(student);
        if (b) {
            return ServerResponse.ofSuccess("注册成功", student);
        }
        return ServerResponse.ofError("注册失败，请联系管理员!");
    }

    /**
     * @param grade
     * @return ServerResponse
     * 给学生创建学号,注册学生的时候调用
     */
    @RequestMapping(value = "/createstudentno/{grade}", method = RequestMethod.POST)
    public ServerResponse createStudentNo(@PathVariable("grade") String grade) {
        Random r = new Random();
        // 得到当前年份字符串2021
        String str1 = LocalDateTime.now().getYear() + "";
        LOG.info("当前的年份(com.aliwo.controller.StudentController.createStudentNo) str1：" + str1);
        // 得到10位学号,2021 02 7845
        do {
            // 随机四位数
            String str2 = String.valueOf(r.nextInt(10000));
            LOG.info("生成的四位随机数(com.aliwo.controller.StudentController.createStudentNo) str2：" + str2);
            // 拼接学号  2021##****  十位(三个部分):  年:4位  年级:两位  随机数4位
            String str3 = str1 + grade + str2;
            LOG.info("生成的10位数 年份四位，随机数四位，年级两位(com.aliwo.controller.StudentController.createStudentNo) str3：" + str3);
            // 查询学号是否已经存在的条件
            QueryWrapper<Student> wrapper = new QueryWrapper<Student>().eq("student_no", str3);
            Student student = studentService.getOne(wrapper);
            // 如果查不到该学号，则学号可用，跳出循环
            if (null == student) {
                return ServerResponse.ofSuccess(str3);
            }
        } while (true);
    }

    /**
     * 根据学生id获取
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @UserLoginToken
    public ServerResponse queryStudentById(@PathVariable("id") Integer id) {
        // 查询出来需要修改的学生实体
        return ServerResponse.ofSuccess(studentService.getById(id));
    }

    /**
     * 学生修改密码
     *
     * @param passwordVO
     * @return
     */
    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ServerResponse updatePass(@RequestBody PasswordVO passwordVO) {
        QueryWrapper<Student> wrapper = new QueryWrapper();
        wrapper.eq("id", passwordVO.getId());
        wrapper.eq("password", passwordVO.getOldPass());
        Student student = studentService.getOne(wrapper);
        if (null == student) {
            return ServerResponse.ofError("旧密码错误");
        }
        // 否则进入修改密码流程
        student.setPassword(passwordVO.getNewPass());
        boolean b = studentService.updateById(student);
        if (b) {
            return ServerResponse.ofSuccess("密码修改成功");
        }
        return ServerResponse.ofError("密码更新失败");
    }

    /**
     * 学生加入班级，只有加入班级后才可以看到本班的课表，文档
     *
     * @param id      学生id
     * @param classNo 班级编号
     * @return
     */
    @RequestMapping(value = "/join/{id}/{classNo}", method = RequestMethod.POST)
    public ServerResponse joinClass(@PathVariable("id") Integer id, @PathVariable("classNo") String classNo) {
        if (StringUtils.isEmpty(Integer.toString(id))) {
            return ServerResponse.ofError("没有查询到学生信息(id)!");
        }
        if (StringUtils.isEmpty(classNo)) {
            return ServerResponse.ofError("没有查询到班级信息(classNo)!");
        }
        // TODO 学生加入年级，学生查看本班的文档(文档控制器中),查看自己所在的班级课表
        Student student = studentService.getById(id);
        if (null == student) {
            return ServerResponse.ofError("查询学生信息错误 学生id是: id=" + id);
        }
        student.setClassNo(classNo);
        boolean b = studentService.saveOrUpdate(student);
        if (b) {
            return ServerResponse.ofSuccess("加入班级成功");
        }
        return ServerResponse.ofError("加入班级失败");
    }

    /**
     * 修改学生信息
     *
     * @param student
     * @return ServerResponse
     * token 验证现在不能用后续完善
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    //@UserLoginToken
    public ServerResponse modifyStudent(@RequestBody Student student) {
        if (StringUtils.isEmpty(student.getTelephone())){
            return ServerResponse.ofError("手机号必填!");
        }
        if (StringUtils.isEmpty(student.getEmail())){
            return ServerResponse.ofError("邮箱必填");
        }
        // 修改操作
        return studentService.updateById(student) ? ServerResponse.ofSuccess("修改成功") : ServerResponse.ofError("修改失败");
    }

    /**
     * 获取所有学生，带分页  学生管理 获取所有学生
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/students/{page}", method = RequestMethod.GET)
    public ServerResponse queryStudent(@PathVariable("page") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        Page<Student> pages = new Page<>(page, limit);
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>().orderByDesc("student_no");
        IPage<Student> iPage = studentService.page(pages, wrapper);
        if (null ==iPage || 0 == iPage.getTotal()) {
            return ServerResponse.ofError("没有查询到学生信息！！！");
        }
        return ServerResponse.ofSuccess(iPage);

    }


    /**
     * 学生管理 所有学生选项,管理员根据ID删除学生
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ServerResponse deleteTeacher(@PathVariable Integer id) {
        if(StringUtils.isEmpty(id.toString())) {
            return ServerResponse.ofError("删除服务失败(id:) ！！！" + id);
        }
        boolean b = studentService.removeById(id);
        if(b) {
            return ServerResponse.ofSuccess("删除成功！");
        }
        return ServerResponse.ofError("删除失败！");
    }


    /**
     * 学生管理 所有学生选项,编辑学生信息
     *
     * @param student
     * @return
     */
    @PostMapping("/modify/{id}")
    public ServerResponse modifyTeacher(@PathVariable("id") Integer id, @RequestBody Student student) {
        if ( null == student) {
            return ServerResponse.ofError("更新失败！！！");
        }
        if (StringUtils.isEmpty(student.getStudentNo())){
            return ServerResponse.ofError("请填写学号 非空!!!");
        }
        if (StringUtils.isEmpty(student.getUsername())){
            return ServerResponse.ofError("请填写昵称 非空!!!");
        }
        if (StringUtils.isEmpty(student.getRealname())){
            return ServerResponse.ofError("请填写姓名 非空!!!");
        }
        if (StringUtils.isEmpty(student.getStudentNo())){
            return ServerResponse.ofError("请填写学号 非空!!!");
        }
        if (StringUtils.isEmpty(student.getGrade())){
            return ServerResponse.ofError("请填写年级 非空!!!");
        }
        if (StringUtils.isEmpty(student.getClassNo())){
            return ServerResponse.ofError("请填写班级 非空!!!");
        }
        if (StringUtils.isEmpty(student.getGrade())){
            return ServerResponse.ofError("请填写年级 非空!!!");
        }
        if (StringUtils.isEmpty(student.getTelephone())){
            return ServerResponse.ofError("请填写手机号 非空!!!");
        }
        if (StringUtils.isEmpty(student.getEmail())){
            return ServerResponse.ofError("请填写邮箱 非空!!!");
        }
        if (StringUtils.isEmpty(student.getAddress())){
            return ServerResponse.ofError("请填写地址 非空!!!");
        }
        if (student.getAge() == null){
            return ServerResponse.ofError("请填写年龄 非空!!!");
        }
        if (student.getAge() < 0){
            return ServerResponse.ofError("年龄不合法 请填写在 0~150岁之间!!!");
        }
        if (student.getAge() > 150){
            return ServerResponse.ofError("年龄不合法 请填写在 0~150岁之间!!!");
        }
        /*if (StringUtils.isNotEmpty(student.getGrade())){
            switch (student.getGrade()) {
                case "01":
                    student.setGrade("高一");
                    break;
                case "02":
                    student.setGrade("高二");
                    break;
                case "03":
                    student.setGrade("高三");
                    break;
                default:
                    student.setGrade("高四");
                    break;
            }
        }*/
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>().eq("id", id);
        boolean b = studentService.update(student, wrapper);
        if (b) {
            return ServerResponse.ofSuccess("更新成功");
        } else {
            return ServerResponse.ofError("更新失败");
        }
    }


    /**
     * 根据姓名关键字搜学生
     *
     * @return
     */
    @GetMapping("/search/{keyword}")
    public ServerResponse searchTeacher(@PathVariable("keyword") String keyword, @RequestParam(defaultValue = "1")
            Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("update_time");
        wrapper.like(!StringUtils.isEmpty(keyword), "realname", keyword);
        Page<Student> pages = new Page<>(page, limit);
        IPage<Student> iPage = studentService.page(pages, wrapper);
        if (null != iPage && 0 != iPage.getTotal()) {
            return ServerResponse.ofSuccess(0,"查询成功！！！",iPage);
        }
        return ServerResponse.ofError("查询不到数据！！！");
    }
}

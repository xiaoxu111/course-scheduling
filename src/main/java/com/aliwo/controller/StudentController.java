package com.aliwo.controller;

import com.aliwo.common.ServerResponse;
import com.aliwo.entity.Student;
import com.aliwo.entity.request.StudentLoginRequest;
import com.aliwo.entity.request.StudentRegisterRequest;
import com.aliwo.service.StudentService;
import com.aliwo.service.impl.TokenService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


/**
 * package_name:com.aliwo.controller
 *
 * @author:徐亚远 Date:2021/2/2 14:26
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
    public ServerResponse studentLogin(@RequestBody StudentLoginRequest studentLoginRequest) {
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
        Student student = studentService.studentLogin(studentLoginRequest.getUsername(), studentLoginRequest
                .getPassword());
        String token = tokenService.getToken(student);
        if (null != student && StringUtils.isNotEmpty(token)) {
            //允许登录,返回token
            student.setPassword("");
            map.put("student", student);
            map.put("token", token);
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
     *
     */
   @RequestMapping(value = "/createstudentno/{grade}", method = RequestMethod.POST)
    public ServerResponse createStudentNo(@PathVariable("grade") StringUtils grade) {
       Random r = new Random();
       // 得到当前年份字符串2021
       String str1 = LocalDateTime.now().getYear()+"";
       LOG.info("当前的年份(com.aliwo.controller.StudentController.createStudentNo) str1："+str1);
       // 得到10位学号,2021 02 7845
       do {
           // 随机四位数
           String str2 = String.valueOf(r.nextInt(10000));
           LOG.info("生成的四位随机数(com.aliwo.controller.StudentController.createStudentNo) str2："+str2);
           // 拼接学号  2021##****  十位(三个部分):  年:4位  年级:两位  随机数4位
           String str3 = str1 + grade + str2;
           LOG.info("生成的10位数 年份四位，随机数四位，年级两位(com.aliwo.controller.StudentController.createStudentNo) str3："+str3);
           // 查询学号是否已经存在的条件
           QueryWrapper<Student> wrapper = new QueryWrapper<Student>().eq("student_no", str3);
           Student student = studentService.getOne(wrapper);
           // 如果查不到该学号，则学号可用，跳出循环
           if (null == student) {
               return ServerResponse.ofSuccess(str3);
           }
       } while(true);
   }
}

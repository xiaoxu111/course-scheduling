package com.aliwo.entity.request;

import lombok.Data;

/**
 * @author:xuyy19 Date:2021/1/24 19:39
 * 项目名:course-scheduling-system
 * Description:TODO
 * Version: 1.0
 **/
@Data
public class ExerciseVO {


    private Integer categoryId;

    private String classNo;

    private String exerciseTitle;

    private Integer multiselect;

    private String answer;

    private String optionA;

    private String optionB;

    private String optionC;

    private String optionD;

    private String optionE;

    private String optionF;

    private Integer fraction;
}

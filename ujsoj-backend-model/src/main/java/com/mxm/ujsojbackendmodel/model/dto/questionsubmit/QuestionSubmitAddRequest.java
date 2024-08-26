package com.mxm.ujsojbackendmodel.model.dto.questionsubmit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 创建请求
 *
 * @author <a href="https://github.com/limxm">程序员鱼皮</a>
 * @from <a href="https://mxm.icu">编程导航知识星球</a>
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSubmitAddRequest implements Serializable {

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 题目 id
     */
    private Long questionId;


    private static final long serialVersionUID = 1L;
}
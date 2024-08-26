package com.mxm.ujsojbackendjudgeservice.judge.strategy;


import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeCase;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeConfig;
import com.mxm.ujsojbackendmodel.model.entity.QuestionSubmit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JudgeContext {
    private JudgeInfo judgeInfo;

    private List<String> outputList;

    private JudgeConfig judgeConfig;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}

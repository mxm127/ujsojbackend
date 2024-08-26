package com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl;


import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;
import com.mxm.ujsojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.mxm.ujsojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 示例沙箱（仅为了跑通代码）
 */
public class ExampleCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest){
        List<String> inputList = executeCodeRequest.getInputList();
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setTime(100L);
        judgeInfo.setMemory(100L);
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getText());
        List<String> outputList = new ArrayList<>();
        outputList.add("1");
        ExecuteCodeResponse executeCodeResponse = ExecuteCodeResponse.builder()
                .message("测试执行成功")
                .outputList(outputList)
                .status(QuestionSubmitStatusEnum.SUCCEED.getValue())
                .judgeInfo(judgeInfo)
                .build();
        return executeCodeResponse;
    }
}

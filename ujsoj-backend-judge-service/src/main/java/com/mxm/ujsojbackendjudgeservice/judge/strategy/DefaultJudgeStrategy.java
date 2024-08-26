package com.mxm.ujsojbackendjudgeservice.judge.strategy;


import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeCase;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeConfig;
import com.mxm.ujsojbackendmodel.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class DefaultJudgeStrategy implements JudgeStrategy {
    /**
     * 默认判题策略
     * @param judgeContext
     * @return
     */
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {

        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> outputList = judgeContext.getOutputList();
        JudgeConfig judgeConfig = judgeContext.getJudgeConfig();
        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        long memory = judgeInfo.getMemory();
        long time = judgeInfo.getTime();
        JudgeInfo judgeInfoResponse = new JudgeInfo();
        judgeInfoResponse.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfoResponse.setMemory(memory);
        judgeInfoResponse.setTime(time);
//        判断沙箱执行的输出结果的数量是否符合
        if (outputList.size() != judgeCaseList.size()) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
            return judgeInfoResponse;
        }
//        判断输出结果是否符合
        for (int i = 0; i < outputList.size(); i++) {
            String output = judgeCaseList.get(i).getOutput();
            if (!outputList.get(i).equals(output)) {
                judgeInfoResponse.setMessage(JudgeInfoMessageEnum.WRONG_ANSWER.getValue());
                return judgeInfoResponse;
            }
        }
//        判断条件限制是否符合
        long memoryLimit = judgeConfig.getMemoryLimit();
        long timeLimit = judgeConfig.getTimeLimit();
//        判断内存限制
        if (memory > memoryLimit) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
//        判断时间限制
        if (time > timeLimit) {
            judgeInfoResponse.setMessage(JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED.getValue());
            return judgeInfoResponse;
        }
        return judgeInfoResponse;
    }
}

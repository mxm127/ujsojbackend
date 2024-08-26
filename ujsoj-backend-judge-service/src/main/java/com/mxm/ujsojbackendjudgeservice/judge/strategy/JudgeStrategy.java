package com.mxm.ujsojbackendjudgeservice.judge.strategy;


import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;

public interface JudgeStrategy {

    /**
     * 沙箱进行编译执行
     * @param judgeContext
     * @return
     */
    public JudgeInfo doJudge(JudgeContext judgeContext);
}

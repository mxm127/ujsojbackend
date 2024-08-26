package com.mxm.ujsojbackendjudgeservice.judge;


import com.mxm.ujsojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.mxm.ujsojbackendjudgeservice.judge.strategy.JudgeContext;
import com.mxm.ujsojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;
import com.mxm.ujsojbackendmodel.model.entity.QuestionSubmit;
import com.mxm.ujsojbackendmodel.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

@Service
public class JudgeManager {
    /**
     * 根据策略判题
     * @param judgeContext
     * @return
     */
    JudgeInfo doJudge(JudgeContext judgeContext){
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = null;
        if (language.equals(QuestionSubmitLanguageEnum.JAVA.getValue())){
            judgeStrategy = new DefaultJudgeStrategy();
        }else {
            judgeStrategy = new DefaultJudgeStrategy();
        }
        JudgeInfo doneJudge = judgeStrategy.doJudge(judgeContext);
        return doneJudge;
    }
}

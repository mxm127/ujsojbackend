package com.mxm.ujsojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;
import com.mxm.ujsojbackendcommon.common.ErrorCode;
import com.mxm.ujsojbackendcommon.exception.BusinessException;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBoxFactory;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBoxProxy;
import com.mxm.ujsojbackendjudgeservice.judge.strategy.JudgeContext;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.mxm.ujsojbackendmodel.model.codesandbox.JudgeInfo;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeCase;
import com.mxm.ujsojbackendmodel.model.dto.question.JudgeConfig;
import com.mxm.ujsojbackendmodel.model.entity.Question;
import com.mxm.ujsojbackendmodel.model.entity.QuestionSubmit;
import com.mxm.ujsojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.mxm.ujsojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Value("${codesandbox.type:example}")
    private String type;
    @Resource
    private QuestionFeignClient questionFeignClient;

    @Resource
    private JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
//       1.1获取题目提交
        QuestionSubmit questionSubmit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "提交信息不存在");
        }
//       1.2获取题目 并判断题目是否存在
        Question question = questionFeignClient.getQuestionById(questionSubmit.getQuestionId());
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
//        2.如果题目提交状态不为等待状态，不需要重复进行判断
        if (!questionSubmit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题");
        }
//        3.更改题目提交的判断状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目系统错误");
        }

//        4.调用沙箱，获取执行结果
        CodeSandBox codeSandBox = new CodeSandBoxProxy(CodeSandBoxFactory.newInsance(type));
//         获取输入用例
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(questionSubmit.getCode())
                .language(questionSubmit.getLanguage())
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        List<String> outputList = executeCodeResponse.getOutputList();
        JudgeInfo judgeInfo = executeCodeResponse.getJudgeInfo();
//        5.根据沙箱执行结果，设置题目判断结果
        JudgeConfig judgeConfig = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        JudgeContext judgeContext = JudgeContext.builder()
                .outputList(outputList)
                .judgeInfo(judgeInfo)
                .judgeCaseList(judgeCaseList)
                .judgeConfig(judgeConfig)
                .questionSubmit(questionSubmit)
                .build();
        JudgeInfo doneJudge = judgeManager.doJudge(judgeContext);
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCEED.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(doneJudge));
        update = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if (!update) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "题目系统错误");
        }

        return questionFeignClient.getQuestionSubmitById(questionSubmitId);
    }

}


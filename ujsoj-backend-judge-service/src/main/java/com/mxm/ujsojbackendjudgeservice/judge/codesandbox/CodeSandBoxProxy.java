package com.mxm.ujsojbackendjudgeservice.judge.codesandbox;


import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CodeSandBoxProxy implements CodeSandBox{

    private final CodeSandBox codeSandBox;

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        log.info("请求信息："+executeCodeRequest.toString());
        ExecuteCodeResponse executeCodeResponse = codeSandBox.executeCode(executeCodeRequest);
        log.info("返回消息："+executeCodeResponse.toString());
        return executeCodeResponse;
    }
}

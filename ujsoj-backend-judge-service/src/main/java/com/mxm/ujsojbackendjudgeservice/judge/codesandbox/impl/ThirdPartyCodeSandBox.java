package com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl;


import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;

/**
 * 第三方代码沙箱(调用现用的沙箱)
 */
public class ThirdPartyCodeSandBox implements CodeSandBox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        return null;
    }
}

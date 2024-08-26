package com.mxm.ujsojbackendjudgeservice.judge.codesandbox;


import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandBox {

    /**
     * 执行代码
     * @param executeCodeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest);
}

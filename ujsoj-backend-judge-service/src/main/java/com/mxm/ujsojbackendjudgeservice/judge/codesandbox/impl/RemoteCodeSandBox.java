package com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.mxm.ujsojbackendcommon.common.ErrorCode;
import com.mxm.ujsojbackendcommon.exception.BusinessException;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.CodeSandBox;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeRequest;
import com.mxm.ujsojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

/**
 * 实际调用沙箱(调用我们自己实现的沙箱)
 */
public class RemoteCodeSandBox implements CodeSandBox {

    public static final String AUTH_REQUEST_HEADER = "auth";

    public static final String AUTH_REQUEST_SECRET = "secretKey";

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeCodeRequest) {
        System.out.println("远程代码沙箱");
        String url = "http://localhost:8090/executeCode";
        String json = JSONUtil.toJsonStr(executeCodeRequest);
        String responseStr = HttpUtil.createPost(url)
                .header(AUTH_REQUEST_HEADER, AUTH_REQUEST_SECRET)
                .body(json)
                .execute()
                .body();
        if (StringUtils.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executecode remoteSandBox error,message =" + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}

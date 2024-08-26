package com.mxm.ujsojbackendjudgeservice.judge.codesandbox;


import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl.ExampleCodeSandBox;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl.RemoteCodeSandBox;
import com.mxm.ujsojbackendjudgeservice.judge.codesandbox.impl.ThirdPartyCodeSandBox;

/**
 * 代码沙箱工厂(根据字符串参数创建指定的代码沙箱实例)
 */
public class CodeSandBoxFactory {

    /**
     * 创建代码沙箱实例
     * @param type
     * @return
     */
    public static CodeSandBox newInsance(String type){
        switch (type){
            case "example":
                return new ExampleCodeSandBox();
            case "remote":
                return new RemoteCodeSandBox();
            case "thirdParty":
                return new ThirdPartyCodeSandBox();
            default:
                return new ExampleCodeSandBox();
        }
    }
}

package com.dylan.test.auth.common.consts;

import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 模块名称: 常量模块 模块描述: 统一返回对象
 *
 * @author open-cld
 * @date 2020/3/11 9:50
 */
public class ActionResult<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private T data;

    private String msg = "";

    private List<String> msgParams;


    /**
     * 获得根据BindingResult 初始化的results
     * @param results
     * @return
     */
    public static ActionResult getBindingResult(BindingResult results) {
        ActionResult aResult = new ActionResult();
        if (results.hasErrors()){
            aResult.failure(results.getFieldError().getDefaultMessage());
        }else{
            aResult.setCode(RequestConsts.SUCCESS);
        }
        return aResult;
    }


    public static ActionResult success() {
        ActionResult aResult = new ActionResult();
        aResult.setMsg("success");
        aResult.setCode(RequestConsts.SUCCESS);
        return aResult;
    }

    public static ActionResult success(String msg) {
        ActionResult actionResult = ActionResult.success();
        actionResult.setMsg(msg);
        return actionResult;

    }

    public static ActionResult success(String msg, String[] msgParams) {
        ActionResult actionResult = ActionResult.success();
        actionResult.setMsg(msg);
        actionResult.setMsgParams(msgParams);
        return actionResult;

    }


    public static <T extends Serializable> ActionResult success(T data) {
        ActionResult actionResult = ActionResult.success();
        actionResult.setData(data);
        return actionResult;
    }

    public ActionResult success(String msg, T data) {
        ActionResult actionResult = ActionResult.success();
        actionResult.setMsg(msg);
        actionResult.setData(data);
        return actionResult;
    }

    public static <T extends Serializable> ActionResult success(String msg, String[] msgParams, T data) {
        ActionResult actionResult = ActionResult.success();
        actionResult.setMsg(msg);
        actionResult.setMsgParams(msgParams);
        actionResult.setData(data);
        return actionResult;

    }

    public static ActionResult failure() {
        ActionResult actionResult = new ActionResult();
        actionResult.setMsg("failure");
        actionResult.setCode(RequestConsts.FAILURE);
        return actionResult;
    }



    public static ActionResult failure(int code) {
        ActionResult actionResult = ActionResult.failure();
        actionResult.setCode(code);
        return actionResult;

    }

    public static ActionResult failure(int code,String msg) {
        ActionResult actionResult = ActionResult.failure(code);
        actionResult.setMsg(msg);
        return actionResult;
    }



    public static ActionResult failure(String msg) {
        ActionResult actionResult = ActionResult.failure();
        actionResult.setMsg(msg);
        return actionResult;

    }

    public static <T extends Serializable> ActionResult failure(String msg, T data) {
        ActionResult actionResult = ActionResult.failure(msg);
        actionResult.setData(data);
        return actionResult;
    }

    public static <T extends Serializable> ActionResult failure(T data) {
        ActionResult actionResult = ActionResult.failure();
        actionResult.setData(data);
        return actionResult;
    }

    public static ActionResult failure(int code, String msg, String[] msgParams) {
        ActionResult actionResult = ActionResult.failure(code,msg);
        actionResult.setMsgParams(msgParams);
        return actionResult;
    }

    public static   ActionResult failure(String msg, String[] msgParams) {
        ActionResult actionResult = ActionResult.failure(msg);
        actionResult.setMsgParams(msgParams);
        return actionResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String[] getMsgParams() {
        if(msgParams!=null) {
            String[] mss = new String[msgParams.size()];
            return msgParams.toArray(mss);
        }
        return null;
    }
    public List<String> getMsgParams(boolean b) {
        return msgParams;
    }
    public void setMsgParams(String[] msgParams) {
        this.msgParams = Arrays.asList(msgParams);
    }

    public Boolean isSuccess() {
        return code == 2000;
    }
}

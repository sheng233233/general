package edu.zut.utils;

import edu.zut.entity.Result;

public class ResultFactory {

    /**
     * 返回成功信息
     *
     * @param data 返回的数据
     * @return SUCCESS状态码 message成功 data数据
     */
    public static Result buildSuccessResult(Object data) {
        return buidResult(ResultCode.SUCCESS, "成功", data);
    }

    /**
     * 返回失败信息
     *
     * @param message 详细信息
     * @return FALL状态码 message详细信息
     */
    public static Result buildFailResult(String message) {
        return buidResult(ResultCode.FAIL, message, null);
    }

    /**
     * 返回的信息
     *
     * @param resultCode 状态码
     * @param message    详细信息
     * @param data       返回数据
     * @return 状态码+详细信息+返回数据
     */
    public static Result buidResult(ResultCode resultCode, String message, Object data) {
        return buidResult(resultCode.code, message, data);
    }

    /**
     * 返回的信息
     *
     * @param resultCode 系统状态码
     * @param message    详细信息
     * @param data       返回数据
     * @return 状态码+详细信息+返回数据
     */
    public static Result buidResult(int resultCode, String message, Object data) {
        return new Result(resultCode, message, data);
    }
}

package com.seckillprogect.error;

//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {
    private CommonError commonError;
    //直接接收EmBusinessError的传参用于构造业务异常
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }
    //自定义errMsg的方式构造业务异常
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrorMessage(errMsg);
    }


    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrorMessage() {
        return this.commonError.getErrorMessage();
    }

    @Override
    public CommonError setErrorMessage(String errMsg) {
        this.commonError.setErrorMessage(errMsg);
        return this;
    }
}

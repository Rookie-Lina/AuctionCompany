package com.sg.enums;


public enum ResultEnum {

    /**
     * 请求成功
     **/
    SUCCESS(200,"请求成功"),

    /**
     * 请求异常
     **/
    ERROR(500,"请求异常"),

    ;

    private Integer code;
    private String value;

    ResultEnum(Integer code, String value){
        this.code = code;
        this.value = value;
    }

    public static String isSuccessEnum(Integer code){
        ResultEnum[] array = values();
        for(ResultEnum arr: array){
            if(arr.code.equals(code)){
                return arr.value;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

}

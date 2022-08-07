package com.sg.hander;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description
 * @auther Rookie_lin
 * @create 2022-08-04 21:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IllegalityException extends RuntimeException {


    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "IllegalityException{" +
                "message=" + this.getMessage() +
                ", code=" + code +
                '}';
    }
}

package net.hrsoft.transparent_factory_manager.common.exceptions;

import java.io.IOException;

/**
 * @author abtion.
 * @since 17/8/25 18:05.
 * email caiheng@hrsoft.net
 */

public class ResultException extends IOException {
    private int code;
    private String msg;



    public ResultException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package net.hrsoft.transparent_factory_manager.common.exceptions;

import java.io.IOException;

/**
 * @author abtion.
 * @since 17/8/25 18:05.
 * email caiheng@hrsoft.net
 */

public class ResultException extends IOException {
    private int code;
    private String data;



    public ResultException(int code, String data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

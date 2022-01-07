package com.zcq.travelweb.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JSONResult<T> {
    private Integer code; //编码 200 成功 反之 失败
    private String message;
    private T data; //从服务端返回到前端的具体数值

    public JSONResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public JSONResult(String active, T data) {
        this.data = data;
    }
}

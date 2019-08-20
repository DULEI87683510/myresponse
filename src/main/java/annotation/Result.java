package annotation;


import utils.JsonUtils;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author DL
 * @Date 2019/6/21 15:40
 * @Version 1.0
 */
public interface Result<T> extends Serializable {
    String getCode();

    void setCode(String var1);

    String getMsg();

    void setMsg(String var1);

    T getData();

    void setData(T var1);

    default String toJson() {

        return JsonUtils.objectToJson(this);
    }
}
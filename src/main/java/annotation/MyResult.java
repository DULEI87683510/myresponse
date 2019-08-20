package annotation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;


/**
 *@ClassName MyResult
 *@Description TODO
 *@Author DL
 *@Date 2019/7/16 15:50    
 *@Version 1.0
 */
public class MyResult<T> implements Result<T> {
    private static final long serialVersionUID = 874200365941306385L;
    private String code;
    private String msg;
    private T data;

    public MyResult() {
    }

    public MyResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public MyResult(MyResultCode myResultCode) {
        this.code = myResultCode.code();
        this.msg = myResultCode.message();
    }

    public MyResult(MyResultCode myResultCode, T data) {
        this.code = myResultCode.code();
        this.msg = myResultCode.message();
        this.data = data;
    }

    public MyResult(T data) {
        this.code = MyResultCode.SUCCESS.code();
        this.msg = MyResultCode.SUCCESS.message();
        this.data = data;
    }

    public MyResult(MyException myException, String additionalMessage) {
        this.code = myException.getCode();
        this.msg = StringUtils.isEmpty(additionalMessage) ? myException.getMsg() : myException.getMsg().concat(",").concat(additionalMessage);
    }

    @JsonIgnore
    public boolean isSuccess() {
        return MyResultCode.SUCCESS.code().equals(this.code);
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public T getData() {
        return this.data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}

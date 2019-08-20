package annotation;

/**
 *@ClassName MyException
 *@Description TODO
 *@Author DL
 *@Date 2019/7/16 15:58    
 *@Version 1.0
 */

public class MyException extends RuntimeException implements Result<String> {
    private static final long serialVersionUID = 194906846739586856L;
    private final String code;
    private final String msg;

    public MyException(MyResultCode myResultCode) {
        this.code = myResultCode.code();
        this.msg = myResultCode.message();
    }

    public MyException(Result<?> result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    public MyException(String code, String message) {
        this.code = code;
        this.msg = message;
    }

    public MyException(MyResultCode myResultCode, Throwable cause) {
        super(cause);
        this.code = myResultCode.code();
        this.msg = myResultCode.message();
    }

    public MyException(String code, String message, Throwable cause) {
        super(cause);
        this.code = code;
        this.msg = message;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getData() {
        return null;
    }

    @Override
    public void setCode(String code) {
    }

    @Override
    public void setMsg(String msg) {
    }

    @Override
    public void setData(String data) {
    }
}

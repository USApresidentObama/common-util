package com.commom.test;

public class ResultForm {

    public static final int FLAG_SUCCESS = 1;
    public static final int FLAG_FAIL = 0;

    private Object data;

    private int flag = FLAG_SUCCESS;

    private String code;

    private String msg;

    public ResultForm() {
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public int getFlag() {
        return flag;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResultForm{");
        sb.append("data=").append(data);
        sb.append(", flag=").append(flag);
        sb.append(", code='").append(code).append('\'');
        sb.append(", msg='").append(msg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

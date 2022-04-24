package com.nkong.utils;

/**
 * @author nkong
 * @version 1.0
 * @date 2021/8/23 22:09
 */
public enum EnumUtil {

    SUCCESS("200", "调用成功."),
    FAILED("400", "调用失败."),
    ;

    private final String code;
    private final String description;

    EnumUtil(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return String.format("Code:[%s], Description:[%s].", this.code,
                this.description);
    }

    public static void main(String[] args) {
        System.out.println(EnumUtil.SUCCESS);
    }

}

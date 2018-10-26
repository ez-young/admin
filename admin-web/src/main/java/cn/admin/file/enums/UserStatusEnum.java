package cn.admin.file.enums;

/**
 * @author cbk
 * @date 2017/12/23
 */
public enum UserStatusEnum {
    /**
     * 状态-启用
     */
    ENABLE(0,"启用"),
    /**
     * 状态-禁用
     */
    FORBIDDEN(1,"禁用");

    private int status;
    private String comment;

    /**
     * 枚举的构造函数只能是私有的
     * @param status
     * @param comment
     */
    private UserStatusEnum(int status, String comment){
        this.status=status;
        this.comment=comment;
    }

    public int getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }
}

package com.skyler.cobweb.mybatis.model;

import java.io.Serializable;
import java.util.Date;

public class ServerInvocation implements Serializable {
    private Long id;

    /**
     * 调用服务名称
     */
    private String fromApplication;

    /**
     * 调用服务接口path
     */
    private String fromPath;

    /**
     * 被调用服务名称
     */
    private String toApplication;

    /**
     * 被调用服务接口path
     */
    private String toPath;


    private String method;

    private Date ctime;

    private Date utime;

    private Long creatorId;

    private String createName;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFromApplication() {
        return fromApplication;
    }

    public void setFromApplication(String fromApplication) {
        this.fromApplication = fromApplication;
    }

    public String getToApplication() {
        return toApplication;
    }

    public void setToApplication(String toApplication) {
        this.toApplication = toApplication;
    }

    public String getFromPath() {
        return fromPath;
    }

    public void setFromPath(String fromPath) {
        this.fromPath = fromPath;
    }

    public String getToPath() {
        return toPath;
    }

    public void setToPath(String toPath) {
        this.toPath = toPath;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", fromApplication=").append(fromApplication);
        sb.append(", toApplication=").append(toApplication);
        sb.append(", fromPath=").append(fromPath);
        sb.append(", toPath=").append(toPath);
        sb.append(", method=").append(method);
        sb.append(", ctime=").append(ctime);
        sb.append(", utime=").append(utime);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", createName=").append(createName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
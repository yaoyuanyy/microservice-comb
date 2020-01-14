package com.skyler.cobweb.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 18:21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerInvocation {

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
}
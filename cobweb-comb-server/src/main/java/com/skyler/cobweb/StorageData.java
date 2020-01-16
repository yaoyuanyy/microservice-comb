package com.skyler.cobweb;

import com.skyler.cobweb.mybatis.ServerInvocation;

/**
 * Description: 存储数据核心接口
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 18:41
 */
public interface StorageData {

    void save(ServerInvocation serverInvocation);
}

package com.skyler.cobweb.mybatis.mapper;

import com.skyler.cobweb.mybatis.ServerInvocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-14 at 18:23
 */
@Mapper
public interface ServerInvocationMapper {

    int save(@Param("serverInvocation") ServerInvocation serverInvocation);

}

package com.skyler.cobweb.mybatis.mapper;

import com.skyler.cobweb.mybatis.ServerInvocation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * <pre>
 *    refer to
 *    https://www.javaguides.net/2019/08/spring-boot-mybatis-mysql-example.html
 *    https://segmentfault.com/a/1190000017211657
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

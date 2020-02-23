package com.skyler.cobweb.mybatis.mapper;

import com.skyler.cobweb.mybatis.model.ServerInvocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <pre>
 *    refer to
 *    https://www.javaguides.net/2019/08/spring-boot-mybatis-mysql-example.html
 *    https://segmentfault.com/a/1190000017211657
 * </pre>
 *
 */
@Mapper
public interface ServerInvocationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServerInvocation record);

    ServerInvocation selectByPrimaryKey(Long id);

    List<ServerInvocation> selectAll();

    int updateByPrimaryKey(ServerInvocation record);
}
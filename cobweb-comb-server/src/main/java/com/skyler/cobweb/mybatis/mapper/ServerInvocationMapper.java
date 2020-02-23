package com.skyler.cobweb.mybatis.mapper;

import com.skyler.cobweb.mybatis.model.ServerInvocation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ServerInvocationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ServerInvocation record);

    ServerInvocation selectByPrimaryKey(Long id);

    List<ServerInvocation> selectAll();

    int updateByPrimaryKey(ServerInvocation record);
}
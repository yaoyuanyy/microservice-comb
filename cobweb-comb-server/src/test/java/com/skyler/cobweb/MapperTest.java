package com.skyler.cobweb;

import com.skyler.cobweb.mybatis.mapper.ServerInvocationMapper;
import com.skyler.cobweb.mybatis.model.ServerInvocation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description:
 * <p></p>
 * <pre>
 *
 *   NB.
 * </pre>
 * <p>
 * Created by skyler on 2020/2/23 at 12:10 AM
 */
@Slf4j
public class MapperTest extends AppTest {

    @Autowired private ServerInvocationMapper serverInvocationMapper;


    @Test
    public void testSelect(){
        List<ServerInvocation> serverInvocations =  serverInvocationMapper.selectAll();
        log.info("result:{}", gson.toJson(serverInvocations));
    }
}

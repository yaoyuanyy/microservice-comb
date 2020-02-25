package com.skyler.cobweb.a.web;

import com.skyler.cobweb.b.client.ComboFeignClient;
import com.skyler.cobweb.b.dto.ComboDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2019-09-10 at 12:11
 */
@RestController
@Slf4j
@RequestMapping("/combo")
public class ComboController {

    @Autowired
    private ComboFeignClient comboFeignClient;

    @RequestMapping("/getById")
    public ComboDTO getById(Long id) {
        log.info("param id:{}", id);
        return comboFeignClient.getById(id);
    }
}

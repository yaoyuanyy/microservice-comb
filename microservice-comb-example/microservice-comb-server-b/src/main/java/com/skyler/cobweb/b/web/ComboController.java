package com.skyler.cobweb.b.web;

import com.skyler.cobweb.b.client.ComboFeignClient;
import com.skyler.cobweb.b.dto.ComboDTO;
import lombok.extern.slf4j.Slf4j;
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
public class ComboController implements ComboFeignClient {

    @Override
    public ComboDTO getById(Long id) {
        log.info("param id:{}", id);
        return ComboDTO.builder().comboName("海底捞经典套餐").id(id).brandName("海底捞").build();
    }
}

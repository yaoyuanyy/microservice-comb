package com.skyler.cobweb.web;

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
public class ComboController {

//    @Autowired
//    private ConstructionComboService constructionComboService;
//
//    @Override
//    public ResultDTO create(ComboParam param) {
//        log.info("【Enter method create】param:{}", param);
//        return new ResultDTO().success(constructionComboService.create(param));
//    }
//
//    @Override
//    public ResultDTO update(ComboParam param) {
//        log.info("【Enter method update】param:{}", param);
//        constructionComboService.update(param);
//        return new ResultDTO().success();
//    }
//
//    @Override
//    public ResultDTO effect(ComboParam param) {
//        log.info("【Enter method effect】param:{}", param);
//        constructionComboService.effect(param.getId(), param.getEffect());
//        return new ResultDTO().success();
//    }
//
//
//    @Override
//    public ResultDTO<Page<ComboDTO>> listCombo(Long comboId, Byte boundConstructionTemplate, Byte state, Integer currentPage, Integer pageSize) {
//        log.info("【Enter method listCombo】comboId:{}, boundConstructionTemplate{}, state:{}, currentPage:{}, pageSize:{}", comboId, boundConstructionTemplate, state, currentPage, pageSize);
//        ResultDTO dto = new ResultDTO<>().success(constructionComboService.list(comboId, boundConstructionTemplate, state, currentPage, pageSize));
//        log.info("【End method listCombo】result:{}", dto);
//        return dto;
//    }
//
//    @Override
//    public ResultDTO<List<ComboDTO>> listBrand(Long brandId, Byte state) {
//        log.info("【Enter method listCombo】brandId:{}", brandId);
//        ResultDTO dto = new ResultDTO<>().success(constructionComboService.listComboByBrandId(brandId, state));
//        log.info("【End method listCombo】result:{}", dto);
//        return dto;
//    }
}

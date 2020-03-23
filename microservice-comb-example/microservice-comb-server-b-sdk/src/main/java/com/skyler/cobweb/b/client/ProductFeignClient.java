package com.skyler.cobweb.b.client;

import com.skyler.cobweb.b.dto.ProductDTO;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Description:
 * <pre>
 *
 * </pre>
 * NB.
 *
 * @author skyler
 * Created by on 2020-01-08 at 16:50
 */
@FeignClient(name = "microservice-comb-server-b", contextId = "ProductFeignClient" )
public interface ProductFeignClient {

    @RequestMapping("/api/combo/getProductById")
    ProductDTO getProductById(@RequestParam("userId") Long userId);

    @PostMapping(name = "/api/combo/createProduct")
    @ApiOperation(value = "创建产品", notes = "创建产品")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", dataType = "String", name = "productId", value = "productId")
    })
    ProductDTO createProduct(@RequestParam("productId") String productId);

    @GetMapping("/api/combo/getProductByName")
    @ApiOperation(value = "查询产品", notes = "查询产品")
    ProductDTO getProductByName(@RequestParam("productName") Long productName);
}

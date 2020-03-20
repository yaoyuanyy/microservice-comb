package com.skyler.cobweb.b.client;

import com.skyler.cobweb.b.dto.ProductDTO;
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

    @PostMapping("/api/combo/createProduct")
    ProductDTO createProduct(@RequestParam("productId") String productId);

    @GetMapping("/api/combo/getProductByName")
    ProductDTO getProductByName(@RequestParam("productName") Long productName);
}

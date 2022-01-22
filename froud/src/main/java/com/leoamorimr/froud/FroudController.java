package com.leoamorimr.froud;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/froud-check")
@RequiredArgsConstructor
@Slf4j
public class FroudController {

    private final FroudCheckService froudCheckService;

    @GetMapping(path = "{customerId}")
    public FroudCheckResponse isFroudster(@PathVariable("customerId") Integer customerId) {
        boolean isFroudulentCustomer = froudCheckService.isFroudulentCustomer(customerId);
        log.info("froud check history request for customer {}", customerId);
        return new FroudCheckResponse(isFroudulentCustomer);
    }
}

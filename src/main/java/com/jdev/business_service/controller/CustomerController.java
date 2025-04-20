package com.jdev.business_service.controller;

import com.jdev.business_service.service.CustomerService;
import com.jdev.business_service.vo.ui.AddCustomerResponseVO;
import com.jdev.business_service.vo.ui.AddCustomerVo;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("")
    public ResponseEntity<AddCustomerResponseVO> addCustomer(@Valid @RequestBody AddCustomerVo addCustomerVo){
        AddCustomerResponseVO addCustomerResponseVO = customerService.addCustomer(addCustomerVo);
        log.info("{}", addCustomerResponseVO);
        if(addCustomerResponseVO.isSuccess()){
            return ResponseEntity.ok(addCustomerResponseVO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addCustomerResponseVO);
    }
}

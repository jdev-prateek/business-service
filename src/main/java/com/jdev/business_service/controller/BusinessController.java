package com.jdev.business_service.controller;

import com.jdev.business_service.service.BusinessService;
import com.jdev.business_service.vo.ui.AddBusinessVO;
import com.jdev.business_service.vo.ui.BusinessAddResponseVO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("")
    public ResponseEntity<BusinessAddResponseVO> addBusiness(@Valid @RequestBody AddBusinessVO addBusinessVO){
        BusinessAddResponseVO businessAddResponseVO = businessService.addBusiness(addBusinessVO);

        if(businessAddResponseVO.isSuccess()){
            return ResponseEntity.ok(businessAddResponseVO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(businessAddResponseVO);
    }
}

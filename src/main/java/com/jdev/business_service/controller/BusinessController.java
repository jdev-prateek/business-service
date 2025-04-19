package com.jdev.business_service.controller;

import com.jdev.business_service.service.BusinessService;
import com.jdev.business_service.vo.ui.AddBusinessVO;
import com.jdev.business_service.vo.ui.BusinessResponseVO;
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
    public ResponseEntity<BusinessResponseVO> addBusiness(@Valid @RequestBody AddBusinessVO addBusinessVO){
        BusinessResponseVO businessResponseVO = businessService.addBusiness(addBusinessVO);

        if(businessResponseVO.isSuccess()){
            return ResponseEntity.ok(businessResponseVO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(businessResponseVO);
    }
}

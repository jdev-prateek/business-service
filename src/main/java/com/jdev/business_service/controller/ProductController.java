package com.jdev.business_service.controller;

import com.jdev.business_service.service.HSHService;
import com.jdev.business_service.vo.AddHSNVO;
import com.jdev.business_service.vo.ui.AddHSNResponseVO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final HSHService hshService;

    public ProductController(HSHService hshService) {
        this.hshService = hshService;
    }

    @PostMapping("/hsn")
    public ResponseEntity<AddHSNResponseVO> addHSN(@Valid @RequestBody AddHSNVO addHSNVO){
        AddHSNResponseVO addHSNResponseVO = hshService.addHSN(addHSNVO);
        if(addHSNResponseVO.isSuccess()){
            return ResponseEntity.ok(addHSNResponseVO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(addHSNResponseVO);
    }
}

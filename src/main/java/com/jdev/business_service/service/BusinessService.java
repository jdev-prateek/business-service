package com.jdev.business_service.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jdev.business_service.config.ShardContextHolder;
import com.jdev.business_service.constants.ErrorKeyConstants;
import com.jdev.business_service.entity.Business;
import com.jdev.business_service.enums.ResponseStatus;
import com.jdev.business_service.repository.BusinessRepository;
import com.jdev.business_service.vo.ui.AddBusinessVO;
import com.jdev.business_service.vo.ui.BusinessAddResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

@Service
public class BusinessService {

    private static final Logger log = LoggerFactory.getLogger(BusinessService.class);
    private final BusinessRepository businessRepository;

    public BusinessService(BusinessRepository businessRepository) {
        this.businessRepository = businessRepository;
    }

    @Transactional
    public BusinessAddResponseVO addBusiness(AddBusinessVO addBusinessVO){
        ShardContextHolder.setBusinessIdHolder(UuidCreator.getTimeOrderedEpoch());

        BusinessAddResponseVO businessResponseVO = new BusinessAddResponseVO();
        AddBusinessVO.Payload payload = addBusinessVO.getPayload();

        Optional<Business> optionalBusiness = businessRepository.findByGstin(payload.getGstin());

        if(optionalBusiness.isPresent()){
            log.info("Duplicate business found with GSTIN: {}", payload.getGstin());
            businessResponseVO.setStatus(ResponseStatus.FAILURE);
            businessResponseVO.setErrorKey(ErrorKeyConstants.BUSINESS_ALREADY_EXISTS);
            HashMap<String, String> errorMap = new HashMap<>();
            errorMap.put("message", ErrorKeyConstants.BUSINESS_ALREADY_EXISTS_DESC);
            businessResponseVO.setErrorMap(errorMap);
            return businessResponseVO;
        }

        Business newBusiness = new Business();
        newBusiness.setId(ShardContextHolder.getBusinessIdHolder());
        newBusiness.setName(payload.getName());
        newBusiness.setGstin(payload.getGstin());
        newBusiness.setPreferredLang(payload.getPreferredLang());
        Business savedBusiness = businessRepository.save(newBusiness);

        businessResponseVO.setName(savedBusiness.getName());
        businessResponseVO.setGstin(savedBusiness.getGstin());
        businessResponseVO.setPreferredLang(savedBusiness.getPreferredLang());
        businessResponseVO.setId(savedBusiness.getId());
        businessResponseVO.setStatus(ResponseStatus.SUCCESS);

        log.info("Business created: {}", savedBusiness);
        return businessResponseVO;
    }
}

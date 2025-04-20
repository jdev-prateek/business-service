package com.jdev.business_service.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jdev.business_service.constants.ErrorKeyConstants;
import com.jdev.business_service.entity.HSN;
import com.jdev.business_service.enums.ResponseStatus;
import com.jdev.business_service.repository.HSNRepository;
import com.jdev.business_service.vo.AddHSNVO;
import com.jdev.business_service.vo.ui.AddHSNResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HSHService {
    private static final Logger log = LoggerFactory.getLogger(HSHService.class);
    private final HSNRepository hsnRepository;

    public HSHService(HSNRepository hsnRepository) {
        this.hsnRepository = hsnRepository;
    }

    public AddHSNResponseVO addHSN(AddHSNVO addHSNVO){
        AddHSNResponseVO addHSNResponseVO = new AddHSNResponseVO();
        AddHSNVO.Payload payload = addHSNVO.getPayload();

        Optional<HSN> optionalHSN = hsnRepository.findByCode(payload.getCode());

        if(optionalHSN.isPresent()){
            log.info("Duplicate HSN found");
            addHSNResponseVO.setStatus(ResponseStatus.FAILURE);
            addHSNResponseVO.setErrorKey(ErrorKeyConstants.HSID_ALREADY_EXISTS_DESC);
            return addHSNResponseVO;
        }

        HSN newHsn = new HSN();
        newHsn.setId(UuidCreator.getTimeOrderedEpoch());
        newHsn.setHsnCode(payload.getCode());
        newHsn.setGst(payload.getGst());
        newHsn.setDescription(payload.getDescription());

        HSN savedHSN = hsnRepository.saveAndFlush(newHsn);

        addHSNResponseVO.setId(savedHSN.getId());
        addHSNResponseVO.setCode(savedHSN.getHsnCode());
        addHSNResponseVO.setGst(savedHSN.getGst());
        addHSNResponseVO.setDescription(savedHSN.getDescription());
        addHSNResponseVO.setStatus(ResponseStatus.SUCCESS);

        log.info("HSN saved: {}", savedHSN);
        return addHSNResponseVO;
    }
}

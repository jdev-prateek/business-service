package com.jdev.business_service.service;

import com.github.f4b6a3.uuid.UuidCreator;
import com.jdev.business_service.constants.CustomerTypeConstants;
import com.jdev.business_service.constants.ErrorKeyConstants;
import com.jdev.business_service.entity.Business;
import com.jdev.business_service.entity.Customer;
import com.jdev.business_service.enums.ResponseStatus;
import com.jdev.business_service.filter.ShardContextFilter;
import com.jdev.business_service.repository.BusinessRepository;
import com.jdev.business_service.repository.CustomerRepository;
import com.jdev.business_service.vo.ui.AddCustomerResponseVO;
import com.jdev.business_service.vo.ui.AddCustomerVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CustomerService(CustomerRepository customerRepository, BusinessRepository businessRepository) {
        this.customerRepository = customerRepository;
        this.businessRepository = businessRepository;
    }

    @Transactional
    public AddCustomerResponseVO addCustomer(AddCustomerVo addCustomerVo){
        AddCustomerResponseVO addCustomerResponseVO = new AddCustomerResponseVO();
        AddCustomerVo.Payload payload = addCustomerVo.getPayload();

        Optional<Business> optionalBusiness =
                businessRepository.findById(UUID.fromString(payload.getBusinessId()));

        if(optionalBusiness.isEmpty()){
            addCustomerResponseVO.setStatus(ResponseStatus.FAILURE);
            addCustomerResponseVO.setErrorKey(ErrorKeyConstants.BUSINESS_NOT_FOUND);
            return addCustomerResponseVO;
        }

        Customer newCustomer = new Customer();
        newCustomer.setId(UuidCreator.getTimeOrderedEpoch());
        newCustomer.setFirstName(payload.getFirstName());
        newCustomer.setMiddleName(payload.getMiddleName());
        newCustomer.setLastName(payload.getLastName());
        newCustomer.setPhone(payload.getPhone());
        newCustomer.setEmail(payload.getEmail());
        newCustomer.setType(CustomerTypeConstants.valueOf(payload.getType()));

        Business business = optionalBusiness.get();
        newCustomer.getBusinessSet().add(business);
        Customer savedCustomer = customerRepository.saveAndFlush(newCustomer);

        addCustomerResponseVO.setStatus(ResponseStatus.SUCCESS);
        addCustomerResponseVO.setFirstName(savedCustomer.getFirstName());
        addCustomerResponseVO.setMiddleName(savedCustomer.getMiddleName());
        addCustomerResponseVO.setLastName(savedCustomer.getLastName());
        addCustomerResponseVO.setPhone(savedCustomer.getPhone());
        addCustomerResponseVO.setType(savedCustomer.getType());
        addCustomerResponseVO.setCreatedAt(savedCustomer.getCreatedAt());
        addCustomerResponseVO.setUpdatedAt(savedCustomer.getUpdatedAt());

        return addCustomerResponseVO;
    }
}

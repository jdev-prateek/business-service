package com.jdev.business_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class ShardRoutingDataSource extends AbstractRoutingDataSource {
    private final Logger log = LoggerFactory.getLogger(ShardContextHolder.class);

    @Override
    protected Object determineCurrentLookupKey() {
        Long businessId = ShardContextHolder.getHashCode();
        int shard = (businessId != null) ? Math.abs(businessId.intValue()) % 2 : 0;
        log.info("ROUTING TO SHARD: {}", shard);
        return shard;
    }
}

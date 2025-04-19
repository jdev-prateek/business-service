package com.jdev.business_service.config;

import java.util.UUID;

public class ShardContextHolder {
    private static final ThreadLocal<UUID> businessIdHolder = new ThreadLocal<>();

    public static UUID getBusinessIdHolder() {
        return businessIdHolder.get();
    }

    public static void setBusinessIdHolder(UUID businessId) {
        businessIdHolder.set(businessId);
    }

    public static Long getHashCode(){
        if(businessIdHolder.get() == null){
            return null;
        }

        return (long) businessIdHolder.get().hashCode();
    }

    public static void clear(){
        businessIdHolder.remove();
    }
}

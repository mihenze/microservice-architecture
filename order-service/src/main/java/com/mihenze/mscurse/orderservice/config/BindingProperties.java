package com.mihenze.mscurse.orderservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.bindings")
@Getter
@Setter
public class BindingProperties {
    private String paymentCreated;
    private String shipmentCreated;
<<<<<<< task11-idempotent-event
    private String paymentConfirm;
    private String shipmentConfirm;
=======
>>>>>>> main
}

package com.tom.pharmacy.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI pharmacyOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("药店药品进销存管理系统 API")
                        .description("提供药品管理、入库出库、库存预警、销售统计及供应商管理接口")
                        .version("1.0.0"));
    }
}

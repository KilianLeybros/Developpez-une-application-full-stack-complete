package com.openclassrooms.mddapi.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
/*@SecurityScheme(
        scheme = "cookieAuth",
        name = "token",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.COOKIE
)*/
@OpenAPIDefinition(info = @Info(title = "MDD"), security = @SecurityRequirement(name = "token"))
public class SwaggerConfiguration {

}
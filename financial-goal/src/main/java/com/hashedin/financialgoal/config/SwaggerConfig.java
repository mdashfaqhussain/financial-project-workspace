package com.hashedin.financialgoal.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
		info = @Info(
				title = "Finance Goal",
				description = "CRUD for financial goals",
				contact = @Contact(
						name = "Ashfaq",
						email="ashhussain@deloitte.com"
						)
				
				
				),
		security = @SecurityRequirement(name = "authBearer")
		
		)



@SecurityScheme(
		name = "authBearer",
		in = SecuritySchemeIn.HEADER,
		type = SecuritySchemeType.HTTP,
		bearerFormat="JWT",
		scheme = "bearer",
		description = "JWT token desc"
		)
public class SwaggerConfig {

}

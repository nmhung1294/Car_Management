package facenet.car_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    public OpenAPI createApiDoc() {
            return new OpenAPI().info(
                    new Info().title("Car Management API")
                            .version("1.0")
                            .description("API cho project nhỏ quản lý xe")
            );
    }

}

package faceRecognition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


//@EnableAutoConfiguration
@SpringBootApplication
//@ComponentScan("faceRecognition.config")
@ComponentScan
@EntityScan
@PropertySource("classpath:db.properties")
@EnableAutoConfiguration(exclude = { 
        org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class 
    })
public class FaceRecognitionV2Application {

	public static void main(String[] args) {
		SpringApplication.run(FaceRecognitionV2Application.class, args);
	}
}

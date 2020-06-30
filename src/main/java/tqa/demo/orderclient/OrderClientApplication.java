package tqa.demo.orderclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderClientApplication.class, args);
	}
	
	/*
	 * 더 이상 자동으로 RestTemplate을 지원하지 않으므로, 아래와 같이 application시작할 때 bean으로 등록한다.
	 */
	@Bean
	public RestTemplate restTemplate() {
	   return new RestTemplate();
	}


}

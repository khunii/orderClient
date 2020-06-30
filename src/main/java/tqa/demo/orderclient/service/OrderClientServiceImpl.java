package tqa.demo.orderclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import tqa.demo.order.dto.OrderDTO;

@Service
public class OrderClientServiceImpl implements OrderClientService {
	
	private final RestTemplate restTemplate;
	private final String localUrl;

	public OrderClientServiceImpl(RestTemplate restTemplate, @Value("${order.service.url}") String localUrl) {
		this.restTemplate = restTemplate;
		this.localUrl = localUrl;
	}
	
	@Override
	public OrderDTO callPlaceOrder(OrderDTO dto) throws Exception {
		String url = localUrl + "/demo/order/v1";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<OrderDTO> request = new HttpEntity<>(dto, headers);
		OrderDTO retDTO = restTemplate.postForObject(url, request, OrderDTO.class);
		return retDTO;
	}

}

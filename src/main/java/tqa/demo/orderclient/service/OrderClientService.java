package tqa.demo.orderclient.service;

import tqa.demo.order.dto.OrderDTO;

public interface OrderClientService {
	
	public OrderDTO callPlaceOrder(OrderDTO dto) throws Exception;

}

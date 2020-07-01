package tqa.demo.orderclient.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import tqa.demo.order.dto.OrderDTO;
import tqa.demo.order.dto.OrderedProductDTO;
import tqa.demo.order.dto.ShippingAddressDTO;
import tqa.demo.order.entity.Status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureStubRunner(ids="tqa.demo:order:+:stubs:7475")
@AutoConfigureJsonTesters
public class OrderClientServiceTest {
	
	@Autowired
	OrderClientService service;
	
	private JacksonTester<OrderDTO> orderDTOJson;

	@Before
	public void setUp() throws Exception{
		ObjectMapper objMapper = new ObjectMapper();
		JacksonTester.initFields(this, objMapper);
	}
	
	@Test
	public void testcallPlaceOrder() throws Exception{
		//given(입력값)
		List<OrderedProductDTO> productDTOList = new ArrayList<>();
		productDTOList.add(OrderedProductDTO.builder().productId(1L).price(2000L).qty(10).build());
		productDTOList.add(OrderedProductDTO.builder().productId(2L).price(1000L).qty(20).build());

		OrderDTO orderDTO = OrderDTO.builder()
				.orderUserId("jacob_test")
				.orderedDate("20200701")
				.updatedDate("20200701")
				.status(Status.PREPARED)
				.orderedProducts(productDTOList)
				.shippingAddress(ShippingAddressDTO.builder().recipient("Jacob_test").zipCode("123-345").build())
				.build();
		
		//when
		OrderDTO actual = service.callPlaceOrder(orderDTO);
		
		//then
		//consumer입장에서는 stub된 response의 value는 정확히 알지 못하므로, 전달되는 구조 검증
		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.id");
		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.orderUserId");
		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.orderedDate");
		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.updatedDate");
		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.status");
		//consumer가 필요한 송수신 scheme만 검증하기 위해 주석처리해봄
//		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.orderedProducts");
//		assertThat(this.orderDTOJson.write(actual)).hasJsonPath("@.shippingAddress");
	}

}

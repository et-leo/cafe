package cafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import cafe.interfaces.iOrmMethods;
import cafe.model.entities.CafeOrder;
import cafe.model.entities.Supplier;

@RestController 
@SpringBootApplication
@ImportResource("classpath:beans.xml")

public class CafeWebController {

	
	@Autowired
	iOrmMethods cafe;

	static RestTemplate restTemplate = new RestTemplate();
	static String url = "http://localhost:8080/";

	@RequestMapping(value = "getSupplier/{id}", method = RequestMethod.GET)
	public Supplier getSupplierById(@PathVariable int id) {
		return cafe.getSupplierById(id);
	}
	
	@RequestMapping(value = "getOrder", method = RequestMethod.GET)
	public CafeOrder getCafeOrder(long id) {
		return cafe.getCafeOrderById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(CafeWebController.class, args);
	}

}

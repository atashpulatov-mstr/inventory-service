package com.atashpulatov.inventory_service.inventory_service;

import com.atashpulatov.inventory_service.model.Inventory;
import com.atashpulatov.inventory_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory firstInventory = Inventory.builder()
					.skuCode("iPhone_15")
					.quantity(100)
					.build();
			inventoryRepository.save(firstInventory);

			Inventory secondInventory = Inventory.builder()
					.skuCode("iPhone_13_red")
					.quantity(0)
					.build();
			inventoryRepository.save(secondInventory);
		};
	}

	@KafkaListener(topics = "greetings", groupId = "demo")
	public void listen(String message) {
		System.out.println("Received message: " + message);
	}
}

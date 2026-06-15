package com.atashpulatov.inventory_service.inventory_service.service;

import com.atashpulatov.inventory_service.dto.InventoryResponse;
import com.atashpulatov.inventory_service.model.Inventory;
import com.atashpulatov.inventory_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        List<Inventory> list =  inventoryRepository.findBySkuCodeIn(skuCode);
        List<InventoryResponse> response = list.stream()
                .map(inventory -> InventoryResponse.builder()
                            .skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()
                ).toList();
        return response;
    }

}

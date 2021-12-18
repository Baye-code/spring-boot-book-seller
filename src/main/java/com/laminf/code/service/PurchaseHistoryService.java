package com.laminf.code.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laminf.code.model.PurchaseHistory;
import com.laminf.code.repository.IPurchaseRepository;
import com.laminf.code.repository.projection.IPurchaseItem;

@Service
public class PurchaseHistoryService implements IPurchaseHistoryService {
	
	// ================================================================================= //
	
	private final IPurchaseRepository purchaseRepository;
	
	public PurchaseHistoryService(IPurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}
	
// ================================================================================= //

	@Override
	public PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory) {
		purchaseHistory.setPurchaseTime(LocalDateTime.now());
		return purchaseRepository.save(purchaseHistory);
	}
	
	
	@Override
	public List<IPurchaseItem> findPurchasedItemsOfUser(Long userId){
		return purchaseRepository.findAllPurchasesOfUsers(userId);
	}
	
	// ================================================================================= //

}

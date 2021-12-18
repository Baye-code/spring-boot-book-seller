package com.laminf.code.service;

import java.util.List;

import com.laminf.code.model.PurchaseHistory;
import com.laminf.code.repository.projection.IPurchaseItem;

public interface IPurchaseHistoryService {

	List<IPurchaseItem> findPurchasedItemsOfUser(Long userId);

	PurchaseHistory savePurchaseHistory(PurchaseHistory purchaseHistory);

}

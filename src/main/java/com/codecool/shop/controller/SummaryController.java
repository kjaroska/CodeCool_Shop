package com.codecool.shop.controller;


import com.codecool.shop.model.Basket;
import com.codecool.shop.model.Item;
import java.util.ArrayList;

class SummaryController {

  private static void printOrderSummary(Basket basket) {
      Float orderPrice = 0.0f;
      for (Item product : basket.getItemList()) {
          orderPrice = orderPrice + product.getTotalPrice();
      }
      ;
      basket.setTotalPrice(orderPrice);
  }

    private static void cardVerification() {
        ArrayList<String> cardData = new ArrayList<>();
        String cardNumber = "";
        String cardVerificationCode = "";
        while (cardNumber.length() != 16 && (cardVerificationCode.length() != 4
            || cardVerificationCode.length() != 3)) {
        }
        cardData.add(cardNumber);
        cardData.add(cardVerificationCode);
    }
}

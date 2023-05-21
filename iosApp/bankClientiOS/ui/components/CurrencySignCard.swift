//
//  CurrencySignCard.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 21.05.2023.
//

import Foundation
import shared
import SwiftUI

struct CurrencySignCard : View {
    
    private let currency: CurrencyItem
    private let isSelected: Bool
    private let cardBackgroundColor: Color
    private let onClickAction: (CurrencyItem) -> Void
    
    init(currency: CurrencyItem, isSelected: Bool, onClickAction: @escaping (CurrencyItem) -> Void) {
        self.currency = currency
        self.isSelected = isSelected
        if (isSelected) {
            self.cardBackgroundColor = Color.secondary
        }  else {
            self.cardBackgroundColor = Color.primary
        }
        self.onClickAction = onClickAction
    }
    
    var body : some View {
        ZStack {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(cardBackgroundColor)
                .shadow(radius: 10)
                .frame(width: 100, height: 100)
                .onTapGesture {
                    onClickAction(currency)
                }
            VStack {
                Text(
                    CurrencySign().getCurrencySign(charCode: currency.charCode)
                ).font(.title3)
                Text(
                    currency.charCode
                ).font(.body)
            }
        }
    }
    
}

//
//  TransactionHistoryBlock.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 21.05.2023.
//

import Foundation
import SwiftUI
import shared

struct TransactionHistoryBlock : View {
    
    private let transactionHistory: Array<TransactionHistory>
    private let selectedCurrency: CurrencyItem
    private let currency: Currency
    
    init(transactionHistory: Array<TransactionHistory>, selectedCurrency: CurrencyItem, currency: Currency) {
        self.transactionHistory = transactionHistory
        self.selectedCurrency = selectedCurrency
        self.currency = currency
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(Color.primary)
                .shadow(radius: 10)
            VStack(alignment: .leading) {
                Text("History")
                    .font(.body)
                    .bold()
                    .padding(EdgeInsets(top: 12, leading: 12, bottom: 12, trailing: 12))
                ForEach(transactionHistory, id: \.self) { transaction in
                    TransactionItem(transactionHistory: transaction, selectedCurrency: selectedCurrency, currency: currency)
                }
            }
            
        }.padding()
        .fixedSize(horizontal: false, vertical: true)
    }
    
}

struct TransactionItem : View {
    
    private let transactionHistory: TransactionHistory
    private let selectedCurrency: CurrencyItem
    private let currency: Currency
    
    init(transactionHistory: TransactionHistory, selectedCurrency: CurrencyItem, currency: Currency) {
        self.transactionHistory = transactionHistory
        self.selectedCurrency = selectedCurrency
        self.currency = currency
    }
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: transactionHistory.iconUrl))
                .padding(EdgeInsets(top: 4, leading: 8, bottom: 4, trailing: 8))
            
            VStack(alignment: .leading) {
                Text(transactionHistory.title)
                    .font(.body)
                Text(transactionHistory.date)
                    .font(.system(size: 8))
                    .foregroundColor(Color.onSurface)
            }
            
            Spacer()
            
            VStack(alignment: .trailing) {
                HStack {
                    Group {
                        Text("- " + "\(CurrencySign().getCurrencySign(charCode: selectedCurrency.charCode)) ")
                                .foregroundColor(Color.onSurface) +
                        Text(CurrencyChange().changeCurrencyAndRound(
                            usdAmount: abs(Double(transactionHistory.amount) ?? 0.0),
                            usdChangeRate: currency.valute.uSD.value,
                            targetChangeRate: selectedCurrency.value)
                        )
                        .foregroundColor(Color.onPrimary)
                        
                    }.font(.title3)
                }
                Text(
                    "\(CurrencySign().getCurrencySign(charCode: "USD")) \(CurrencyChange().toMoneyString(data: abs(Double(transactionHistory.amount) ?? 0.0)))"
                ).foregroundColor(Color.onSurface)
                    .font(.system(size: 10))
            }.padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 12))
        }
    }
}



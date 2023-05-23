//
//  ContentView.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 29.04.2023.
//

import SwiftUI
import shared

struct MainScreen: View {
    
    @ObservedObject private var viewModel = MainViewModel.getInstance()
    
    
    var body: some View {
        
        @State var usersData  = viewModel.usersData
        @State var selectedCurrency = viewModel.selectedCurrency
        @State var selectedUser = viewModel.selectedUser
        @State var currency = viewModel.currency
                
        VStack(alignment: .leading) {
            
            if (usersData != nil && selectedCurrency != nil && selectedUser != nil && currency != nil) {
                UserInfoCard(users: usersData!, selectedCurrency: selectedCurrency!, user: selectedUser!, currency: currency!)
                
                Text(
                    "Change currency"
                )
                .font(.title2)
                .foregroundColor(Color.onPrimary)
                .padding(EdgeInsets(top: 8, leading: 12, bottom: 12, trailing: 12))
                
                HStack {
                    CurrencySignCard(
                        currency: currency!.valute.gBP,
                        isSelected: selectedCurrency!.charCode == currency!.valute.gBP.charCode,
                        onClickAction: { newSelectedCurrency in
                            viewModel.changeSelectedCurrency(currency: newSelectedCurrency)
                        }
                    )
                    Spacer()
                    CurrencySignCard(
                        currency: currency!.valute.eUR,
                        isSelected: selectedCurrency!.charCode == currency!.valute.eUR.charCode,
                        onClickAction: { newSelectedCurrency in
                            viewModel.changeSelectedCurrency(currency: newSelectedCurrency)
                        }
                    )
                    Spacer()
                    CurrencySignCard(
                        currency: currency!.valute.rUB,
                        isSelected: selectedCurrency!.charCode == currency!.valute.rUB.charCode,
                        onClickAction: { newSelectedCurrency in
                            viewModel.changeSelectedCurrency(currency: newSelectedCurrency)
                        }
                    )
                }.padding(EdgeInsets(top: 4, leading: 12, bottom: 12, trailing: 12))
                TransactionHistoryBlock(
                    transactionHistory: selectedUser!.transactionHistory,
                    selectedCurrency: selectedCurrency!,
                    currency: currency!
                )
            }
            
        }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
    }
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}

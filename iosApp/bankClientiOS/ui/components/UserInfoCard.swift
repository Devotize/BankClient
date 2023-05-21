//
//  UserInfoCard.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 21.05.2023.
//

import SwiftUI
import shared

struct UserInfoCard : View {
    let users: Users
    let selectedCurrency: CurrencyItem
    let user: User
    let currency: Currency
    
    init(users: Users, selectedCurrency: CurrencyItem, user: User, currency: Currency) {
        self.users = users
        self.user = user
        self.selectedCurrency = selectedCurrency
        self.currency = currency
    }
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(Color.primary)
                .shadow(radius: 10)
            VStack {
                HStack {
                    Image(CardTypeImage.getImageByName(name: user.type))
                        .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 12))
                    Text("\(user.cardNumber)")
                        .font(.title2)
                        .foregroundColor(Color.onPrimary)
                }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
                Spacer(minLength: 16)
                HStack {
                    HStack {
                        Image("user_icon").padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 8))
                        Text(user.cardholderName)
                            .font(.subheadline)
                            .foregroundColor(Color.onSurface)
                    }
                    Spacer()
                    VStack {
                        Text("VALID THRU")
                            .font(.system(size: 6))
                            .foregroundColor(Color.onSurface)
                        Text(user.valid)
                            .font(.subheadline)
                            .foregroundColor(Color.onSurface)
                    }
                }
                Spacer(minLength: 16)
                HStack {
                    let amountWithSign = CurrencySign().getCurrencySign(charCode: selectedCurrency.charCode) + CurrencyChange().changeCurrencyAndRound(usdAmount: user.balance, usdChangeRate: currency.valute.uSD.value,targetChangeRate: selectedCurrency.value)
                    Text("\(amountWithSign)")
                        .font(.title)
                    Spacer()
                    VStack {
                        Text("Your balance")
                            .font(.system(size: 7))
                            .foregroundColor(Color.onSurface)
                        let balanceWithSignUsd = CurrencySign().getCurrencySign(charCode:"USD") + CurrencyChange().toMoneyString(data: user.balance)
                        Text(balanceWithSignUsd)
                            .font(.subheadline)
                    }
                }
            }.padding(EdgeInsets(top: 18, leading: 12, bottom: 18, trailing: 12))
        }
        .padding()
        .fixedSize(horizontal: false, vertical: true)
        
    }
    
}

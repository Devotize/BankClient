//
//  ContentView.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 29.04.2023.
//

import SwiftUI
import shared

struct MainScreen: View {
    
   @ObservedObject private var viewModel = MainViewModel()
    
    
    var body: some View {
        
        @State var usersData  = viewModel.usersData
        @State var selectedCurrency = viewModel.selectedCurrency
        @State var selectedUser = viewModel.selectedUser
                
        VStack(alignment: .leading) {
            
            if (usersData != nil && selectedCurrency != nil && selectedUser != nil) {
                UserInfoCard(users: usersData!, selectedCurrency: selectedCurrency!, user: selectedUser!)
            }
        }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
    }
}

struct UserInfoCard : View {
    let users: Users
    let selectedCurrency: CurrencyItem
    let user: User
    
    init(users: Users, selectedCurrency: CurrencyItem, user: User) {
        self.users = users
        self.user = user
        self.selectedCurrency = selectedCurrency
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
                    
                }
            }.padding(EdgeInsets(top: 18, leading: 12, bottom: 18, trailing: 12))
        }
        .padding()
        .fixedSize(horizontal: false, vertical: true)
        
    }
    
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}

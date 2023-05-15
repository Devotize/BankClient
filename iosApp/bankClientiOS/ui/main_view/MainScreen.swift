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
                
        VStack(alignment: .leading) {
            
            if let data = usersData {
                if let user = data.users.first {
                    UserInfoCard(user: user)
                }
                
            }
        }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
    }
}

struct UserInfoCard : View {
    let user: User
    
    init(user: User) {
        self.user = user
    }
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(Color.primary)
                .shadow(radius: 10)
            VStack {
                HStack {
                    Image(CardTypeImage.getImageByName(name: user.type))
                    
                    Text("\(user.cardNumber)")
                        .font(.title2)
                        .foregroundColor(Color.onPrimary)
                }
            }
        }.fixedSize()
    }
    
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}

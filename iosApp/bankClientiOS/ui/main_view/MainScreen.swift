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
                
        VStack {
            
            if let data = usersData {
                if let user = data.users.first {
                    UserInfoCard(user: user)
                }
                
            }
        }
    }
}

struct UserInfoCard : View {
    let user: User
    
    init(user: User) {
        self.user = user
    }
    
    var body: some View {
        VStack {
            
        }
    }
    
}

struct MainScreen_Previews: PreviewProvider {
    static var previews: some View {
        MainScreen()
    }
}

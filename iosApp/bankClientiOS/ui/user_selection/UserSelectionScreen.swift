//
//  UserSelectionScreen.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 22.05.2023.
//

import SwiftUI
import shared

struct UserSelectionScreen : View {
    
    private let viewModel = MainViewModel.getInstance()
    
    var body: some View {
        @State var selectedUser = viewModel.selectedUser
        @State var users = viewModel.usersData
        if let safeUsers = users {
            VStack {
                ForEach(safeUsers.users, id: \.self) { user in
                    UserSelectionCard(user: user, isSelected: user == selectedUser) { userToSelect in
                        viewModel.changeSelectedUser(user: userToSelect)
                    }
                    Divider()
                        .frame(idealHeight: 2)
                        .background(Color.primaryVariant)
                }
            }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .topLeading)
        }
        Text("User Selection")
    }
    
}

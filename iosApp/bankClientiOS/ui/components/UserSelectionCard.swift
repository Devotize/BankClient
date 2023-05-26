//
//  UserSelectionCard.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 25.05.2023.
//

import Foundation
import SwiftUI
import shared

struct UserSelectionCard : View {
    
    let user: User
    let onClickAction: (User) -> Void
    let isSelected: Bool
    
    @Environment(\.dismiss) private var dismiss
    
    init(user: User, isSelected: Bool, onClickAction: @escaping (User) -> Void) {
        self.user = user
        self.isSelected = isSelected
        self.onClickAction = onClickAction
    }
    
    var body: some View {
        ZStack {
            HStack {
                HStack() {
                    Image(CardTypeImage.getImageByName(name: user.type))
                        .padding(EdgeInsets(top: 6, leading: 8, bottom: 6, trailing: 2))
                    
                    Text(user.cardNumber)
                        .font(.body)
                        .bold()
                        .foregroundColor(Color.onPrimary)
                        .padding(EdgeInsets(top: 0, leading: 2, bottom: 0, trailing: 0))
                }
                .onTapGesture {
                    onClickAction(user)
                    dismiss()
                }.fixedSize(horizontal: true, vertical: true)
                Spacer()
                if (isSelected) {
                    HStack {
                        Circle()
                        .size(.init(width: 6, height: 6))
                        .foregroundColor(Color.accentColor)
                        .padding(EdgeInsets(top: 0, leading: 0, bottom: 0, trailing: 12))
                    }.fixedSize(horizontal: true, vertical: true)
                }
            }.frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, alignment: .topLeading)
        }

    }
    
}

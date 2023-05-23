//
//  bankClientiOSApp.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 29.04.2023.
//

import SwiftUI

@main
struct bankClientiOSApp: App {
    
    let bankUserRepository = repositoriesProvider.bankUserRepository
    
    var body: some Scene {
        WindowGroup {
            NavigationStack {
                MainScreen()
                    .navigationTitle("Home")
                    .navigationBarTitleDisplayMode(.large)
            }
        }
    }
}

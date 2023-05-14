//
//  MainViewModel.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 09.05.2023.
//

import Foundation
import shared

class MainViewModel: ObservableObject {
    
    private let userRepository = repositoriesProvider.bankUserRepository
    
    @Published var usersData: Users? = nil
        
    func fetchUsers(callback: @escaping (Users?) -> Void) {
        userRepository.fetchUsers { users, error in
            callback(users)
        }
    }
    
    init() {
        fetchUsers { users in
            DispatchQueue.main.async {
                self.usersData = users
            }
        }
    }
    
}

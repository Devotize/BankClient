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
    private let currencyRepository = repositoriesProvider.currencyRepository
    
    private init() {
        fetchUsers { users in
            DispatchQueue.main.async {
                self.usersData = users
                self.selectedUser = users?.users.first
            }
        }
        fetchCurrency { currency in
            DispatchQueue.main.async {
                self.currency = currency
                self.selectedCurrency = currency?.valute.gBP
            }
        }
    }

    private static var instance = { MainViewModel() }()
    
    static func getInstance() -> MainViewModel {
        return instance
    }
    
    @Published var usersData: Users? = nil
    
    @Published var selectedCurrency: CurrencyItem? = nil
    
    @Published var currency: Currency? = nil
    
    @Published var selectedUser: User? = nil
        
    func fetchUsers(callback: @escaping (Users?) -> Void) {
        userRepository.fetchUsers { users, error in
            callback(users)
        }
    }
    
    func fetchCurrency(callback: @escaping (Currency?) -> Void) {
        currencyRepository.getCurrency { currency, error in
            callback(currency)
        }
    }
    
    func changeSelectedCurrency(currency: CurrencyItem) {
        selectedCurrency = currency
    }
    
    func changeSelectedUser(user: User) {
        self.selectedUser = user
    }
    
}

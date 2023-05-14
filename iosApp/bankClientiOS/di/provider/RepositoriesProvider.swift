//
//  RepositoriesProvider.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 09.05.2023.
//

import Foundation
import shared

class RepositoriesProvider {
    
    private let userDtoMapper = UsersDtoMapper()
    private let currencyDtoMapper = CurrencyDtoMapper()
    
    let bankUserRepository: BankUsersRepository
    let currencyRepository: CurrencyRepository
    
    private init () {
        bankUserRepository = BankUsersRepositoryImpl(usersDtoMapper: userDtoMapper)
        currencyRepository = CurrencyRepositoryImpl(currencyDtoMapper: currencyDtoMapper)
    }
    
    static func initialize() -> RepositoriesProvider {
        return RepositoriesProvider()
    }
    
}


let repositoriesProvider = RepositoriesProvider.initialize()

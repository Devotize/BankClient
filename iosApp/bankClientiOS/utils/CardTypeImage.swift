//
//  CardTypeImage.swift
//  bankClientiOS
//
//  Created by Denis Sychev on 14.05.2023.
//

import Foundation

struct CardTypeImage {
    
    static func getImageByName(name: String) -> String {
        switch name {
        case "mastercard":
            return "mastercard_icon"
        case "visa":
            return "visa_icon"
        case "unionpay":
            return "unionpay_icon"
        default:
            return "mastercard_icon"
        }
    }
    
}

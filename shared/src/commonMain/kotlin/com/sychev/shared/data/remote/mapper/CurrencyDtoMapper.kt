package com.sychev.shared.data.remote.mapper

import com.sychev.shared.data.remote.model.currency.CurrencyDto
import com.sychev.shared.data.remote.model.currency.CurrencyItemDto
import com.sychev.shared.domain.mapper.DomainMapper
import com.sychev.shared.domain.model.currency.Currency
import com.sychev.shared.domain.model.currency.CurrencyItem
import com.sychev.shared.domain.model.currency.Valute

class CurrencyDtoMapper: DomainMapper<Currency, CurrencyDto> {
    override fun toDomainModel(model: CurrencyDto): Currency {
        return Currency(
            Valute(
                eUR = currencyItemDtoToDomainModel(model.valuteDto.eUR),
                uSD = currencyItemDtoToDomainModel(model.valuteDto.uSD),
                gBP = currencyItemDtoToDomainModel(model.valuteDto.gBP)
            )
        )
    }
    private fun currencyItemDtoToDomainModel(currencyItemDto: CurrencyItemDto): CurrencyItem {
        return CurrencyItem(
            charCode = currencyItemDto.charCode,
            id = currencyItemDto.iD,
            name = currencyItemDto.name,
            nominal = currencyItemDto.nominal,
            numCode = currencyItemDto.numCode,
            previous = currencyItemDto.previous,
            value = currencyItemDto.value
        )
    }
 }
package com.sychev.shared.domain.mapper

interface DomainMapper <DomainModel, T> {
    fun toDomainModel(model: T): DomainModel
}
package com.sychev.bankclient.domain.mapper

interface DomainMapper <DomainModel, T> {
    fun toDomainModel(model: T): DomainModel
}
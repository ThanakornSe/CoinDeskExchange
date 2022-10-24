package com.example.coindeskexchange.di

import com.example.coindeskexchange.repository.CoinsRepository
import com.example.coindeskexchange.repository.CoinsRepositoryImpl
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { MainFragmentViewModel(get()) }
    single<CoinsRepository> { CoinsRepositoryImpl(get(),get()) }
}
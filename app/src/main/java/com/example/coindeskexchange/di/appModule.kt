package com.example.coindeskexchange.di

import androidx.lifecycle.ViewModel
import com.example.coindeskexchange.repository.CoinsRepository
import com.example.coindeskexchange.repository.CoinsRepositoryImpl
import com.example.coindeskexchange.viewModel.MainFragmentViewModel
import org.koin.dsl.module

val appModule = module {

    single { MainFragmentViewModel(get()) }
    single<CoinsRepository> { CoinsRepositoryImpl(get()) }
}
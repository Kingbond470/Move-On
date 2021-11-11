package dev.kingbond.moveon.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.kingbond.moveon.Repository.WareHouseRepo

class WareHouseViewmodelFactory(val wareHouseRepo: WareHouseRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WareHouseViewmodel(wareHouseRepo) as T
    }


}
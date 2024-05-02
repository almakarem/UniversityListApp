package com.example.universitylistapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.universitylistapp.data.local.UniversityDatabase
import com.example.universitylistapp.data.local.UniversityEntity
import com.example.universitylistapp.data.mappers.toUniversity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    pager: Pager<Int, UniversityEntity>
): ViewModel() {

    val universityPaginFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map { it.toUniversity() }
        }.cachedIn(viewModelScope)




}





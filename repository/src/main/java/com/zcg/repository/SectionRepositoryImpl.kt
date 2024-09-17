package com.zcg.repository

import com.zcg.core.SectionService
import com.zcg.model.Section
import com.zcg.repository.mapper.toLocal
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val sectionService: SectionService
) : SectionRepository {
    override suspend fun getSections(): Result<List<Section>> {
        return withContext(defaultDispatcher) {
            try {
                Result.Success(sectionService.getSections().map {
                    it.toLocal()
                })
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}

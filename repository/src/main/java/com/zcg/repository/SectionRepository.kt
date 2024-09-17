package com.zcg.repository

import com.zcg.model.Section

interface SectionRepository {
    suspend fun getSections() : Result<List<Section>>
}

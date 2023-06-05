package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetLimitAdminsCollectionsUseCase(
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(limit: Int): BaseResponse<List<CollectionDto>> {
        val collections = collectionDao.getLimitAdminCollections(
            limit = limit.makeLimitCollectionLessThen20()
        )

        if (collections.isEmpty()) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)
        }

        return BaseResponse.SuccessResponse(
            data = collections,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}

private fun Int.makeLimitCollectionLessThen20() = this.coerceIn(5..20)
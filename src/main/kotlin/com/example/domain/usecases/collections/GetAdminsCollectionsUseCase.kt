package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAdminsCollectionsUseCase(
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(): BaseResponse<List<CollectionDto>> {
        val collections = collectionDao.getAdminCollections()

        if (collections.isEmpty()) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)
        }

        return BaseResponse.SuccessResponse(
            data = collections,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}
package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUsersCollectionsUseCase(
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(): BaseResponse<List<CollectionWithUserDto>> {
        val collections = collectionDao.getUsersCollections()

        if (collections.isEmpty()) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)
        }

        return BaseResponse.SuccessResponse(
            data = collections,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}
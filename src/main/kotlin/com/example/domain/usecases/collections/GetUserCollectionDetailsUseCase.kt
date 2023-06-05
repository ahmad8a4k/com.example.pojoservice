package com.example.domain.usecases.collections

import com.example.data.dto.collections.CollectionDto
import com.example.data.dto.collections.CollectionWithUserDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetUserCollectionDetailsUseCase (
    private val collectionDao: CollectionDao,
) {
    suspend operator fun invoke(collectionId: Int): BaseResponse<CollectionWithUserDto> {

        val collection = collectionDao.getUserCollectionDetails(collectionId = collectionId)

        if (collection.collection_id == 0) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchData.message)
        }

        return BaseResponse.SuccessResponse(
            data = collection,
            message = ResponseMessages.SuccessFetchList.message
        )
    }
}
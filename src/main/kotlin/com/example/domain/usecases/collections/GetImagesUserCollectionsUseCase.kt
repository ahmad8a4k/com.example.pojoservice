package com.example.domain.usecases.collections

import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.dto.LiteImageDetailsWithLiteUserInformationDto
import com.example.data.source.dao.CollectionDao
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetImagesUserCollectionsUseCase(
    private val collectionDao: CollectionDao,
)  {

    suspend operator fun invoke(collectionId: Int): BaseResponse<List<LiteImageDetailsWithLiteUserInformationDto>> {

        if (collectionId == 0 || collectionId < 0)
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)

        val collections = collectionDao.getImagesFromUsersCollectionsByCollectionId(collectionId)

        if (collections.isEmpty()) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.EmptyFetchList.message)
        }

        return BaseResponse.SuccessResponse(
            data = collections,
            message = ResponseMessages.SuccessFetchList.message
        )
    }

}
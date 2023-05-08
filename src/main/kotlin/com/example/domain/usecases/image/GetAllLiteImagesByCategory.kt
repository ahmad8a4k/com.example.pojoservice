package com.example.domain.usecases.image

import com.example.data.dto.LiteImageDetailsWithLikesCountDto
import com.example.data.source.dao.ImageDao
import com.example.data.tables.NaturalTable
import com.example.domain.usecases.util.makePageNumberDefaultIfItZero
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllLiteImagesByCategory(
    private val imageDao: ImageDao
) {
    suspend operator fun invoke(
        pageSize: Int,
        page: Int,
        categoryId:Int,
        categoryName:String
    ) : BaseResponse<List<LiteImageDetailsWithLikesCountDto>>{

        val totalPages = imageDao.getTotalPagesTable(NaturalTable, pageSize)

        val pageNumberInRange = page.pageNumberToMakeItInRange(totalPages = totalPages)

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        val images = imageDao.getAllLiteImageByCategories(
            pageSize = pageSize.makePageSizeInRange(),
            page = pageNumberInRange.makePageNumberDefaultIfItZero(),
            categoryName = categoryName,
            categoryId = categoryId
        )

        if(images.isEmpty()){
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchNaturalLites.message)
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = images
        )

    }
}
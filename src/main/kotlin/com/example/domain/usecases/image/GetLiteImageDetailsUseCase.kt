package com.example.domain.usecases.image

import com.example.data.dto.LiteImageDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.data.tables.ImageDetailsTable
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.makePageNumberDefaultIfItZero
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetLiteImageDetailsUseCase(
    private val imageDao: ImageDao,
) {

    suspend operator fun invoke(pageSize: Int = 10, pageNumber: Int = 1): BaseResponse<List<LiteImageDetailsDto>> {

        val totalPageNumber = imageDao.getTotalPagesTable(ImageDetailsTable, pageSize)

        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(
            totalPages = totalPageNumber
        )

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPageNumber)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        val images = imageDao.getPagingLiteImageDetails(
            pageSize.makePageSizeInRange(), pageNumberInRange.makePageNumberDefaultIfItZero()
        )

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = images
        )
    }
}
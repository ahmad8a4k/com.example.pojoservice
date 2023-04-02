package com.example.domain.usecases.image

import com.example.data.dto.imageDetails.ImageDetailsFullDto
import com.example.data.source.dao.ImageDao
import com.example.data.tables.ImageDetailsTable
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class ImagesByPageSizeUseCase(
    private val imageDao: ImageDao,
) {

    suspend operator fun invoke(pageSize: Int = 5, pageNumber: Int = 1): BaseResponse<List<ImageDetailsFullDto>> {

        val inRangePageNumber = makePageNumberInRageIfItMoreThenIt(pageSize = pageSize, pageNumber = pageNumber)

        if (!checkIfPageExist(pageSize, inRangePageNumber)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        val images = imageDao.imagesByPageSizeAndPageNumber(
            pageSize.makePageSizeInRange(), inRangePageNumber.makePageNumberDefaultIfItZero())

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = images
        )
    }

    private suspend fun checkIfPageExist(pageSize: Int, pageNumber: Int): Boolean {
        return if (pageNumber > imageDao.getTotalPagesTable(ImageDetailsTable, pageSize)) false
        else imageDao.getTotalPagesTable(ImageDetailsTable, pageSize) >= pageNumber
    }

    /*
    make page size in range from 10 to 20
     */
    private fun Int.makePageSizeInRange(): Int {
        return this.coerceIn(0..20)
    }

    private fun Int.makePageNumberDefaultIfItZero(): Int {
        return if (this == 0) 1 else this
    }

    private suspend fun makePageNumberInRageIfItMoreThenIt(pageSize: Int, pageNumber: Int): Int {
        val totalPageNumber = imageDao.getTotalPagesTable(ImageDetailsTable, pageSize)
        return if (pageNumber > totalPageNumber) totalPageNumber
        else pageNumber
    }

}
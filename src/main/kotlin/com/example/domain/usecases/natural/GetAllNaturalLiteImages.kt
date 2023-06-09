package com.example.domain.usecases.natural

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.data.source.dao.NaturalImagesDao
import com.example.data.tables.NaturalTable
import com.example.domain.usecases.util.makePageNumberDefaultIfItZero
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetAllNaturalLiteImages(
    private val naturalImagesDao: NaturalImagesDao,
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(pageSize: Int, pageNumber: Int): BaseResponse<List<LiteNaturalDetailsDto>> {

        val totalPages = imageDao.getTotalPagesTable(NaturalTable.id, pageSize)
        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(totalPages = totalPages)

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        val naturalImages = naturalImagesDao.getAllNaturalLiteImages(
            pageSize.makePageSizeInRange(), pageNumberInRange.makePageNumberDefaultIfItZero()
        )

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = naturalImages
        )
    }
}
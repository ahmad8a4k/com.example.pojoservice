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
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetNaturalLiteImagesByColorUseCase(
    private val naturalImagesDao: NaturalImagesDao,
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        pageSize: Int = 40,
        pageNumber: Int = 1,
        colorId: Int = 1,
        colorName: String = "red",
    ): BaseResponse<List<LiteNaturalDetailsDto>> {
        return coroutineScope {

            val totalPagesAsync = async { imageDao.getTotalPagesTable(NaturalTable, pageSize) }
            val totalPages = totalPagesAsync.await()

            val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(totalPages = totalPages)

            if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
                return@coroutineScope BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
            }

            val naturalImagesAsync = async {
                naturalImagesDao.getLitesNaturalsByNaturalColorPagingAnd(
                    pageSize = pageSize.makePageSizeInRange(),
                    page = pageNumberInRange.makePageNumberDefaultIfItZero(),
                    colorName = colorName,
                    colorId = colorId
                )
            }

            val naturalImages = naturalImagesAsync.await()

            if (naturalImages.isEmpty()) {
                return@coroutineScope BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchNaturalLites.message)
            }

            BaseResponse.SuccessResponse(
                message = ResponseMessages.SuccessFetchImageDetails.message,
                data = naturalImages
            )
        }
    }
}
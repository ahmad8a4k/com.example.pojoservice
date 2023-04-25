package com.example.domain.usecases.natural

import com.example.data.dto.imageDetails.LiteNaturalDetailsDto
import com.example.data.source.dao.ImageDao
import com.example.data.source.dao.NaturalImagesDao
import com.example.data.tables.NaturalTable
import com.example.domain.usecases.util.pageNumberToCheckIfPageExist
import com.example.domain.usecases.util.makePageNumberDefaultIfItZero
import com.example.domain.usecases.util.pageNumberToMakeItInRange
import com.example.domain.usecases.util.makePageSizeInRange
import com.example.utils.BaseResponse
import com.example.utils.ResponseMessages

class GetNaturalLiteImagesByCategoryUseCase(
    private val naturalImagesDao: NaturalImagesDao,
    private val imageDao: ImageDao,
) {
    suspend operator fun invoke(
        pageSize: Int = 40,
        pageNumber: Int = 1,
        categoryId: Int = 1,
        categoryName: String = "trees",
    ): BaseResponse<List<LiteNaturalDetailsDto>> {

        val totalPages = imageDao.getTotalPagesTable(NaturalTable, pageSize)

        val pageNumberInRange = pageNumber.pageNumberToMakeItInRange(totalPages = totalPages)

        if (!pageNumberInRange.pageNumberToCheckIfPageExist(totalPages = totalPages)) {
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchImageDetails.message)
        }

        val naturalImages = naturalImagesDao.getLitesNaturalsByNaturalCategoryPagingAnd(
            pageSize = pageSize.makePageSizeInRange(),
            page = pageNumberInRange.makePageNumberDefaultIfItZero(),
            categoryName = categoryName,
            categoryId = categoryId
        )

        if(naturalImages[0].natural_id == 0){
            return BaseResponse.ErrorLiseResponse(message = ResponseMessages.FailFetchNaturalLites.message)
        }

        return BaseResponse.SuccessResponse(
            message = ResponseMessages.SuccessFetchImageDetails.message,
            data = naturalImages
        )
    }
}
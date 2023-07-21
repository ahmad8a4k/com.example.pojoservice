package com.example.db

import com.example.data.source.dao.*
import com.example.domain.usecases.category.GetAllCategoriesUseCase
import com.example.domain.usecases.category.GetAllLiteCategoriesUserCase
import com.example.domain.usecases.category.GetSevenCategoriesUseCases
import com.example.domain.usecases.category.UpdateAllBlurHashCategory
import com.example.domain.usecases.collections.*
import com.example.domain.usecases.image.*
import com.example.domain.usecases.natural.*
import com.example.domain.usecases.user.*
import com.example.utils.Constants
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.postgresql.PostgreSqlDialect

val mainModule = module {

    single {
        Database.connect(
            url = Constants.DATABASE_URL,
            driver = Constants.DATABASE_DRIVER,
            user = System.getenv("dbname") ?: "pojo_user_database",
            password = System.getenv("dbpassword") ?: "kguITyf41pkYBKeKo7ZIRfdTV2vOKKka",
            dialect = PostgreSqlDialect()
        )
//        Database.connect(
//            url = DATABASE_URL_ROOT,
//            driver = DATABASE_DRIVER,
//            user = DATABASE_USER_ROOT,
//            password = DATABASE_PASSWORD_ROOT,
//            dialect = MySqlDialect(),
//        )
    }

    /**
    Dao
     */
    single<UserDao> {
        UserDaoImpl(get())
    }

    single<ImageDao> {
        ImageDaoImpl(get())
    }

    single<NaturalImagesDao> {
        NaturalImagesDaoImpl(get())
    }

    single<CollectionDao> {
        CollectionDaoImpl(get())
    }

    /**
    UseCases
     */
    /*User*/
    single {
        UpdateUserPasswordUseCase(get())
    }

    single {
        AuthUserUserCase()
    }

    single {
        DeleteUserUseCase(get())
    }

    single {
        UserSignInByUserNameUseCase(get())
    }

    single {
        SignUpUseCase(get())
    }

    single {
        GetUserDetailsByIDUserCase(get())
    }

    single {
        UserSignInByUserEmailUserCase(get())
    }

    /**
     * Others
     */

    single {
        GetLiteImagesOrderByDateUseCase(get())
    }

    /* Categories */
    single {
        GetAllCategoriesUseCase(get())
    }

    single {
        GetAllNaturalCategoriesUseCase(get())
    }

    single {
        GetSevenCategoriesUseCases(get())
    }

    single {
        GetTopRatedLiteImagesThreeWeeksAgoUseCase(get())
    }

    single {
        GetListOfTopRatedLiteImages(get())
    }

    /*Categories*/

    single {
        GetNaturalImagesByPagingUseCase(get(), get())
    }

    single {
        GetNaturalLiteImagesByCategoryUseCase(get(), get())
    }

    single {
        GetNaturalLiteImagesByColorUseCase(get(), get())
    }

    single {
        GetAllNaturalLiteImages(get(), get())
    }

    single {
        GetAllLiteCategoriesUserCase(get())
    }

    single {
        GetAllLiteImagesByCategory(get())
    }

    single {
        UpdateBlurHashForLiteImagesByCategoryId(get())
    }

    single {
        UpdateAllBlurHashCategory(get())
    }

    single {
        GetImagesDetailsBasedOnCategoryOrColorUseCase(get())
    }

    /**
    Collections
     */

    single {
        GetUsersCollectionsUseCase(get())
    }

    single {
        GetAdminsCollectionsUseCase(get())
    }

    single {
        GetImagesUserCollectionsUseCase(get())
    }

    single {
        GetImagesAdminsCollectionsUseCase(get())
    }

    single {
        GetLimitAdminsCollectionsUseCase(get())
    }

    single {
        GetUserCollectionDetailsUseCase(get())
    }

    single {
        GetAdminCollectionDetailsUseCase(get())
    }

    /**
     * Images
     */

    single {
        UpdateUserLikedImage(get())
    }

    single {
        AddUserLikeImageUseCase(get())
    }

    single {
        RemoveUserLikeImageUseCase(get())
    }

    single {
        UpdateWatchImageCountUseCase(get())
    }
    single {
        GetSearchImageByTextUseCase(get())
    }
}
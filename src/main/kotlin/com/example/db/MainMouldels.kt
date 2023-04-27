package com.example.db

import com.example.data.source.dao.*
import com.example.domain.usecases.image.*
import com.example.domain.usecases.natural.GetAllNaturalCategoriesUseCase
import com.example.domain.usecases.natural.GetNaturalImagesByPagingUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByCategoryUseCase
import com.example.domain.usecases.natural.GetNaturalLiteImagesByColorUseCase
import com.example.domain.usecases.user.*
import com.example.utils.Constants
import com.example.utils.Constants.DATABASE_DRIVER
import com.example.utils.Constants.DATABASE_PASSWORD_ROOT
import com.example.utils.Constants.DATABASE_URL_ROOT
import com.example.utils.Constants.DATABASE_USER_ROOT
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.mysql.MySqlDialect

val mainModule = module {

    single {
        Database.connect(
            url = Constants.DATABASE_URL,
            driver = Constants.DATABASE_DRIVER,
            user = System.getenv("dbname") ?: "pojoservicedb",
            password = System.getenv("dbpassword") ?: "ahmadbbatal3d2d3l5y",
            dialect = MySqlDialect(),
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
        SignInUseCase(get())
    }

    single {
        SignUpUseCase(get())
    }

    single {
        GetUserDetailsByIDUserCase(get())
    }

    single {
        GetAllColorsUseCase(get())
    }

    /* Categories */
    single {
        GetAllCategoriesUseCase(get())
    }

    single {
        GetLiteImageDetailsUseCase(get())
    }

    single {
        GetFifteenImagesDetailsUseCase(get())
    }

    single {
        GetAllNaturalCategoriesUseCase(get())
    }

    single {
        GetSevenCategoriesUseCases(get())
    }

    single {
        GetTenTopRatedImagesBasedOnTopWeekOrLastWeek(get())
    }

    single {
        GetListOfTopRatedLiteImages(get())
    }

    /*Categories*/
    single {
        ImagesByPageSizeUseCase(get())
    }

    single {
        GetNaturalImagesByPagingUseCase(get(), get())
    }

    single {
        GetNaturalLiteImagesByCategoryUseCase(get(), get())
    }

    single {
        GetNaturalLiteImagesByColorUseCase(get(), get())
    }
}
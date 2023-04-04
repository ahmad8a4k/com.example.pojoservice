package com.example.db.modules

import com.example.data.repositories.userRepository.UserRepository
import com.example.data.repositories.userRepository.UserRepositoryImpl
import com.example.data.source.dao.*
import com.example.domain.usecases.image.*
import com.example.domain.usecases.user.*
import com.example.utils.Constants.DATABASE_DRIVER
import com.example.utils.Constants.DATABASE_PASSWORD
import com.example.utils.Constants.DATABASE_URL
import com.example.utils.Constants.DATABASE_USER
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.ktorm.database.Database
import org.ktorm.support.mysql.MySqlDialect

val mainModule = module {

    single {
//        Database.connect(
//            url = Constants.DATABASE_URL,
//            driver = Constants.DATABASE_DRIVER,
//            user = System.getenv("dbname") ?: "pojoservicedb",
//            password = System.getenv("dbpassword") ?: "ahmadbbatal3d2d3l5y",
//            dialect = MySqlDialect(),
//        )
        Database.connect(
            url = DATABASE_URL,
            driver = DATABASE_DRIVER,
            user = DATABASE_USER,
            password = DATABASE_PASSWORD,
            dialect = MySqlDialect(),
        )
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
    Repositories
     */
    single<UserRepository> {
        UserRepositoryImpl(get())
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
        GetAllCategoriesUseCase(get())
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

    /*Categories*/
    single {
        ImagesByPageSizeUseCase(get())
    }

    single {
        GetNaturalImagesByPagingUseCase(get(), get())
    }
}
package com.example.db.modules

import com.example.data.repositories.homeRepository.ImageRepository
import com.example.data.repositories.homeRepository.ImageRepositoryImpl
import com.example.data.repositories.userRepository.UserRepository
import com.example.data.repositories.userRepository.UserRepositoryImpl
import com.example.data.source.dao.ImageDao
import com.example.data.source.dao.ImageDaoImpl
import com.example.data.source.dao.UserDao
import com.example.data.source.dao.UserDaoImpl
import com.example.domain.usecases.image.GetAllCategoriesUseCase
import com.example.domain.usecases.image.GetFifteenImagesDetailsUseCase
import com.example.domain.usecases.image.GetSevenCategoriesUseCases
import com.example.domain.usecases.image.ImagesByPageSizeUseCase
import com.example.domain.usecases.user.*
import com.example.utils.Constants.DATABASE_DRIVER
import com.example.utils.Constants.DATABASE_PASSWORD
import com.example.utils.Constants.DATABASE_URL
import com.example.utils.Constants.DATABASE_USER
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

    /**
    Repositories
     */
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    single<ImageRepository> {
        ImageRepositoryImpl(get())
    }

    /**
    UseCases
     */
    /*User*/
    single {
        SignUpUseCase(get())
    }

    single {
        SignInUseCase(get())
    }

    single {
        DeleteUserUseCase(get())
    }

    single {
        UpdateUserPasswordUseCase(get())
    }

    single {
        AuthUserUserCase()
    }

    /*Images*/
    single {
        ImagesByPageSizeUseCase(get())
    }

    single {
        GetFifteenImagesDetailsUseCase(get())
    }

    /*Categories*/
    single {
        GetAllCategoriesUseCase(get())
    }

    single {
        GetSevenCategoriesUseCases(get())
    }
}
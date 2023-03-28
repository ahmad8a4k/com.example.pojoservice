package com.example.db.modules

import com.example.data.repositories.userRepository.UserRepository
import com.example.data.repositories.userRepository.UserRepositoryImpl
import com.example.data.source.dao.UserDao
import com.example.data.source.dao.UserDaoImpl
import com.example.domain.usecases.DeleteUserUseCase
import com.example.domain.usecases.SignInUseCase
import com.example.domain.usecases.SignUpUseCase
import com.example.domain.usecases.UpdateUserPasswordUseCase
import com.example.utils.Constants
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
    }

    /**
     Dao
     */
    single<UserDao> {
        UserDaoImpl(get())
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

//    single<HashingService> { SHA256HashingServiceImpl() }
}
package com.example.utils

object Constants {
    const val DATABASE_URL = "jdbc:mysql://db4free.net:3306/pojoservicedb"
    const val DATABASE_DRIVER = "com.mysql.cj.jdbc.Driver"
    const val DATABASE_USER = "pojoservicedb"
    const val DATABASE_PASSWORD = "ahmadbbatal3d2d3l5y"

    const val SALTED_HASH_ALGORITHM = "SHA1PRNG"

    /**
     * Jwt
     */
    val SECRET = System.getenv("JWT_SECRET") ?: "JWT_SECRET"
    const val ISSUER = "http://db4free.net"
    const val AUDIENCE = "http://db4free.net/pojoservicedb"
    const val REALM = "Access to notes"
    const val REFRESH_TOKEN_EXPIRE_DATE = (365L * 1000L * 60L * 60L * 24L)
}
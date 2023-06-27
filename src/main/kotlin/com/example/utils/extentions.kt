package com.example.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.example.domain.SaltedHash
import com.example.utils.Constants.AUDIENCE
import com.example.utils.Constants.ISSUER
import com.example.utils.Constants.REFRESH_TOKEN_EXPIRE_DATE
import com.example.utils.Constants.SALTED_HASH_ALGORITHM
import com.example.utils.Constants.SECRET
import io.trbl.blurhash.BlurHash
import org.apache.commons.codec.binary.Hex
import org.apache.commons.codec.digest.DigestUtils
import java.net.URL
import java.security.SecureRandom
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.imageio.ImageIO


fun String.stringToLocalDateTime(): LocalDateTime {
    return LocalDateTime.parse(this, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
}

fun String.generateSaltedHash(): SaltedHash {
    // Get Random Salt Number
    val salt = SecureRandom.getInstance(SALTED_HASH_ALGORITHM).generateSeed(32)
    // Hex Encoder for Salt
    val saltAsHex = Hex.encodeHexString(salt)
    // Hash SaltAsHex With User Password
    // Now, We Have
    // Hash( Hex(salt) + value)
    val hash = DigestUtils.sha256Hex("$saltAsHex$this")
    return SaltedHash(hash = hash, salt = saltAsHex)
}

/**
 *  Integer Should Be UserId
 */
fun Int.generateToken(): String =
    JWT.create()
        .withAudience(AUDIENCE)
        .withIssuer(ISSUER)
        .withClaim("user_id", this)
        .withExpiresAt(Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_DATE))
        .sign(Algorithm.HMAC256(SECRET))

fun SaltedHash.verifyPassword(password: String): Boolean {
    return DigestUtils.sha256Hex(salt + password) == hash
}

fun Long.convertLongToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("yyyy/M/dd")
    return format.format(date)
}

fun String.encodeImageToBlurHashUsingURl(): String {
    return try {
        BlurHash.encode(ImageIO.read(URL(this))) ?: ""
    } catch (e: Exception) {
        ""
    }
}
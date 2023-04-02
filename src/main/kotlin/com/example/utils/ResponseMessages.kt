package com.example.utils

sealed class ResponseMessages(val message: String) {
    /**
     * User Response Messages
     */
    object SuccessAuthentication : ResponseMessages("SUCCESS AUTHENTICATION")
    object FailAuthentication : ResponseMessages("FAIL AUTHENTICATION")
    object IncorrectPasswordORUserName : ResponseMessages("INCORRECT USERNAME OR PASSWORD")
    object EmptyField : ResponseMessages("EMPTY FILED")
    object IncorrectPassword : ResponseMessages("INCORRECT PASSWORD")
    object SuccessSignIn : ResponseMessages("SUCCESS SIGN IN")
    object IncorrectRequest : ResponseMessages("INCORRECT REQUEST")
    object UserAlreadyExist : ResponseMessages("USER ALREADY EXIST!")
    object SuccessSignup : ResponseMessages("SUCCESS SIGNUP")
    object SuccessDeleteUser : ResponseMessages("SUCCESS DELETE USER")
    object SuccessUpdatePassword : ResponseMessages("SUCCESS UPDATE USER")
    object NotFoundUser : ResponseMessages("Not Found User With This UserName")

    /**
     * Image Response Messages
     */
    object SuccessFetchImageDetails : ResponseMessages("Success_Fetch_Image_Details")
    object FailFetchImageDetails : ResponseMessages("Fail_Fetch_Image_Details")
    object FailFetchImageDetailsPageCause : ResponseMessages("PageNumber_More_Then_Exist")
    object EmptyImages : ResponseMessages("Empty_Image")
    /**
     * Category Response Messages
     */
    object EmptyFetchCategoryImages : ResponseMessages("Empty_Fetch_Category_Image")

}
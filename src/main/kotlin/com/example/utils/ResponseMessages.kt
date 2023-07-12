package com.example.utils

sealed class ResponseMessages(val message: String) {

    /**
     * User Response Messages
     */
    object SuccessAuthentication : ResponseMessages("SUCCESS AUTHENTICATION")
    object FailAuthentication : ResponseMessages("FAIL AUTHENTICATION")
    object EmptyField : ResponseMessages("EMPTY FILED")
    object SuccessSignup : ResponseMessages("SUCCESS SIGNUP")
    object IncorrectPassword : ResponseMessages("INCORRECT USER PASSWORD!")
    object SuccessSignIn : ResponseMessages("SUCCESS SIGN IN")

    //    object IncorrectRequest : ResponseMessages("INCORRECT REQUEST")
    object UserNameAlreadyExist : ResponseMessages("USER ALREADY EXIST!")
    object NotFoundUserEmail : ResponseMessages("NOT FOUND USER EMAIL!")
    object NotFoundUser : ResponseMessages("NOT FOUND USERNAME!")
    object UserEmailIsAlreadyExist : ResponseMessages("USER EMAIL ALREADY EXIST!")
    object SuccessDeleteUser : ResponseMessages("SUCCESS DELETE USER")
    object SuccessUpdatePassword : ResponseMessages("SUCCESS UPDATE USER")
    object NotFoundUserID : ResponseMessages("Not Found User With This UserID")
    object SuccessFetchUserByID : ResponseMessages("SUCCESS FITCH USER")
    object EmptyParameter : ResponseMessages("EMPTY PARAMETER")
    object UnKnowFail : ResponseMessages("IN KNOW FAIL")
    //object Success : ResponseMessages("SUCCESS")
    object SuccessAddLikeImage : ResponseMessages("SUCCESS_ADD_LIKE_IMAGE")
    object SuccessRemoveLikeImage : ResponseMessages("SUCCESS_REMOVE_LIKE_IMAGE")
    object SuccessUpdateWatchImageCount : ResponseMessages("SUCCESS_UPDATE_WATCH_COUNT")

    /**
     * Image Response Messages
     */
    object SuccessFetchImageDetails : ResponseMessages("Success_Fetch_Image_Details")
    object FailFetchImageDetails : ResponseMessages("Fail_Fetch_Image_Details")

    //   object FailFetchImageDetailsPageCause : ResponseMessages("PageNumber_More_Then_Exist")
    //  object EmptyImages : ResponseMessages("Empty_Image")
    object EmptyFetchImages : ResponseMessages("Empty_Fetch_Image")
    object NotFoundImageByImageID : ResponseMessages("Not_Found_Image_By_ImageID :")

    //   object SuccessFetchImages : ResponseMessages("Success_Fetch_Images")
    object SuccessFetchList : ResponseMessages("Success_Fetch_list")

    /**
     * Category Response Messages
     */
    object EmptyFetchCategoryImages : ResponseMessages("Empty_Fetch_Category_Image")
    object EmptyFetchColorsImages : ResponseMessages("Empty_Fetch_Colors_Image")

    /**
     *  Natural Response Messages
     */
    object FailFetchNaturalLites : ResponseMessages("Fail_Fetch_natural_lites")

    /**
     *  Others Response Messages
     */
    object EmptyFetchList : ResponseMessages("Empty_list")
    object EmptyFetchData : ResponseMessages("Empty_data")

}
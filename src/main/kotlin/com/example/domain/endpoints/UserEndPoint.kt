package com.example.domain.endpoints

sealed class UserEndPoint(val path:String){
    object SignUp: UserEndPoint(path = "/add_user")
    object SignIn: UserEndPoint(path = "/sign_in")
    object Authenticate: UserEndPoint(path = "/authenticate")
    object Secret: UserEndPoint(path = "/secret")
    object UpdatePassword: UserEndPoint(path = "/update_password")
    object DeleteUserByUsernameAndPassword: UserEndPoint(path = "/delete_user")
    object UserDetailsByUserID: UserEndPoint(path = "/user_details")
}
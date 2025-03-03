package com.example.taxidrivercontrol

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserData(var userMail:String?="",var userName:String?="",var userPassword:String?="",var role:String?="") {
}
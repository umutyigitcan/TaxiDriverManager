package com.example.taxidrivercontrol

import com.google.android.gms.common.util.Strings
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class UserData(var userMail:String?="",var userName:String?="",var userPassword:String?="",var role:String?="",var plate:String?="",var carModel:String?="",var carColor:String?="") {
}
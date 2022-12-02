package com.example.lostfound

import android.media.Image
import android.provider.ContactsContract.CommonDataKinds.Email

data class User (
    var name:String?=null,
    var phonenum: String?=null,
    var rollnum: String?=null,
    var UID : String?=null
//    var profilepic: Image?=null
) {}
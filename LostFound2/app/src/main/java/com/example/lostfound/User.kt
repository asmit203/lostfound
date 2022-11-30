package com.example.lostfound

import android.media.Image
import android.provider.ContactsContract.CommonDataKinds.Email

class User {
    var name:String?=null
    var email:String? = null
    var uid:String? = null
    var phonenum: String?=null
    var rollnum: String?=null
    var profilepic: Image?=null

    constructor(){}
    constructor(name: String?,email: String?,phonenum:String?,uid:String?,rollnum:String?,profpic:Image?)
    {
        this.name = name
        this.uid = uid
        this.email = email
        this.phonenum = phonenum
        this.rollnum = rollnum
        this.profilepic = profpic
    }
}
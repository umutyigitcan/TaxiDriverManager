package com.example.taxidrivercontrol

class SavedUserSQLiteDao {

    fun changeUser(vt:SavedUserSQLite,userName:String,mail:String,password:String){
        var db=vt.writableDatabase
        db.execSQL("UPDATE SavedUser SET userName='$userName',mail='$mail',password='$password'")
        db.close()
    }

    fun getUser(vt:SavedUserSQLite):ArrayList<SavedUserData>{
        var db=vt.writableDatabase
        var data=ArrayList<SavedUserData>()
        var cursor=db.rawQuery("SELECT * FROM SavedUser",null)
        while (cursor.moveToNext()){
            var getData=SavedUserData(cursor.getString(cursor.getColumnIndexOrThrow("userName")),
                cursor.getString(cursor.getColumnIndexOrThrow("mail")),
                cursor.getString(cursor.getColumnIndexOrThrow("password")))
            data.add(getData)
        }
        return data
    }

}
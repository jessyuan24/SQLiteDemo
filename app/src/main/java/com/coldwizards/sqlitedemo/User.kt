package com.coldwizards.sqlitedemo

/**
 * Created by jess on 19-2-18.
 */
//class  Name {
//    var id: Int = 0
//    var userName: String? = null
//    constructor(id: Int, userName: String) {
//        this.id = id
//        this.userName = userName
//    }
//    constructor(userName: String) {
//        this.userName = userName
//    }
//}

data class User(
    var id: Int,
    var userName: String
) {
    constructor(userName: String): this(0,userName)
}
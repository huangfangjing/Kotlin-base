package com.example.kotlindemo.model.natives

data class TaskListRequestBean (var userId: String?){
    var yearId: String? = null
    var termId: String? = null
    var classNo: String? = null
    var classId: String? = null
    var startPage = 0
    var pageSize = 0
    var periodType: String? = null
    var type: String? = null
    var subjectId: String? = null
}
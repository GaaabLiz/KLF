package io.github.gaaabliz.kliz.common.data

import com.google.gson.annotations.SerializedName

class ApiOperationInfo(
    @SerializedName("api_op_info_operation_id") val operationId : String? = null,
    @SerializedName("api_op_info_name") val name: String,
    @SerializedName("api_op_info_description") val description:String? = null,
    @SerializedName("api_op_info_user") val user: String,
    @SerializedName("api_op_info_time") val time:String,
    @SerializedName("api_op_info_date") val date:String
) {
    override fun toString(): String {
        return "ApiOperationInfo(operationId=$operationId, name='$name', description=$description, user='$user', time='$time', date='$date')"
    }
}
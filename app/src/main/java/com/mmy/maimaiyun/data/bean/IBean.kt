package com.mmy.maimaiyun.data.bean

import com.google.gson.annotations.SerializedName


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 15:47
 * @描述          TODO
 */
data class IBean constructor(
        @SerializedName("status") var s: Int = -1,
        @SerializedName("info") var data: String
)
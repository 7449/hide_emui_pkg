package hide.emui.pkg

import com.google.gson.annotations.SerializedName

data class PkgEntity(
        @SerializedName("app_name") val appName: String,
        @SerializedName("package_name") val packageName: String,
        @SerializedName("disable") var disable: Boolean,
) {

    fun shell() = if (disable) "adb shell pm disable-user $packageName" else "adb shell pm enable $packageName"

    fun isEmui() = packageName.contains("huawei") //emui
            || packageName.contains("ohos") //emui
            || packageName.contains("sohu") //搜狐视频插件
            || packageName.contains("iflytek") //讯飞语音引擎
            || packageName.contains("androidhwext") //emui主题

    override fun equals(other: Any?): Boolean {
        return (other as? PkgEntity)?.packageName == packageName
    }

    override fun hashCode(): Int {
        var result = appName.hashCode()
        result = 31 * result + packageName.hashCode()
        result = 31 * result + disable.hashCode()
        return result
    }

}

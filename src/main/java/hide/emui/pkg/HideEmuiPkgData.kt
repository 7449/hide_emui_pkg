package hide.emui.pkg

import com.google.gson.annotations.SerializedName

data class HideEmuiPkgData(
        @SerializedName("app_name") val appName: String,
        @SerializedName("package_name") val packageName: String,
        @SerializedName("description") val description: String,
        @SerializedName("disable") val disable: Boolean,
) {

    fun shell() = if (disable) "adb shell pm disable-user $packageName" else "adb shell pm enable $packageName"

}

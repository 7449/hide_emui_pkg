package hide.emui.pkg

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

private val gson = Gson()

fun Any.toJson(): String {
    return gson.toJson(this)
}

fun String.fromJson(): MutableList<PkgEntity> {
    val data = mutableListOf<PkgEntity>()
    runCatching { data.addAll(gson.fromJson(this, object : TypeToken<MutableList<PkgEntity>>() {}.type)) }
    return data
}

fun MutableList<PkgEntity>.exShell() {
    runCatching {
        forEach {
            with(Runtime.getRuntime().exec(it.shell())) {
                println(errorStream.bufferedReader().readText())
                println(inputStream.bufferedReader().readText())
            }
        }
    }.map {
        Thread.sleep(3000)
        Runtime.getRuntime().exec("adb shell reboot")
    }
}

fun File.backup() {
    File(parentFile, "$name.back").writeText(readText())
}

fun File.writePkgToJson() {
    writeShellText("adb shell pm list packages")
}

fun File.writeDisablePkgToJson() {
    writeShellText("adb shell pm list packages -d")
}

fun File.writeEnablePkgToJson() {
    writeShellText("adb shell pm list packages -e")
}

fun File.writeSystemPkgToJson() {
    writeShellText("adb shell pm list packages -s")
}

fun File.write3PkgToJson() {
    writeShellText("adb shell pm list packages -3")
}

fun File.writeShellText(shell: String) {
    Runtime.getRuntime().exec(shell).inputStream.bufferedReader().useLines { it ->
        writeText(it.toMutableList().apply {
            println("$shell 输出pkg${size}个")
        }.map { value ->
            val pkg = value.replace("package:", "").trim()
            PkgEntity("", pkg, false)
        }.toJson())
    }
}

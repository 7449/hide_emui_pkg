package hide.emui.pkg

import java.io.File

/**
 * 目前只判断系统apk,执行shell -s
 */
object Main {

    private val pkgFile = File(".", "/pkg/pkg.json").apply {
        if (!parentFile.exists()) {
            parentFile.mkdirs()
        }
        if (!exists()) {
            createNewFile()
        }
    }
    private val pkgEmuiFile = File(".", "/pkg/pkg_emui.json").apply {
        if (!parentFile.exists()) {
            parentFile.mkdirs()
        }
        if (!exists()) {
            createNewFile()
        }
    }
    private val pkgAndroidFile = File(".", "/pkg/pkg_android.json").apply {
        if (!parentFile.exists()) {
            parentFile.mkdirs()
        }
        if (!exists()) {
            createNewFile()
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        writeClassifyPkg()
        exShell()
    }

    private fun exShell() {
        val merge = arrayListOf<PkgEntity>()
        merge.addAll(pkgEmuiFile.readText().fromJson())
        merge.addAll(pkgAndroidFile.readText().fromJson())
        merge.exShell()
    }

    private fun writeClassifyPkg() {
        pkgFile.writeSystemPkgToJson()

        val pkgJson = pkgFile.readText().fromJson()
        val emuiJson = pkgEmuiFile.readText().fromJson()
        val androidJson = pkgAndroidFile.readText().fromJson()

        val emuiPkg = pkgJson.filter { it.isEmui() }.filter { !emuiJson.contains(it) }
        val androidPkg = pkgJson.filter { !it.isEmui() }.filter { !androidJson.contains(it) }

        println("new emuiPkg:$emuiPkg")
        println("new androidPkg:$androidPkg")

        if (emuiPkg.isNotEmpty()) {
            pkgEmuiFile.writeText(emuiJson.apply {
                addAll(emuiPkg)
                sortBy { it.packageName }
            }.toJson())
        }
        if (androidPkg.isNotEmpty()) {
            pkgAndroidFile.writeText(androidJson.apply {
                addAll(androidPkg)
                sortBy { it.packageName }
            }.toJson())
        }
        println("write classify success")
    }

}

package hide.emui.pkg

import java.io.File

object Main {

    private val emuiEntity = arrayListOf<HideEmuiPkgData>()
    private val androidEntity = arrayListOf<HideEmuiPkgData>()

    private val pkgFile = File(".", "/pkg/pkg.json")
    private val pkgEmuiFile = File(".", "/pkg/pkg_emui.json")
    private val pkgAndroidFile = File(".", "/pkg/pkg_android.json")

    private val pkgOldFile = File(".", "/pkg/pkg_old.json")

    @JvmStatic
    fun main(args: Array<String>) {
    }

}

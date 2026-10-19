package dev.wahid.bare

import org.junit.Assert.assertEquals
import org.junit.Test

class AppsTest {

    private fun app(packageName: String, label: String) =
        AppEntry(packageName, "$packageName.Main", label, icon = null)

    @Test
    fun `sorts by label ignoring case`() {
        val apps = listOf(app("c", "YouTube"), app("a", "kodi"), app("b", "Netflix"))

        assertEquals(listOf("kodi", "Netflix", "YouTube"), apps.forGrid("self").map { it.label })
    }

    @Test
    fun `leaves out Bare itself`() {
        val apps = listOf(app("dev.wahid.bare", "Bare"), app("a", "Netflix"))

        assertEquals(listOf("Netflix"), apps.forGrid("dev.wahid.bare").map { it.label })
    }
}

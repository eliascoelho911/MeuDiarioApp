package com.github.eliascoelho911.meudiario.root

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.common.TestCustomApplication
import com.github.eliascoelho911.meudiario.common.clickOn
import com.github.eliascoelho911.meudiario.diary.DiaryFragment
import kotlin.reflect.KClass
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.instanceOf
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestCustomApplication::class)
class RootActivityTest {

    @Test
    fun givenActivityOpened_whenClickOnDiaryTab_shouldOpenDiaryFragment() {
        launchActivity().onActivity {
            clickOn(R.id.menu_diary_screen)
        }.recreate().onActivity {
            it.isShowingFragment(DiaryFragment::class)
        }
    }

    private fun AppCompatActivity.isShowingFragment(kClass: KClass<out Fragment>) {
        supportFragmentManager.findFragmentById(R.id.container)?.let {
            assertThat(it, instanceOf(kClass.java))
        }
    }

    private fun launchActivity(action: () -> Unit = {}): ActivityScenario<RootActivity> {
        action()

        val context = ApplicationProvider.getApplicationContext<Context>()
        val intent = Intent(context, RootActivity::class.java)

        return launch(intent)
    }
}
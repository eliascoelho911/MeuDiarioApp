package com.github.eliascoelho911.meudiario.root

import android.content.Context
import android.content.Intent
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.eliascoelho911.meudiario.R
import com.github.eliascoelho911.meudiario.common.TestCustomApplication
import com.github.eliascoelho911.meudiario.common.clickOn
import com.github.eliascoelho911.meudiario.common.loadAllModulesOnKoin
import com.github.eliascoelho911.meudiario.diary.DiaryFragment
import kotlin.reflect.KClass
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestCustomApplication::class)
class RootActivityTest {

    @Before
    fun setup() {
        loadAllModulesOnKoin()
    }

    @Test
    fun givenActivityOpened_whenClickOnDiaryTab_shouldOpenDiaryFragment() {
        launchActivity().onActivity {
            clickOn(R.id.menu_diary_screen)
        }.recreate().onActivity {
            it.isShowingFragment(DiaryFragment::class)
        }
    }

    @Test
    fun givenActivityOpened_whenClickOnDiaryTab_shouldChangeTitle() {
        launchActivity().onActivity {
            clickOn(R.id.menu_diary_screen)
        }.recreate().onActivity {
            onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
                .check(matches(withText("Diário")))
        }
    }

    @Test
    fun givenActivityOpened_whenOnCreate_shouldOpenDiaryFragment() {
        launchActivity().moveToState(CREATED).onActivity {
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
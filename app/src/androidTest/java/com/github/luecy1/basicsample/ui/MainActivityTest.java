package com.github.luecy1.basicsample.ui;

import android.arch.core.executor.testing.CountingTaskExecutorRule;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.github.luecy1.basicsample.AppExecutors;
import com.github.luecy1.basicsample.R;
import com.github.luecy1.basicsample.db.AppDatabase;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;

import static org.hamcrest.core.IsNot.not;


/**
 * Created by you on 2018/03/14.
 */
// TODO
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(
            MainActivity.class
    );

    @Rule
    public CountingTaskExecutorRule mCountingTaskExecutorRule = new CountingTaskExecutorRule();

    public MainActivityTest() {
        InstrumentationRegistry.getTargetContext().deleteDatabase(AppDatabase.DATABASE_NAME);
    }

    @Before
    public void waitForDbCreation() throws Throwable {
        final CountDownLatch latch = new CountDownLatch(1);
        final LiveData<Boolean> databaseCreated = AppDatabase.getInstance(
                InstrumentationRegistry.getTargetContext(), new AppExecutors()).getDatabaseCreated();
        mActivityRule.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                databaseCreated.observeForever(new Observer<Boolean>() {
                    @Override
                    public void onChanged(@Nullable Boolean aBoolean) {
                        if (Boolean.TRUE.equals(aBoolean)) {
                            databaseCreated.removeObserver(this);
                            latch.countDown();
                        }
                    }
                });
            }
        });

        MatcherAssert.assertThat("database should've initialized",
                latch.await(1, TimeUnit.MINUTES), CoreMatchers.is(true));

    }

//    @Test
//    public void clickOnFirstItem_opensComments() throws Throwable {
//
//        drain();
//
//        onView(ViewMatchers.withContentDescription(R.string.cd_products_list))
//                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
//
//        drain();
//        onView(withContentDescription(R.string.cd_comments_list))
//                .check(matches(isDisplayed()));
//
//        drain();
//        onView(withContentDescription(R.string.cd_product_name))
//                .check(matches(not(withText(""))));
//
//    }

    private void drain() throws TimeoutException,InterruptedException {
        mCountingTaskExecutorRule.drainTasks(1, TimeUnit.MINUTES);
    }
}

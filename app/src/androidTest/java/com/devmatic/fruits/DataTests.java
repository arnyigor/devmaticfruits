package com.devmatic.fruits;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.devmatic.fruits.data.models.Fruit;
import com.devmatic.fruits.data.source.main.FruitRepository;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataTests {
	private final Context context = InstrumentationRegistry.getTargetContext();
	private final Object syncObject = new Object();
	private final FruitRepository fruitRepository = new FruitRepository();
	private static class ImmediateSchedulersRule implements TestRule {
		@Override
		public Statement apply(final Statement base, Description description) {
			return new Statement() {
				@Override
				public void evaluate() throws Throwable {
					RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
					RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
					RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
					try {
						base.evaluate();
					} finally {
						RxJavaPlugins.reset();
					}
				}
			};
		}
	}

	@Rule
	public final ImmediateSchedulersRule schedulers = new ImmediateSchedulersRule();

	@Test
	public void aa_getTask() throws Exception {
		TestObserver<Fruit> observer = new TestObserver<>();
//		MoveitAPI.test503(context)
//				.doOnError(throwable -> {
//					System.out.println("complete:" + throwable);
//				})
//				.doOnNext(apiBaseResponse -> {
//					System.out.println("apiBaseResponse:" + apiBaseResponse);
//				})
//				.subscribeOn(Schedulers.computation())
//				.subscribe(observer);
//		System.out.println("complete:" + observer.errors());
//		System.out.println("complete:" + observer.values());
	}
}

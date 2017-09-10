package net.hrsoft.transparent_factory_manager.utils;

import android.support.annotation.NonNull;

import java.util.Vector;

import io.reactivex.Observable;
import io.reactivex.subjects.*;
import io.reactivex.subscribers.SerializedSubscriber;

/**
 * @author abtion.
 * @since 17/9/10 12:39.
 * email caiheng@hrsoft.net
 */

public class RxBus {
    private static RxBus defaultRxBus;
    private Subject<Object> bus;

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }

    public static RxBus getInstance() {
        if (null == defaultRxBus) {
            synchronized (RxBus.class) {
                if (null == defaultRxBus) {
                    defaultRxBus = new RxBus();
                }
            }
        }
        return defaultRxBus;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public boolean hasObservable() {
        return bus.hasObservers();
    }

    /**
     * 转换为特定类型的Obserbale
     */
    public <T> Observable<T> toObservable(Class<T> type) {
        return bus.ofType(type);
    }
}



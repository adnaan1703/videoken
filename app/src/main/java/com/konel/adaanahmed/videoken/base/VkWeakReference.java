package com.konel.adaanahmed.videoken.base;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author : Adnaan 'Zohran' Ahmed <adnaanahmed@urbanclap.com>
 * @version : 1.0.0
 * @since : 04 Jun 2017 3:20 AM
 */


public class VkWeakReference<T> extends WeakReference<T> {

    public VkWeakReference(T referent) {
        super(referent);
    }

    @SuppressWarnings("unused")
    public VkWeakReference(T referent, ReferenceQueue<? super T> q) {
        super(referent, q);
    }

    public T getNonNull() {
        T t = get();
        if (t != null) {
            return t;
        } else {
            throw new NullPointerException("weak reference contains null object");
        }
    }
}

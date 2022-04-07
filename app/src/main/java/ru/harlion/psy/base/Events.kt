package ru.harlion.psy.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class Event<out T>(private var content: T?) {

    fun consume(): T? {
        return content.also {
            content = null
        }
    }
}


inline fun <T> LiveData<Event<T>>.onEvent(owner: LifecycleOwner, crossinline onEventUnhandledContent: (T) -> Unit) {
    this.observe(owner, Observer { event ->
        event?.consume()?.let {
            onEventUnhandledContent(it)
        }
    })
}

typealias LiveEvent<T> = LiveData<Event<T>>

typealias MutableLiveEvent<T> = MutableLiveData<Event<T>>

fun <T>MutableLiveEvent<T>.setContent(content: T?) {
    value = Event(content)
}

fun <T>MutableLiveEvent<T>.postContent(content : T?) {
    postValue(Event(content))
}

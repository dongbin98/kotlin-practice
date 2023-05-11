package com.example.kotlin_practice

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread
import kotlin.reflect.typeOf

fun main() {
    observableOperator()
}

fun observableOperator() {
    // Merge
    val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS)
    val observable2 = Observable.interval(250, TimeUnit.MILLISECONDS)
    Observable.merge(observable1, observable2).subscribe { println(it) }
    Thread.sleep(700)

    // Zip
    /*val intObservable = Observable.create<Int> { emitter ->
        thread {
            for (i in 1 .. 10) {
                emitter.onNext(i)
                try {
                    Thread.sleep(500)
                } catch (_: Exception) {}
            }
        }
    }

    val strObservable = Observable.create { emitter ->
        thread {
            for(i in 1 .. 10) {
                emitter.onNext((i+64).toChar())
                try {
                    Thread.sleep((i * 100).toLong())
                } catch (_: Exception) {}
            }
        }
    }

    Observable.zip(intObservable, strObservable) { num, str -> num.toString() + str}
        .subscribe {
            println(it)
        }*/

    // CombineLatest
    /*val intObservable = Observable.create<Int> { emitter ->
        thread {
            for (i in 1 .. 10) {
                emitter.onNext(i)
                try {
                    Thread.sleep(500)
                } catch (_: Exception) {}
            }
        }
    }

    val strObservable = Observable.create { emitter ->
        thread {
            for(i in 1 .. 10) {
                emitter.onNext((i+64).toChar())
                try {
                    Thread.sleep((i * 100).toLong())
                } catch (_: Exception) {}
            }
        }
    }

    Observable.combineLatest(intObservable, strObservable) { num, str -> num.toString() + str }
        .subscribe {
        println(it)
    }*/

    // All
    /*val observable = Observable.just(1, 2, 3, 4)
    observable.all { item -> item > 0 }.subscribe(System.out::println)*/

    // Take
/*    val observable = Observable.just(1, 2, 3, 4, 5, 6)
    observable.take(3).subscribe { println(it) }*/

    // Skip
/*    val observable = Observable.just(1, 2, 3, 4, 5, 6)
    observable.skip(2).subscribe { println(it) }*/

    // Sample
    /*val observable = Observable.interval(100, TimeUnit.MILLISECONDS)
    Thread.sleep(1000)
    observable.sample(300, TimeUnit.MILLISECONDS).subscribe { println(it) }
    Thread.sleep(1000)*/

    // Filter
/*    val observable = Observable.just(1, 2, 3, 4, 5, 6)
    observable.filter { item -> item % 2 == 0 }.subscribe { println(it) }*/

    // ElementAt
/*    val observable = Observable.just(1, 2, 3,4, 5)
    observable.elementAt(3).subscribe { println(it) }*/

    // District
/*    val observable = Observable.just(1, 2, 1, 4, 2)
    observable.distinct().subscribe {
        println(it)
    }*/
/*    // Debounce
    val observable = Observable.create<String> { emitter ->
        emitter.onNext("1")
        Thread.sleep(100)
        emitter.onNext("2")
        emitter.onNext("3")
        emitter.onNext("4")
        Thread.sleep(100)
        emitter.onNext("5")
    }.debounce(10, TimeUnit.MILLISECONDS).subscribe {
        println(it)
    }*/

/*    // flatMap
    val observable = Observable.just("a", "b", "c")
    observable.flatMap { item -> Observable.just(item + "1", item + "2") }.subscribe {
        println(it)
    }*/

    // Map
/*    val observable = Observable.just("a", "b", "c")
    observable.map { it + it }.subscribe {
        println(it)
    }*/

    // Scan
/*    val observable = Observable.just("a", "b", "c")
    observable.scan { prev, item -> prev + item }.subscribe {
        println(it)
    }*/
}

fun variousObservableExample() {
//    val single = Single.create { emitter ->
//        emitter.onSuccess("혼자 왔어?")
//    }.subscribe { data -> println(data) }
//
//    val maybe = Maybe.create { emitter ->
//        emitter.onSuccess("어 싱글이야")
//    }.subscribe { data -> println(data) }
//
//    val completable = Completable.create { emitter ->
//        emitter.onComplete()
//    }.subscribe { println("조용해라") }
}

fun observableExample() {
/*    val observable = Observable.create<String> { emitter ->
        emitter.onNext("Hello")
        emitter.onNext("Bye")
//        emitter.onError(Throwable())
        emitter.onComplete()
    }.subscribe { data -> println(data) }*/

/*    val observable = Observable.just("item", 1, true, "four")
        .subscribe { data -> print("$data ") }*/

    val array = arrayOf("Morning", "taxi", "is", "good")
    // fromArray() 사용 시 *을 통해 데이터를 읽어올 수 있도록 지정해야 함
    // 대체로 array.toObservable()를 하면 내부에서 자체적으로 fromArray(*this)를 호출
    Observable.fromArray(*array).subscribe { data -> print("$data ") }

    println()

    val iterable = arrayListOf("Evening", "taxi", "is", "expensive")
    Observable.fromIterable(iterable).subscribe { data -> print("$data ") }

    println()

    val future = Executors.newSingleThreadExecutor().submit<String> { "I think bus is perfect" }
    Observable.fromFuture(future).subscribe { data -> println(data) }

    val publisher = Publisher { subscriber ->
        subscriber.onNext("Metro, too")
        subscriber.onComplete()
    }
    Observable.fromPublisher(publisher).subscribe { data -> println(data) }

    val callable = Callable { "Five from operator done\n" }
    Observable.fromCallable(callable).subscribe { data -> println(data) }

/*    val disposable = observable
//        .subscribeOn(AndroidSchedulers.mainThread())
        .doOnNext { item -> println(item) }
        .doOnError { throwable -> println(throwable.message)}
        .doOnComplete { println("complete") }
        .subscribe { println("subscribe") }*/
}

fun imperativeExample() {
    println("call imperative()")
    val items = mutableListOf<Int>()
    items.add(1)
    items.add(2)
    items.add(3)
    items.add(4)
    for (item in items) {
        if (item % 2 == 0) println(item)
    }
    items.add(5)
    items.add(6)
}

fun reactiveExample() {
    println("call reactive()")
//    val items = PublishSubject.create<Int>()
    val items = ReplaySubject.create<Int>()
    items.onNext(1)
    items.onNext(2)
    items.onNext(3)
    items.onNext(4)
    items.filter { it % 2 == 0 }.subscribe { println(it) }
    items.onNext(5)
    items.onNext(6)
}
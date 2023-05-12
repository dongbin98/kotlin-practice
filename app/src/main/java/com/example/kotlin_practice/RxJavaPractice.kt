package com.example.kotlin_practice

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.subjects.ReplaySubject
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.internal.operators.observable.ObservableTimer
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.AsyncSubject
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.PublishSubject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.reactivestreams.Publisher
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

fun main() {
    dispatcherExample()
}

fun dispatcherExample() {
    // Base Observable
    // Observable은 기본적으로 subscribe하는 thread에서 동작
/*    runBlocking {
        val observable = Observable.just(1, 2, 3).map {
            println("map : $it - ${Thread.currentThread().name}")
        }
        observable.subscribe { println("First : $it - ${Thread.currentThread().name}") }
        println("--------------------------")
        thread { observable.subscribe { println("Second : $it - ${Thread.currentThread().name}") } }
        delay(1000)
    }*/

    // Various Scheduler
/*     runBlocking {
        val observable = Observable.just(1)
        // Schedulers.io()
        observable.subscribeOn(Schedulers.io())
            .subscribe { println("io() - ${Thread.currentThread().name}") }
        // Schedulers.computation()
        observable.subscribeOn(Schedulers.computation())
            .subscribe { println("computation() - ${Thread.currentThread().name}") }
        // Schedulers.newThread()
        observable.subscribeOn(Schedulers.newThread())
            .subscribe { println("newThread() - ${Thread.currentThread().name}") }
        // Schedulers.single()
        observable.subscribeOn(Schedulers.single())
            .subscribe { println("single() - ${Thread.currentThread().name}") }
        // Schedulers.trampoline()
        observable.subscribeOn(Schedulers.trampoline())
            .subscribe { println("trampoline() - ${Thread.currentThread().name}") }
        // Schedulers.from()
        val executors = Executors.newFixedThreadPool(2)
        val schedulers = Schedulers.from(executors)
        observable.subscribeOn(schedulers)
            .subscribe { println("from - ${Thread.currentThread().name}") }
        delay(1000)
    }*/

    // io() vs computation() -> 둘 다 비동기 작업처리
/*    runBlocking {
        val observable = Observable.just(1)

        println("start Schedulers.io()")
        observable.subscribeOn(Schedulers.io()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.io() - ${Thread.currentThread().name}")
        }
        println("start Schedulers.computation()")
        observable.subscribeOn(Schedulers.computation()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.computation() - ${Thread.currentThread().name}")
        }
        println("done")
        delay(100)
    }*/

    // single() vs single()
/*    runBlocking {
        val observable = Observable.just(1, 2, 3)

        println("start Schedulers.single()-#1")
        observable.subscribeOn(Schedulers.single()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.single()-#1 - ${Thread.currentThread().name}")
        }
        println("start Schedulers.single()-#2")
        observable.subscribeOn(Schedulers.single()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.single()-#2 - ${Thread.currentThread().name}")
        }
        println("done")
        delay(1000)
    }*/

    // trampoline() vs trampoline()
    /*runBlocking {
        val observable = Observable.just(1, 2, 3)

        println("start Schedulers.trampoline()-#1")
        observable.subscribeOn(Schedulers.trampoline()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.trampoline()-#1 - ${Thread.currentThread().name}")
        }
        println("start Schedulers.trampoline()-#2")
        observable.subscribeOn(Schedulers.trampoline()).subscribe {
            runBlocking { delay(100) }
            println("$it-Schedulers.trampoline()-#2 - ${Thread.currentThread().name}")
        }
        println("done")
        delay(1000)
    }*/

    // trampoline() with interval operator
/*    runBlocking {
        println("start Schedulers.trampoline()")
        val observable = Observable.interval(1, TimeUnit.SECONDS)
        observable.subscribeOn(Schedulers.trampoline())
            .subscribe { println(it) }
        println("done")
    }*/

    // subscribeOn() vs observeOn()
/*    runBlocking {
        Observable.range(1, 5).map {
            println("map: $it - ${Thread.currentThread().name}")
        }.subscribeOn(Schedulers.io()).subscribe {
            println("subscribe: $it - ${Thread.currentThread().name}")
        }
        delay(100)
    }*/

    /*runBlocking {
        Observable.range(1, 3).observeOn(Schedulers.io()).map {
            println("map-#1: $it - ${Thread.currentThread().name}")
        }.observeOn(Schedulers.computation()).map {
            println("map-#2: $it - ${Thread.currentThread().name}")
        }.observeOn(Schedulers.single()).subscribe {
            println("subscribe: $it - ${Thread.currentThread().name}")
        }
        delay(100)
    }*/

    // subscribeOn() + observeOn()
    /*runBlocking {
        println("start")
        val observable = Observable.just(1)

        observable.subscribeOn(Schedulers.io()).map {
            println("processed : ${Thread.currentThread().name}")
        }.observeOn(Schedulers.single()).subscribe {
            println("subscribed : ${Thread.currentThread().name}")
        }
        delay(100)
        println("end")
    }*/

    /*runBlocking {
        println("start")
        val observable = Observable.just(1)

        observable.subscribeOn(Schedulers.io()).map {
            println("processed : ${Thread.currentThread().name}")
        }.observeOn(Schedulers.single()).subscribeOn(Schedulers.computation()).subscribe {
            println("subscribed : ${Thread.currentThread().name}")
        }
        delay(100)
        println("end")
    }*/

    /*runBlocking {
        println("start")
        val observable = Observable.just(1)

        observable.subscribeOn(Schedulers.io()).map {
            println("processed : ${Thread.currentThread().name}")
        }.subscribeOn(Schedulers.computation()).subscribe {
            println("subscribed : ${Thread.currentThread().name}")
        }
        delay(100)
        println("end")
    }*/

    runBlocking {
        println("start")
        val observable = Observable.just(1)

        observable.map { println("processed : ${Thread.currentThread().name}") }
            .observeOn(Schedulers.single())
            .subscribeOn(Schedulers.computation())
            .subscribe { println("subscribed : ${Thread.currentThread().name}") }
        delay(100)
        println("end")
    }
}

fun operatorExample() {
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

    // Buffer
/*    Observable.range(0, 8).buffer(4).subscribe {
        print("emit::")
        for (item in it) print("$item ")
        println()
    }*/
    // Timer

/*    val timerObservable = Observable.timer(3, TimeUnit.SECONDS)
    timerObservable.subscribe { println(it) }
    Thread.sleep(4000)*/

    // Range
/*    Observable.range(1, 5).subscribe { print("$it ") }*/
    // Interval
/*    val intervalObservable = Observable.interval(1, TimeUnit.SECONDS).subscribe { println(it) }
    Thread.sleep(3000)
    intervalObservable.dispose()*/
    // Empty, Never
/*    Observable.empty<Any>().doOnComplete { println("empty::onComplete()") }
        .doOnTerminate { println("empty::종료") }.subscribe()
    Observable.never<Any>().doOnComplete { println("never::onComplete()") }
        .doOnTerminate { println("never::종료") }.subscribe()*/
    // Defer
/*    val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA)
    val justObservable = Observable.just(System.currentTimeMillis())
    val deferObservable = Observable.defer { Observable.just(System.currentTimeMillis()) }

    println("현재 시각 ${format.format(System.currentTimeMillis())}")
    Thread.sleep(3000)
    println("현재 시각 ${format.format(System.currentTimeMillis())}")
    justObservable.subscribe { data -> println("just = ${format.format(data)}") }
    deferObservable.subscribe { data -> println("defer = ${format.format(data)}") }*/
}

fun compositeDisposable() {
    val observable = Observable.interval(1, TimeUnit.SECONDS)
    val disposable1 = observable.subscribe { data -> println(data) }
    val disposable2 = observable.subscribe { data -> println(data) }
    val compositeDisposable =
        CompositeDisposable().apply { addAll(disposable1, disposable2) }

    thread {
        Thread.sleep(4000)
        compositeDisposable.dispose()
    }
}

fun disposable() {
    val observable = Observable.interval(1, TimeUnit.SECONDS)
    val disposable = observable.subscribe { data -> println(data) }

    thread {
        Thread.sleep(4000)
        disposable.dispose()
    }
}

fun coldObservable() {
    val observable = Observable.interval(1, TimeUnit.SECONDS)
    observable.subscribe { data -> println("first subscribe : $data") }
    Thread.sleep(2000)
    observable.subscribe { data -> println("second subscribe : $data") }
    Thread.sleep(2000)
}

fun hotObservable() {
    // 1. ConnectableObservable
/*    val hotObservable1 = ConnectableObservable.interval(1, TimeUnit.SECONDS).publish()
    hotObservable1.connect()
    hotObservable1.subscribe { data -> println("first subscribe : $data") }
    Thread.sleep(2000)
    hotObservable1.subscribe { data -> println("second subscribe : $data") }
    Thread.sleep(2000)*/
    // 2. Subject
    // 2-1. AsyncSubject
/*    val asyncSubject = AsyncSubject.create<Long>()
    asyncSubject.subscribe { data -> println("first subscribe : $data") }
    asyncSubject.onNext(1)
    asyncSubject.onNext(2)
    asyncSubject.subscribe { data -> println("second subscribe : $data") }
    asyncSubject.onNext(3)
    asyncSubject.onComplete()*/

/*    val observable = Observable.just(1, 2, 3, 4)
    val observerAsyncSubject = AsyncSubject.create<Int>()
    observerAsyncSubject.subscribe { data -> println("subscribe : $data") }
    observable.subscribe(observerAsyncSubject)*/
    // 2-2. BehaviorSubject
/*    val behaviorSubject = BehaviorSubject.createDefault(777)
    behaviorSubject.subscribe { data -> println("first subscribe : $data") }
    behaviorSubject.onNext(1)
    behaviorSubject.onNext(2)
    behaviorSubject.subscribe { data -> println("second subscribe : $data") }
    behaviorSubject.onNext(3)
    behaviorSubject.onComplete()*/
    // 2-3. PublishSubject
/*    val publishSubject = PublishSubject.create<Int>()

    publishSubject.subscribe { data -> println("first subscribe : $data") }
    publishSubject.onNext(1)
    publishSubject.onNext(2)
    publishSubject.subscribe { data -> println("second subscribe : $data") }
    publishSubject.onNext(3)
    publishSubject.onComplete()*/
    // 2-4. ReplaySubject
    val replaySubject = ReplaySubject.create<Int>()
    replaySubject.subscribe { data -> println("first subscribe : $data") }
    replaySubject.onNext(1)
    replaySubject.onNext(2)
    replaySubject.subscribe { data -> println("second subscribe : $data") }
    replaySubject.onNext(3)
    replaySubject.onComplete()
}

fun variousObservableExample() {
    val single = Single.create { emitter ->
        emitter.onSuccess("혼자 왔어?")
    }.subscribe { data -> println(data) }

    val maybe = Maybe.create { emitter ->
        emitter.onSuccess("어 싱글이야")
    }.subscribe { data -> println(data) }

    val completable = Completable.create { emitter ->
        emitter.onComplete()
    }.subscribe { println("조용해라") }
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

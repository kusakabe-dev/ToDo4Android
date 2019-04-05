package com.syousa1982.todo4android.util.extention

import androidx.annotation.CheckResult
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.util.rx.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Result型に変換
 *
 * @return Flowable
 */
@CheckResult
fun <T> Flowable<T>.toResult(schedulerProvider: SchedulerProvider): Flowable<Result<T>> {
    return compose { item ->
        item
                .map { Result.success(it) }
                .onErrorReturn { Result.failure(it) }
                .startWith(Result.progress())
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
    }
}

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Single<T>.toResult(schedulerProvider: SchedulerProvider): Flowable<Result<T>> = toFlowable().toResult(schedulerProvider)

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Maybe<T>.toResult(schedulerProvider: SchedulerProvider): Flowable<Result<T>> = toFlowable().toResult(schedulerProvider)

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Completable.toResult(schedulerProvider: SchedulerProvider): Flowable<Result<T>> = toFlowable<T>().toResult(schedulerProvider)
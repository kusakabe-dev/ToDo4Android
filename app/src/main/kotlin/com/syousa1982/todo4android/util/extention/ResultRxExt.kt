package com.syousa1982.todo4android.util.extention

import androidx.annotation.CheckResult
import com.syousa1982.todo4android.domain.Result
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Result型に変換
 *
 * @return Flowable
 */
@CheckResult
fun <T> Flowable<T>.toResult(): Flowable<Result<T>> {
    return compose { item ->
        item
                .map { Result.success(it) }
                .onErrorReturn { Result.failure(it) }
                .startWith(Result.progress())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Single<T>.toResult(): Flowable<Result<T>> = toFlowable().toResult()

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Maybe<T>.toResult(): Flowable<Result<T>> = toFlowable().toResult()

/**
 * Result型に変換
 *
 * @return Observable
 */
@CheckResult
fun <T> Completable.toResult(): Flowable<Result<T>> = toFlowable<T>().toResult()
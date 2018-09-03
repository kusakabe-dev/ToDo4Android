package com.syousa1982.todo4android.manager

import com.syousa1982.todo4android.model.repository.ITaskRepository

/**
 * Repository管理 インタフェース
 * <p>
 * リポジトリは「データベース」「キャッシュ」「API」を隠蔽する
 * </p>
 */
interface IRepositoryManager {

    val task: ITaskRepository
}
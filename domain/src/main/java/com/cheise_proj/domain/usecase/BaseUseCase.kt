package com.cheise_proj.domain.usecase

import io.reactivex.rxjava3.core.Scheduler

abstract class BaseUseCase constructor(
    protected val backgroundScheduler: Scheduler,
    protected val foregroundScheduler: Scheduler
)
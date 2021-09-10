package com.mohamed.nbateamsandplayers.ui.viewmodels.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseAbstractViewModel: ViewModel(), CoroutineScope{

    val presenterJob = Job()

    override val coroutineContext: CoroutineContext
        get() = presenterJob + Dispatchers.Main

    val backgroundCoroutineScope: CoroutineScope
        get() = CoroutineScope(context = Dispatchers.IO + SupervisorJob(presenterJob))
}
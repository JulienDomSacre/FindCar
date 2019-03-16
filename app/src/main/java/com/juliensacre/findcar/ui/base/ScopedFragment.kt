package com.juliensacre.findcar.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * Create this class because if i use GlobalScope of coroutine in my fragment, he exists a possibility
 * where the fragment are destroy before my coroutine has finished and then crash my app.
 * With this class i add the coroutine execution in a job who as destroy when the fragment was destroy
 */
abstract class ScopedFragment :Fragment(), CoroutineScope{
    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = Job()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

}
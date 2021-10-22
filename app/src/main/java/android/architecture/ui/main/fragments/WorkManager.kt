package android.architecture.ui.main.fragments

import android.architecture.utils.CreateNotificationClass
import android.architecture.utils.showLog
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay
import javax.inject.Inject

class WorkManager @Inject constructor(
    val context: Context,
    val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {
    val createNotificationClass by lazy { CreateNotificationClass(context) }

    override suspend fun doWork(): Result {
        delay(2000)
        //val savedUri = context.getUriFromUrl()
        createNotificationClass.createNotification()
        showLog("Log")
        return Result.success()
    }

}
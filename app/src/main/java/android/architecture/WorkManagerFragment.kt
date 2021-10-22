package android.architecture

import android.architecture.ui.main.fragments.WorkManager
import android.architecture.utils.showToast
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.*
import kotlinx.android.synthetic.main.fragment_work_manager.*
import java.util.*


class WorkManagerFragment : Fragment() {
    val imageDownloadWorker by lazy { WorkManager.getInstance(activity!!.applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_work_manager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btShowNotification.setOnClickListener {
            setUI()
        }
    }

    fun setUI() {
        setWorkManager()

    }

    fun setWorkManager() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val worker = OneTimeWorkRequestBuilder<WorkManager>()
            .setConstraints(constraints)
            .build()
        imageDownloadWorker.enqueueUniqueWork(
            "oneTimeImageDownload",
            ExistingWorkPolicy.KEEP,
            worker
        )
        observeWork(worker.id)
    }

    private fun observeWork(id: UUID) {
        imageDownloadWorker.getWorkInfoByIdLiveData(id)
            .observe(this, { info ->
                if (info != null && info.state.isFinished) {
                    showToast("Task Finished")
                }
            })
    }
}
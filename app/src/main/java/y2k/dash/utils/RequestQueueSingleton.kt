package y2k.dash.utils

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class RequestQueueSingleton constructor(context: Context) {
    companion object {
        @Volatile
        private var INSTANCE: RequestQueueSingleton? = null

        fun getInstance(context: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: RequestQueueSingleton(context)
        }
    }

    private val _requestTag = "RequestQueueSingleton"
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)

    private var onFinishedListener: (() -> Unit)? = null
    private var lastSequence: Int = 0

    init {
        requestQueue.addRequestFinishedListener<Unit> { request ->
            if (request?.sequence == lastSequence) {
                onFinishedListener?.invoke()
            }
        }
    }

    fun <T> add(request: Request<T>) {
        request.tag = _requestTag
        requestQueue.add(request)
        lastSequence = request.sequence
    }

    fun cancelAll() {
        lastSequence = 0
        onFinishedListener?.invoke()
        requestQueue.cancelAll(_requestTag)
    }

    fun setOnFinishedListener(listener: () -> Unit) {
        onFinishedListener = listener
    }
}

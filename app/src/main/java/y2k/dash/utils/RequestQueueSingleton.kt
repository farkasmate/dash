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

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    fun <T> add(request: Request<T>) {
        requestQueue.add(request)
    }

    fun addRequestFinishedListener(listener: () -> Unit) {
        requestQueue.addRequestFinishedListener<Unit> { listener() }
    }
}

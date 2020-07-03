package mx.ernestovaldez.androidtest.interfaces

interface ResponseListener<T> {
    fun onSuccess(responseObject: T)
    fun onError(error: String)
}
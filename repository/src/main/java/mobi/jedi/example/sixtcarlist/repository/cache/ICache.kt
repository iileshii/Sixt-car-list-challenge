package mobi.jedi.example.sixtcarlist.repository.cache

interface ICache<T> {
    fun put(id: String, value: T)
    fun has(id: String): Boolean
    fun get(id: String): T?
    fun invalidate()
}
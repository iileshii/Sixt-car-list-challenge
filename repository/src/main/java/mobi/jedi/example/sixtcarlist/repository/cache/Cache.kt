package mobi.jedi.example.sixtcarlist.repository.cache

internal class Cache<T> : ICache<T> {

    private val map = hashMapOf<String, T>()

    override fun put(id: String, value: T) {
        value?.let { map[id] = it }
    }

    override fun has(id: String): Boolean {
        return map.contains(id)
    }

    override fun get(id: String): T? {
        return map[id]
    }

    override fun invalidate() {
        map.clear()
    }
}
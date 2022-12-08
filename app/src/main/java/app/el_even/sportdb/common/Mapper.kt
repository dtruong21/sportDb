package app.el_even.sportdb.common

abstract class Mapper<I, O> {

	abstract fun map(obj: I): O

	fun map(collection: Collection<I>): List<O> = ArrayList(collection.map { map(it) })
}
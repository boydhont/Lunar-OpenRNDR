package lunar.lists

import java.util.*


fun <T> combinedList(lists: ArrayList<ArrayList<T>>): ArrayList<T> {
    val combinedData = ArrayList<T>()
    for (list in lists) combinedData.addAll(list)
    return combinedData
}

fun <T> dispatchedLists(list: ArrayList<T>, pattern: ArrayList<Boolean>): ArrayList<ArrayList<T>>
{
    val dispatchedLists = ArrayList<ArrayList<T>>()
    val listA = ArrayList<T>()
    val listB = ArrayList<T>()

    while (pattern.size < list.size) pattern.addAll(pattern)

    for (i in list.indices) {
        if (pattern[i]) listA.add(list[i])
        else listB.add(list[i])
    }

    dispatchedLists.add(listA)
    dispatchedLists.add(listB)
    
    return dispatchedLists
}

fun <T> insertedItemsList(list: ArrayList<T>, items: ArrayList<T>, index: Int): ArrayList<T> {
    val mergedList = ArrayList<T>()
    val splittedLists: ArrayList<ArrayList<T>> = splitLists(list, index)

    for (t in splittedLists[0]) mergedList.add(t)
    for (t in items) mergedList.add(t)
    for (t in splittedLists[1]) mergedList.add(t)

    return mergedList
}

fun <T> nullPattern(list: ArrayList<T>): ArrayList<Boolean> {
    val isNull = ArrayList<Boolean>()

    for (o in list) {
        if (o == null) isNull.add(true)
        else isNull.add(false)
    }

    return isNull
}

fun <T> listItem(list: ArrayList<T>, index: Int): T {
    return list[index]
}

fun <T> listLength(list: ArrayList<T>): Int {
    return list.size
}

fun <T> listPartitions(list: ArrayList<T>, size: Int): ArrayList<ArrayList<T>> {
    val partitions = ArrayList<ArrayList<T>>()
    var partition = ArrayList<T>()
    var count = 0

    for (t in list) {
        partition.add(t)
        count++
        if (count == size) {
            partitions.add(partition)
            partition = ArrayList()
            count = 0
        }
    }
    if (partition.size > 0) partitions.add(partition)

    return partitions
}

fun <T> pickedAndChosenList(listA: ArrayList<T>, listB: ArrayList<T>, pattern: ArrayList<Boolean>): ArrayList<T> {
    val chosenList = ArrayList<T>()

    var listSize = listA.size
    if (listB.size > listA.size) listSize = listB.size
    while (pattern.size < listSize) pattern.addAll(pattern)

    for (i in 0 until listSize) {
        if (pattern[i]) {
            if (i < listA.size) {
                val t = listA[i]
                chosenList.add(t)
            } else {
                val t = listB[i]
                chosenList.add(t)
            }
        } else {
            if (i < listB.size) {
                val t = listB[i]
                chosenList.add(t)
            } else {
                val t = listA[i]
                chosenList.add(t)
            }
        }
    }

    return chosenList
}

fun <T> replacedItemsList(list: ArrayList<T>, replacements: ArrayList<T>, indexes: ArrayList<Int>): ArrayList<T> {
    var lists = ArrayList<ArrayList<T>>()
    lists.add(list)
    lists.add(ArrayList<T>())
    var replacedList = combinedList(lists)

    for (i in indexes.indices) {
        val index = indexes[i]
        val t = replacements[i]
        replacedList[index] = t
    }
    return replacedList
}

fun <T> replacedNullsList(list: ArrayList<T>, replacements: ArrayList<T>): ArrayList<T> {
    var lists = ArrayList<ArrayList<T>>()
    lists.add(list)
    lists.add(ArrayList<T>())
    var replacedList = combinedList(lists)
    var index = 0

    for (j in replacedList.indices) {
        val t: T = replacedList[j]
        if (t == null) {
            val r = replacements[index]
            replacedList[j] = r
            index++
        }
    }

    return replacedList
}

fun <T> reversedList(list: ArrayList<T>): ArrayList<T> {
    val reversedList = ArrayList<T>()
    for (i in list.indices) {
        val t = list[list.size - 1 - i]
        reversedList.add(t)
    }
    return reversedList
}

fun <T> shiftedList(list: ArrayList<T>, size: Int): ArrayList<T> {
    var size: Int = size
    var shiftedList: ArrayList<T>

    while (size > list.size) size -= list.size

    val splittedLists: ArrayList<ArrayList<T>> = splitLists(list, list.size - size)
    shiftedList = splittedLists[1]
    for (t in splittedLists[0]) shiftedList.add(t)

    return shiftedList
}

fun <T> splitLists(list: ArrayList<T>, index: Int): ArrayList<ArrayList<T>> {
    val splittedLists = ArrayList<ArrayList<T>>()
    val listA = ArrayList<T>()
    val listB = ArrayList<T>()

    for (i in list.indices) {
        val t = list[i]
        if (i < index) listA.add(t)
        else listB.add(t)
    }

    splittedLists.add(listA)
    splittedLists.add(listB)
    return splittedLists
}

fun <T> subList(list: ArrayList<T>, start: Int, end: Int): ArrayList<T> {
    val subList = ArrayList<T>()
    for (i in list.indices) {
        if (i in start until end) {
            val t = list[i]
            subList.add(t)
        }
    }
    return subList
}

fun <T> wovenList(listA: ArrayList<T>, listB: ArrayList<T>, pattern: ArrayList<Boolean>): ArrayList<T> {
    val wovenList = ArrayList<T>()
    val listSize = listA.size + listB.size

    while (pattern.size < listSize) pattern.addAll(pattern)

    var indexA = 0
    var indexB = 0

    for (i in 0 until listSize) {
        if (pattern[i]) {
            if (indexA < listA.size) {
                val t = listA[indexA]
                wovenList.add(t)
                indexA++
            } else {
                val t = listB[indexB]
                wovenList.add(t)
                indexB++
            }
        } else {
            if (indexB < listB.size) {
                val t = listB[indexB]
                wovenList.add(t)
                indexB++
            } else {
                val t = listA[indexA]
                wovenList.add(t)
                indexA++
            }
        }
    }

    return wovenList
}
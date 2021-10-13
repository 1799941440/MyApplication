package com.djkj.myapplication

import java.util.*
import kotlin.math.sqrt

/**
 * @author wz
 * @date 2021/9/6 16:06
 * @version 1.0
 * @description
 **/

fun sort(array: IntArray, start: Int = 0, end: Int = array.size - 1) {
    if (start < end) {
        var s = start
        var e = end
        val baseNum = array[s]
        while (s != e) {
            while (s < e && array[s] <= array[e]) e--
            array[s] = array[e]
            while (s < e && array[e] >= array[s]) s++
            array[e] = array[s]
        }
        array[s] = baseNum
        sort(array, start, s - 1)
        sort(array, s + 1, end)
    }
}

fun reverseNode(node: Node?): Node? {
    var node0: Node? = null
    var node1 = node
    var node2 = node?.next
    do {
        node1?.next = node0
        node0 = node1
        node1 = node2
        node2 = node2?.next
    } while (node2 != null)
    return node0
}

class Node(var next: Node? = null, var desc: String = "") {
    override fun toString(): String {
        return "Node($desc) -> ${next.toString()}"
    }
}

fun main() {
//    val array = intArrayOf(12, 32, 12, 10, 45, 125, 45 ,65 ,87 ,95)
//    sort(array)
//    array.forEach(::println)

//    val node = Node(Node(Node(Node(Node(Node(), "5"), "4"), "3"), "2"), "1")
//    println(reverseNode(node).toString())

//    println(getCount())

//    val node =
//        TreeNode(
//            TreeNode(
//                TreeNode(TreeNode(value = 55), TreeNode(value = 12), 35),
//                TreeNode(TreeNode(value = 85), TreeNode(value = 79), 46),
//            72),
//            TreeNode(
//                TreeNode(TreeNode(value = 1), TreeNode(value = 23), 42),
//                TreeNode(TreeNode(value = 4), TreeNode(value = 99), 502),
//            64),
//        89)
//    println(getNearestCommonAncestorInBinaryTree(node, TreeNode(value = 42), TreeNode(value = 23))?.value)

//    val stackByQueue = StackByQueue()
//    stackByQueue.offer(1)
//    stackByQueue.offer(2)
//    stackByQueue.offer(3)
//    println(stackByQueue.pop())
//    println(stackByQueue.pop())
//    stackByQueue.offer(3)
//    println(stackByQueue.pop())
//    println(stackByQueue.pop())
//
//    isMajorNum(1)
//    isMajorNum(2)
//    isMajorNum(3)
//    isMajorNum(4)
//    isMajorNum(5)
//    isMajorNum(6)
//    isMajorNum(7)
//    isMajorNum(81)
//    isMajorNum(9)
//    isMajorNum(11)
//    isMajorNum(12)
//    isMajorNum(13)
//    isMajorNum(14)
//    isMajorNum(15)
//    isMajorNum(16)
//    isMajorNum(31)
//    isMajorNum(21)
//    isMajorNum(13)
//    isMajorNum(41)
//    isMajorNum(53)

//    println(coinCharge(intArrayOf(2, 5, 6), 20))

}

fun coinCharge(coins: IntArray, amount: Int) : Int {
    if (coins.isEmpty() || amount == 0) return -1
    val temp = IntArray(amount + 1) { amount + 1 }
    for (i in 1..amount) {
        for (j in coins.indices) {
            if (i - coins[j] == 0) {
                temp[i] = 1
            } else if (i - coins[j] > 0) {
                temp[i] = Math.min(temp[i], temp[i - coins[j]] + 1)
            }
        }
    }
    return if (amount < temp[amount]) -1 else temp[amount]
}

fun isMajorNum(target: Int): Boolean {
    var i = 0
    var j = (sqrt(target.toDouble())).toInt()
    while (i <= j) {
        val temp = i * i + j * j
        if (temp == target) {
            println("target $target is    i = $i j = $j")
            return true
        } else if (temp > target) {
            j--
        } else {
            i++
        }
    }
    println("target $target is not")
    return false
}

class StackByQueue() {
    private val queue1 = LinkedList<Int>()
    private val queue2 = LinkedList<Int>()

    fun offer(num: Int) {
        if (queue1.isNotEmpty()) {
            queue1.offer(num)
        } else {
            queue2.offer(num)
        }
    }

    fun pop(): Int? {
        if (queue1.isEmpty() && queue2.isEmpty()) return null
        if (queue1.isNotEmpty() && queue2.isEmpty()) {
            while (queue1.size > 1) {
                queue2.offer(queue1.poll())
            }
            return queue1.poll()
        }
        if (queue1.isEmpty() && queue2.isNotEmpty()) {
            while (queue2.size > 1) {
                queue1.offer(queue2.poll())
            }
            return queue2.poll()
        }
        return null
    }
}

data class TreeNode(
    val left: TreeNode? = null,
    val right: TreeNode? = null,
    val value: Int = 0
)

fun getNearestCommonAncestorInBinarySearchTree(node: TreeNode?, one: TreeNode, another: TreeNode): TreeNode? {
    if (node == null || node.value == one.value || node.value == another.value) return node
    if (node.value > one.value  && node.value > another.value) return getNearestCommonAncestorInBinarySearchTree(node.left, one, another)
    if (node.value < one.value  && node.value < another.value) return getNearestCommonAncestorInBinarySearchTree(node.right, one, another)
    return node
}

fun getNearestCommonAncestorInBinaryTree(node: TreeNode?, one: TreeNode, another: TreeNode): TreeNode? {
    if (node == null || node.value == one.value || node.value == another.value) return node
    val left = getNearestCommonAncestorInBinaryTree(node.left, one, another)
    val right = getNearestCommonAncestorInBinaryTree(node.right, one, another)
    if (left != null && right != null) return node
    return left ?: right
}

fun getCount(): Int {
    val arr = IntArray(100) { 0 }
    for (i in 1..100) { // 人
        for (j in i..100) { // 灯,若能被整除,则至少要比人的编号大,直接取 i..100。
            if (j % i == 0) {
                arr[j - 1] += 1
            }
        }
    }
    return arr.count { it % 2 == 1 }
}
fun main() {
    val a = 5   // Binary: 101
    val b = 3   // Binary: 011

    println("a and b = ${a and b}")     // 1 (Binary: 001)
    println("a or b = ${a or b}")       // 7 (Binary: 111)
    println("a xor b = ${a xor b}")     // 6 (Binary: 110)
    println("a shl 1 = ${a shl 1}")     // 10 (Binary: 1010)
    println("a.inv() = ${a.inv()}")     // -6
}
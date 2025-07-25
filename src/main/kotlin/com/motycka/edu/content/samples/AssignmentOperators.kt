fun main() {
    val readOnlyVar = 10    // Type inferred as Int
    var mutableVar = "Hello" // Type inferred as String

    val immutable = 5
    // immutable = 10 // Error: Val cannot be reassigned

    var mutable = 5
    mutable = 10 // OK: Var can be reassigned
}
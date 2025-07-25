fun main() {
    val name: String? = null
    val age: Int? = 25

    // Logical AND
    if (name != null && name.length > 0) {
        println("Name is not empty: $name")
    } else {
        println("Name is null or empty")
    }

    // Logical OR
    if (name == null || name.isEmpty()) {
        println("Name is missing")
    }

    // Logical NOT
    val isNotNull = name != null
    val isEmpty = !isNotNull
    println("Is empty: $isEmpty")

    // Complex conditions
    if ((age != null && age >= 18) && (name != null && name.isNotBlank())) {
        println("Valid adult user")
    } else {
        println("Invalid user data")
    }
}
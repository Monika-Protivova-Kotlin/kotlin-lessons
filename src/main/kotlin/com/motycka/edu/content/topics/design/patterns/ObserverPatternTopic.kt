package com.motycka.edu.content.topics.design.patterns

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import com.motycka.edu.model.kotlinPlayground
import kotlinx.html.*

object ObserverPatternTopic : Topic(
    title = "Observer Pattern",
    slides = listOf(
        ObserverPatternExplanationSlide,
        ObserverPatternExampleSlide,
//        ObserverExercise1Slide,
//        ObserverExercise2Slide
    )
)

object ObserverPatternExplanationSlide : Slide(
    header = "Observer Pattern (Behavioral)",
    summary = {
        +"Defines a one-to-many dependency where multiple observers are notified when the subject changes state."
    },
    content = {
        p {
            +"The Observer pattern establishes a subscription mechanism to notify multiple objects about events "
            +"that happen to the object they're observing. Common in event-driven systems and UI frameworks."
        }

        p { highlight("When to use:") }
        ul {
            li { +"Event handling systems" }
            li { +"When changes to one object require updating others" }
            li { +"Implementing distributed event systems" }
            li { +"Model-View patterns where views need to update when model changes" }
        }

        p { highlight("Pros & Cons:") }
        ul {
            li { +"✓ Loose coupling between subject and observers" }
            li { +"✓ Dynamic subscription at runtime" }
            li { +"✗ Observers notified in random order" }
            li { +"✗ Memory leaks if observers not properly removed" }
        }
    }
)


object ObserverPatternExampleSlide : Slide(
    header = "Observer Pattern - Kotlin Implementation",
    summary = {
        +"Example: News agency notifying subscribers"
    },
    content = {
        kotlinPlayground(
            code = """
                interface Observer {
                    fun update(message: String)
                }
                
                class Subject {
                    private val observers = mutableListOf<Observer>()
                
                    fun attach(observer: Observer) {
                        observers.add(observer)
                    }
                
                    fun detach(observer: Observer) {
                        observers.remove(observer)
                    }
                
                    fun notify(message: String) {
                        observers.forEach { it.update(message) }
                    }
                }
                
                class ConcreteObserver(val name: String) : Observer {
                    override fun update(message: String) {
                        println("${'$'}name received: ${'$'}message")
                    }
                }
                
                fun main() {
                    val subject = Subject()
                    val observer1 = ConcreteObserver("Observer 1")
                    val observer2 = ConcreteObserver("Observer 2")
                
                    subject.attach(observer1)
                    subject.attach(observer2)
                    subject.notify("Hello observers!")
                
                    subject.detach(observer1)
                    subject.notify("Observer 1 won't see this")
                }
            """.trimMargin(),
            executable = true
        )
    }
)

object ObserverExercise1Slide : Slide(
    header = "Exercise 1: Weather Station",
    summary = {
        +"Implement a weather station that notifies multiple displays when conditions change."
    },
    content = {
        p {
            +"Create a "; inlineCode("WeatherStation"); +" that notifies observers when temperature, "
            +"humidity, or pressure changes."
        }

        p { strong { +"Requirements:" } }
        ol {
            li { +"Create "; inlineCode("interface WeatherObserver"); +" with "; inlineCode("fun update(temp: Double, humidity: Double, pressure: Double)") }
            li { +"Create "; inlineCode("class WeatherStation"); +" with:" }
            ul {
                li { inlineCode("fun subscribe(observer: WeatherObserver)") }
                li { inlineCode("fun unsubscribe(observer: WeatherObserver)") }
                li { inlineCode("fun setMeasurements(temp: Double, humidity: Double, pressure: Double)"); +" - notifies all observers" }
            }
            li { +"Create 3 observer classes:" }
            ul {
                li { inlineCode("CurrentConditionsDisplay"); +" - prints current conditions" }
                li { inlineCode("StatisticsDisplay"); +" - tracks min/max/avg temperature" }
                li { inlineCode("ForecastDisplay"); +" - shows \"Improving\" if pressure > 1020, else \"Getting worse\"" }
            }
        }

        p { strong { +"Example Usage:" } }
        kotlinPlayground(
            code = """
                // Your implementation here
                interface WeatherObserver {
                    fun update(temp: Double, humidity: Double, pressure: Double)
                }
                
                class WeatherStation {
                    // TODO: Implement WeatherStation
                }
                
                class CurrentConditionsDisplay : WeatherObserver {
                    override fun update(temp: Double, humidity: Double, pressure: Double) {
                        println("Current: ${'$'}temp°C, ${'$'}humidity% humidity, ${'$'}pressure hPa")
                    }
                }
                
                // TODO: Implement StatisticsDisplay and ForecastDisplay
                
                fun main() {
                    val station = WeatherStation()
                    val current = CurrentConditionsDisplay()
                    val stats = StatisticsDisplay()
                    val forecast = ForecastDisplay()
                
                    station.subscribe(current)
                    station.subscribe(stats)
                    station.subscribe(forecast)
                
                    println("=== Measurement 1 ===")
                    station.setMeasurements(25.0, 65.0, 1013.0)
                
                    println("\n=== Measurement 2 ===")
                    station.setMeasurements(28.0, 70.0, 1025.0)
                
                    println("\n=== Measurement 3 ===")
                    station.setMeasurements(22.0, 60.0, 1010.0)
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Expected Output:" } }
        pre {
            +"""
                |=== Measurement 1 ===
                |Current: 25.0°C, 65.0% humidity, 1013.0 hPa
                |Statistics: min=25.0, max=25.0, avg=25.0
                |Forecast: Getting worse
                |
                |=== Measurement 2 ===
                |Current: 28.0°C, 70.0% humidity, 1025.0 hPa
                |Statistics: min=25.0, max=28.0, avg=26.5
                |Forecast: Improving
            """.trimMargin()
        }
    }
)

object ObserverExercise2Slide : Slide(
    header = "Exercise 2: Pattern Identification",
    summary = {
        +"Identify the Observer pattern in real-world code examples."
    },
    content = {
        p { +"Examine the following code and answer the questions:" }

        p { strong { +"Example: Stock Market Ticker" } }
        kotlinPlayground(
            code = """
                class Stock(private val symbol: String) {
                    private var price: Double = 0.0
                    private val watchers = mutableListOf<(String, Double) -> Unit>()
                
                    fun addWatcher(watcher: (String, Double) -> Unit) {
                        watchers.add(watcher)
                    }
                
                    fun setPrice(newPrice: Double) {
                        if (price != newPrice) {
                            price = newPrice
                            watchers.forEach { it(symbol, price) }
                        }
                    }
                }
                
                fun main() {
                    val apple = Stock("AAPL")
                
                    apple.addWatcher { symbol, price ->
                        println("Alert: ${'$'}symbol is now $${'$'}price")
                    }
                
                    apple.addWatcher { symbol, price ->
                        if (price > 150.0) println("${'$'}symbol exceeded $${'$'}150!")
                    }
                
                    apple.setPrice(145.0)
                    apple.setPrice(155.0)
                }
            """.trimMargin(),
            executable = true
        )

        p { strong { +"Questions:" } }
        ol {
            li { +"Is this an implementation of the Observer pattern? Why or why not?" }
            li { +"What is the \"subject\" in this example?" }
            li { +"What are the \"observers\"?" }
            li { +"What's different about this implementation compared to the traditional Observer pattern?" }
            li { +"What are the advantages of using lambda functions as observers?" }
            li { +"What's a potential problem with this implementation? (Hint: memory leaks)" }
        }

        p { strong { +"Real-World Examples:" } }
        ul {
            li { +"Android: "; inlineCode("LiveData"); +" observers in MVVM architecture" }
            li { +"Java Swing: "; inlineCode("addActionListener()"); +" for button clicks" }
            li { +"JavaScript: Event listeners ("; inlineCode("addEventListener"); +")" }
            li { +"RxJava/Kotlin Flow: Observable streams" }
        }

        p { em { +"Discuss: Can you think of other examples where the Observer pattern is used?" } }
    }
)

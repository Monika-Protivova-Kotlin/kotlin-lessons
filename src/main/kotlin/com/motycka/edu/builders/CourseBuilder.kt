package com.motycka.edu.builders

import com.motycka.edu.ext.normalizedFileName
import com.motycka.edu.model.Course
import com.motycka.edu.model.Lesson
import com.motycka.edu.model.lessonIntro
import com.motycka.edu.model.topic
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.File
import java.io.StringWriter

object CourseBuilder {
    const val CHARSET = "UTF-8"
    const val ARROW_RIGHT = "→"
    const val ARROW_DOWN = "↓"

    const val PATH = "public/courses"

    const val REVEAL_JS = "https://unpkg.com/reveal.js"
    const val REVEAL_VERSION = "5.2.1"

    fun createCourse(
        course: Course
    ) {
        val outputDir = File("$PATH/${course.name.normalizedFileName()}")
        if (outputDir.exists()) { outputDir.deleteRecursively() }
        outputDir.mkdirs()

        println("Generating ${course.name} ${course.subTitle} course...")

        course.lessons.mapIndexed { index, lesson ->
            val stringWriter = createLesson(
                lesson = lesson
            )

            val fileName = "lesson-${(index + 1).toString().padStart(2, '0')}-${lesson.title.normalizedFileName()}.html"
            val file = File(outputDir, fileName)
            println("Writing lesson: $fileName with ${lesson.topics.size} topics")
            file.writeText(stringWriter.toString())
            file
        }

        println("Actual output path: ${outputDir.absolutePath}")

        val indexStringWriter = createContent(course) {
            createIndex(course)
        }

        val indexFile = File(outputDir, "index.html")
        println("Writing index: index.html")
        indexFile.writeText(indexStringWriter.toString())
    }

    fun createMainIndex(courses: List<Course>) {
        val outputFile = File("public/index.html")
        println("Generating main index page...")

        val html = StringWriter().appendHTML().html {
            attributes["lang"] = "en"
            head {
                meta(charset = CHARSET)
                meta(
                    name = "viewport",
                    content = "width=device-width, initial-scale=1.0"
                )
                title { +"Kotlin Courses" }
                style {
                    unsafe {
                        raw("""
                            * {
                                margin: 0;
                                padding: 0;
                                box-sizing: border-box;
                            }

                            body {
                                font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
                                background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                                min-height: 100vh;
                                display: flex;
                                align-items: center;
                                justify-content: center;
                                padding: 20px;
                            }

                            .container {
                                max-width: 900px;
                                width: 100%;
                            }

                            h1 {
                                color: white;
                                text-align: center;
                                font-size: 3em;
                                margin-bottom: 1em;
                                text-shadow: 2px 2px 4px rgba(0,0,0,0.2);
                            }

                            .courses {
                                display: grid;
                                grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
                                gap: 2em;
                            }

                            .course-card {
                                background: white;
                                border-radius: 12px;
                                padding: 2em;
                                box-shadow: 0 10px 30px rgba(0,0,0,0.2);
                                transition: transform 0.3s ease, box-shadow 0.3s ease;
                                text-decoration: none;
                                color: inherit;
                                display: block;
                            }

                            .course-card:hover {
                                transform: translateY(-5px);
                                box-shadow: 0 15px 40px rgba(0,0,0,0.3);
                            }

                            .course-card h2 {
                                color: #667eea;
                                font-size: 1.8em;
                                margin-bottom: 0.5em;
                            }

                            .course-card .subtitle {
                                color: #764ba2;
                                font-size: 1.2em;
                                margin-bottom: 1em;
                                font-weight: 500;
                            }

                            .course-card p {
                                color: #666;
                                line-height: 1.6;
                                margin-bottom: 1.5em;
                            }

                            .course-card .cta {
                                color: #667eea;
                                font-weight: 600;
                                display: inline-flex;
                                align-items: center;
                                gap: 0.5em;
                            }

                            .course-card .cta::after {
                                content: '→';
                                font-size: 1.2em;
                                transition: transform 0.3s ease;
                            }

                            .course-card:hover .cta::after {
                                transform: translateX(5px);
                            }

                            @media (max-width: 768px) {
                                h1 {
                                    font-size: 2em;
                                }

                                .courses {
                                    grid-template-columns: 1fr;
                                }
                            }
                        """.trimIndent())
                    }
                }
            }

            body {
                div(classes = "container") {
                    h1 { +"Kotlin Courses" }

                    div(classes = "courses") {
                        courses.forEach { course ->
                            a(href = "courses/${course.name.normalizedFileName()}/", classes = "course-card") {
                                h2 { +course.name }
                                div(classes = "subtitle") { +course.subTitle }
                                p {
                                    // Create description from first few lesson summaries
                                    val description = course.lessons
                                        .take(3)
                                        .mapNotNull { it.summary }
                                        .joinToString(" ")
                                        .take(200)
                                        .let { if (it.length == 200) "$it..." else it }
                                    +description
                                }
                                span(classes = "cta") {
                                    +"Start Learning"
                                }
                            }
                        }
                    }
                }
            }
        }

        outputFile.writeText(html.toString())
        println("Written main index: ${outputFile.absolutePath}")
    }

    fun createLesson(
        lesson: Lesson
    ) = StringWriter().appendHTML().html {
        attributes["lang"] = "en"
        head {
            meta(charset = CHARSET)
            meta(
                name = "viewport",
                content = "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
            )
            title { +lesson.title }
            link(rel = "stylesheet", href = "$REVEAL_JS@$REVEAL_VERSION/dist/reset.css")
            link(rel = "stylesheet", href = "$REVEAL_JS@$REVEAL_VERSION/dist/reveal.css")
            link(rel = "stylesheet", href = "../../dist/theme/kotlin.css")
            link(rel = "stylesheet", href = "../../dist/theme/gc-animations.css")
        }

        body {
            div("reveal") {
                div("slides") {
                    lessonIntro(lesson)
                    lesson.topics.forEach(::topic)
                }
            }

            script(src = "$REVEAL_JS@$REVEAL_VERSION/dist/reveal.js") { attributes["data-charset"] = CHARSET }
            script(src = "$REVEAL_JS@$REVEAL_VERSION/plugin/notes/notes.js") { attributes["data-charset"] = CHARSET }
            script(type = "module") {
                unsafe {
                    raw("""
                        Reveal.initialize({
                            width: 1200,
                            margin: 0.02,
                            hash: true,
                            plugins: [ RevealNotes ]
                        });
                        Reveal.configure({ showNotes: false });
                    """.trimIndent())
                }
            }
            script {
                src = "https://kit.fontawesome.com/aa8dffce33.js"
//                crossorigin = "anonymous"
            }
            script {
                unsafe {
                    raw("""
                        function initKotlinPlayground() {
                            let initialized = false;

                            function doInit() {
                                if (!initialized && typeof KotlinPlayground !== 'undefined') {
                                    KotlinPlayground('.kotlin-playground');
                                    initialized = true;
                                    console.log('Kotlin Playground initialized');
                                }
                            }

                            // Wait for Reveal and add delay to ensure DOM is ready
                            if (typeof Reveal !== 'undefined' && Reveal.isReady()) {
                                setTimeout(doInit, 300);
                            } else {
                                Reveal.on('ready', () => {
                                    setTimeout(doInit, 300);
                                });
                            }
                        }
                    """.trimIndent())
                }
            }
            script(src = "https://unpkg.com/kotlin-playground@1") {
                attributes["onload"] = "initKotlinPlayground()"
            }
        }
    }

    fun FlowContent.createIndex(
        course: Course,
    ) {
        section {
            section {
                attributes["data-background-gradient"] = "var(--kotlin-gradient)"
                h1 { +course.name }
                h2 { +course.subTitle }
                br()
                em { +"Course Info $ARROW_RIGHT" }
                br()
                br()
                em { +"Syllabus $ARROW_DOWN" }
            }
            section {
                h3 { +"Syllabus" }
                div(classes = "content") {
                    style = "font-size: 60%"
                    div(classes = "row") {
                        // Split lessons into 3 weeks (5 lessons each)
                        val week1Lessons = course.lessons.take(5)
                        val week2Lessons = course.lessons.drop(5).take(5)
                        val week3Lessons = course.lessons.drop(10)

                        // Week 1 Column
                        div(classes = "column") {
                            p { strong { +"Week 1: Kotlin Fundamentals" } }
                            ul {
                                week1Lessons.forEachIndexed { index, lesson ->
                                    li {
                                        a(href = "lesson-${(index + 1).toString().padStart(2, '0')}-${lesson.title.normalizedFileName()}.html") {
                                            strong { +"Lesson ${index + 1}: ${lesson.title}" }
                                        }
                                        br()
                                        em { +(lesson.summary ?: "") }
                                    }
                                }
                            }
                        }

                        // Week 2 Column
                        div(classes = "column") {
                            p { strong { +"Week 2: Advanced Concepts & Spring Boot" } }
                            ul {
                                week2Lessons.forEachIndexed { index, lesson ->
                                    val lessonNumber = index + 6
                                    li {
                                        a(href = "lesson-${lessonNumber.toString().padStart(2, '0')}-${lesson.title.normalizedFileName()}.html") {
                                            strong { +"Lesson $lessonNumber: ${lesson.title}" }
                                        }
                                        br()
                                        em { +(lesson.summary ?: "") }
                                    }
                                }
                            }
                        }

                        // Week 3 Column
                        div(classes = "column") {
                            p { strong { +"Week 3: Spring Boot & Production" } }
                            ul {
                                week3Lessons.forEachIndexed { index, lesson ->
                                    val lessonNumber = index + 11
                                    li {
                                        a(href = "lesson-${lessonNumber.toString().padStart(2, '0')}-${lesson.title.normalizedFileName()}.html") {
                                            strong { +"Lesson $lessonNumber: ${lesson.title}" }
                                        }
                                        br()
                                        em { +(lesson.summary ?: "") }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // What will we learn in this course?
        section {
            h3 { +"What will we learn in this course?" }
            div(classes = "content") {
                p {
                    style = "font-weight: bold"
                    +"In the first week, we will establish a essential foundation in Kotlin programming language."
                }
                ol {
                    style = "padding-left: 50px"
                    li { +"Kotlin language syntax and fundamentals" }
                    li { +"Object-oriented programming principles" }
                    li { +"Collections and functional programming" }
                    li { +"File I/O and exception handling" }
                    li { +"Inheritance, polymorphism and generics" }
                }
                p {
                    style = "font-weight: bold"
                    +"During the second week, we will explore advanced concepts and begin building Spring Boot applications."
                }
                ol {
                    style = "padding-left: 50px"
                    li { +"Testing fundamentals with JUnit" }
                    li { +"Memory management and concurrent programming" }
                    li { +"SOLID principles and design patterns" }
                    li { +"Spring Boot framework and MVC architecture" }
                    li { +"REST API design and implementation" }
                }
                p {
                    style = "font-weight: bold"
                    +"During the third week, we build production-ready Spring Boot applications."
                }
                ol {
                    style = "padding-left: 50px"
                    li { +"Business logic and service layer implementation" }
                    li { +"Data persistence with JDBC, JPA, and JOOQ" }
                    li { +"Security and authentication with JWT" }
                    li { +"Deployment and observability in production" }
                }
            }
        }
        // Rules
        section {
            h3 { +"Rules" }
            div(classes = "content content-100") {
                ul {
                    li { +"You are welcome to use any tools and resources real-world software developer would." }
                    li {
                        strong { +"That includes AI!" }
                        ul {
                            li { +"However, I will recognize if you just used generated code without understanding, or if you truly understand the subject." }
                            li { +"In fact, I myself used AI to help me formulate some of the lesson materials. Tools exists to be used." }
                        }
                    }
                    li { +"I want to treat students as equals, and I expected to be treated equally." }
                    li {
                        +"Be respectful and helpful to your classmates."
                        ul {
                            li { +"You will learn more if you help each other." }
                            li { +"But don't cheat." }
                        }
                    }
                }
            }
        }
        // Grading
        section {
            h3 { +"Grading" }
            div(classes = "content content-100 content-center") {
                ul {
                    li {
                        strong { +"Kotlin Practice Assignments 1-8" }
                        +" = "
                        em { +"40%" }
                    }
                    li {
                        strong { +"Final Project" }
                        +" = "
                        em { +"40%" }
                    }
                    li {
                        strong { +"Coffee Shop Exercise" }
                        +" = "
                        em { +"10%" }
                    }
                    li {
                        strong { +"Class participation" }
                        +" = "
                        em { +"10%" }
                    }
                }
            }
        }
        // About Me
        section {
            section {
                h3 { +"About Me" }
                div(classes = "summary") {
                    +"Monika Protivová, Prague, Czech Republic"
                    br()
                    +"\"Moni\""
                }
                div(classes = "content content-center") {
                    style = "font-size: 60%"
                    p {
                        i(classes = "fa-solid fa-user") {}
                        br()
                        +"I have been working in software engineering for over 16 years."
                        br()
                        +"I started as a tester, then test architect, test automation engineer (technically a developer)"
                        br()
                        +"and eventually decided to become a full-time developer."
                    }
                    p {
                        i(classes = "fa-solid fa-person-digging") {}
                        br()
                        +"I am a simple, mostly self-taught,"
                        br()
                        +"not the smartest in the room,"
                        br()
                        +"but I am resilient and hard-working with \"do the right thing\" mindset."
                    }
                    p {
                        i(classes = "fa-solid fa-graduation-cap") {}
                        br()
                        +"I teach about testing and test automation for a non-profit organization specialized in education"
                        br()
                        +"for women who want to enter the world of IT."
                    }
                    p {
                        i(classes = "fa-solid fa-brain") {}
                        br()
                        +"I don't know everything, about Kotlin."
                        br()
                        +"What I can offer is practical, simple, real-world experience."
                    }
                    p {
                        i(classes = "fa-solid fa-motorcycle") {}
                        br()
                        +"Outside my job, I am a active person who likes exercise: "
                        br()
                        +"weigh training, fitness, Muay Thai :)."
                        br()
                        +"I love motorcycles. I have three - I travel on them, ride off-road and race on a track."
                    }
                    p {
                        i(classes = "fa-solid fa-heart") {}
                        br()
                        +"My belief is to enjoy each day like the next one is not guaranteed."
                        br()
                        +"Try something when possible. Don't hold back just because you are afraid."
                        br()
                        +"Treat others the way I want to be treated, be respectful, non-judgemental and kind."
                    }
                    p {
                        em {
                            +"Pictures of my life $ARROW_DOWN"
                        }
                    }
                }
            }
            section {
                h3 { +"About Me" }
                div(classes = "content") {
                    div(classes = "row") {
                        div(classes = "column") {
                            br()
                            ul {
                                li {
                                    em {
                                        +"I like to be outside, in nature, "
                                        br()
                                        +"in the mountains, in the forest, by the sea."
                                    }
                                    br()
                                    br()
                                }
                                li {
                                    em {
                                        +"I like to travel, see new places, "
                                        br()
                                        +"meet new people, try new things."
                                    }
                                    br()
                                    br()
                                }
                                li {
                                    em {
                                        +"I work out a lot, lift weights, "
                                        br()
                                        +"and even do Muay Thai."
                                    }
                                    br()
                                    br()
                                }
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/me1.png") {
                                style = "max-height: 300px"
                            }
                        }
                    }
                    div(classes = "row") {
                        div(classes = "column") {
                            img(src = "../../img/aboutme/me3.png") {
                                style = "max-height: 240px"
                            }
                            img(src = "../../img/aboutme/me4.png") {
                                style = "max-height: 240px"
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/me2.png") {
                                style = "max-height: 240px"
                            }
                            img(src = "../../img/aboutme/crf1.png") {
                                style = "max-height: 240px"
                            }
                        }
                    }
                }
            }
            section {
                h3 { +"My Cats" }
                div(classes = "content") {
                    div(classes = "row") {
                        div(classes = "column") {
                            p {
                                em { +"I have 3 cats. They are all very chill and friendly." }
                            }
                            ul {
                                li {
                                    strong(classes = "inline") { +"Elfie" }
                                    br()
                                    em { +"young Siamese girl" }
                                    br()
                                    br()
                                }
                                li {
                                    strong(classes = "inline") { +"Coffee" }
                                    br()
                                    em { +"black boy" }
                                    br()
                                    br()
                                }
                                li {
                                    strong(classes = "inline") { +"Oli" }
                                    br()
                                    em { +"brown tabby boy" }
                                    br()
                                    br()
                                }
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/elfie1.png") {
                                style = "max-height: 280px"
                            }
                        }
                    }
                    div(classes = "row") {
                        div(classes = "column") {
                            img(src = "../../img/aboutme/coffee3.png") {
                                style = "max-height: 280px"
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/oli1.png") {
                                style = "max-height: 280px"
                            }
                        }
                    }
                }
            }
            section {
                h3 { +"My Motorbikes" }
                div(classes = "content") {
                    div(classes = "row") {
                        div(classes = "column") {
                            p {
                                em {
                                    +"I love riding and even racing motorcycles."
                                    br()
                                    +"I have 3 bikes."
                                }
                            }
                            ul {
                                li {
                                    strong(classes = "inline") { +"CRF 300L" }
                                    br()
                                    em { +"my off-road and off-road touring bike" }
                                    br()
                                    br()
                                }
                                li {
                                    strong(classes = "inline") { +"Aprilia Tuono 660 Factory" }
                                    br()
                                    em { +"my track bike" }
                                    br()
                                    br()
                                }
                                li {
                                    strong(classes = "inline") { +"Triumph Tiger 900 Rally Pro" }
                                    br()
                                    em { +"my travel and light off-road bike" }
                                    br()
                                    br()
                                }
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/fofrnda.png") {
                                style = "max-height: 280px"
                            }
                        }
                    }
                    div(classes = "row") {
                        div(classes = "column") {
                            img(src = "../../img/aboutme/frnda.png") {
                                style = "max-height: 280px"
                            }
                        }
                        div(classes = "column") {
                            img(src = "../../img/aboutme/crf2.png") {
                                style = "max-height: 280px"
                            }
                        }
                    }
                }
            }
        }
    }

    fun createContent(
        course: Course,
        content: FlowContent.() -> Unit
    ) = StringWriter().appendHTML().html {
        attributes["lang"] = "en"
        head {
            meta(charset = CHARSET)
            meta(
                name = "viewport",
                content = "width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
            )
            title { +"${course.name} ${course.subTitle}" }
            link(rel = "stylesheet", href = "https://unpkg.com/reveal.js@$REVEAL_VERSION/dist/reset.css")
            link(rel = "stylesheet", href = "https://unpkg.com/reveal.js@$REVEAL_VERSION/dist/reveal.css")
            link(rel = "stylesheet", href = "../../dist/theme/kotlin.css")
            link(rel = "stylesheet", href = "../../dist/theme/gc-animations.css")
        }

        body {
            div("reveal") {
                div("slides") {
                    content()
                }
            }

            script(src = "https://unpkg.com/reveal.js@$REVEAL_VERSION/dist/reveal.js") { attributes["data-charset"] = CHARSET }
            script(src = "https://unpkg.com/reveal.js@$REVEAL_VERSION/plugin/notes/notes.js") { attributes["data-charset"] = CHARSET }
            script(type = "module") {
                unsafe {
                    raw("""
                        Reveal.initialize({
                            width: 1200,
                            margin: 0.02,
                            hash: true,
                            plugins: [ RevealNotes ]
                        });
                        Reveal.configure({ showNotes: false });
                    """.trimIndent())
                }
            }
            script {
                src = "https://kit.fontawesome.com/aa8dffce33.js"
//                crossorigin = "anonymous"
            }
            script {
                unsafe {
                    raw("""
                        function initKotlinPlayground() {
                            let initialized = false;

                            function doInit() {
                                if (!initialized && typeof KotlinPlayground !== 'undefined') {
                                    KotlinPlayground('.kotlin-playground');
                                    initialized = true;
                                    console.log('Kotlin Playground initialized');
                                }
                            }

                            // Wait for Reveal and add delay to ensure DOM is ready
                            if (typeof Reveal !== 'undefined' && Reveal.isReady()) {
                                setTimeout(doInit, 300);
                            } else {
                                Reveal.on('ready', () => {
                                    setTimeout(doInit, 300);
                                });
                            }
                        }
                    """.trimIndent())
                }
            }
            script(src = "https://unpkg.com/kotlin-playground@1") {
                attributes["onload"] = "initKotlinPlayground()"
            }
        }
    }

//    fun SectioningOrFlowContent.createIntroSlide(
//        lesson: Lesson,
//    ) {
//        section {
//            section {
//                attributes["data-background-gradient"] = "var(--kotlin-gradient)"
//                lesson.preTitle?.let { em { +it } }
//                h1 { +lesson.title }
//                lesson.subTitle?.let { h2 { +it } }
//                br()
//                lesson.contentInfo.forEach {
//                    em { +it }
//                    br()
//                }
//                br()
//                br()
//                em { +"© ${LocalDate.now().year} by Monika Protivová" }
//            }
//        }
//    }

//    fun SectioningOrFlowContent.createTopic(
//        title: String,
//        subTitle: String,
//        contentInfo: List<String>,
//        slides: List<Slide> = emptyList(),
//    ) {
//        section {
//            section {
//                attributes["data-background-gradient"] = "var(--kotlin-gradient)"
//                h1 { +title }
//                h2 { +subTitle }
//                br()
//                contentInfo.forEach {
//                    em { +it }
//                    br()
//                }
//                br()
//                br()
//                em { +"© ${LocalDate.now().year} by Monika Protivová" }
//            }
////            slides.forEach { slide ->
////                addSlide(slide)
////            }
//        }
//    }

//    fun SectioningOrFlowContent.addSlide(slide: Slide) {
//        section {
//            h3 { +slide.header }
//            slide.summary?.let {
//                div(classes = "summary") {
//                    unsafe {
//                        raw(it)
//                    }
//                }
//            }
//            slide.content?.let {
//                div(classes = "content") {
//                    unsafe {
//                        raw(it)
//                    }
//                }
//            }
//        }
//    }


//    fun SectioningOrFlowContent.topicSection(
//        title: String,
//        subtitle: String,
//        contentInfo: List<String>
//    ) = section {
//        attributes["data-background"] = "var(--kotlin-gradient)"
//        h1 { +title }
//        h2 { +subtitle }
//        br()
//        contentInfo.forEach {
//            em { +it }
//            br()
//        }
//        br()
//        br()
//        em { +"© ${LocalDate.now().year} by Monika Protivová" }
//    }

}


package com.motycka.edu.model

import kotlinx.html.FlowContent
import kotlinx.html.FlowOrPhrasingContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.STRONG
import kotlinx.html.UL
import kotlinx.html.attributesMapOf
import kotlinx.html.br
import kotlinx.html.code
import kotlinx.html.div
import kotlinx.html.h3
import kotlinx.html.li
import kotlinx.html.ol
import kotlinx.html.p
import kotlinx.html.pre
import kotlinx.html.section
import kotlinx.html.span
import kotlinx.html.strong
import kotlinx.html.style
import kotlinx.html.ul
import kotlinx.html.visit
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

open class Slide(
    val header: String,
    val summary: (FlowContent.() -> Unit)? = null,
    val content: FlowContent.() -> Unit,
    val textAlign: String = "left",
    val fontSize: String = "80%"
) {
    companion object {
        const val DOLLAR = '$'
    }
}

const val IMG_PATH = "../../img"
fun imgByName(name: String, suffix: String = "png"): String {
    return "$IMG_PATH/$name.$suffix"
}


fun FlowContent.slide(slide: Slide, isExercise: Boolean = false) {
    section {
        if (isExercise) {
            attributes["data-exercise"] = "true"
        }
        h3 { +slide.header }
        if (slide.summary != null) {
            div(classes = "summary") {
                slide.summary.invoke(this)
            }
        }
        div(classes = "content", ) {
            val borderStyle = if (slide.summary == null) "border-top: none; " else ""
            style = "${borderStyle}text-align: ${slide.textAlign}; font-size: ${slide.fontSize}"
            slide.content.invoke(this)
        }
    }
}

fun FlowContent.inlineCode(code: String) {
    code(classes = "inline") { +code }
}

fun FlowContent.highlight(text: String) {
    strong(classes = "highlight") { +text }
}

@HtmlTagMarker
inline fun FlowOrPhrasingContent.highlight(crossinline block : STRONG.() -> Unit = {}) {
    STRONG(attributesMapOf("class", "highlight"), consumer).visit(block)
}

fun FlowContent.kotlinPlayground(
    code: String,
    executable: Boolean = true,
    erroneous: Boolean = false,
    showLines: Boolean = true,
    selectLines: IntRange? = null,
    arguments: Array<String>? = null
) {
    div {
        style = when {
            executable -> "border-left: 5px solid #7F52FF; padding-left: 0px"
            erroneous -> "border-left: 5px solid red; padding-left: 0px"
            else -> "border-left: 5px solid lightgrey; padding-left: 0px"
        }
        code(classes = "kotlin-playground") {
            attributes["data-target-platform"] = "kotlin"
            attributes["lines"] = showLines.toString()
            attributes["theme"] = "idea"
            if (selectLines != null) {
                attributes["from"] = selectLines.first.toString()
                attributes["to"] = selectLines.last.toString()
            }
            if (executable.not()) {
                attributes["data-highlight-only"] = ""
            }
            if (arguments.isNullOrEmpty().not()) attributes["args"] = arguments.joinToString(" ")
            +code.trimMargin("|").trimIndent()
        }
    }
}

fun FlowContent.contentCard(
    content: FlowContent.() -> Unit
) {
    div(classes = "content-card") {
        content()
    }
}

fun FlowContent.contentCard(
    icon: String = "",
    header: String = "",
    content: FlowContent.() -> Unit
) {
    div(classes = "content-card") {
        strong { +"$icon ️${if (header.isEmpty()) " " else "$header: "}" }
        content()
    }
}

fun FlowContent.infoCard(
    header: String = "",
    content: FlowContent.() -> Unit
) = contentCard("\uD83D\uDCDD", header, content)

fun FlowContent.warningCard(
    header: String = "",
    content: FlowContent.() -> Unit
) = contentCard("⚠️", header, content)

fun FlowContent.hintCard(
    header: String = "",
    content: FlowContent.() -> Unit
) = contentCard("💡", header, content)


fun FlowContent.importantCard(
    header: String = "",
    content: FlowContent.() -> Unit
) = contentCard("❗", header, content)

fun FlowContent.twoColumns(
    left: FlowContent.() -> Unit,
    right: FlowContent.() -> Unit,
    ratio: Pair<Int, Int> = 1 to 1
) {
    val totalParts = ratio.first + ratio.second
    val leftWidth = (ratio.first * 100.0 / totalParts)
    val rightWidth = (ratio.second * 100.0 / totalParts)

    div(classes = "row") {
        div(classes = "column") {
            style = "width: ${leftWidth}%"
            left.invoke(this)
        }
        div(classes = "column") {
            style = "width: ${rightWidth}%; padding-left: 20px; box-sizing: border-box"
            right.invoke(this)
        }
    }
}

fun FlowContent.list(items: List<FlowContent.() -> Unit>) {
    ul {
        items.forEach { item ->
            item()
        }
    }
}

fun FlowContent.nestedList(vararg items: Pair<String, List<String>>) {
    ul {
        items.forEach { (item, subItems) ->
            span { +item }
            subItems.forEach { subItem ->
                ul {
                    li { +subItem }
                }
            }
            br()
        }
    }
}

fun FlowContent.nestedNumberedList(items: List<Pair<String, List<String>>>, startFrom: Int = 1) {
    ol {
        start = startFrom.toString()
        items.forEach { (item, subItems) ->
            span { +item }
            subItems.forEach { subItem ->
                ol {
//                    start = startFrom.toString()
                    li { +subItem }
                }
            }
        }
    }
}

fun FlowContent.mermaidDiagram(diagram: String) {
    div(classes = "mermaid") {
        pre {
            +diagram
                .trimMargin("|")
                .trimIndent()
        }
    }
}

fun FlowContent.svgDiagram(
    width: Int = 800,
    height: Int = 600,
    svgContent: String
) {
    // Use consumer API to insert raw HTML/SVG
    consumer.onTagContentUnsafe {
        raw("""
            <div style="display: flex; justify-content: center; align-items: center; width: 100%;">
                <svg width="$width" height="$height" viewBox="0 0 $width $height" xmlns="http://www.w3.org/2000/svg">
                    <defs>
                        <marker id="arrowhead" markerWidth="10" markerHeight="7" refX="9" refY="3.5" orient="auto">
                            <polygon points="0 0, 10 3.5, 0 7" fill="#333" />
                        </marker>
                    </defs>
                    $svgContent
                </svg>
            </div>
        """.trimIndent())
    }
}

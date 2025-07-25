package com.motycka.edu.builders

import kotlinx.html.*
import kotlinx.html.strong
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode

fun FlowContent.highlightOrderedList(
    vararg items: Pair<String, FlowContent.() -> Unit>
) {
    ul {
        items.forEach { (header, flowContent) ->
            li { highlightListItem(header, flowContent) }
        }
    }
}

fun FlowContent.highlightNumberedList(
    startFrom: Int,
    vararg items: Pair<String, FlowContent.() -> Unit>
) {
    ol {
        start = startFrom.toString()
        items.forEach { (header, flowContent) ->
            li { highlightListItem(header, flowContent) }
        }
    }
}

fun FlowContent.codeOrderedList(
    vararg items: Pair<String, FlowContent.() -> Unit>
) {
    ul {
        items.forEach { (header, flowContent) ->
            li { codeListItem(header, flowContent) }
        }
    }
}

fun FlowContent.highlightListItem(header: String, flowContent: FlowContent.() -> Unit) {
    highlight(header)
    br()
    flowContent()
}

fun FlowContent.codeListItem(header: String, flowContent: FlowContent.() -> Unit) {
    inlineCode(header)
    br()
    flowContent()
}

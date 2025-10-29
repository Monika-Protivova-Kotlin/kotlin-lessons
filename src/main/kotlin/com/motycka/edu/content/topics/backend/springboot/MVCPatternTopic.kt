package com.motycka.edu.content.topics.backend.springboot

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.svgDiagram
import kotlinx.html.*

object MVCPatternTopic : Topic(
    title = "Model-View-Controller",
    slides = listOf(
        MVCSlide,
        TraditionalMVCDiagramSlide,
        SpringMVCSlide,
        SpringBootMVCDiagramSlide
    )
)

object MVCSlide : Slide(
    header = "Model-View-Controller (MVC)",
    summary = {
        +"The Model-View-Controller (MVC) is a design pattern that separates an application into three main components:"
    },
    content = {
        ul {
            li {
                strong { highlight("Model") }
                br()
                +"represents the data and the business logic of the application"
            }
            li {
                strong { highlight("View") }
                br()
                +"represents the user interface"
            }
            li {
                strong { highlight("Controller") }
                br()
                +"handles the user input and updates the model and view"
            }
        }
    }
)

object TraditionalMVCDiagramSlide : Slide(
    header = "Traditional MVC Pattern",
    content = {
        svgDiagram(width = 800, height = 500, svgContent = """
            <!-- User -->
            <rect x="325" y="20" width="150" height="50" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="400" y="50" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">User</text>

            <!-- Arrow from User to Controller (user actions) -->
            <line x1="350" y1="70" x2="200" y2="150" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="250" y="100" fill="#333" font-size="12">user actions</text>

            <!-- Arrow from View to User (display) -->
            <line x1="550" y1="150" x2="450" y2="70" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="520" y="100" fill="#333" font-size="12">display</text>

            <!-- Controller -->
            <rect x="100" y="160" width="180" height="100" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="190" y="195" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Controller</text>
            <text x="190" y="220" text-anchor="middle" fill="#333" font-size="13">- Handles user input</text>
            <text x="190" y="240" text-anchor="middle" fill="#333" font-size="13">- Updates model</text>

            <!-- Model -->
            <rect x="310" y="160" width="180" height="100" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="400" y="195" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Model</text>
            <text x="400" y="220" text-anchor="middle" fill="#333" font-size="13">- Business logic</text>
            <text x="400" y="240" text-anchor="middle" fill="#333" font-size="13">- Manages data</text>

            <!-- View -->
            <rect x="520" y="160" width="180" height="100" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="610" y="195" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">View</text>
            <text x="610" y="220" text-anchor="middle" fill="#333" font-size="13">- Displays data</text>
            <text x="610" y="240" text-anchor="middle" fill="#333" font-size="13">- UI presentation</text>

            <!-- Arrow from Controller to Model (updates) -->
            <line x1="280" y1="210" x2="310" y2="210" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="295" y="200" text-anchor="middle" fill="#333" font-size="12">updates</text>

            <!-- Arrow from Model to View (notifies) -->
            <line x1="490" y1="210" x2="520" y2="210" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="505" y="200" text-anchor="middle" fill="#333" font-size="12">notifies</text>

            <!-- Arrow from View to Model (queries) -->
            <line x1="610" y1="260" x2="400" y2="260" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)" stroke-dasharray="5,5"/>
            <text x="505" y="280" text-anchor="middle" fill="#333" font-size="12">queries data</text>

            <!-- Database -->
            <ellipse cx="400" cy="380" rx="120" ry="40" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="400" y="385" text-anchor="middle" fill="#333" font-size="15" font-weight="bold">Database</text>

            <!-- Arrow from Model to Database -->
            <line x1="400" y1="260" x2="400" y2="340" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="420" y="300" fill="#333" font-size="12">read/write</text>

            <!-- Legend -->
            <text x="50" y="440" fill="#333" font-size="13" font-weight="bold">Flow:</text>
            <text x="50" y="465" fill="#333" font-size="12">1. User interacts with View</text>
            <text x="50" y="485" fill="#333" font-size="12">2. Controller handles input and updates Model</text>
            <text x="450" y="465" fill="#333" font-size="12">3. Model notifies View of changes</text>
            <text x="450" y="485" fill="#333" font-size="12">4. View queries Model and displays to User</text>
        """.trimIndent())
    }
)

object SpringMVCSlide : Slide(
    header = "Spring MVC",
    summary = {
        +"Spring Boot follows the MVC pattern, but implementation may be slightly different from the traditional "
        +"MVC frameworks because of its nature as a web application framework."
    },
    content = {
        p {
            +"For example, for RESTful applications written in Spring Boot, "
            +"the controller is responsible for handling the HTTP requests and returning the HTTP responses. "
            +"There is no user interface, so the view is often represented as a JSON or XML document."
        }
        p {
            +"Spring Boot applications implementing RESTful services usually have this architecture:"
        }
        ul {
            li {
                strong { highlight("Controller") }
                br()
                +"handles the HTTP requests and returns the HTTP responses"
            }
            li {
                strong { highlight("Service") }
                br()
                +"contains the business logic"
            }
            li {
                strong { highlight("Repository") }
                br()
                +"contains the data access logic"
            }
        }
    }
)

object SpringBootMVCDiagramSlide : Slide(
    header = "Spring Boot MVC Architecture",
    content = {
        svgDiagram(width = 700, height = 550, svgContent = """
            <!-- Client -->
            <rect x="275" y="20" width="150" height="60" rx="5" fill="#88DCC0" stroke="#333" stroke-width="2"/>
            <text x="350" y="45" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Client</text>
            <text x="350" y="65" text-anchor="middle" fill="#333" font-size="12">(Browser, Mobile App)</text>

            <!-- HTTP Request Arrow -->
            <line x1="350" y1="80" x2="350" y2="120" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="380" y="100" fill="#333" font-size="12" font-weight="bold">HTTP Request</text>

            <!-- Controller -->
            <rect x="225" y="130" width="250" height="70" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="350" y="160" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Controller</text>
            <text x="350" y="180" text-anchor="middle" fill="#333" font-size="13">@RestController</text>
            <text x="350" y="195" text-anchor="middle" fill="#333" font-size="12">Handles HTTP requests/responses</text>

            <!-- Request Flow Arrow -->
            <line x1="350" y1="200" x2="350" y2="240" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="380" y="220" fill="#333" font-size="11">request</text>

            <!-- Service Layer -->
            <rect x="225" y="250" width="250" height="70" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="350" y="280" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Service Layer</text>
            <text x="350" y="300" text-anchor="middle" fill="#333" font-size="13">@Service</text>
            <text x="350" y="315" text-anchor="middle" fill="#333" font-size="12">Business logic & orchestration</text>

            <!-- Request Flow Arrow -->
            <line x1="350" y1="320" x2="350" y2="360" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="380" y="340" fill="#333" font-size="11">request</text>

            <!-- Repository Layer -->
            <rect x="225" y="370" width="250" height="70" rx="5" fill="#CFB8FF" stroke="#333" stroke-width="2"/>
            <text x="350" y="400" text-anchor="middle" fill="#333" font-size="16" font-weight="bold">Repository</text>
            <text x="350" y="420" text-anchor="middle" fill="#333" font-size="13">@Repository</text>
            <text x="350" y="435" text-anchor="middle" fill="#333" font-size="12">Data access layer</text>

            <!-- Request Flow Arrow -->
            <line x1="350" y1="440" x2="350" y2="470" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="380" y="455" fill="#333" font-size="11">query</text>

            <!-- Database -->
            <ellipse cx="350" cy="505" rx="110" ry="35" fill="#B5B8BF" stroke="#333" stroke-width="2"/>
            <text x="350" y="510" text-anchor="middle" fill="#333" font-size="15" font-weight="bold">Database</text>

            <!-- Response Flow Arrows (dashed) -->
            <!-- DB to Repository -->
            <line x1="330" y1="470" x2="330" y2="440" stroke="#333" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
            <text x="300" y="455" fill="#333" font-size="11">data</text>

            <!-- Repository to Service -->
            <line x1="330" y1="360" x2="330" y2="320" stroke="#333" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
            <text x="300" y="340" fill="#333" font-size="11">result</text>

            <!-- Service to Controller -->
            <line x1="330" y1="240" x2="330" y2="200" stroke="#333" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
            <text x="300" y="220" fill="#333" font-size="11">response</text>

            <!-- HTTP Response Arrow -->
            <line x1="330" y1="120" x2="330" y2="80" stroke="#333" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
            <text x="280" y="100" fill="#333" font-size="12" font-weight="bold">HTTP Response</text>

            <!-- Legend -->
            <rect x="520" y="130" width="160" height="120" rx="5" fill="#f5f5f5" stroke="#333" stroke-width="1"/>
            <text x="600" y="150" text-anchor="middle" fill="#333" font-size="13" font-weight="bold">Request Flow</text>

            <line x1="530" y1="165" x2="580" y2="165" stroke="#333" stroke-width="2" marker-end="url(#arrowhead)"/>
            <text x="600" y="170" fill="#333" font-size="11">Solid arrow</text>

            <line x1="530" y1="190" x2="580" y2="190" stroke="#333" stroke-width="2" stroke-dasharray="5,5" marker-end="url(#arrowhead)"/>
            <text x="600" y="195" fill="#333" font-size="11">Dashed arrow</text>

            <text x="600" y="220" text-anchor="middle" fill="#333" font-size="11">= Request down</text>
            <text x="600" y="235" text-anchor="middle" fill="#333" font-size="11">= Response up</text>
        """.trimIndent())
    }
)

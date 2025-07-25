package com.motycka.edu.content.topics.basics.api

import com.motycka.edu.model.Slide
import com.motycka.edu.model.Topic
import com.motycka.edu.model.highlight
import com.motycka.edu.model.inlineCode
import kotlinx.html.*

object WhatIsRESTTopic : Topic(
    title = "What is REST",
    slides = listOf(
        WhatIsRESTSlide,
        RESTAPISlide,
        RequestSlide,
        ResponseSlide,
        MethodsSlide,
        PathsSlide,
        HeadersSlide,
        BodySlide,
        StatusCodesSlide
    )
)

object WhatIsRESTSlide : Slide(
    header = "What is REST",
    summary = {
        +"REST stands for "
        strong { +"Representational State Transfer" }
        +"."
    },
    content = {
        blockQuote {
            +"It is an architectural style for designing networked applications. "
            +"Systems that follow REST principles are often called RESTful systems. "
            +"Characteristics of RESTful systems include "
            strong { +"statelessness" }
            +", "
            strong { +"client-server architecture" }
            +", and a "
            strong { +"uniform interface" }
            +"."
        }
        ul {
            li {
                highlight("Statelessness")
                br()
                +"Each request from a client to a server must contain all of the information "
                +"necessary process the request, without relying on any server state being held between requests."
            }
            li {
                highlight("Client-server architecture")
                br()
                +"The client and server are separate and independent of each other, only communicating by well-defined requests and responses. "
                +"This allows each to be developed and scaled independently."
                br()
                br()
                +"In real applications, client is usually responsible for the user interactions and the server is responsible for the data storage and processing."
            }
            li {
                highlight("Uniform interface")
                br()
                +"The API should be designed in a way that is consistent, predictable, "
                +"handles errors gracefully, is platform-agnostic, and is easy to understand and use."
            }
        }
    }
)

object RESTAPISlide : Slide(
    header = "REST API",
    summary = {
        +"Communication through REST API is done using standard HTTP methods, "
        +"such as "
        strong { +"GET" }
        +", "
        strong { +"POST" }
        +", "
        strong { +"PUT" }
        +", and "
        strong { +"DELETE" }
        +"."
    },
    content = {
        p {
            +"REST communication is "
            strong { +"request-response" }
            +" protocol, which means that the client sends a request to the server, and the server sends a response back to the client."
        }
        p {
            +"Each request is sent to a unique URI (Uniform Resource Identifier), "
            +"which represents a "
            strong { +"resource" }
            +" on the server."
        }
        p {
            +"The server processes the request and sends back a response, which may include data, status, "
            +"and other information, usually in JSON or XML format."
        }
    }
)

object RequestSlide : Slide(
    header = "Request",
    content = {
        ul {
            li {
                strong { +"HTTP Method" }
                br()
                em { +"Defines the type of action to be performed on the resource." }
                br()
                inlineCode("GET /accounts")
            }
            li {
                strong { +"URI" }
                br()
                em {
                    +"Identifies a unique "
                    strong { +"resource" }
                    +" on the server. "
                    +"It is usually composed of "
                    strong { +"path" }
                    +" and optionally "
                    strong { +"query parameters" }
                    +"."
                }
                br()
                inlineCode("GET /accounts/123/users?limit=10&search=joe")
            }
            li {
                strong { +"Headers" }
                br()
                em { +"These can be used to send additional data with the request, such as the content type or an authorization token." }
                br()
                inlineCode("Content-Type: application/json")
                br()
                inlineCode("Authorization: Bearer some-token-value")
            }
            li {
                strong { +"Body" }
                br()
                em {
                    +"Body is usually sent only with "
                    strong { +"POST" }
                    +", "
                    strong { +"PUT" }
                    +" and "
                    strong { +"PATCH" }
                    +" requests. In most cases, this will be formatted as JSON or XML."
                }
                br()
                inlineCode("""{ "username": "testUser", "password" : "123456" }""")
            }
        }
    }
)

object ResponseSlide : Slide(
    header = "Response",
    content = {
        ul {
            li {
                strong { +"HTTP Status Code" }
                br()
                +"A numerical code that indicates the success or failure of the request. "
                +"There is a convention for what status code should be used in what situation."
            }
            li {
                strong { +"Headers" }
                br()
                +"As in the request, headers in the response can be used to pass additional information. "
                +"This might include the content type of the response, or a Set-Cookie header to store information in the client's browser."
            }
            li {
                strong { +"Body" }
                br()
                +"This contains the actual data being returned from the server. "
                +"This will usually be in JSON or XML format, or could also be plain text."
                br()
                inlineCode("""{ "id": 1, "username": "testUser", "email": "testUser@example.com" }""")
            }
        }
    }
)

object MethodsSlide : Slide(
    header = "Methods",
    summary = { +"In theory, you can use all methods of HTTP protocol to communicate with REST API." },
    content = {
        p { +"In practice, you will mostly use ..." }
        ul {
            li {
                strong { +"GET" }
                br()
                +"Used to retrieve data from the server. It should never change the state of the server."
            }
            li {
                strong { +"POST" }
                br()
                +"Used to send data to the server to create a new resource."
            }
            li {
                strong { +"PUT" }
                br()
                em {
                    +"Used to send data to the server to update an existing resource. "
                    +"Changes should be idempotent, meaning that if you send the same request multiple times, "
                    +"the result should be the same as if you sent it once. "
                    +"In other words, PUT should be used to update the resource as a whole."
                }
            }
            li {
                strong { +"DELETE" }
                br()
                +"Used to delete a resource from the server."
            }
            li {
                strong { +"PATCH" }
                br()
                +"Used to partially update a resource on the server."
            }
        }
    }
)

object PathsSlide : Slide(
    header = "Paths",
    summary = { +"URI is the path to the resource on the server." },
    content = {
        p {
            +"You can use "
            strong { +"path parameters" }
            +" to specify a particular resource, and "
            strong { +"query parameters" }
            +" to, for example, filter or paginate the results."
        }
        p {
            +"Here is the conventional structure of the resource:"
            br()
            br()
            code { attributes["style"] = "color: steelblue"; +"/resources/" }
            code { attributes["style"] = "color: magenta"; +"{path-parameter}" }
            code { attributes["style"] = "color: steelblue"; +"/sub-resource" }
            code { attributes["style"] = "color: red"; +"?" }
            code { attributes["style"] = "color: green"; +"param1=value" }
            code { attributes["style"] = "color: orange"; +"&" }
            code { attributes["style"] = "color: green"; +"param2=value" }
        }
        ul {
            li { span { attributes["style"] = "color: steelblue"; +"resource path" } }
            li { span { attributes["style"] = "color: magenta"; +"path parameter" } }
            li { span { attributes["style"] = "color: red"; +"path and query parameter separator" } }
            li { span { attributes["style"] = "color: orange"; +"query parameter separator" } }
            li {
                span { attributes["style"] = "color: green"; +"query parameters " }
                +"and their values"
            }
        }
    }
)

object HeadersSlide : Slide(
    header = "Headers",
    summary = { +"Headers are used to pass additional information with the request or response." },
    content = {
        p {
            +"Headers are usually conventional, meaning that there are some standard headers that are used in most APIs. "
            +"REST services can also define their own custom headers."
        }
        p { +"Some of the most common conventional headers are:" }
        ul {
            li {
                strong { +"Content-Type" }
                br()
                +"Used to specify the format of the body of the request."
                br()
                inlineCode("Content-Type: application/json")
            }
            li {
                strong { +"Accept" }
                br()
                +"Used to specify the format of the response."
                br()
                inlineCode("Accept: application/json")
            }
            li {
                strong { +"Authorization" }
                br()
                +"Used to pass an authorization token with the request."
                br()
                inlineCode("Authorization: Bearer some-token-value")
            }
            li {
                strong { +"X-Api-Key" }
                br()
                +"Used to pass an API key with the request."
                br()
                inlineCode("X-Api-Key: some-api-key-value")
            }
        }
    }
)

object BodySlide : Slide(
    header = "Body",
    summary = {
        +"Body is usually sent only with "
        strong { +"POST" }
        +", "
        strong { +"PUT" }
        +" and "
        strong { +"PATCH" }
        +" requests."
    },
    content = {
        p {
            +"Body is almost exclusively custom, meaning that it is up to the service to define what the body of the request or response should look like."
        }
        p { +"Most common formats for the body are JSON and XML." }
        p { +"JSON:" }
        pre {
            code(classes = "hljs json") {
                attributes["data-trim"] = ""
                attributes["data-noescape"] = ""
                attributes["data-line-numbers"] = ""
                +"""
                    {
                        "id": 1234,
                        "firstName": "Monika",
                        "lastname": "Protivova",
                        "email": "monika.protivova@gmail.com",
                        "isAdmin": true
                    }
                """.trimIndent()
            }
        }
        p { +"XML:" }
        pre {
            code(classes = "hljs xml") {
                attributes["data-trim"] = ""
                attributes["data-noescape"] = ""
                attributes["data-line-numbers"] = ""
                +"""
                    <user>
                        <id>1234</id>
                        <firstName>Monika</firstName>
                        <lastname>Protivova</lastname>
                        <email>monika.protivova@gmail.com</email>
                        <isAdmin>true</isAdmin>
                    </user>
                """.trimIndent()
            }
        }
    }
)

object StatusCodesSlide : Slide(
    header = "Status Codes",
    summary = { +"HTTPS status codes are used to indicate the success or failure of the request." },
    content = {
        p { +"The most common status codes are:" }
        ul {
            li {
                strong { +"200 OK" }
                +" - Request succeeded"
            }
            li {
                strong { +"201 Created" }
                +" - Request succeeded and a new resource was created"
            }
            li {
                strong { +"400 Bad Request" }
                +" - The server cannot process the request due to a client error"
            }
            li {
                strong { +"401 Unauthorized" }
                +" - The client must authenticate itself to get the requested response"
            }
            li {
                strong { +"403 Forbidden" }
                +" - The client does not have access rights to the content"
            }
            li {
                strong { +"404 Not Found" }
                +" - The server can not find the requested resource"
            }
            li {
                strong { +"500 Internal Server Error" }
                +" - The server has encountered a situation it doesn't know how to handle"
            }
        }
    }
)
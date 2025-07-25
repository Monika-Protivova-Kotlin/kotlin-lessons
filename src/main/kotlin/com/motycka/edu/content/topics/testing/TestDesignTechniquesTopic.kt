package com.motycka.edu.content.topics.testing

import kotlinx.html.*
import com.motycka.edu.model.Topic
import com.motycka.edu.model.Slide

object TestDesignTechniquesTopic : Topic(
    title = "Test design techniques",
    slides = listOf(
        TestDesignTechniquesOverviewSlide,
        EquivalencePartitioningSlide,
        BoundaryValueAnalysisSlide,
        DecisionTablesSlide,
        StateTransitionAnalysisSlide,
        OrthogonalArrayTestingSlide,
        AllPairsTestingSlide
    )
)

object TestDesignTechniquesOverviewSlide : Slide(
    header = "Test design techniques",
    summary = {
        +"What are they and why should developers care?"
    },
    content = {
        p {
            span("highlight") { +"Test design techniques" }
            +" are techniques used to design tests."
            br()
            br()
            +"They are used to ensure adequate "
            strong { +"test coverage" }
            +", optimize the number of tests, maximize the effectiveness of tests and manage risks."
        }
        br()
        p {
            span("highlight") { +"Test coverage" }
            +" is a measure of the degree to which the source code of a program has been tested."
        }
        p {
            +"It is usually expressed as a percentage of code that has been executed by the test suite."
            br()
            +"Different metrics are used to measure test coverage, such as "
            em { +"function coverage, statement coverage, branch coverage, etc." }
        }
        br()
        blockQuote {
            +"Remember that exhaustive testing is impossible!"
        }
    }
)

object EquivalencePartitioningSlide : Slide(
    header = "Equivalence Partitioning",
    summary = {
        +"Equivalence partitioning is a technique used to reduce the number of test cases by dividing the input data of a software unit into partitions of equivalent data from which test cases can be derived."
    },
    content = {
        img(src = "./src/img/equivalence_boundary.png")
        p {
            +"In this example, there are 4 partitions of equivalent data. "
            +"In theory, any test case from a partition should yield the same result."
        }
        p {
            +"For example these sets of values belong to the same partitions:"
            br()
            span { style = "color: red"; +"-275.0" }
            +", "
            span { style = "color: green"; +"-1.0" }
            +", "
            span { style = "color: green"; +"10.0" }
            +", "
            span { style = "color: red"; +"100.1" }
            br()
            span { style = "color: red"; +"-280.0" }
            +", "
            span { style = "color: green"; +"-100.0" }
            +", "
            span { style = "color: green"; +"99.0" }
            +", "
            span { style = "color: red"; +"101.0" }
        }
    }
)

object BoundaryValueAnalysisSlide : Slide(
    header = "Boundary Value Analysis",
    summary = {
        +"Boundary value analysis is a software testing technique similar to equivalence partitioning, "
        +"but the tests are designed to look program behavior at boundary values."
    },
    content = {
        img(src = "./src/img/equivalence_boundary.png")
        p {
            +"There are 3 boundary values in this example: "
            strong { +"-273.15" }
            +", "
            strong { +"0.0" }
            +" and "
            strong { +"100.0" }
        }
        p {
            +"Equivalence partitioning and boundary value analysis are often used together."
        }
    }
)

object DecisionTablesSlide : Slide(
    header = "Decision Tables",
    summary = {
        +"Decision table testing is a testing technique in which test cases are designed to execute the combinations of inputs and/or stimuli (causes) shown in a decision table."
    },
    content = {
        table {
            thead {
                style = "font-weight: bold; background-color: var(--kotlin-color-2)"
                tr {
                    td { +"Conditions" }
                    td { +"Test 1" }
                    td { +"Test 2" }
                    td { +"Test 3" }
                    td { +"Test 4" }
                }
            }
            tbody {
                tr {
                    td { +"User exists" }
                    td { +"YES" }
                    td { +"YES" }
                    td { +"NO" }
                    td { +"YES" }
                }
                tr {
                    td { +"Password correct" }
                    td { +"YES" }
                    td { +"NO" }
                    td { +"-" }
                    td { +"YES" }
                }
                tr {
                    td { +"User blocked" }
                    td { +"NO" }
                    td { +"NO" }
                    td { +"NO" }
                    td { +"YES" }
                }
            }
            thead {
                style = "font-weight: bold; background-color: lightgrey"
                tr {
                    td { +"Actions" }
                    td { +"Test 1" }
                    td { +"Test 2" }
                    td { +"Test 3" }
                    td { +"Test 4" }
                }
            }
            tbody {
                tr {
                    td { +"Allow access" }
                    td { +"YES" }
                    td { +"NO" }
                    td { +"NO" }
                    td { +"NO" }
                }
                tr {
                    td { +"Block user" }
                    td { +"NO" }
                    td { +"YES" }
                    td { +"-" }
                    td { +"-" }
                }
            }
        }
    }
)

object StateTransitionAnalysisSlide : Slide(
    header = "State Transition Analysis",
    summary = {
        +"State transition testing is a testing technique in which outputs are triggered by changes to the input conditions or changes to state of the system."
    },
    content = {
        img(src = "./src/img/state_transition.png")
    }
)

object OrthogonalArrayTestingSlide : Slide(
    header = "Orthogonal array testing",
    summary = {
        +"Orthogonal array testing statistical method of test design aimed to test interactions of multiple variables, "
        +"their combinations and interactions, while minimizing the number of test cases."
    },
    content = {
        p {
            h4 { +"Example:" }
            +"Assume we have a system that takes 3 parameters: color, shape and size, each parameter has 2 values."
            br()
            br()
            +"To test all possible combinations of these parameters, we would need 8 test cases."
            br()
            br()
            +"With orthogonal array testing, we can achieve the same coverage with only 4 test cases."
        }
        table {
            thead {
                tr {
                    style = "font-weight: bold"
                    td { style = "font-weight: bold"; +"" }
                    td { +"Color" }
                    td { +"Shape" }
                    td { +"Size" }
                }
            }
            tbody {
                tr {
                    td { style = "font-weight: bold"; +"Test 1" }
                    td { +"red" }
                    td { +"square" }
                    td { +"small" }
                }
                tr {
                    td { style = "font-weight: bold"; +"Test 2" }
                    td { +"red" }
                    td { +"circle" }
                    td { +"large" }
                }
                tr {
                    td { style = "font-weight: bold"; +"Test 3" }
                    td { +"green" }
                    td { +"square" }
                    td { +"large" }
                }
                tr {
                    td { style = "font-weight: bold"; +"Test 4" }
                    td { +"green" }
                    td { +"circle" }
                    td { +"small" }
                }
            }
        }
        p {
            +"This is an orthogonal array of 3 factors with 2 levels each - L4(2^3)."
        }
    }
)

object AllPairsTestingSlide : Slide(
    header = "All-Pairs Testing",
    summary = {
        +"All-pairs testing is a combinatorial software testing method that, for each pair of input parameters to a system (typically, a software algorithm), tests all possible discrete combinations of those parameters."
    },
    content = {
        blockQuote {
            +"It is based on the observation that most faults are caused by interactions of at most two factors."
        }
        p {
            +"This testing technique is rarely implemented \"by hand\", but usually with the help of specialized tools."
        }
        p {
            +"There are techniques that extend all-pairs testing to more than two factors, such as all-tuples testing, but these techniques are not widely used, because they generate very large number of test cases with insignificant added benefit."
        }
    }
)
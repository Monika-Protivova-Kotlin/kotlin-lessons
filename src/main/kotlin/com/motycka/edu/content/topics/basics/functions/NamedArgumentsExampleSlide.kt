package com.motycka.edu.content.topics.basics.functions

import com.motycka.edu.model.Slide
import com.motycka.edu.model.kotlinPlayground
import com.motycka.edu.model.twoColumns
import kotlinx.html.br
import kotlinx.html.div
import kotlinx.html.p
import kotlinx.html.strong

object NamedArgumentsExampleSlide : Slide(
    header = "Named arguments",
    content = {
        twoColumns(
            left = {
                p {
                    +"This is an example of a function with many parameters, where using named arguments at the call site can improve readability significantly."
                }
                kotlinPlayground(
                    code = """
                        fun calculateEvapotranspiration(
                            temperature: Double,
                            solarRadiation: Double,
                            humidity: Double,
                            windSpeed: Double,
                            atmosphericPressure: Double,
                            location: Pair<Double, Double>,
                            time: LocalDateTime,
                            soilType: SoilType,
                            cropType: CropType
                        ): Double {
                            // calculation
                        }
                    """,
                    executable = false
                )
            },
            right = {
                p {
                    strong { +"A)" }
                    +" Calling the function without named arguments makes it hard to understand what each argument represents ..."
                    kotlinPlayground(
                        code = """
                        val et = calculateEvapotranspiration(
                            25.0,
                            800.0,
                            0.6,
                            3.0,
                            1013.25,
                            13.7655756 to 100.5675686,
                            LocalDateTime.now(),
                            SoilType.CLAY,
                            CropType.WHEAT,
                        )
                    """,
                        executable = false
                    )
                }
                p {
                    strong { +"B)" }
                    +" Named arguments clarify the purpose of each argument ..."
                    kotlinPlayground(
                        code = """
                        val et = calculateEvapotranspiration(
                            soilType = SoilType.CLAY,
                            cropType = CropType.WHEAT,
                            time = LocalDateTime.now(),
                            location = 13.7655756 to 100.5675686,
                            temperature = 25.0,
                            solarRadiation = 800.0,
                            humidity = 0.6,
                            windSpeed = 3.0,
                            atmosphericPressure = 1013.25
                        )
                    """,
                        executable = false
                    )
                }
            },
        )
    }
)

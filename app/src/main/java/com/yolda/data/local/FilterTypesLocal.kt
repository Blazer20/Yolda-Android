package com.yolda.data.local


data class FilterTypesLocal(
    val fuel: FuelGasAndDieselLocal = FuelGasAndDieselLocal(
        name = "Fuel",
        subTypes = listOf(
            CommonSubTypesLocal(
                name = "AI 80",
                price = "8600",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "AI 91",
                price = "9200",
                selected = false
            ),
            CommonSubTypesLocal(
                name = "AI 92",
                price = "9200",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "AI 95",
                price = "10000",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "AI 100",
                price = "15000",
                selected = false
            )
        )
    ),
    val gasoline: FuelGasAndDieselLocal = FuelGasAndDieselLocal(
        name = "Gasoline",
        subTypes = listOf(
            CommonSubTypesLocal(
                name = "metan",
                price = "5000",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "propan",
                price = "6000",
                selected = true
            )
        )
    ),
    val electro: ElectroLocal = ElectroLocal(),
    val diesel: FuelGasAndDieselLocal = FuelGasAndDieselLocal(
        name = "Diesel",
        subTypes = listOf(
            CommonSubTypesLocal(
                name = "Eco",
                price = "5000",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "GTL",
                price = "6000",
                selected = true
            ),
            CommonSubTypesLocal(
                name = "K5",
                price = "6000",
                selected = true
            )
        )
    )
)

data class FuelGasAndDieselLocal(
    val name: String = "Fuel",
    val subTypes: List<CommonSubTypesLocal> = listOf(
        CommonSubTypesLocal(
            name = "AI 80",
            price = "8600",
            selected = false
        ),
        CommonSubTypesLocal(
            name = "AI 91",
            price = "9200",
            selected = false
        ),
        CommonSubTypesLocal(
            name = "AI 92",
            price = "9200",
            selected = false
        ),
        CommonSubTypesLocal(
            name = "AI 95",
            price = "10000",
            selected = false
        ),
        CommonSubTypesLocal(
            name = "AI 100",
            price = "15000",
            selected = false
        )
    )
)

data class CommonSubTypesLocal(
    val name: String,
    val price: String,
    val selected: Boolean
)

data class ElectroLocal(
    val name: String = "Electro",
    val electroSubTypes: List<ElectroSubTypesLocal> = listOf(
        ElectroSubTypesLocal(
            name = "GB/T AC",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "7kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "GB/T DC",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "32kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "60kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "80kW",
                    price = "100",
                    selected = true
                ),
                CommonSubTypesLocal(
                    name = "100kW",
                    price = "100",
                    selected = true
                ),
                CommonSubTypesLocal(
                    name = "120kW",
                    price = "100",
                    selected = true
                ),
                CommonSubTypesLocal(
                    name = "130kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "160kW",
                    price = "100",
                    selected = true
                ),
                CommonSubTypesLocal(
                    name = "200kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "240kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "CCS Combo 1",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "80kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "160kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "CCS Combo 2",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "60kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "80kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "160kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "Type 1 (J1772)",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "16kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "Type 2 (3 phase)",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "7kW",
                    price = "100",
                    selected = false
                )
            )
        ),
        ElectroSubTypesLocal(
            name = "CHAdeMO",
            electroSubTypes = listOf(
                CommonSubTypesLocal(
                    name = "40kW",
                    price = "100",
                    selected = false
                ),
                CommonSubTypesLocal(
                    name = "50kW",
                    price = "100",
                    selected = false
                )
            )
        )
    )
)

data class ElectroSubTypesLocal(
    val name: String?,
    val electroSubTypes: List<CommonSubTypesLocal>
)

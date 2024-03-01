package com.example.testteen.presentation.extension

fun String.paymentValidate(): Boolean {
    val accountNumberRegex = """[A-Z]{2}\d{2}[A-Z0-9]{13,30}"""
    val phoneNumberRegex = """\d{9}"""
    val passportNumberRegex = """\d{11}"""

    return matches(Regex(accountNumberRegex)) ||
            matches(Regex(phoneNumberRegex)) ||
            matches(Regex(passportNumberRegex))
}
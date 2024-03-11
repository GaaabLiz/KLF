@file:Suppress("unused")

package io.github.gaaabliz.kliz.common.util

import java.util.regex.Pattern

/**
 * Gestione dei regex dei campi d'input dell'app.
 * @author Loris Farrauto
 */
sealed class RegexUtils {

    companion object {

        fun isValidEmail(email: String): Boolean {
            return email.matches(
                Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$")
            )
        }

        fun isValidPassword(password: String): Boolean {
            return password.matches(
                Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
            )
        }

        fun isValidPhoneNumber(phoneNumber: String): Boolean {
            return phoneNumber.matches(
                Regex("^[0-9]{10}$")
            )
        }

        fun isValidName(name: String): Boolean {
            return name.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidAddress(address: String): Boolean {
            return address.matches(
                Regex("^[a-zA-Z0-9 ]{2,}$")
            )
        }

        fun isValidCity(city: String): Boolean {
            return city.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidState(state: String): Boolean {
            return state.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidZip(zip: String): Boolean {
            return zip.matches(
                Regex("^[0-9]{5}$")
            )
        }

        fun isValidCountry(country: String): Boolean {
            return country.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidCardNumber(cardNumber: String): Boolean {
            return cardNumber.matches(
                Regex("^[0-9]{16}$")
            )
        }

        fun isValidCardExpiry(cardExpiry: String): Boolean {
            return cardExpiry.matches(
                Regex("^[0-9]{4}$")
            )
        }

        fun isValidCardCVV(cardCVV: String): Boolean {
            return cardCVV.matches(
                Regex("^[0-9]{3}$")
            )
        }

        fun isValidCardHolderName(cardHolderName: String): Boolean {
            return cardHolderName.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidCardType(cardType: String): Boolean {
            return cardType.matches(
                Regex("^[a-zA-Z ]{2,}$")
            )
        }

        fun isValidCardExpiryMonth(cardExpiryMonth: String): Boolean {
            return cardExpiryMonth.matches(
                Regex("^[0-9]{2}$")
            )
        }

        fun isValidCardExpiryYear(cardExpiryYear: String): Boolean {
            return cardExpiryYear.matches(
                Regex("^[0-9]{4}$")
            )
        }

        fun isValidDate(date: String): Boolean {
            return date.matches(
                Regex("^[0-9]{2}/[0-9]{2}/[0-9]{4}$")
            )
        }

        private val DATE_PATTERN: Pattern = Pattern.compile(
            "^((2000|2400|2800|(19|2[0-9])(0[48]|[2468][048]|[13579][26]))-02-29)$"
                    + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
                    + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$"
        )

    }

    interface Matcher {
        fun isValid(value: String): Boolean
    }

    class DateMatcher() : Matcher {
        override fun isValid(value: String): Boolean {
            return DATE_PATTERN.matcher(value).matches()
        }
    }
}

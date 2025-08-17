package com.github.lerocha.mcpserver

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class MortgageServiceTest {
    private lateinit var mortgageService: MortgageService

    @BeforeEach
    fun setUp() {
        mortgageService = MortgageService()
    }

    @Test
    fun `should calculate mortgage payment for standard 30-year loan`() {
        val loanAmount = 300000.toBigDecimal()
        val interestRate = 6.0.toBigDecimal()
        val months = 360

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(1798.65.toBigDecimal(), result)
    }

    @Test
    fun `should calculate mortgage payment for 15-year loan`() {
        val loanAmount = 250000.toBigDecimal()
        val interestRate = 5.5.toBigDecimal()
        val months = 180

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(2042.71.toBigDecimal(), result)
    }

    @Test
    fun `should calculate mortgage payment for zero interest rate`() {
        val loanAmount = 120000.toBigDecimal()
        val interestRate = BigDecimal.ZERO
        val months = 240

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(BigDecimal("500.00"), result)
    }

    @Test
    fun `should calculate mortgage payment for high interest rate`() {
        val loanAmount = 100000.toBigDecimal()
        val interestRate = 15.0.toBigDecimal()
        val months = 360

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(1264.44.toBigDecimal(), result)
    }

    @Test
    fun `should calculate mortgage payment for short term loan`() {
        val loanAmount = 50000.toBigDecimal()
        val interestRate = 4.5.toBigDecimal()
        val months = 60

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(932.15.toBigDecimal(), result)
    }

    @Test
    fun `should calculate mortgage payment for small loan amount`() {
        val loanAmount = 10000.toBigDecimal()
        val interestRate = 3.5.toBigDecimal()
        val months = 120

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(98.89.toBigDecimal(), result)
    }

    @Test
    fun `should throw exception for negative loan amount`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                mortgageService.calculateMortgagePayment((-100000).toBigDecimal(), 5.0.toBigDecimal(), 360)
            }
        assertEquals("Loan amount must be positive, interest rate must be non-negative, and months must be positive", exception.message)
    }

    @Test
    fun `should throw exception for zero loan amount`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                mortgageService.calculateMortgagePayment(BigDecimal.ZERO, 5.0.toBigDecimal(), 360)
            }
        assertEquals("Loan amount must be positive, interest rate must be non-negative, and months must be positive", exception.message)
    }

    @Test
    fun `should throw exception for negative interest rate`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                mortgageService.calculateMortgagePayment(100000.toBigDecimal(), (-1.0).toBigDecimal(), 360)
            }
        assertEquals("Loan amount must be positive, interest rate must be non-negative, and months must be positive", exception.message)
    }

    @Test
    fun `should throw exception for zero months`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                mortgageService.calculateMortgagePayment(100000.toBigDecimal(), 5.0.toBigDecimal(), 0)
            }
        assertEquals("Loan amount must be positive, interest rate must be non-negative, and months must be positive", exception.message)
    }

    @Test
    fun `should throw exception for negative months`() {
        val exception =
            assertThrows(IllegalArgumentException::class.java) {
                mortgageService.calculateMortgagePayment(100000.toBigDecimal(), 5.0.toBigDecimal(), -12)
            }
        assertEquals("Loan amount must be positive, interest rate must be non-negative, and months must be positive", exception.message)
    }

    @Test
    fun `should calculate mortgage payment with decimal interest rate`() {
        val loanAmount = 200000.toBigDecimal()
        val interestRate = 4.25.toBigDecimal()
        val months = 300

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(1083.48.toBigDecimal(), result)
    }

    @Test
    fun `should calculate mortgage payment for very low interest rate`() {
        val loanAmount = 150000.toBigDecimal()
        val interestRate = 0.5.toBigDecimal()
        val months = 360

        val result = mortgageService.calculateMortgagePayment(loanAmount, interestRate, months)

        assertEquals(448.78.toBigDecimal(), result)
    }
}

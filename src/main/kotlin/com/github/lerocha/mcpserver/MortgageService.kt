package com.github.lerocha.mcpserver

import org.springframework.ai.tool.annotation.Tool
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class MortgageService {
    @Tool(description = "Calculate the mortgage payment for a given loan amount, interest rate and number of months")
    fun calculateMortgagePayment(
        loanAmount: BigDecimal,
        interestRate: BigDecimal,
        months: Int,
    ): BigDecimal {
        if (loanAmount <= BigDecimal.ZERO || interestRate < BigDecimal.ZERO || months <= 0) {
            throw IllegalArgumentException("Loan amount must be positive, interest rate must be non-negative, and months must be positive")
        }

        if (interestRate == BigDecimal.ZERO) {
            return loanAmount.divide(BigDecimal(months), 2, java.math.RoundingMode.HALF_UP)
        }

        val monthlyRate = interestRate.divide(BigDecimal(1200), 10, java.math.RoundingMode.HALF_UP)
        val onePlusRate = BigDecimal.ONE.add(monthlyRate)
        val numerator = monthlyRate.multiply(onePlusRate.pow(months))
        val denominator = onePlusRate.pow(months).subtract(BigDecimal.ONE)

        return loanAmount.multiply(numerator.divide(denominator, 10, java.math.RoundingMode.HALF_UP))
            .setScale(2, java.math.RoundingMode.HALF_UP)
    }
}

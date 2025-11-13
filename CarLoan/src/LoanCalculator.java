class LoanCalculator {

    // Simple Interest = (P × R × T) / 100
    public static double calculateSimpleInterest(double p, double r, int t) {
        return (p * r * t) / 100;
    }

    // Compound Interest = P × (1 + R/100)^T − P
    public static double calculateCompoundInterest(double p, double r, int t) {
        return p * (Math.pow((1 + r / 100), t)) - p;
    }

    // EMI = [P × R × (1+R)^N] / [(1+R)^N − 1]
    public static double calculateEMI(double p, double annualRate, int t) {
        double mR = annualRate / (12 * 100);
        int months = t * 12;
        return (p * mR * Math.pow(1 + mR, months)) /
               (Math.pow(1 + mR, months) - 1);
    }
}

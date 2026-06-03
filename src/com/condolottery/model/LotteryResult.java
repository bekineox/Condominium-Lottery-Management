package com.condolottery.model;

/**
 * Represents the result of a lottery draw for a specific registration.
 * Stores whether the applicant won or lost.
 * Demonstrates: encapsulation with private fields and public getters/setters.
 */
public class LotteryResult {

    // Private fields — encapsulation
    private String resultId;
    private String registrationId;
    private String applicantId;
    private String unitId;
    private String drawDate;
    private boolean winner;

    /**
     * Constructs a LotteryResult with all required details.
     * @param resultId unique result ID
     * @param registrationId ID of the registration
     * @param applicantId ID of the applicant
     * @param unitId ID of the unit
     * @param drawDate date of the lottery draw (yyyy-MM-dd)
     * @param winner true if this applicant won
     */
    public LotteryResult(String resultId, String registrationId, String applicantId,
                         String unitId, String drawDate, boolean winner) {
        this.resultId = resultId;
        this.registrationId = registrationId;
        this.applicantId = applicantId;
        this.unitId = unitId;
        this.drawDate = drawDate;
        this.winner = winner;
    }

    // ==================== Getters and Setters ====================

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getDrawDate() {
        return drawDate;
    }

    public void setDrawDate(String drawDate) {
        this.drawDate = drawDate;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    // ==================== Display ====================

    /**
     * Returns a formatted string with the lottery result details.
     * @return formatted info string
     */
    public String displayInfo() {
        return String.format(
            "╔══════════════════════════════════════════╗%n" +
            "║          LOTTERY RESULT                  ║%n" +
            "╠══════════════════════════════════════════╣%n" +
            "║ Result ID:      %-24s║%n" +
            "║ Registration ID:%-24s║%n" +
            "║ Applicant ID:   %-24s║%n" +
            "║ Unit ID:        %-24s║%n" +
            "║ Draw Date:      %-24s║%n" +
            "║ Status:         %-24s║%n" +
            "╚══════════════════════════════════════════╝",
            resultId, registrationId, applicantId, unitId, drawDate,
            (winner ? "*** WINNER ***" : "Did Not Win")
        );
    }
}

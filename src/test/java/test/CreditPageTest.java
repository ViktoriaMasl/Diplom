package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.DataHelper;
import data.SQL_bd;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import page.StartPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditPageTest {
    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void openHost() {
        String url = System.getProperty("sut.url");
        open(url);
    }

    @AfterEach
    public void clearBD() {
        SQL_bd.clearDB();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldBuyWithApprovedCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getApprovedCard());
        creditPage.getStatusOk();
        assertEquals("APPROVED", SQL_bd.getCreditRequestStatus());
    }

    @Test
    void  shouldBuyWithDeclinedCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getDeclinedCard());
        creditPage.getStatusDenied();
        assertEquals("DECLINED", SQL_bd.getCreditRequestStatus());
    }

    @Test
    void shouldBuyWithNonexistentCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getNonexistentCard());
        creditPage.getStatusDenied();
        assertEquals("0", SQL_bd.getOrderCount());
    }

    @Test
    void shouldBuyWithIncompleteCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncompleteNumberCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithMonthZeroCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getMonthZeroCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredMonthCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getExpiredMonthCard());
        creditPage.getStatusInvalidField();
        assertEquals("Истёк срок действия карты", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredYearCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getExpiredYearCard());
        creditPage.getStatusInvalidField();
        assertEquals("Истёк срок действия карты", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectMonthCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectMonthCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectYearCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectYearCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectNameCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectNameCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectCVCCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getIncorrectCVCCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithEmptyFieldCard() {
        val startPage = new StartPage();
        val creditPage = startPage.openCreditPage();
        creditPage.inputField(DataHelper.getEmptyCard());
        creditPage.getStatusInvalidField();
        assertEquals("Неверный формат", creditPage.getStatusInvalidField());
    }
}

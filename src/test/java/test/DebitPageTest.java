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

public class DebitPageTest {
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
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getApprovedCard());
        debitPage.getStatusOk();
        assertEquals("APPROVED", SQL_bd.getPaymentStatus());
    }

    @Test
    void  shouldBuyWithDeclinedCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getDeclinedCard());
        debitPage.getStatusDenied();
        assertEquals("DECLINED", SQL_bd.getPaymentStatus());
    }

    @Test
    void shouldBuyWithNonexistentCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getNonexistentCard());
        debitPage.getStatusDenied();
        assertEquals("0", SQL_bd.getOrderCount());
    }

    @Test
    void shouldBuyWithIncompleteCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncompleteNumberCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithMonthZeroCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getMonthZeroCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithNonexistentMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getNonexistentMonthCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверно указан срок действия карты", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getExpiredMonthCard());
        debitPage.getStatusInvalidField();
        assertEquals("Истёк срок действия карты", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithExpiredYearCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getExpiredYearCard());
        debitPage.getStatusInvalidField();
        assertEquals("Истёк срок действия карты", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectMonthCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectMonthCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectYearCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectYearCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectNameCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectNameCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithIncorrectCVCCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getIncorrectCVCCard());
        debitPage.getStatusInvalidField();
        assertEquals("Неверный формат", debitPage.getStatusInvalidField());
    }

    @Test
    void shouldBuyWithEmptyFieldCard() {
        val startPage = new StartPage();
        val debitPage = startPage.openDebitPage();
        debitPage.inputField(DataHelper.getEmptyCard());
        debitPage.getStatusInvalidField();
        assertEquals("Поле обязательно для заполнения", debitPage.getStatusInvalidField());
    }

}

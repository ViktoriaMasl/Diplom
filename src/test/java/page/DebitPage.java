package page;

import com.codeborne.selenide.SelenideElement;
import data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitPage {
    private SelenideElement heading = $$("h3").find(exactText("Оплата по карте"));
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardMonth = $(byText("Месяц")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardYear = $(byText("Год")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardOwner = $(byText("Владелец")).parent().$("[class=\"input__control\"]");
    private SelenideElement cardCode = $(byText("CVC/CVV")).parent().$("[class=\"input__control\"]");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement operationApproved = $(".notification_status_ok");
    private SelenideElement operationDenied = $(".notification_status_error");
    private SelenideElement invalidField = $(".input__sub");

    public DebitPage() {
        heading.shouldBe(visible);
    }

    public void inputField(Card card) {
        cardNumber.setValue(card.getNumber());
        cardMonth.setValue(card.getMonth());
        cardYear.setValue(card.getYear());
        cardOwner.setValue(card.getName());
        cardCode.setValue(card.getCvc());
        continueButton.click();
    }

    public void getStatusOk() {
        operationApproved.shouldBe(visible, Duration.ofMillis(15000));
    }

    public void getStatusDenied() {
        operationDenied.shouldBe(visible, Duration.ofMillis(15000));
    }

    public String getStatusInvalidField() {
        return invalidField.getText();
    }
}

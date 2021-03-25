package page;

import com.codeborne.selenide.SelenideElement;
import data.Card;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPage {
    private SelenideElement heading = $$("h3").find(exactText("Кредит по данным карты"));
    private SelenideElement cardNumber = $(byText("Номер карты")).parent().$("[input__control]");
    private SelenideElement cardMonth = $(byText("Месяц")).parent().$("[input__control]");
    private SelenideElement cardYear = $(byText("Год")).parent().$("[input__control]");
    private SelenideElement cardOwner = $(byText("Владелец")).parent().$("[input__control]");
    private SelenideElement cardCode = $(byText("CVC/CVV")).parent().$("[input__control]");
    private SelenideElement continueButton = $$("button").find(exactText("Продолжить"));
    private SelenideElement operationApproved = $(".notification_status_ok");
    private SelenideElement operationDenied = $(".notification_status_error");
    private SelenideElement invalidField = $(".input__sub");

    public CreditPage() {
        heading.shouldBe(visible);
    }

    public void inputField(Card info) {
        cardNumber.setValue(cardNumber.getValue());
        cardMonth.setValue(cardMonth.getValue());
        cardYear.setValue(cardYear.getValue());
        cardOwner.setValue(cardOwner.getValue());
        cardCode.setValue(cardCode.getValue());
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

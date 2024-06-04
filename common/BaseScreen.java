package vn.vietinbank.mobile.common;

import com.google.common.collect.ImmutableList;
import io.appium.java_client.AppiumBy.ByAndroidUIAutomator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.fail;
import static vn.vietinbank.utils.Constants.APPIUM_TIMEOUT;

public class BaseScreen {

    private static final Logger logger = LogManager.getLogger(BaseScreen.class);
    private static final Duration SCROLL_DUR = Duration.ofMillis(1000);
    private static final double SCROLL_RATIO = 0.8;
    private static final int ANDROID_SCROLL_DIVISOR = 3;
    private static final int defaultTimeOut = 5;
    protected AppiumDriver appiumDriver;

    public BaseScreen(AppiumDriver appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(APPIUM_TIMEOUT)), this);
    }

    public void delay(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public WebElement findElement(String locator) {
        WebElement element = null;
        logger.info("Finding mobile element {}", locator);
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(defaultTimeOut));
            element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            logger.info("Found 1 mobile element {}", locator);
        } catch (Exception e) {
            logger.error("Cannot find mobile element {}. Root cause: {}", locator, e.getMessage());
        }
        return element;
    }

    public List<WebElement> findElements(String locator) {
        List<WebElement> elements = List.of();
        logger.info("Finding mobile elements located by {}", locator);
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(defaultTimeOut));
            elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(locator)));
            logger.info("Found {} mobile element(s) located by {}", elements.size(), locator);
        } catch (Exception e) {
            logger.error("Cannot find mobile elements located by {}. Root cause: {}", locator,
                    e.getMessage());
        }
        return elements;
    }

    public void click(String locator) {
        try {
            logger.info("Click on mobile element located by {}", locator);
            WebElement we = findElement(locator);
            we.click();
            logger.info("Clicked on mobile element located by {} successfully", locator);
        } catch (Exception e) {
            logger.error("Cannot click on mobile element located by ''{}''. Root cause: {}",
                    locator, e.getMessage());
        }
    }

    public void click(WebElement we) {
        try {
            logger.info("Clicking on mobile element {}", we);
            we.click();
            logger.info("Clicked on mobile element {} successfully", we);
        } catch (Exception e) {
            logger.error("Cannot click on mobile element ''{}''. Root cause: {}",
                    we, e.getMessage());
            fail(String.format("Cannot click on mobile element ''%s''. Root cause: %s",
                    we, e.getMessage()));
        }
    }

    public void doubleClick(WebElement we) {
        try {
            logger.info("Double Clicking on mobile element {}", we);
            we.click();
            we.click();
            logger.info("Double Clicked on mobile element {} successfully", we);
        } catch (Exception e) {
            logger.error("Cannot double click on mobile element ''{}''. Root cause: {}",
                    we, e.getMessage());
            fail(String.format("Cannot double click on mobile element ''%s''. Root cause: %s",
                    we, e.getMessage()));
        }
    }

    public void tap(WebElement we) {
        try {
            Rectangle elRect = we.getRect();
            Point point = new Point(
                    elRect.x + (int) (elRect.getWidth() / 2.0),
                    elRect.y + (int) (elRect.getHeight() / 2.0)
            );
            tapAtPoint(point);
            logger.info("Tapped on mobile element {} successfully", we);
        } catch (Exception e) {
            logger.error("Cannot tap on mobile element ''{}''. Root cause: {}",
                    we, e.getMessage());
        }
    }

    public void tapAtPoint(Point point) {
        PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
        Sequence tap = new Sequence(input, 0);
        tap.addAction(input.createPointerMove(Duration.ZERO, Origin.viewport(), point.x, point.y));
        tap.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));
        tap.addAction(new Pause(input, Duration.ofMillis(200)));
        tap.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));
        appiumDriver.perform(ImmutableList.of(tap));
    }

    public void sendKeys(String locator, String text) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.sendKeys(text);
                logger.info("Enter text ''{}'' to mobile element located by ''{}''", text, locator);
            } catch (Exception e) {
                logger.error("Cannot enter text {} into mobile element located by {}. Root cause: {}", text,
                        locator,
                        e.getMessage());
            }
        }
    }

    public void sendKeys(WebElement we, String text) {
        if (we != null) {
            try {
                we.sendKeys(text);
                logger.info("Enter text ''{}'' to mobile element ''{}''", text, we);
            } catch (Exception e) {
                logger.error("Cannot enter text {} into mobile element {}. Root cause: {}", text, we,
                        e.getMessage());
                fail(String.format("Cannot enter text ''%s'' into mobile element %s. Root cause: %s", text,
                        we,
                        e.getMessage()));
            }
        }
    }

    public void clearText(String locator) {
        WebElement element = findElement(locator);
        if (element != null) {
            try {
                element.clear();
                logger.info("Clear text to mobile element located by ''{}''", locator);
            } catch (Exception e) {
                logger.error("Cannot clear text into mobile element located by {}. Root cause: {}", locator,
                        e.getMessage());
            }
        }
    }

    public void clearText(WebElement we) {
        if (we != null) {
            try {
                we.clear();
                logger.info("Clear text to mobile element ''{}''", we);
            } catch (Exception e) {
                logger.error("Cannot clear text into mobile element {}. Root cause: {}", we,
                        e.getMessage());
                fail(String.format("Cannot clear text into mobile element %s. Root cause: %s", we,
                        e.getMessage()));
            }
        }
    }

    public void swipe(Point start, Point end, Duration duration) {
        boolean isAndroid = appiumDriver instanceof AndroidDriver;

        PointerInput input = new PointerInput(Kind.TOUCH, "finger1");
        Sequence swipe = new Sequence(input, 0);
        swipe.addAction(input.createPointerMove(Duration.ZERO, Origin.viewport(), start.x, start.y));
        swipe.addAction(input.createPointerDown(MouseButton.LEFT.asArg()));
        if (isAndroid) {
            duration = duration.dividedBy(ANDROID_SCROLL_DIVISOR);
        } else {
            swipe.addAction(new Pause(input, duration));
            duration = Duration.ZERO;
        }
        swipe.addAction(input.createPointerMove(duration, Origin.viewport(), end.x, end.y));
        swipe.addAction(input.createPointerUp(MouseButton.LEFT.asArg()));
        (appiumDriver).perform(ImmutableList.of(swipe));
    }

    public void scrollToElement(String locator, ScrollDirection scrollDirection, int numberOfTimes) {
        boolean found = false;
        WebElement we;
        Dimension size = appiumDriver.manage().window().getSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) (size.height * 0.5);
        int bottom = midPoint.y + (int) (size.height * 0.5);
        int count = 0;
        do {
            delay(1000);
            if (scrollDirection == ScrollDirection.DOWN) {
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
            } else if (scrollDirection == ScrollDirection.UP) {
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
            }
            By by = By.xpath(locator);
            try {
                we = appiumDriver.findElement(by);
                if (we != null) {
                    found = true;
                    logger.info("Scrolled to mobile element located by {} successfully", locator);
                    break;
                }
            } catch (NoSuchElementException ignored) {

            }
            count++;
        } while (count == numberOfTimes);
        if (!found) {
            logger.error(
                    "Cannot scroll to mobile element located by {}. Root cause: the web element not fount",
                    locator);
        }
    }

    public void scrollToElement(WebElement we, ScrollDirection scrollDirection, int numberOfTimes) {
        boolean found = false;
        Dimension size = appiumDriver.manage().window().getSize();
        Point midPoint = new Point((int) (size.width * 0.5), (int) (size.height * 0.5));
        int top = midPoint.y - (int) (size.height * 0.5);
        int bottom = midPoint.y + (int) (size.height * 0.5);
        int count = 0;
        do {
            delay(1000);
            if (scrollDirection == ScrollDirection.DOWN) {
                swipe(new Point(midPoint.x, bottom), new Point(midPoint.x, top), SCROLL_DUR);
            } else if (scrollDirection == ScrollDirection.UP) {
                swipe(new Point(midPoint.x, top), new Point(midPoint.x, bottom), SCROLL_DUR);
            }
            try {
                if (we.isDisplayed()) {
                    found = true;
                    logger.info("Scrolled to mobile element {} successfully", we);
                    break;
                }
            } catch (NoSuchElementException ignored) {

            }
            count++;
        } while (count == numberOfTimes);
        if (!found) {
            logger.error(
                    "Cannot scroll to mobile element {}. Root cause: the web element not visible",
                    we);
        }
    }

    public void scrollTo(String text) {
        try {
            delay(500);
            WebElement element = null;
            if (appiumDriver instanceof AndroidDriver) {
                String uiScrollable = String.format("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(%s))", text);
                element = appiumDriver.findElement(new ByAndroidUIAutomator(uiScrollable));
            } else if (appiumDriver instanceof IOSDriver) {
                List<WebElement> elements =
                        appiumDriver.findElements(By.xpath(
                                "//*[contains(@label, '" + text + "') or contains(@text, '" + text
                                        + "') or contains(@name, '" + text + "')]"));
                logger.info("Elements found: {}", elements.size());
                if (!elements.isEmpty()) {
                    logger.info("Text ''{}'' was found in {} element(s).", text, elements.size());
                    RemoteWebElement remoteElement = (RemoteWebElement) elements.get(0);
                    String parentID = remoteElement.getId();
                    HashMap<String, String> scrollObject = new HashMap<>();
                    scrollObject.put("element", parentID);
                    scrollObject.put("toVisible", text);
                    appiumDriver.executeScript("mobile:scroll", scrollObject);
                    element = remoteElement;
                }
            }
            if (element != null) {
                logger.info("Scrolled to {} successfully", text);
            } else {
                logger.error("Text {} is not found", text);
            }
        } catch (Exception e) {
            logger.error("Cannot scroll to text {}. Root cause: {}", text, e.getMessage());
        }
    }

    public byte[] takeScreenShot() {
        byte[] image = null;
        try {
            BufferedImage bufferedImage = ImageIO.read(
                    new ByteArrayInputStream(((TakesScreenshot) appiumDriver).getScreenshotAs(
                            OutputType.BYTES)));
            try {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, "png", bos);
                image = bos.toByteArray();
            } catch (Exception e) {
                logger.error("Cannot mark element on image. Root cause: {}", e.getMessage());
            }
        } catch (Exception e) {
            logger.error("Cannot take screen shot. Root cause: {}", e.getMessage());
        }
        return image;
    }

    public boolean verifyElementText(String locator, String text) {
        WebElement element = findElement(locator);
        try {
            logger.info("Verify text {} into element located by {}", text, locator);
            String actualText = element.getText();
            if (actualText.equals(text)) {
                logger.info("Text of element located by {} is {}", locator, text);
                return true;
            }
        } catch (Exception e) {
            logger.error("Cannot verify text of element located by {}. Root cause: {}", locator,
                    e.getMessage());
        }
        return false;
    }

    public String getElementText(String locator) {
        WebElement element = findElement(locator);
        logger.info("Get text into element located by {}", locator);
        return element.getText();
    }

    public String getElementText(WebElement element) {
        logger.info("Get text into element located by {}", element);
        return element.getText();
    }

    public boolean waitForElementVisible(String locator, int timeOut) {
        try {
            logger.info("Waiting for mobile element {} to be visible", locator);
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeOut));
            WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            if (we != null) {
                logger.info("Mobile element located by {} is visible", locator);
                return true;
            }
        } catch (Exception e) {
            logger.error(
                    "Cannot wait for mobile element located by {} to be visible within {} second(s). Root cause: {}",
                    locator, timeOut, e.getMessage());
        }
        return false;
    }

    public boolean waitForElementNotVisible(String locator, int timeOut) {
        try {
            logger.info("Waiting for mobile element {} to be visible", locator);
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeOut));
            WebElement we = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            if (we == null) {
                logger.info("Mobile element located by {} is not visible", locator);
                return true;
            }
        } catch (Exception e) {
            logger.error(
                    "Cannot wait for mobile element located by {} to be not visible within {} second(s). Root cause: {}",
                    locator, timeOut, e.getMessage());
        }
        return false;
    }

    public boolean waitForElementVisible(WebElement we, int timeOut) {
        try {
            logger.info("Waiting for element {} to be visible", we);
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeOut));
            WebElement foundWe = wait.until(ExpectedConditions.visibilityOf(we));
            if (foundWe != null) {
                logger.info("Element {} is visible", we);
                return true;
            }
        } catch (Exception e) {
            logger.error("Cannot wait for element {} to be visible within {} second(s). Root cause: {}",
                    we, timeOut, e.getMessage());
        }
        return false;
    }

    public boolean waitForElementNotVisible(WebElement we, int timeOut) {
        try {
            logger.info("Waiting for element {} to be not visible", we);
            WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(timeOut));
            WebElement foundWe = wait.until(ExpectedConditions.visibilityOf(we));
            if (foundWe == null) {
                logger.info("Element {} is not visible", we);
                return true;
            }
        } catch (Exception e) {
            logger.error("Cannot wait for element {} to be not visible within {} second(s). Root cause: {}",
                    we, timeOut, e.getMessage());
        }
        return false;
    }
}

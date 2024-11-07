import org.junit.Assert;
import org.junit.Test;

import java.util.regex.PatternSyntaxException;


public class RegexUtilTest {

    /**
     * Тестирует, что метод возвращает true, когда текст соответствует регулярному выражению.
     */
    @Test
    public void testMatchesTrue() {
        Assert.assertTrue(RegexUtil.matches("\\d+", "12345"));
    }

    /**
     * Тестирует, что метод возвращает false, когда текст не соответствует регулярному выражению.
     */
    @Test
    public void testMatchesFalse() {
        Assert.assertFalse(RegexUtil.matches("\\d+", "12345a"));
    }

    /**
     * Тестирует, что метод выбрасывает NullPointerException, если regex равен null.
     */
    @Test(expected = NullPointerException.class)
    public void testRegexNull() {
        RegexUtil.matches(null, "test");
    }

    /**
     * Тестирует, что метод выбрасывает NullPointerException, если text равен null.
     */
    @Test(expected = NullPointerException.class)
    public void testTextNull() {
        RegexUtil.matches("\\d+", null);
    }

    /**
     * Тестирует, что метод выбрасывает PatternSyntaxException, если regex имеет неверный синтаксис.
     */
    @Test(expected = PatternSyntaxException.class)
    public void testInvalidRegex() {
        RegexUtil.matches("[a-z", "test");
    }

    /**
     * Тестирует, что кэширование шаблонов работает корректно.
     */
    @Test
    public void testCacheFunctionality() {
        String regex = "\\d+";
        String text = "12345";
        // Первый вызов должен скомпилировать и кэшировать шаблон
        Assert.assertTrue(RegexUtil.matches(regex, text));
        // Второй вызов должен использовать кэшированный шаблон
        Assert.assertTrue(RegexUtil.matches(regex, text));
    }

    /**
     * Тестирует, что метод корректно обрабатывает пустую строку в качестве текста.
     */
    @Test
    public void testEmptyText() {
        Assert.assertFalse(RegexUtil.matches("\\d+", ""));
    }

    /**
     * Тестирует, что метод корректно обрабатывает пустое регулярное выражение.
     */
    @Test
    public void testEmptyRegex() {
        // Пустое регулярное выражение должно соответствовать только пустой строке
        Assert.assertTrue(RegexUtil.matches("", ""));
        Assert.assertFalse(RegexUtil.matches("", "non-empty"));
    }

    /**
     * Тестирует, что метод корректно обрабатывает специальные символы в регулярном выражении.
     */
    @Test
    public void testSpecialCharacters() {
        Assert.assertTrue(RegexUtil.matches("\\w+\\s\\w+", "Hello World"));
        Assert.assertFalse(RegexUtil.matches("\\w+\\s\\w+", "HelloWorld"));
    }

    /**
     * Тестирует, что метод корректно работает с юникодными символами.
     */
    @Test
    public void testUnicodeCharacters() {
        Assert.assertTrue(RegexUtil.matches("\\p{L}+", "Привет"));
        Assert.assertFalse(RegexUtil.matches("\\p{L}+", "123"));
    }
}
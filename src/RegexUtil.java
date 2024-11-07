import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * <p><strong>Проблемы и решения:</strong></p>
 * <ul>
 *     <li>
 *         <strong>Неэффективная компиляция регулярного выражения:</strong>
 *         Для повышения производительности используется кэширование скомпилированных шаблонов.
 *     </li>
 *     <li>
 *         <strong>Использование класса-обертки `Boolean`:</strong>
 *         Заменено на примитивный тип `boolean` для избежания автоупаковки и распаковки.
 *     </li>
 *     <li>
 *         <strong>Отсутствие обработки исключений:</strong>
 *         Добавлены проверки на `null` и документированы возможные исключения.
 *     </li>
 *     <li>
 *         <strong>Отсутствие документации:</strong>
 *         Добавлены JavaDoc-комментарии для лучшего понимания кода.
 *     </li>
 * </ul>
 */
public class RegexUtil {
    /**
     * Кэш скомпилированных шаблонов регулярных выражений.
     */
    private static final ConcurrentHashMap<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();

    /**
     * Проверяет, соответствует ли текст заданному регулярному выражению.
     *
     * @param regex регулярное выражение для проверки
     * @param text  текст, который необходимо проверить
     * @return {@code true}, если текст соответствует регулярному выражению; {@code false} в противном случае
     * @throws NullPointerException   если {@code regex} или {@code text} равны {@code null}
     * @throws PatternSyntaxException если синтаксис регулярного выражения неверен
     */
    public static boolean matches(String regex, String text) {
        if (regex == null) {
            throw new NullPointerException("Параметр 'regex' не должен быть null.");
        }
        if (text == null) {
            throw new NullPointerException("Параметр 'text' не должен быть null.");
        }

        // Получаем скомпилированный шаблон из кэша или компилируем новый
        Pattern pattern = PATTERN_CACHE.computeIfAbsent(regex, Pattern::compile);
        return pattern.matcher(text).matches();
    }
}


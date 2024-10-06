package org.migrate1337.farmingenchantments.utils;

import net.md_5.bungee.api.ChatColor; // Импортируем Bungee ChatColor
import org.bukkit.event.Listener;

public class ColorUtils implements Listener {

    // Метод для обработки строк с кодами цвета в формате & и &#
    public static String parseMessage(String message) {
        StringBuilder result = new StringBuilder();
        String[] parts = message.split("(?=&#)|(?=&)"); // Разделяем по началу цветового кода

        for (String part : parts) {
            if (part.startsWith("&#")) {
                if (part.length() < 8) {
                    result.append(part); // Если код некорректен, добавляем его без изменений
                    continue;
                }
                String colorCode = part.substring(2, 8); // Извлекаем цветовой код (6 символов)
                String text = part.length() > 8 ? part.substring(8) : ""; // Остальная часть текста

                // Создаем цвет
                ChatColor chatColor = ChatColor.of("#" + colorCode);
                result.append(chatColor).append(text); // Добавляем цвет и текст
            } else if (part.startsWith("&")) {
                result.append(ChatColor.translateAlternateColorCodes('&', part)); // Обрабатываем коды формата &
            } else {
                result.append(part); // Добавляем часть, которая не содержит цветовой код
            }
        }

        return result.toString();
    }
}

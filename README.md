![image](https://github.com/Fics3/just-ai-test/assets/114524392/3da9088e-8a23-458c-abcc-4aa3750824d2)# README

## Описание проекта

Это приложение представляет собой VK-бота, который обрабатывает непрочитанные сообщения и дает эхо ответ. Приложение
использует Spring Boot и WebClient для взаимодействия с API VK.

## Требования

Для запуска приложения необходимы следующие зависимости:

- Java 17 или выше
- Maven 3.6.0 или выше

## Конфигурация

Приложение требует конфигурации следующих параметров, которые можно задать в файле `application.yml`:

```yaml
app:
  scheduler:
    delay: 5s

vk-client:
  vk-api-key: ${VK_API_KEY}
  vk-api-version: ${VK_API_VERSION}
  vk-api-url: "https://api.vk.com"
  vk-unread-message-url: "/method/messages.getConversations?filter=unread&count=%d&access_token=%s&v=%s"
  vk-send-message-url: "/method/messages.send?access_token=%s&peer_id=%d&random_id=%d&message=%s&v=%s"
  message-count: 30

spring:
  application:
    name: bot
```

## Описание параметров

* app.scheduler.delay: Задержка между запусками задачи обработки сообщений.
* vk-client.vk-api-key: API ключ для доступа к VK API.
* vk-client.vk-api-version: Версия VK API.
* vk-client.vk-api-url: Базовый URL для VK API.
* vk-client.vk-unread-message-url: URL для получения непрочитанных сообщений.
* vk-client.vk-send-message-url: URL для отправки сообщений.
* vk-client.message-count: Количество сообщений для обработки за один запрос.
* spring.application.name: Название приложения.

## Запуск приложения

### Шаг 1: Установка зависимостей

Убедитесь, что у вас установлены необходимые зависимости:

    Java 17 или выше
    Maven 3.6.0 или выше

### Шаг 2: Сборка проекта

Выполните команду для сборки проекта:

```bash
mvn clean install
```

### Шаг 3: Запуск приложения

Запустите приложение с помощью Maven:

```bash
mvn spring-boot:run
```

Или запустите скомпилированный JAR-файл:

```bash
java -jar target/bot.jar
```

### Шаг 4: Настройка переменных окружения

Перед запуском убедитесь, что заданы следующие переменные окружения:

```bash
export VK_API_KEY=ваш_ключ_API
export VK_API_VERSION=ваша_версия_API
```

Эти переменные будут использованы для замены параметров ${VK_API_KEY} и ${VK_API_VERSION} в файле конфигурации
application.yml.

Тестирование

Для запуска тестов используйте следующую команду:

```bash
mvn test
```

## Пример работы
![image](https://github.com/Fics3/just-ai-test/assets/114524392/c88784f2-a501-4d83-86e5-30a919fbaedc)

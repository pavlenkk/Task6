### Лабораторная работа №6. Уведомления.
## Выполнила: Павленко А.А. ИСП-211о.
## Описание

- Это приложение позволяет пользователям создавать напоминания с заданным временем и текстом, сохранять их в базу данных, просматривать список напоминаний, удалять их, а также получать уведомления в указанный момент времени. Уведомления отображаются в Notification Center и переходят в детальный экран при нажатии. 
---
 
## Основные возможности

**1. Создание напоминаний:**
Пользователь задает заголовок, текст и дату/время напоминания через DatePickerDialog и TimePickerDialog.
Данные сохраняются в базу данных SQLite.

**2. Просмотр напоминаний:**
Список созданных напоминаний отображается в основном экране.

**3. Удаление напоминаний:**
Пользователь может удалить любое напоминание из списка.

**4. Стилизованные уведомления:**
Уведомления в статус-баре имеют кастомный логотип и содержат текст напоминания.

**5. Обработка нажатия на уведомление:**
При нажатии на уведомление открывается экран с полным текстом напоминания.

---

<p align="center">
    <img src="https://github.com/user-attachments/assets/77d9a489-ad0f-4be6-87c9-58fb4e32fbd1" width="250">
    <img src="https://github.com/user-attachments/assets/d288501d-0f81-4886-9d44-fc984fcbba6e" width="250">
    <img src="https://github.com/user-attachments/assets/4d73bfd8-7ec9-43e5-887b-5134d7c655f7" width="250">
</p>

---

## Основной функционал
1. Создание напоминаний
Пользователь вводит заголовок и текст напоминания, выбирает дату и время. После нажатия кнопки Сохранить данные сохраняются в базу, а AlarmManager настраивает уведомление.

2. Уведомления
Уведомление настраивается с использованием:
- NotificationManager для управления уведомлениями.
- PendingIntent для обработки нажатий.
- AlarmManager для срабатывания уведомления в указанное время.

3. Список напоминаний
Отображение списка напоминаний происходит через RecyclerView, данные берутся из базы SQLite.

4. Удаление напоминаний
Долгое нажатие на элемент в списке позволяет удалить его.

---

## Установка и настройка
1. Клонируйте репозиторий на свой компьютер:
2. Откройте проект в Android Studio.
3. Подключите устройство или запустите эмулятор.
4. Нажмите кнопку **Run** в Android Studio для сборки и запуска приложения.
